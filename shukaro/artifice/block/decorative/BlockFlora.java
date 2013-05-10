package shukaro.artifice.block.decorative;

import java.util.List;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockFlora extends BlockFlower
{
	@SideOnly(Side.CLIENT)
	public static Icon[] textureList = new Icon[ArtificeCore.flora.length];
	
	public BlockFlora(int id)
	{
		super(id, Material.plants);
        this.setTickRandomly(true);
        setCreativeTab(ArtificeCreativeTab.tab);
		setHardness(0.0F);
		setStepSound(soundGrassFootstep);
		setUnlocalizedName("artifice.flora");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
    {
        return textureList[meta];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg)
	{
		for (int i=0;i<ArtificeCore.flora.length;i++)
		{
			String name = "artifice:" + "flora_" + ArtificeCore.flora[i].toLowerCase();
			this.textureList[i] = reg.registerIcon(name);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs tabs, List list)
	{
		for (int j=0; j<ArtificeCore.flora.length; j++)
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
