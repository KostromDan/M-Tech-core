package dev.kostromdan.mods.mtech_fixes_mod.mixins;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadows.apotheosis.ench.enchantments.masterwork.EarthsBoonEnchant;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import shadows.apotheosis.Apoth;

@Mixin(EarthsBoonEnchant.class)
public class EarthsBoonEnchantMixin {

    @Inject(method = "provideBenefits", at = @At("HEAD"))
    private void overrideProvideBenefits(BreakEvent e, CallbackInfo ci) {
        Player player = e.getPlayer();
        ItemStack stack = player.getMainHandItem();
        int level = stack.getEnchantmentLevel((EarthsBoonEnchant) (Object) this);
        if (player.level.isClientSide) return;
        if (e.getState().is(Tags.Blocks.STONE) && level > 0 && player.random.nextFloat() <= 0.01F * level) {
            ItemStack newDrop = new ItemStack(ForgeRegistries.ITEMS.tags().getTag(Apoth.Tags.BOON_DROPS).getRandomElement(player.random).orElse(Items.AIR));
            Block.popResource(player.level, e.getPos(), newDrop);
        }
    }
}
