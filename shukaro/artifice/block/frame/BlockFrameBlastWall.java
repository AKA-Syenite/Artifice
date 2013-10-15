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
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameBlastWall extends BlockFrame implements IConnectedTexture
{
    private Icon[] icons = new Icon[ArtificeCore.tiers.length];
    private ConnectedTextureBase basic = new SolidConnectedTexture(ConnectedTexture.BasicBlastWall);
    private ConnectedTextureBase reinforced = new SolidConnectedTexture(ConnectedTexture.ReinforcedBlastWall);
    private ConnectedTextureBase industrial = new SolidConnectedTexture(ConnectedTexture.IndustrialBlastWall);
    private ConnectedTextureBase advanced = new SolidConnectedTexture(ConnectedTexture.AdvancedBlastWall);
    
    public BlockFrameBlastWall(int id)
    {
        super(id);
        setUnlocalizedName("artifice.reinforced");
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
            return ConnectedTexture.BasicBlastWall;
        case 1:
            return ConnectedTexture.ReinforcedBlastWall;
        case 2:
            return ConnectedTexture.IndustrialBlastWall;
        case 3:
            return ConnectedTexture.AdvancedBlastWall;
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
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        for (int i=0; i<ArtificeCore.tiers.length; i++)
            icons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(), "blastwall");
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
		return true;
	}
}
