package shukaro.artifice.block.world;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.block.ItemBlockArtifice;

public class ItemBlockNiter extends ItemBlockArtifice
{
    public ItemBlockNiter(Block block)
    {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + ".ore";
        else
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + ".block";
    }
}