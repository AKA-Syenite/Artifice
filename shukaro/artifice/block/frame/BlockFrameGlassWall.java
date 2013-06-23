package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
import shukaro.artifice.render.connectedtexture.ILayeredRender;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFrameGlassWall extends BlockFrame implements IConnectedTexture, ILayeredRender
{
	private Icon[] icons = new Icon[ArtificeCore.tiers.length];
	private ConnectedTextureBase basic = new TransparentConnectedTexture(ConnectedTexture.BasicFrame);
	private ConnectedTextureBase reinforced = new TransparentConnectedTexture(ConnectedTexture.ReinforcedFrame);
	private ConnectedTextureBase industrial = new TransparentConnectedTexture(ConnectedTexture.IndustrialFrame);
	private ConnectedTextureBase advanced = new TransparentConnectedTexture(ConnectedTexture.AdvancedFrame);
	
	public BlockFrameGlassWall(int id)
	{
		super(id);
		setUnlocalizedName("artifice.glasswall");
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
		int meta = world.getBlockMetadata(x, y, z);
        return this.getResistance(meta);
    }
	
	public float getResistance(int meta)
	{
		switch (meta)
        {
        case 0:
        	return 15.0F;
        case 1:
        	return 25.0F;
        case 2:
        	return 45.0F;
        case 3:
        	return 75.0F;
        default:
        	return 10.0F;
        }
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}

	@Override
	public Icon getRenderIcon(int side, int meta)
	{
		return icons[meta];
	}

	@Override
	public ConnectedTexture getTextureType(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return ConnectedTexture.BasicFrame;
		case 1:
			return ConnectedTexture.ReinforcedFrame;
		case 2:
			return ConnectedTexture.IndustrialFrame;
		case 3:
			return ConnectedTexture.AdvancedFrame;
		default:
			return null;
		}
	}

	@Override
	public ConnectedTextureBase getTextureRenderer(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return basic;
		case 1:
			return reinforced;
		case 2:
			return industrial;
		case 3:
			return advanced;
		default:
			return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		if (!ConnectedTexture.BasicFrame.isRegistered)
			IconHandler.registerConnectedTexture(reg, ConnectedTexture.BasicFrame, "frame/basic");
		if (!ConnectedTexture.ReinforcedFrame.isRegistered)
			IconHandler.registerConnectedTexture(reg, ConnectedTexture.ReinforcedFrame, "frame/reinforced");
		if (!ConnectedTexture.IndustrialFrame.isRegistered)
			IconHandler.registerConnectedTexture(reg, ConnectedTexture.IndustrialFrame, "frame/industrial");
		if (!ConnectedTexture.AdvancedFrame.isRegistered)
			IconHandler.registerConnectedTexture(reg, ConnectedTexture.AdvancedFrame, "frame/advanced");
		for (int i=0; i<ArtificeCore.tiers.length; i++)
			icons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(), "glasswall");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return this.getTextureType(side, meta).textureList[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
	{
		return this.getTextureType(side, block.getBlockMetadata(x, y, z)).textureList[this.getTextureRenderer(side, block.getBlockMetadata(x, y, z)).getTextureIndex(block, x, y, z, side)];
	}
}
