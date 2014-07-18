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
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.net.PacketDispatcher;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BlockMarbleSlab extends BlockSlab
{
    private final String[] types = {"marbleBrick", "marbleCobble", "marblePaver", "marbleAntipaver"};

    private IIcon paverSide;

    public BlockMarbleSlab(boolean isDouble)
    {
        super(isDouble, Material.rock);
        setCreativeTab(ArtificeCreativeTab.main);
        setLightOpacity(0);
        setHardness(1.5F);
        setResistance(10.0F);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ArtificeBlocks.blockMarbleSlab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List list)
    {
        if (!id.equals(Item.getItemFromBlock(ArtificeBlocks.blockMarbleDoubleSlab)))
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
        return "tile.artifice.slab." + types[meta].toLowerCase(Locale.ENGLISH);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        ArtificeConfig.registerConnectedTextures(reg);
        paverSide = IconHandler.registerSingle(reg, "paverside", "marble");
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
                return ConnectedTextures.MarblePaver.textureList[0];
            else
                return this.paverSide;
        }
        if (meta == 3)
        {
            if (side == 0 || side == 1)
                return ConnectedTextures.MarbleAntipaver.textureList[0];
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        return ArtificeBlocks.blockMarble.getIcon(side, meta);
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
        return ArtificeBlocks.blockMarble.getIcon(side, meta);
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
}