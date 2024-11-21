package dev.kostromdan.mods.mtech_core.events;

import com.github.alexthe666.alexsmobs.event.ServerEvents;
import com.github.alexthe666.alexsmobs.world.AMWorldData;
import com.github.alexthe666.citadel.server.world.CitadelServerData;
import com.mrh0.createaddition.energy.network.EnergyNetworkManager;
import dev.kostromdan.mods.mtech_core.MTechCore;
import dev.kostromdan.mods.mtech_core.utils.ReflectionHelper;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.invoke.VarHandle;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MTechCore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerStoppingMemoryLeakFix {
    public static final VarHandle CITADEL_DATA_MAP;
    public static final VarHandle AM_DATA_MAP;
    public static final VarHandle BEACHED_CACHALOT_WHALE_SPAWNER_MAP;


    static {
        CITADEL_DATA_MAP = ReflectionHelper.getFieldFromClass(CitadelServerData.class, "dataMap", Map.class, true);
        AM_DATA_MAP = ReflectionHelper.getFieldFromClass(AMWorldData.class, "dataMap", Map.class, true);
        BEACHED_CACHALOT_WHALE_SPAWNER_MAP = ReflectionHelper.getFieldFromClass(ServerEvents.class, "BEACHED_CACHALOT_WHALE_SPAWNER_MAP", Map.class, true);
    }

    @SubscribeEvent
    @SuppressWarnings("rawtypes")
    public static void ServerStopping(ServerStoppingEvent event) {
        ((Map) CITADEL_DATA_MAP.get()).remove(event.getServer());
    }

    @SubscribeEvent
    public static void LevelUnload(LevelEvent.Unload event) {
        if (!event.getLevel().isClientSide()) {
            EnergyNetworkManager.instances.remove(event.getLevel());
            ((Map) AM_DATA_MAP.get()).remove(event.getLevel());
            ((Map) BEACHED_CACHALOT_WHALE_SPAWNER_MAP.get()).remove(event.getLevel());
        }
    }
}
