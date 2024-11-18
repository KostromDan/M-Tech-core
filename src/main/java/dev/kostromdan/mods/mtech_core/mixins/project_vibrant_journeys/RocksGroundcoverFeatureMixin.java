package dev.kostromdan.mods.mtech_core.mixins.project_vibrant_journeys;

import com.projectvibrantjourneys.common.world.features.RocksGroundcoverFeature;
import dev.kostromdan.mods.mtech_core.MTechCore;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RocksGroundcoverFeature.class)
public class RocksGroundcoverFeatureMixin {
    @Inject(
            at = @At("HEAD"),
            method = "place",
            cancellable = true
    )
    private void beforePlace(FeaturePlaceContext<RandomPatchConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        BlockState originState = level.getBlockState(origin);
        if (originState.getMaterial().isSolid()) {
//            String blockId = Registry.BLOCK.getKey(originState.getBlock()).toString();
//            MTechCore.LOGGER.info("RocksGroundcoverFeature: Solid block '" + blockId + "' found /tp " + origin.getX() + " " + origin.getY() + " " + origin.getZ());
//            level.setBlock(origin, Blocks.RED_WOOL.defaultBlockState(), 3);
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
