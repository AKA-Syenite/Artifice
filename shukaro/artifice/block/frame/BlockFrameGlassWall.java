package shukaro.artifice.block.frame;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
import shukaro.artifice.render.connectedtexture.schemes.TransparentConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameGlassWall extends BlockFrame implements IConnectedTexture
{
    private Icon[] icons = new Icon[ArtificeCore.tiers.length];
    private ConnectedTextureBase basic = new TransparentConnectedTexture(ConnectedTexture.BasicGlassWall);
    private ConnectedTextureBase reinforced = new TransparentConnectedTexture(ConnectedTexture.ReinforcedGlassWall);
    private ConnectedTextureBase industrial = new TransparentConnectedTexture(ConnectedTexture.IndustrialGlassWall);
    private ConnectedTextureBase advanced = new TransparentConnectedTexture(ConnectedTexture.AdvancedGlassWall);
    
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
    public ConnectedTexture getTextureType(int side, int meta)
    {
        switch (meta)
        {
        case 0:
            return ConnectedTexture.BasicGlassWall;
        case 1:
            return ConnectedTexture.ReinforcedGlassWall;
        case 2:
            return ConnectedTexture.IndustrialGlassWall;
        case 3:
            return ConnectedTexture.AdvancedGlassWall;
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
    	ArtificeConfig.registerConnectedTextures(reg);
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

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return i1 == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
