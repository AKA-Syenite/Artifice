package shukaro.artifice.block.decorative;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBasaltSlab extends BlockHalfSlab
{
    private final String[] types = { "basaltBrick", "basaltCobble", "basaltPaver", "basaltAntipaver" };

    private Icon paverSide;

    private final boolean isDouble;

    public BlockBasaltSlab(int id, boolean isDouble)
    {
        super(id, isDouble, Material.rock);
        setCreativeTab(ArtificeCreativeTab.main);
        setLightOpacity(0);
        setHardness(1.5F);
        setResistance(10.0F);
        this.isDouble = isDouble;
    }

    @Override
    public int idDropped(int id, Random rand, int meta)
    {
        return ArtificeBlocks.blockBasaltSlab.blockID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        if (id != ArtificeBlocks.blockBasaltDoubleSlab.blockID)
        {
            for (int i = 0; i < types.length; i++)
                list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public String getFullSlabName(int meta)
    {
        meta = meta > 7 ? meta - 8 : meta;
        if (meta >= types.length)
            meta = 0;
        return "tile.artifice.slab." + types[meta].toLowerCase(Locale.ENGLISH);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        paverSide = IconHandler.registerSingle(reg, "paverside", "basalt");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        meta = meta & 7;
        if (meta > types.length)
        	meta = 0;
        if (meta == 2)
        {
            if (side == 0 || side == 1)
            	return ConnectedTextures.BasaltPaver.textureList[0];
            else
                return this.paverSide;
        }
        if (meta == 3)
        {
            if (side == 0 || side == 1)
            	return ConnectedTextures.BasaltAntipaver.textureList[0];
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        return ArtificeBlocks.blockBasalt.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z) & 7;
        if (meta == 2 || meta == 3)
        {
            if (side == 0 || side == 1)
            {
            	BlockCoord coord = new BlockCoord(x, y, z);

            	if (!ArtificeCore.textureCache.containsKey(coord))
            		TextureHandler.updateTexture(coord);

            	if (ArtificeCore.textureCache.get(coord) == null)
            		return this.getIcon(side, meta);
            	if (TextureHandler.getConnectedTexture(this.getIcon(side, meta)) != null)
            		return TextureHandler.getConnectedTexture(this.getIcon(side, meta)).textureList[ArtificeCore.textureCache.get(coord)[side]];
            	return this.getIcon(side, meta);
            }
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        return ArtificeBlocks.blockBasalt.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
    	int meta = world.getBlockMetadata(x, y, z) & 7;
    	BlockCoord coord = new BlockCoord(x, y, z);
        if (coord.getBlock(world) != null && (meta == 2 || meta == 3))
        {
        	TextureHandler.updateTexture(coord);
        	for (BlockCoord n : coord.getAdjacent())
	    		TextureHandler.updateTexture(n);
        }
    }
}
