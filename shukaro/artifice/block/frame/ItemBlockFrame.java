package shukaro.artifice.block.frame;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockFrame extends ItemBlockArtifice
{
	public ItemBlockFrame(int id)
	{
		super(id);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.tiers[stack.getItemDamage()].toLowerCase();
	}
}
