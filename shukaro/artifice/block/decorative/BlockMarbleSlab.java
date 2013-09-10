package shukaro.artifice.block.decorative;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
import shukaro.artifice.render.connectedtexture.SlabConnectedTexture;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarbleSlab extends BlockHalfSlab implements IConnectedTexture
{
    private final String[] types = { "marbleBrick", "marbleCobble", "marblePaver", "marbleAntipaver" };
    
    private Icon paverSide;
    
    private ConnectedTextureBase paver = new SlabConnectedTexture(ConnectedTexture.MarblePaver);
    private ConnectedTextureBase antipaver = new SlabConnectedTexture(ConnectedTexture.MarbleAntipaver);
    
    private final boolean isDouble;
    
    public BlockMarbleSlab(int id, boolean isDouble)
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
        return ArtificeBlocks.blockMarbleSlab.blockID;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        if (id != ArtificeBlocks.blockMarbleDoubleSlab.blockID)
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
        return "tile.artifice.slab." + types[meta].toLowerCase();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        paverSide = IconHandler.registerSingle(reg, "paverside", "marble");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        meta = meta > 7 ? meta - 8 : meta;
        if (meta == 2 || meta == 3)
        {
            if (side == 0 || side == 1)
                return this.getTextureType(side, meta).textureList[0];
            else
                return this.paverSide;
        }
        if (meta == 0)
            meta = 2;
        return ArtificeBlocks.blockMarble.getIcon(side, meta);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        int t = block.getBlockMetadata(x, y, z) & 7;
        int meta = block.getBlockMetadata(x, y, z);
        if (t == 2 || t == 3)
        {
            if (side == 0 || side == 1)
                return this.getTextureType(side, block.getBlockMetadata(x, y, z)).textureList[this.getTextureRenderer(side, block.getBlockMetadata(x, y, z)).getTextureIndex(block, x, y, z, side)];
            else
                return this.paverSide;
        }
        if (t == 0)
            t = 2;
        return ArtificeBlocks.blockMarble.getIcon(side, t);
    }

    @Override
    public ConnectedTexture getTextureType(int side, int meta)
    {
        meta = meta & 7;
        if (meta == 2)
            return ConnectedTexture.MarblePaver;
        if (meta == 3)
            return ConnectedTexture.MarbleAntipaver;
        return null;
    }

    @Override
    public ConnectedTextureBase getTextureRenderer(int side, int meta)
    {
        meta = meta & 7;
        if (meta == 2)
            return this.paver;
        if (meta == 3)
            return this.antipaver;
        return null;
    }
}