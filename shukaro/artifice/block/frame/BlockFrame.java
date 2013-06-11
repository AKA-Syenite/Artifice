package shukaro.artifice.block.frame;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFrame extends BlockArtifice
{
	protected List<String> validTiers = new ArrayList<String>();
	private Icon renderIcon;
	
    public BlockFrame(int id)
    {
        super(id, Material.rock);
        this.validTiers.add(ArtificeCore.tiers[0]);
        this.validTiers.add(ArtificeCore.tiers[1]);
        this.validTiers.add(ArtificeCore.tiers[2]);
        this.validTiers.add(ArtificeCore.tiers[3]);
    }
    
    public abstract Block getInnerBlock(int meta);
    
    public abstract int getInnerMeta(int meta);
    
    public abstract Icon getRenderIcon(int meta);
    
    @Override
    public abstract boolean isOpaqueCube();
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
    	if (single)
    		return singleTextureList[meta];
    	if (normal)
    		return normalTextureList[meta][side];
    	return textureList[meta][0];
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
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.tiers.length; j++)
        {
        	if (this.validTiers.contains(ArtificeCore.tiers[j]))
        		list.add(new ItemStack(i, 1, j));
        }
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return (float) meta + 5;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }
    
    @Override
    public int getRenderType()
    {
        return ArtificeCore.frameRenderID;
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof IMultiblockPart)
        {
            ((IMultiblockPart)te).onBlockAdded(world, x, y, z);
        }
    }
    
    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityMultiblock();
    }
}
