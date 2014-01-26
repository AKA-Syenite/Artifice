package shukaro.artifice.block.frame;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.Packets;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.TransparentConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;

public class BlockFrameGlassWall extends BlockFrame
{
    private ConnectedTextureBase basic = new TransparentConnectedTexture(ConnectedTextures.BasicGlassWall);
    private ConnectedTextureBase reinforced = new TransparentConnectedTexture(ConnectedTextures.ReinforcedGlassWall);
    private ConnectedTextureBase industrial = new TransparentConnectedTexture(ConnectedTextures.IndustrialGlassWall);
    private ConnectedTextureBase advanced = new TransparentConnectedTexture(ConnectedTextures.AdvancedGlassWall);

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
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
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
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
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
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        if (!world.isRemote)
        {
            BlockCoord c = new BlockCoord(x, y, z);
            PacketDispatcher.sendPacketToAllAround(c.x, c.y, c.z, 192, world.provider.dimensionId, PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.TEXTUREUPDATE, new Object[]{c.x, c.y, c.z}));
        }
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
        return i1 != this.blockID && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
