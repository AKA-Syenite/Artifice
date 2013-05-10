package shukaro.artifice.block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockArtifice extends Block
{
	@SideOnly(Side.CLIENT)
	protected Icon[][] textureList = new Icon[ArtificeCore.tiers.length][256];
	protected String textureName;
	
	public ConnectedTextureBase textureRenderer;
	
	public BlockArtifice(int id, Material mat)
	{
		super(id, mat);
		setCreativeTab(ArtificeCreativeTab.tab);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon[][] getTextureList()
	{
		return this.textureList;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return textureList[meta][0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
	{
		return textureList[block.getBlockMetadata(x, y, z)][textureRenderer.getBlockTexture(block, x, y, z, side)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		for (int i=0;i<ArtificeCore.tiers.length;i++)
		{
			for (int j=0;j<47;j++)
			{
				String name = "artifice:" + this.textureName + "/" + this.textureName + "_" + ArtificeCore.tiers[i].toLowerCase() + "_" + j;
				this.textureList[i][j] = reg.registerIcon(name);
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tabs, List list)
	{
		for (int j=0; j<ArtificeCore.tiers.length; j++)
		{
			list.add(new ItemStack(i, 1, j));
		}
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}
}
