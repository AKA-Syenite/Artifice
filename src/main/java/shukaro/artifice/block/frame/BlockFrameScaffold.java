package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.PacketDispatcher;
import shukaro.artifice.net.PlayerTracking;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;

import java.util.Locale;

public class BlockFrameScaffold extends BlockFrame
{
    private static final ForgeDirection[] sides = new ForgeDirection[]{ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN};

    private IIcon[] icons = new IIcon[ArtificeCore.tiers.length];
    private IIcon[] sideIcons = new IIcon[ArtificeCore.tiers.length];

    public BlockFrameScaffold()
    {
        super();
        setBlockName("artifice.scaffold");
        setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity instanceof EntityPlayerMP)
        {
            NetHandlerPlayServer nhps = ((EntityPlayerMP) entity).playerNetServerHandler;
            try
            {
                NetHandlerPlayServer.class.getDeclaredField("floatingTickCount").setInt(nhps, 0);
            }
            catch (Exception e)
            {
            }
        }
        entity.fallDistance = 0;
        if (entity.isCollidedHorizontally)
        {
            entity.motionY = 0.2D;
        }
        else if (PlayerTracking.sneaks.contains(entity.getEntityId()))
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
        if (held == null || held.getItem() == null || Block.getBlockFromItem(held.getItem()) == null)
            return;
        Block hb = Block.getBlockFromItem(held.getItem());
        if (held != null && hb != null && hb.equals(this) && held.getItemDamage() == world.getBlockMetadata(x, y, z))
        {
            while (world.getBlock(x, y, z).equals(this))
                y++;
            if (checkStay(world, x, y, z, held.getItemDamage()) && (world.isAirBlock(x, y, z)))
            {
                world.setBlock(x, y, z, this, held.getItemDamage(), 3);
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
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack)
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
                if (t.blockEquals(world, this, meta))
                {
                    if (isRooted(world, t.x, t.y, t.z, meta))
                        return true;
                    for (BlockCoord match : c.getRadiusMatches(world, getOverhang(meta), this, meta))
                    {
                        if (isRooted(world, match.x, match.y, match.z, meta) && c.isConnected(world, match, this, meta) && c.getDistance(match) <= getOverhang(meta))
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
            if (world.isSideSolid(x, i, z, ForgeDirection.UP))
            {
                if (world.getBlock(x, i, z).equals(this))
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return side < 2;
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
        TextureHandler.registerConnectedTexture(reg, this, 0, 0, "basic", "scaffold");
        TextureHandler.registerConnectedTexture(reg, this, 0, 1, "basic", "scaffold");
        icons[0] = TextureHandler.getConnectedTexture(this, 0, 1).icon;
        TextureHandler.registerConnectedTexture(reg, this, 1, 0, "reinforced", "scaffold");
        TextureHandler.registerConnectedTexture(reg, this, 1, 1, "reinforced", "scaffold");
        icons[1] = TextureHandler.getConnectedTexture(this, 1, 1).icon;
        TextureHandler.registerConnectedTexture(reg, this, 2, 0, "industrial", "scaffold");
        TextureHandler.registerConnectedTexture(reg, this, 2, 1, "industrial", "scaffold");
        icons[2] = TextureHandler.getConnectedTexture(this, 2, 1).icon;
        TextureHandler.registerConnectedTexture(reg, this, 3, 0, "advanced", "scaffold");
        TextureHandler.registerConnectedTexture(reg, this, 3, 1, "advanced", "scaffold");
        icons[3] = TextureHandler.getConnectedTexture(this, 3, 1).icon;
        for (int i = 0; i < ArtificeCore.tiers.length; i++)
            sideIcons[i] = TextureHandler.registerIcon(reg, ArtificeCore.tiers[i].toLowerCase(Locale.ENGLISH), "scaffold/sides");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.tiers.length)
            meta = 0;
        if (side == 0 || side == 1)
        {
            return icons[meta];
        }
        return this.sideIcons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
    {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.tiers.length)
            meta = 0;
        if (side == 0 || side == 1)
        {
            return this.getIcon(side, meta);
        }
        return this.sideIcons[access.getBlockMetadata(x, y, z)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return ArtificeConfig.ctmRenderID;
    }
}
