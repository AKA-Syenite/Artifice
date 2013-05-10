package shukaro.artifice.block.frame;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.net.ClientProxy;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFrame extends BlockArtifice
{
	public BlockFrame(int id)
	{
		super(id, Material.rock);
		this.textureName = "frame";
		setUnlocalizedName("artifice.frame");
	}
	
	public Block getInnerBlock(int meta)
	{
		return null;
	}
	
	public int getInnerMeta(int meta)
	{
		return 0;
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return (float) meta + 5;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return ArtificeCore.frameRenderID;
	}
}
