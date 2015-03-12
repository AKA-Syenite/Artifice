package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.ItemBlockArtifice;

import java.util.Locale;

public class ItemBlockFrame extends ItemBlockArtifice
{
    public ItemBlockFrame(Block block)
    {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() >= ArtificeConfig.tiers.length)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.tiers[0].toLowerCase(Locale.ENGLISH);
        return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.tiers[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}
