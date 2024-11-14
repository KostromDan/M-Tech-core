package dev.kostromdan.mods.mtech_core;

import com.mojang.logging.LogUtils;
import dev.kostromdan.mods.mtech_core.config.MaxSoundsPerTickConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MTechCore.MODID)
public class MTechCore {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mtech_core";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static MaxSoundsPerTickConfig MAX_SOUNDS_PER_TICK_CONFIG;


    public MTechCore(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
