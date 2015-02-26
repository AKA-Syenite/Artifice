package shukaro.artifice.block.world;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.block.ItemBlockArtifice;

public class ItemBlockOre extends ItemBlockArtifice
{
    public ItemBlockOre(Block block)
    {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() >= ArtificeBlocks.rockBlockNames.length)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName();
        return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeBlocks.rockBlockNames[stack.getItemDamage()];
    }
}
