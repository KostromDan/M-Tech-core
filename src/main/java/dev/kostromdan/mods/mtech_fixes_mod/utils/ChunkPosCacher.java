package dev.kostromdan.mods.mtech_fixes_mod.utils;

import net.minecraft.world.level.ChunkPos;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

public class ChunkPosCacher {
    private static final int MAX_CACHE_SIZE = 5;
    public static final HashMap<UUID, LinkedHashSet<ChunkPos>> chunkPosCache = new HashMap<>();

    public static void addChunkPos(UUID uuid, ChunkPos chunkPos) {
        chunkPosCache.putIfAbsent(uuid, new LinkedHashSet<>());
        LinkedHashSet<ChunkPos> cache = chunkPosCache.get(uuid);

        if (cache.contains(chunkPos)) {
            cache.remove(chunkPos);
        } else if (cache.size() >= MAX_CACHE_SIZE) {
            ChunkPos first = cache.iterator().next();
            cache.remove(first);
        }
        cache.add(chunkPos);
    }

    public static boolean containsChunkPos(UUID uuid, ChunkPos chunkPos) {
        LinkedHashSet<ChunkPos> cache = chunkPosCache.get(uuid);
        return cache != null && cache.contains(chunkPos);
    }
}
