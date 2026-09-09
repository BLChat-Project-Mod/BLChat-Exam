package net.ming.bilibilichatmcforge;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    static {
        BUILDER.comment("BLChat Configuration");
        SPEC = BUILDER.build();
    }
}