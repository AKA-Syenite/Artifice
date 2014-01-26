package shukaro.artifice.block.frame;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.Packets;
import shukaro.artifice.net.PlayerTracking;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;

import java.util.Locale;

public class BlockFrameScaffold extends BlockFrame
{
    private static final ForgeDirection[] sides = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN};

    private Icon[] sideIcons = new Icon[ArtificeCore.tiers.length];
    private ConnectedTextureBase basic = new SolidConnectedTexture(ConnectedTextures.BasicScaffold);
    private ConnectedTextureBase reinforced = new SolidConnectedTexture(ConnectedTextures.ReinforcedScaffold);
    private ConnectedTextureBase industrial = new SolidConnectedTexture(ConnectedTextures.IndustrialScaffold);
    private ConnectedTextureBase advanced = new SolidConnectedTexture(ConnectedTextures.AdvancedScaffold);

    public BlockFrameScaffold(int id)
    {
        super(id);
        setUnlocalizedName("artifice.scaffold");
        setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityPlayerMP)
            ((EntityPlayerMP) entity).playerNetServerHandler.ticksForFloatKick = 0;
        entity.fallDistance = 0;
        if (entity.isCollidedHorizontally)
        {
            entity.motionY = 0.2D;
        }
        else if (PlayerTracking.sneaks.contains(entity.entityId))
        {
            double diff = entity.prevPosY - entity.posY;
            entity.boundingBox.minY += diff;
            entity.boundingBox.maxY += diff;
            entity.posY = entity.prevPosY;
        }
        else
        {
            entity.motionY = -0.10D;
        }
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];
        if (held != null && held.itemID == this.blockID && held.getItemDamage() == world.getBlockMetadata(x, y, z))
        {
            while (world.getBlockId(x, y, z) == this.blockID)
                y++;
            if (checkStay(world, x, y, z, held.getItemDamage()) && (Block.blocksList[world.getBlockId(x, y, z)] == null || Block.blocksList[world.getBlockId(x, y, z)].isAirBlock(world, x, y, z)))
            {
                world.setBlock(x, y, z, this.blockID, held.getItemDamage(), 3);
                if (!player.capabilities.isCreativeMode)
                {
                    held.stackSize--;
                    if (held.stackSize <= 0)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
            }
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack stack)
    {
        return this.checkStay(world, x, y, z, stack.getItemDamage());
    }

    public int getOverhang(int meta)
    {
        switch (meta)
        {
            case 0:
                return 4;
            case 1:
                return 8;
            case 2:
                return 12;
            case 3:
                return 16;
            default:
                return 0;
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return this.checkStay(world, x, y, z, world.getBlockMetadata(x, y, z));
    }

    private boolean checkStay(World world, int x, int y, int z, int meta)
    {
        BlockCoord c = new BlockCoord(x, y, z);

        if (isRooted(world, x, y, z, meta))
            return true;

        for (ForgeDirection d : sides)
        {
            BlockCoord t = c.copy().offset(d.ordinal());
            if (t.getBlock(world) instanceof BlockFrameScaffold)
            {
                if (t.blockEquals(world, this.blockID, meta))
                {
                    if (isRooted(world, t.x, t.y, t.z, meta))
                        return true;
                    for (BlockCoord match : c.getRadiusMatches(world, getOverhang(meta), this.blockID, meta))
                    {
                        if (isRooted(world, match.x, match.y, match.z, meta) && c.isConnected(world, match, this.blockID, meta) && c.getDistance(match) <= getOverhang(meta))
                            return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private boolean isRooted(World world, int x, int y, int z, int meta)
    {
        for (int i = y - 1; i > 0; i--)
        {
            if (world.isBlockSolidOnSide(x, i, z, ForgeDirection.UP))
            {
                if (world.getBlockId(x, i, z) == this.blockID)
                {
                    if (world.getBlockMetadata(x, i, z) == meta)
                        continue;
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        if (!world.isRemote)
        {
            BlockCoord c = new BlockCoord(x, y, z);
            PacketDispatcher.sendPacketToAllAround(c.x, c.y, c.z, 192, world.provider.dimensionId, PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.TEXTUREUPDATE, new Object[]{c.x, c.y, c.z}));
        }

        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP || side == ForgeDirection.DOWN;
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
        for (int i = 0; i < ArtificeCore.tiers.length; i++)
            sideIcons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(Locale.ENGLISH), "scaffold/sides");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.tiers.length)
            meta = 0;
        if (side == 0 || side == 1)
        {
            switch (meta)
            {
                case 0:
                    return ConnectedTextures.BasicScaffold.textureList[0];
                case 1:
                    return ConnectedTextures.ReinforcedScaffold.textureList[0];
                case 2:
                    return ConnectedTextures.IndustrialScaffold.textureList[0];
                case 3:
                    return ConnectedTextures.AdvancedScaffold.textureList[0];
                default:
                    return ConnectedTextures.BasicScaffold.textureList[0];
            }
        }
        return this.sideIcons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.tiers.length)
            meta = 0;

        if (side == 0 || side == 1)
        {
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
        return this.sideIcons[access.getBlockMetadata(x, y, z)];
    }

    @Override
    public int getRenderType()
    {
        return ArtificeConfig.frameRenderID;
    }
}
