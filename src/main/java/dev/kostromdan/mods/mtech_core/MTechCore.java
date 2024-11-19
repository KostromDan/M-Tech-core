package dev.kostromdan.mods.mtech_core;

import com.mojang.logging.LogUtils;
import dev.kostromdan.mods.mtech_core.channel.MTechNetworking;
import dev.kostromdan.mods.mtech_core.config.MaxSoundsPerTickConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MTechCore.MODID)
public class MTechCore {
    public static final String MODID = "mtech_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static MaxSoundsPerTickConfig MAX_SOUNDS_PER_TICK_CONFIG;


    public MTechCore(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
        context.getModEventBus().addListener(this::setup);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void setup(FMLCommonSetupEvent event) {
        MTechNetworking.registerMessages();
    }
}
