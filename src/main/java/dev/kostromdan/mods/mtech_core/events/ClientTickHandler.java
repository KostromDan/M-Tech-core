package dev.kostromdan.mods.mtech_core.events;

import dev.kostromdan.mods.mtech_core.MTechCore;
import dev.kostromdan.mods.mtech_core.utils.SoundsPerTickCounter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MTechCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientTickHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            SoundsPerTickCounter.resetAllCounts();
        }
    }
}