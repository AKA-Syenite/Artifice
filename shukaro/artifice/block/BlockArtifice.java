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
    
    protected boolean oriented;
    protected int facing;
    protected Icon[][][] orientedTextureList = new Icon[ArtificeCore.tiers.length][6][6];
    
    protected boolean normal;
    protected Icon[][] normalTextureList = new Icon[ArtificeCore.tiers.length][6];
    
    protected List<Integer> validTiers = new ArrayList<Integer>();
    
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
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
    	if (oriented)
    		return orientedTextureList[meta][facing][side];
    	if (single)
    		return singleTextureList[meta];
    	return normalTextureList[meta][side];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        if (textureRenderer != null)
            return textureList[block.getBlockMetadata(x, y, z)][textureRenderer.getBlockTexture(block, x, y, z, side)];
        return this.getIcon(side, block.getBlockMetadata(x, y, z));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	if (textureRenderer != null)
    	{
	        for (int i = 0; i < ArtificeCore.tiers.length; i++)
	        {
	            for (int j = 0; j < 47; j++)
	            {
	                String name = "artifice:" + this.textureName + "/" + this.textureName + "_" + ArtificeCore.tiers[i].toLowerCase() + "_" + j;
	                this.textureList[i][j] = reg.registerIcon(name);
	            }
	        }
    	}
    	if (single)
    	{
    		for (int i=0; i<ArtificeCore.tiers.length; i++)
    		{
    			String name = "artifice:" + this.textureName + "/" + this.textureName + "_" + ArtificeCore.tiers[i].toLowerCase();
    			this.singleTextureList[i] = reg.registerIcon(name);
    		}
    	}
    	if (normal)
    	{
    		for (int i=0; i<ArtificeCore.tiers.length; i++)
    		{
    			for (int j=0; j<6; j++)
    			{
    				String name = "artifice:" + this.textureName + "/" + this.textureName + "_" + ArtificeCore.tiers[i].toLowerCase() + "_" + ForgeDirection.VALID_DIRECTIONS[j].toString().toLowerCase();
    				this.normalTextureList[i][j] = reg.registerIcon(name);
    			}
    		}
    	}
    	if (oriented)
    	{
    		
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.tiers.length; j++)
        {
        	if (validTiers.contains(j))
        		list.add(new ItemStack(i, 1, j));
        }
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
