package shukaro.artifice.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockArtifice extends ItemBlock
{
	public ItemBlockArtifice(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}
