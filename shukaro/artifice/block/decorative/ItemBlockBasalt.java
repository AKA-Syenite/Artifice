package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
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
		return "artifice.basalt." + ArtificeCore.rocks[stack.getItemDamage()].toLowerCase();
	}
}
