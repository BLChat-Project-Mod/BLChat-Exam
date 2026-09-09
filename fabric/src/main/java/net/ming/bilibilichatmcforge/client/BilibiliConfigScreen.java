package net.ming.bilibilichatmcforge.client;

import net.ming.bilibilichatmcforge.JsonConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BilibiliConfigScreen extends Screen {
    private final Screen parent;
    private EditBox identityCodeBox;

    protected BilibiliConfigScreen(Screen parent) {
        super(Component.translatable("mod.bilibilichatmcforge.config.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        identityCodeBox = new EditBox(font, width / 2 - 100, height / 2 - 20, 200, 20, Component.literal("Identity Code"));
        identityCodeBox.setMaxLength(64);
        identityCodeBox.setValue(JsonConfigManager.identityCode);
        addRenderableWidget(identityCodeBox);
        addRenderableWidget(Button.builder(Component.translatable("mod.bilibilichatmcforge.config.save"), button -> {
            JsonConfigManager.setIdentityCode(identityCodeBox.getValue());
            Minecraft.getInstance().setScreen(parent);
        }).bounds(width / 2 - 50, height / 2 + 20, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        graphics.drawCenteredString(font, title, width / 2, height / 2 - 50, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(parent);
    }
}