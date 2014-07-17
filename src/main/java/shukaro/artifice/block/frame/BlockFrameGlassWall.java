package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.PacketSender;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.TransparentConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

public class BlockFrameGlassWall extends BlockFrame
{
    private ConnectedTextureBase basic = new TransparentConnectedTexture(ConnectedTextures.BasicGlassWall);
    private ConnectedTextureBase reinforced = new TransparentConnectedTexture(ConnectedTextures.ReinforcedGlassWall);
    private ConnectedTextureBase industrial = new TransparentConnectedTexture(ConnectedTextures.IndustrialGlassWall);
    private ConnectedTextureBase advanced = new TransparentConnectedTexture(ConnectedTextures.AdvancedGlassWall);

    public BlockFrameGlassWall()
    {
        super();
        setBlockName("artifice.glasswall");
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
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.tiers.length)
            meta = 0;
        switch (meta)
        {
            case 0:
                return ConnectedTextures.BasicGlassWall.textureList[0];
            case 1:
                return ConnectedTextures.ReinforcedGlassWall.textureList[0];
            case 2:
                return ConnectedTextures.IndustrialGlassWall.textureList[0];
            case 3:
                return ConnectedTextures.AdvancedGlassWall.textureList[0];
            default:
                return ConnectedTextures.BasicGlassWall.textureList[0];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
    {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.tiers.length)
            meta = 0;

        BlockCoord coord = new BlockCoord(x, y, z);
        boolean found = false;
        for (ChunkCoord sector : ArtificeCore.textureCache.keySet())
        {
            if (ArtificeCore.textureCache.get(sector).containsKey(coord))
                found = true;
        }
        if (!found)
            TextureHandler.updateTexture(coord);

        if (TextureHandler.getConnectedTexture(this.getIcon(side, meta)) != null && ArtificeCore.textureCache.containsKey(new ChunkCoord(coord)) && ArtificeCore.textureCache.get(new ChunkCoord(coord)).get(coord) != null)
            return TextureHandler.getConnectedTexture(this.getIcon(side, meta)).textureList[ArtificeCore.textureCache.get(new ChunkCoord(coord)).get(coord)[side]];
        return this.getIcon(side, meta);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!world.isRemote)
            PacketSender.sendTextureUpdatePacket(world, x, y, z);
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
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
        Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
        return i1 != this && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
