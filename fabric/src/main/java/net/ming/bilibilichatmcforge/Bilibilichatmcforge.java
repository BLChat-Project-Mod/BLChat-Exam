package net.ming.bilibilichatmcforge;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bilibilichatmcforge implements ModInitializer, ClientModInitializer {

    public static final String MODID = "bilibilichatmcforge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("BilibiliChat Fabric mod initializing...");
    }

    @Override
    public void onInitializeClient() {
        JsonConfigManager.load();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(net.minecraft.commands.Commands.literal("bilibili")
                    .then(net.minecraft.commands.Commands.literal("identitycode")
                            .then(net.minecraft.commands.Commands.argument("id", com.mojang.brigadier.arguments.StringArgumentType.string())
                                    .executes(context -> {
                                        String id = com.mojang.brigadier.arguments.StringArgumentType.getString(context, "id");
                                        JsonConfigManager.setIdentityCode(id);
                                        context.getSource().sendFeedback(Component.translatable("mod.bilibilichatmcforge.chat.identity_code_updated", id));
                                        return 1;
                                    }))));
        });
        ScreenEvents.AFTER_INIT.register((mc, screen, scaledWidth, scaledHeight) -> {
            // Placeholder for screen-related initialization if needed.
        });
    }
}