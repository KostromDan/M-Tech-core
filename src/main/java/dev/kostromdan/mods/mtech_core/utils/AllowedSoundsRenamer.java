package dev.kostromdan.mods.mtech_core.utils;

import com.sonicether.soundphysics.Loggers;
import com.sonicether.soundphysics.SoundPhysicsMod;
import net.minecraftforge.fml.loading.FMLLoader;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class AllowedSoundsRenamer {
    public static void renameAllowedSounds() {
        Path oldPath = FMLLoader.getGamePath().resolve("config").resolve("sound_physics_remastered").resolve("allowed_sounds.properties");
        Path newPath = FMLLoader.getGamePath().resolve("config").resolve("sound_physics_remastered").resolve("max_sounds_per_tick.properties");

        try {
            Files.move(oldPath, newPath);
            Loggers.warn(oldPath.getFileName() + " file renamed to " + newPath.getFileName());
        } catch (NoSuchFileException ignored) {
        } catch (FileAlreadyExistsException ignored) {
            try {
                Files.delete(oldPath); // If max_sounds_per_tick was already generated but allowed_sounds for some reason returned(by user, etc) we should delete it
                Loggers.warn(oldPath.getFileName() + " was deleted due to " + newPath.getFileName() + " already exists.");
            } catch (IOException ignored1) {}
        } catch (IOException e) {
            Loggers.error("Error renaming file: " + e);
        }
    }
}
