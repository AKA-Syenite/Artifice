package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import net.minecraft.item.ItemStack;

public class ItemBlockMarble extends ItemBlockArtifice
{
	public ItemBlockMarble(int id)
	{
		super(id);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "artifice.marble." + ArtificeCore.rocks[stack.getItemDamage()].toLowerCase();
	}
}
