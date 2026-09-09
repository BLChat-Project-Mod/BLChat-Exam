package net.ming.bilibilichatmcforge.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class BilibiliClient {
    private static final Logger LOGGER = LoggerFactory.getLogger("bilibilichatmcforge");
    private static final Gson GSON = new Gson();
    private WebSocket webSocket;
    private final HttpClient httpClient;
    private volatile boolean running;
    private final AtomicInteger heartbeatCount = new AtomicInteger(0);
    private Thread reconnectThread;

    public BilibiliClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        connect();
        startHeartbeat();
    }

    public synchronized void stop() {
        running = false;
        if (webSocket != null) {
            try {
                webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Mod stopping").join();
            } catch (Exception e) {
                LOGGER.warn("Error closing WebSocket", e);
            }
            webSocket = null;
        }
    }

    private void connect() {
        try {
            URI uri = new URI("wss://live-open.biliapi.com/v2/mic/sub");
            webSocket = httpClient.newWebSocketBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .buildAsync(uri, new WebSocket.Listener() {
                        @Override
                        public void onOpen(WebSocket webSocket) {
                            LOGGER.info("WebSocket connected");
                        }

                        @Override
                        public void onText(WebSocket webSocket, CharSequence data, boolean last) {
                            LOGGER.debug("Received: {}", data);
                        }

                        @Override
                        public void onClose(WebSocket webSocket, int statusCode, String reason, boolean initiatedByRemote) {
                            LOGGER.info("WebSocket closed: {} - {}", statusCode, reason);
                            scheduleReconnect();
                        }

                        @Override
                        public void onError(WebSocket webSocket, Throwable error) {
                            LOGGER.error("WebSocket error", error);
                        }
                    }).join();
        } catch (Exception e) {
            LOGGER.error("Failed to connect WebSocket", e);
            scheduleReconnect();
        }
    }

    private void startHeartbeat() {
        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(30000);
                    if (webSocket != null && running) {
                        String heartbeat = GSON.toJson(new Heartbeat(heartbeatCount.incrementAndGet()));
                        webSocket.sendText(heartbeat, true);
                    }
                } catch (Exception e) {
                    LOGGER.debug("Heartbeat error", e);
                }
            }
        }, "BLChat-Heartbeat");
        thread.setDaemon(true);
        thread.start();
    }

    private void scheduleReconnect() {
        if (reconnectThread != null && reconnectThread.isAlive()) return;
        reconnectThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                if (running) {
                    connect();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "BLChat-Reconnect");
        reconnectThread.setDaemon(true);
        reconnectThread.start();
    }

    private record Heartbeat(int count) {}
}