package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;

public class ItemBlockFrame extends ItemBlockArtifice
{
    public ItemBlockFrame(int id)
    {
        super(id);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
    	if (stack.getItemDamage() > ArtificeCore.tiers.length)
    		return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.tiers[0].toLowerCase();
        return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.tiers[stack.getItemDamage()].toLowerCase();
    }
}
