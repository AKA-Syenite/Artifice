package shukaro.artifice.block;

import shukaro.artifice.ArtificeCore;
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
		return "artifice.frame." + ArtificeCore.tiers[stack.getItemDamage()].toLowerCase();
	}
}
