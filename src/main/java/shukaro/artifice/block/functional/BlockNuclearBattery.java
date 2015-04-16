package shukaro.artifice.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.tile.TileEntityNuclearBattery;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.MiscUtils;

import java.util.List;
import java.util.Random;

public class BlockNuclearBattery extends Block implements ITileEntityProvider
{
    private IIcon[] icons = new IIcon[4];

    public BlockNuclearBattery()
    {
        super(Material.iron);
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setBlockName("artifice.nuclearbattery");
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icons[0] = TextureHandler.registerIcon(reg, "full", "nuclearbattery");
        this.icons[1] = TextureHandler.registerIcon(reg, "partial", "nuclearbattery");
        this.icons[2] = TextureHandler.registerIcon(reg, "low", "nuclearbattery");
        this.icons[3] = TextureHandler.registerIcon(reg, "empty", "nuclearbattery");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return meta < this.icons.length ? this.icons[meta] : this.icons[0];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        ItemStack stack = new ItemStack(item, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("charge", ArtificeConfig.nuclearBatteryCapacity);
        stack.setTagCompound(tag);
        list.add(stack);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && player.getHeldItem() == null)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityNuclearBattery)
            {
                TileEntityNuclearBattery teb = (TileEntityNuclearBattery)te;
                MiscUtils.addChatMessage(player, FormatCodes.Yellow.code + teb.getEnergyStored(ForgeDirection.DOWN) + FormatCodes.Reset.code + " " + StatCollector.translateToLocal("chat.artifice.rf")
                        + " @ " + FormatCodes.Aqua.code + teb.getMaxRate() + FormatCodes.Reset.code + " " + StatCollector.translateToLocal("chat.artifice.rft"));
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return (3-meta) * 2;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, living, stack);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityNuclearBattery)
        {
            TileEntityNuclearBattery teb = (TileEntityNuclearBattery)te;
            if (stack.hasTagCompound())
                teb.setEnergy(stack.getTagCompound().getInteger("charge"));
            else
                teb.setEnergy(ArtificeConfig.nuclearBatteryCapacity);
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
            if (te instanceof TileEntityNuclearBattery)
            {
                ItemStack stack = new ItemStack(world.getBlock(x, y, z), 1, 0);
                TileEntityNuclearBattery teb = (TileEntityNuclearBattery)te;
                NBTTagCompound tag = new NBTTagCompound();
                tag.setInteger("charge", teb.getEnergyStored(ForgeDirection.DOWN));
                stack.setTagCompound(tag);
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
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityNuclearBattery();
    }

    @Override
    public boolean hasTileEntity(int metadata) { return true; }
}
