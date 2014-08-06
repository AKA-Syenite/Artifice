package shukaro.artifice.block.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.WordUtils;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.PacketDispatcher;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.List;
import java.util.Random;

public class BlockRockSlab extends BlockSlab
{
    private static String[] types = {"brick", "cobble", "paver", "antipaver"};

    private IIcon paverSide;

    private boolean isDouble;
    private String name;
    private int color = 16777215;

    public BlockRockSlab(String name, boolean isDouble)
    {
        super(isDouble, Material.rock);
        setCreativeTab(ArtificeCore.worldTab);
        setLightOpacity(0);
        setHardness(1.5F);
        setResistance(10.0F);
        setBlockName("artifice." + name + (isDouble ? ".doubleslab" : ".slab"));
        this.name = name;
        this.isDouble = isDouble;
    }

    public BlockRockSlab(String name, int color, boolean isDouble)
    {
        this(name, isDouble);
        this.color = color;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list)
    {
        if (!isDouble)
        {
            for (int i = 0; i < types.length; i++)
                list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public String func_150002_b(int meta)
    {
        meta = meta > 7 ? meta - 8 : meta;
        if (meta >= types.length)
            meta = 0;
        return "tile.artifice." + name + (isDouble ? ".doubleslab." : ".slab.") + types[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
        paverSide = IconHandler.registerSingle(reg, "paverside", name.split("[.]")[0]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        meta = meta & 7;
        if (meta > types.length)
            meta = 0;
        if (meta == 2)
        {
            if (side == 0 || side == 1)
            {
                for (ConnectedTextures texture : ConnectedTextures.values())
                {
                    if (texture.name().equals(WordUtils.capitalize(name.split("[.]")[0]) + "Paver"))
                        return texture.textureList[0];
                }
            }
            else
                return this.paverSide;
        }
        if (meta == 3)
        {
            if (side == 0 || side == 1)
            {
                for (ConnectedTextures texture : ConnectedTextures.values())
                {
                    if (texture.name().equals(WordUtils.capitalize(name.split("[.]")[0]) + "Antipaver"))
                        return texture.textureList[0];
                }
            }
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        if (name.split("[.]")[0].equals("basalt"))
            return ArtificeBlocks.blockBasalt.getIcon(side, meta);
        else if (name.split("[.]")[0].equals("marble"))
            return ArtificeBlocks.blockBasalt.getIcon(side, meta);
        else
            return ArtificeBlocks.blockLimestones[0].getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z) & 7;
        if (meta == 2 || meta == 3)
        {
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
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        if (name.split("[.]")[0].equals("basalt"))
            return ArtificeBlocks.blockBasalt.getIcon(side, meta);
        else if (name.split("[.]")[0].equals("marble"))
            return ArtificeBlocks.blockBasalt.getIcon(side, meta);
        else
            return ArtificeBlocks.blockLimestones[0].getIcon(side, meta);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z) & 7;
            BlockCoord c = new BlockCoord(x, y, z);
            if (c.getBlock(world) != null && (meta == 2 || meta == 3))
                PacketDispatcher.sendTextureUpdatePacket(world, x, y, z);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        return color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess block, int x, int y, int z)
    {
        return color;
    }
}
