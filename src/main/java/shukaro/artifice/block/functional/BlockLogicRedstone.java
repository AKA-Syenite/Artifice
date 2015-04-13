package shukaro.artifice.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.tile.TileEntityLogic;

import java.util.List;

public class BlockLogicRedstone extends Block implements ITileEntityProvider
{
    private IIcon disabled;
    private IIcon output;
    private IIcon positive;
    private IIcon negative;

    public BlockLogicRedstone()
    {
        super(Material.rock);
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setBlockName("artifice.logicredstone");
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.disabled = TextureHandler.registerIcon(reg, "disabled", "logic");
        this.output = TextureHandler.registerIcon(reg, "output", "logic");
        this.positive = TextureHandler.registerIcon(reg, "positive", "logic");
        this.negative = TextureHandler.registerIcon(reg, "negative", "logic");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.disabled;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityLogic)
        {
            TileEntityLogic tel = (TileEntityLogic)te;
            switch (tel.sides[side].ordinal())
            {
                case 0:
                    return this.disabled;
                case 1:
                    return this.output;
                case 2:
                    return this.positive;
                case 3:
                    return this.negative;
                default:
                    return this.disabled;
            }
        }
        return this.disabled;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking() && player.getHeldItem() == null)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityLogic)
            {
                TileEntityLogic tel = (TileEntityLogic)te;
                int i = tel.sides[side].ordinal() + 1;
                if (i > 3)
                    i = 0;
                tel.sides[side] = TileEntityLogic.SideState.values()[i];
                tel.updateEntity();
                world.markBlockForUpdate(x, y, z);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityLogic)
        {
            TileEntityLogic tel = (TileEntityLogic)te;
            tel.updateEntity();
        }
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        if (te instanceof TileEntityLogic)
        {
            TileEntityLogic tel = (TileEntityLogic)te;
            return tel.sides[ForgeDirection.OPPOSITES[side]] == TileEntityLogic.SideState.OUTPUT ? tel.power : 0;
        }
        return 0;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        TileEntity te = blockAccess.getTileEntity(x, y, z);
        if (te instanceof TileEntityLogic)
        {
            TileEntityLogic tel = (TileEntityLogic)te;
            switch (side)
            {
                case -1:
                    return tel.sides[1] != TileEntityLogic.SideState.DISABLED;
                case 0:
                    return tel.sides[2] != TileEntityLogic.SideState.DISABLED;
                case 1:
                    return tel.sides[5] != TileEntityLogic.SideState.DISABLED;
                case 2:
                    return tel.sides[3] != TileEntityLogic.SideState.DISABLED;
                case 3:
                    return tel.sides[4] != TileEntityLogic.SideState.DISABLED;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityLogic();
    }

    @Override
    public boolean hasTileEntity(int metadata) { return true; }
}
