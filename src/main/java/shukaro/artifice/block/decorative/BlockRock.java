package shukaro.artifice.block.decorative;

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
import org.apache.commons.lang3.text.WordUtils;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.net.PacketDispatcher;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.List;

public class BlockRock extends BlockArtifice
{
    private IIcon[] icons = new IIcon[ArtificeCore.rocks.length];
    private String name;
    private int color = 16777215;

    public BlockRock(String name)
    {
        super(Material.rock);
        setHardness(1.5F);
        setResistance(10.0F);
        setBlockName("artifice." + name);
        setCreativeTab(ArtificeCore.worldTab);
        this.name = name;
    }

    public BlockRock(String name, int color)
    {
        this(name);
        this.name = name.split("[.]")[0];
        this.color = color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
        icons[0] = IconHandler.registerSingle(reg, name, name);
        icons[1] = IconHandler.registerSingle(reg, "cobblestone", name);
        icons[2] = IconHandler.registerSingle(reg, "bricks", name);
        icons[5] = IconHandler.registerSingle(reg, "chiseled", name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3)
        {
            for (ConnectedTextures texture : ConnectedTextures.values())
            {
                if (texture.name().equals(WordUtils.capitalize(name) + "Paver"))
                    return texture.textureList[0];
            }
        }
        if (meta == 4)
        {
            {
                for (ConnectedTextures texture : ConnectedTextures.values())
                {
                    if (texture.name().equals(WordUtils.capitalize(name) + "Antipaver"))
                        return texture.textureList[0];
                }
            }
        }
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
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
            return icons[meta];
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta == 0 ? 1 : meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.rocks.length; j++)
        {
            list.add(new ItemStack(i, 1, j));
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);
            BlockCoord c = new BlockCoord(x, y, z);
            if (neighbor != null && (meta == 3 || meta == 4))
                PacketDispatcher.sendTextureUpdatePacket(world, x, y, z);
        }
    }

    @Override
    public boolean isOpaqueCube() { return true; }

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

    @Override
    public int getRenderType() { return ArtificeConfig.rockRenderID; }
}
