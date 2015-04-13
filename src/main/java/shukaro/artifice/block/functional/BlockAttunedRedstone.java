package shukaro.artifice.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.event.Tracking;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.tile.TileEntityAttuned;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.MiscUtils;

import java.util.Random;

public class BlockAttunedRedstone extends Block implements ITileEntityProvider
{
    private IIcon powered;
    private IIcon unpowered;
    private boolean transmitter;

    public BlockAttunedRedstone(boolean transmitter)
    {
        super(Material.rock);
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setBlockName("artifice.attunedredstone." + (transmitter ? "transmitter" : "receiver"));
        this.transmitter = transmitter;
        GameRegistry.registerBlock(this, ItemBlockAttunedRedstone.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.powered = TextureHandler.registerIcon(reg, "powered" + (transmitter ? "transmitter" : "receiver"), "attuned");
        this.unpowered = TextureHandler.registerIcon(reg, "unpowered" + (transmitter ? "transmitter" : "receiver"), "attuned");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.unpowered;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityAttuned)
            return ((TileEntityAttuned)te).power > 0 ? this.powered : this.unpowered;
        return this.unpowered;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && player.isSneaking() && player.getHeldItem() == null)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityAttuned)
            {
                TileEntityAttuned tea = (TileEntityAttuned)te;
                if (tea.frequency.length() > 0)
                    MiscUtils.addChatMessage(player, FormatCodes.Italic.code + StatCollector.translateToLocal("chat.artifice.frequency") + " " + FormatCodes.Aqua.code + tea.frequency);
                else
                    MiscUtils.addChatMessage(player, FormatCodes.Italic.code + StatCollector.translateToLocal("chat.artifice.nofrequency"));
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        ItemStack stack = new ItemStack(world.getBlock(x, y, z), 1, 0);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityAttuned)
        {
            TileEntityAttuned tea = (TileEntityAttuned)te;
            if (tea.frequency.length() > 0)
                stack.setStackDisplayName(tea.frequency);
        }
        return stack;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) { return true; }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        if (te instanceof TileEntityAttuned)
        {
            TileEntityAttuned tea = (TileEntityAttuned)te;
            return transmitter ? tea.power : 0;
        }
        return 0;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public boolean canProvidePower()
    {
        return transmitter;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityAttuned)
        {
            TileEntityAttuned tea = (TileEntityAttuned)te;
            if (tea.frequency.length() > 0)
                Tracking.updateFrequency(tea.frequency);
        }
    }

    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, living, stack);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityAttuned)
        {
            TileEntityAttuned tea = (TileEntityAttuned)te;
            if (stack.hasDisplayName())
            {
                tea.frequency = stack.getDisplayName();
                Tracking.updateFrequency(tea.frequency);
            }
        }
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
    {
        if (!player.capabilities.isCreativeMode)
            player.addExhaustion(0.025F);
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityAttuned)
            {
                ItemStack stack = new ItemStack(world.getBlock(x, y, z), 1, 0);
                TileEntityAttuned tea = (TileEntityAttuned)te;
                if (tea.frequency.length() > 0)
                {
                    stack.setStackDisplayName(tea.frequency);
                    Tracking.updateFrequency(stack.getDisplayName());
                }
                if (!player.capabilities.isCreativeMode)
                    MiscUtils.dropStack(world, x, y, z, stack);
            }
        }
        return world.setBlockToAir(x, y, z);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityAttuned();
    }

    @Override
    public boolean hasTileEntity(int metadata) { return true; }
}
