package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameBase extends BlockFrame
{
	private Icon[] singleTextureList = new Icon[ArtificeCore.tiers.length];
	
	public BlockFrameBase(int id)
	{
		super(id);
	}

	@Override
	public Block getInnerBlock(int meta)
	{
		return null;
	}

	@Override
	public int getInnerMeta(int meta)
	{
		return 0;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return singleTextureList[meta];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		super.registerIcons(reg);
		for (int i=0; i<ArtificeCore.tiers.length; i++)
		{
			singleTextureList[i] = reg.registerIcon("artifice:frame_" + ArtificeCore.tiers[i].toLowerCase() + "_base");
		}
	}
}
