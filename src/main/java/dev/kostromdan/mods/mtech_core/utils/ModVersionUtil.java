package dev.kostromdan.mods.mtech_core.utils;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

public class ModVersionUtil {
    public static String getModVersion(String modId) {
        return ModList.get().getModContainerById(modId)
                .map(container -> container.getModInfo().getVersion().toString())
                .orElse("UNKNOWN");
    }
}

