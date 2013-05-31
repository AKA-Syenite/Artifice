package shukaro.artifice.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArtifice extends Block
{
	protected String textureName;
	
	public ConnectedTextureBase textureRenderer;
    protected Icon[][] textureList = new Icon[ArtificeCore.tiers.length][256];
    
    protected boolean single;
    protected Icon[] singleTextureList = new Icon[ArtificeCore.tiers.length];
    
    protected boolean normal;
    protected Icon[][] normalTextureList = new Icon[ArtificeCore.tiers.length][6];
    
    public BlockArtifice(int id, Material mat)
    {
        super(id, mat);
        setCreativeTab(ArtificeCreativeTab.tab);
    }
    
    public Icon[][] getTextureList()
    {
        return this.textureList;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
