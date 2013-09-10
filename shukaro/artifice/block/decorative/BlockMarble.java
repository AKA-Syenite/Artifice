package shukaro.artifice.block.decorative;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarble extends BlockArtifice implements IConnectedTexture
{
    private Icon[] icons = new Icon[ArtificeCore.rocks.length];
    private ConnectedTextureBase paver = new SolidConnectedTexture(ConnectedTexture.MarblePaver);
    private ConnectedTextureBase antipaver = new SolidConnectedTexture(ConnectedTexture.MarbleAntipaver);
    
    public BlockMarble(int id)
    {
        super(id, Material.rock);
        setCreativeTab(ArtificeCreativeTab.main);
        setHardness(1.5F);
        setResistance(10.0F);
        setUnlocalizedName("artifice.marble");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.rocks.length; j++)
        {
            list.add(new ItemStack(i, 1, j));
        }
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta == 0 ? 1 : meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        icons[0] = IconHandler.registerSingle(reg, "marble", "marble");
        icons[1] = IconHandler.registerSingle(reg, "cobblestone", "marble");
        icons[2] = IconHandler.registerSingle(reg, "bricks", "marble");
        icons[5] = IconHandler.registerSingle(reg, "chiseled", "marble");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
            return this.getTextureType(side, meta).textureList[0];
        else
            return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
            return this.getTextureType(side, meta).textureList[this.getTextureRenderer(side, meta).getTextureIndex(block, x, y, z, side)];
        else
            return icons[meta];
    }

    @Override
    public ConnectedTexture getTextureType(int side, int meta)
    {
        if (meta == 3)
            return ConnectedTexture.MarblePaver;
        if (meta == 4)
            return ConnectedTexture.MarbleAntipaver;
        return null;
    }

    @Override
    public ConnectedTextureBase getTextureRenderer(int side, int meta)
    {
        if (meta == 3)
            return this.paver;
        if (meta == 4)
            return this.antipaver;
        return null;
    }
}
