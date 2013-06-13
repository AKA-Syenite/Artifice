package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockFrameBlastWall extends BlockFrame
{
	public BlockFrameBlastWall(int id)
	{
		super(id);
		setUnlocalizedName("artifice.reinforced");
		this.textureName = "reinforced";
		this.textureRenderer = new SolidConnectedTexture(id);
		this.single = true;
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
		int meta = world.getBlockMetadata(x, y, z);
        switch (meta)
        {
        case 0:
        	return 20.0F;
        case 1:
        	return 30.0F;
        case 2:
        	return 50.0F;
        case 3:
        	return 80.0F;
        default:
        	return 10.0F;
        }
    }
	
	@Override
    public float getBlockHardness(World world, int x, int y, int z)
	{
		return super.getBlockHardness(world, x, y, z) + 5;
	}

	@Override
	public Block getInnerBlock(int meta)
	{
		return Block.stone;
	}

	@Override
	public int getInnerMeta(int meta)
	{
		return 0;
	}

	@Override
	public Icon getRenderIcon(int meta)
	{
		return this.singleTextureList[meta];
	}

	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}

}
