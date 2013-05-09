package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMarble extends ItemBlock
{
	public ItemBlockMarble(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "artifice.marble." + ArtificeCore.rocks[stack.getItemDamage()].toLowerCase();
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}
