package shukaro.artifice.block.decorative;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.connectivity.IRedNetConnection;
import powercrystals.minefactoryreloaded.api.rednet.connectivity.RedNetConnectionType;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.net.PacketDispatcher;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.MinecraftColors;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Optional.Interface(iface = "powercrystals.minefactoryreloaded.api.rednet.connectivity.IRedNetConnection", modid = "MineFactoryReloaded")
public class BlockLamp extends BlockArtifice implements IRedNetConnection
{
    MinecraftColors color;
    private boolean inverted;
    private IIcon icon;

    public BlockLamp(MinecraftColors color, boolean inverted)
    {
        super(Material.glass);
        this.color = color;
        this.inverted = inverted;
        setHardness(0.3F);
        setStepSound(soundTypeGlass);
        if (inverted)
            setBlockName("artifice.lampinverted." + color.name().toLowerCase(Locale.ENGLISH));
        else
            setBlockName("artifice.lamp." + color.name().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
        this.icon = IconHandler.registerSingle(reg, "lamp_normal_00", "lamp/normal");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.icon;
    }

    @Override
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        if (meta >= 16)
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
        {
            world.scheduleBlockUpdate(x, y, z, this, 2);
            BlockCoord c = new BlockCoord(x, y, z);
            if (neighbor != null)
                PacketDispatcher.sendTextureUpdatePacket(world, x, y, z);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isBlockIndirectlyGettingPowered(x, y, z) && world.getBlockPowerInput(x, y, z) == 0)
        {
            if (world.getBlockMetadata(x, y, z) != 0)
                world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }
        else
        {
            int power = world.getStrongestIndirectPower(x, y, z) > world.getBlockPowerInput(x, y, z) ? world.getStrongestIndirectPower(x, y, z) : world.getBlockPowerInput(x, y, z);
            if (world.getBlockMetadata(x, y, z) != power)
                world.setBlockMetadataWithNotify(x, y, z, power, 3);
        }
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    @Override
    public int damageDropped(int meta) { return 0; }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 0;
    }

    @Override
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side)
    {
        return RedNetConnectionType.CableSingle;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        if (inverted)
            return color.toAdjusted(5);
        return color.toAdjusted(-5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess block, int x, int y, int z)
    {
        if (inverted)
            return color.toAdjusted(5 - block.getBlockMetadata(x, y, z));
        return color.toAdjusted(block.getBlockMetadata(x, y, z) - 5);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if (inverted)
            return 15 - world.getBlockMetadata(x, y, z);
        else
            return world.getBlockMetadata(x, y, z);
    }
}
