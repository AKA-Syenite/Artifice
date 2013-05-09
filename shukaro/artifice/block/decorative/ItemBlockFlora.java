package shukaro.artifice.block.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;

public class ItemBlockFlora extends ItemBlockArtifice
{
	public ItemBlockFlora(int id)
	{
		super(id);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int meta)
	{
		return BlockFlora.textureList[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "artifice.flora." + ArtificeCore.flora[stack.getItemDamage()].toLowerCase();
	}
}
