package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

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
		return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.rocks[stack.getItemDamage()].toLowerCase();
	}
}
