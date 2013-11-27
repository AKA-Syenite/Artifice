package shukaro.artifice.block.decorative;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;

public class ItemBlockBasalt extends ItemBlockArtifice
{
    public ItemBlockBasalt(int id)
    {
        super(id);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0)
            return Block.blocksList[stack.itemID].getUnlocalizedName();
        if (stack.getItemDamage() > ArtificeCore.rocks.length)
        	return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.rocks[0].toLowerCase(Locale.ENGLISH);
        return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.rocks[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}
