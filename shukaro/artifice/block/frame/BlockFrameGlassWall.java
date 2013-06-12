package shukaro.artifice.block.frame;

import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockFrameGlassWall extends BlockFrame
{
	public BlockFrameGlassWall(int id)
	{
		super(id);
		setUnlocalizedName("artifice.glasswall");
		this.textureName = "glasswall";
		this.textureRenderer = new TransparentConnectedTexture(id);
		this.single = true;
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
		int meta = world.getBlockMetadata(x, y, z);
        switch (meta)
        {
        case 0:
        	return 900.0F;
        case 1:
        	return 1900.0F;
        case 2:
        	return 2900.0F;
        case 3:
        	return 3900.0F;
        default:
        	return 10.0F;
        }
    }

	@Override
	public Block getInnerBlock(int meta)
	{
		return Block.glass;
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
		return false;
	}
}
