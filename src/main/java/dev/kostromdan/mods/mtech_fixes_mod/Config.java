package dev.kostromdan.mods.mtech_fixes_mod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = MTechFixesMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<String> EarthsBoonEnchantChanceRealPLayer = BUILDER
            .comment("Chance for EarthsBoon Enchant to be applied for real players, apotheosis deafult: 0.01, m-tech default: 0.005, range: 0.0 - 1.0")
            .define("earthsBoonRealPLayer", "0.005");

    private static final ForgeConfigSpec.ConfigValue<String> EarthsBoonEnchantChanceFakePLayer = BUILDER
            .comment("Chance for EarthsBoon Enchant to be applied for fake players(contraptions, robots, etc), apotheosis deafult: 0.01, m-tech default: 0.00001, range: 0.0 - 1.0")
            .define("earthsBoonFakePLayer", "0.00001");



    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static double earthsBoonRealPLayer;
    public static double earthsBoonFakePLayer;

    public static double parseAndValidateDoubleValue(String text, double min, double max) {
        double value = Double.parseDouble(text);

        final double EPSILON = 1e-6;

        if (value < min - EPSILON || value > max + EPSILON) {
            throw new NumberFormatException("Value out of range: " + text + " min: " + min + " max: " + max);
        }

        return value;
    }


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        earthsBoonRealPLayer = parseAndValidateDoubleValue(EarthsBoonEnchantChanceRealPLayer.get(),0.0D, 1.0D);
        earthsBoonFakePLayer = parseAndValidateDoubleValue(EarthsBoonEnchantChanceFakePLayer.get(),0.0D, 1.0D);
    }
}
