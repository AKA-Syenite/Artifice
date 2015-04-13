package shukaro.artifice.block.world;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.ItemBlockArtifice;

import java.util.Locale;

public class ItemBlockRock extends ItemBlockArtifice
{
    public ItemBlockRock(Block block)
    {
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName();
        if (stack.getItemDamage() >= ArtificeConfig.rocks.length)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.rocks[0].toLowerCase(Locale.ENGLISH);
        return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.rocks[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}
