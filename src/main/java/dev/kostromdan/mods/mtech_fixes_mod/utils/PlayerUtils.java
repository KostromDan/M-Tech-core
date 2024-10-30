package dev.kostromdan.mods.mtech_fixes_mod.utils;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.FakePlayer;

public class PlayerUtils {
    public static boolean isFakePlayer(Entity entity) {
        return entity instanceof FakePlayer;
    }
}
