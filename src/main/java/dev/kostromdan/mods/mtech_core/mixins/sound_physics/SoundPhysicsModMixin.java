package dev.kostromdan.mods.mtech_core.mixins.sound_physics;

import com.sonicether.soundphysics.SoundPhysicsMod;
import dev.kostromdan.mods.mtech_core.MTechCore;
import dev.kostromdan.mods.mtech_core.config.MaxSoundsPerTickConfig;
import dev.kostromdan.mods.mtech_core.utils.AllowedSoundsRenamer;
import net.minecraftforge.fml.loading.FMLLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundPhysicsMod.class)
public class SoundPhysicsModMixin {

    @Inject(method = "initClient", at = @At("RETURN"), remap = false)
    private void afterInitClient(CallbackInfo ci) {
        AllowedSoundsRenamer.renameAllowedSounds();
        MTechCore.MAX_SOUNDS_PER_TICK_CONFIG = new MaxSoundsPerTickConfig(FMLLoader.getGamePath().resolve("config").resolve(SoundPhysicsMod.MODID).resolve("max_sounds_per_tick.properties"));
    }
}