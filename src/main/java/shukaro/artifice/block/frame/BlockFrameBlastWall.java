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
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;

public class BlockFrameBlastWall extends BlockFrame
{
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
    public boolean isOpaqueCube()
    {
        return true;
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
                return ConnectedTextures.BasicBlastWall.textureList[0];
            case 1:
                return ConnectedTextures.ReinforcedBlastWall.textureList[0];
            case 2:
                return ConnectedTextures.IndustrialBlastWall.textureList[0];
            case 3:
                return ConnectedTextures.AdvancedBlastWall.textureList[0];
            default:
                return ConnectedTextures.BasicBlastWall.textureList[0];
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
        return true;
    }
}
