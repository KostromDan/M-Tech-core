package dev.kostromdan.mods.mtech_core.mixins.apotheosis;

import dev.kostromdan.mods.mtech_core.Config;
import dev.kostromdan.mods.mtech_core.utils.ChunkPosCacher;
import dev.kostromdan.mods.mtech_core.utils.PlayerUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shadows.apotheosis.Apoth;
import shadows.apotheosis.ench.enchantments.masterwork.EarthsBoonEnchant;

@Mixin(EarthsBoonEnchant.class)
public class EarthsBoonEnchantMixin {

    /**
     * @author KostromDan
     * @reason Adjust chance separately for fake players and real players to balance EarthsBoonEnchant. And prevent afk farms.
     */
    @Overwrite(remap = false)
    public void provideBenefits(BreakEvent e) {
        Player player = e.getPlayer();
        ItemStack stack = player.getMainHandItem();
        ChunkPos curChunkPos = new ChunkPos(e.getPos());
        int level = stack.getEnchantmentLevel((EarthsBoonEnchant) (Object) this);
        if (player.level.isClientSide) return;

        double chance;
        if (PlayerUtils.isFakePlayer(player)) {
            chance = Config.earthsBoonFakePLayer;
        } else {
            chance = Config.earthsBoonRealPLayer;
            if (ChunkPosCacher.containsChunkPos(player.getUUID(), curChunkPos)) {
                chance = Config.earthsBoonFakePLayer;
            }
        }

        if (e.getState().is(Tags.Blocks.STONE) && level > 0 && player.random.nextFloat() <= chance * level) {
            ItemStack newDrop = new ItemStack(ForgeRegistries.ITEMS.tags().getTag(Apoth.Tags.BOON_DROPS).getRandomElement(player.random).orElse(Items.AIR));
            Block.popResource(player.level, e.getPos(), newDrop);
            ChunkPosCacher.addChunkPos(player.getUUID(), curChunkPos);
        }
    }
}
