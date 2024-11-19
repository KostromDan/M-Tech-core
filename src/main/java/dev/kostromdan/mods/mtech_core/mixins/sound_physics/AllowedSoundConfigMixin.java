package dev.kostromdan.mods.mtech_core.mixins.sound_physics;

import com.sonicether.soundphysics.config.AllowedSoundConfig;
import dev.kostromdan.mods.mtech_core.MTechCore;
import dev.kostromdan.mods.mtech_core.utils.SoundsPerTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AllowedSoundConfig.class)
public class AllowedSoundConfigMixin {
    /**
     * @author KostromDan
     * @reason Replace AllowedSounds logic to MaxSoundsPerTick
     */
    @Overwrite(remap = false)
    public boolean isAllowed(String soundEvent) {
        return !(SoundsPerTickCounter.getCountAndIncrement(soundEvent) >= MTechCore.MAX_SOUNDS_PER_TICK_CONFIG.getMaxCount(soundEvent));
    }

    /**
     * @author KostromDan
     * @reason Prevent AllowedSoundConfig from generating.
     */
    @Overwrite(remap = false)
    public void load() {
    }

    /**
     * @author KostromDan
     * @reason Prevent AllowedSoundConfig from generating.
     */
    @Overwrite(remap = false)
    public void saveSync() {
    }


}
