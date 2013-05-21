package shukaro.artifice.block.decorative;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBasalt extends BlockArtifice
{
    private final Icon[] singleTextureList = new Icon[ArtificeCore.rocks.length];
    
    public BlockBasalt(int id)
    {
        super(id, Material.rock);
        this.textureRenderer = new SolidConnectedTexture(id);
        setCreativeTab(ArtificeCreativeTab.tab);
        setHardness(1.5F);
        setUnlocalizedName("artifice.basalt");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta == 3 || meta == 4)
            return this.textureList[3][0];
        else
            return singleTextureList[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        if (meta == 3 || meta == 4)
            return textureList[3][textureRenderer.getBlockTexture(block, x, y, z, side)];
        else
            return singleTextureList[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        this.singleTextureList[0] = reg.registerIcon("artifice:basalt");
        for (int i = 1; i < ArtificeCore.rocks.length; i++)
        {
            if (i != 3 && i != 4)
            {
                String name = "artifice:basalt_" + ArtificeCore.rocks[i].toLowerCase();
                this.singleTextureList[i] = reg.registerIcon(name);
            }
        }
        for (int j = 0; j < 47; j++)
        {
            String name = "artifice:basalt/basalt_paver_" + j;
            this.textureList[3][j] = reg.registerIcon(name);
        }
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
}
