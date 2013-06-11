package shukaro.artifice.item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import shukaro.artifice.ArtificeItems;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.util.IdMetaPair;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemSledge extends ItemTool
{
	private Icon icon;
	private int lossChance;
	
	public ItemSledge(int id, EnumToolMaterial mat)
	{
		super(id, 2, mat, null);
		this.setCreativeTab(ArtificeCreativeTab.tab);
		this.setMaxDamage(mat.getMaxUses()/4);
		this.setUnlocalizedName("artifice.sledge." + this.toolMaterial.toString().toLowerCase());
		this.lossChance = getLossChance(mat);
	}
	
	public int getLossChance(EnumToolMaterial mat)
	{
		switch (mat)
		{
			case EMERALD:
				return 20;
			case GOLD:
				return 25;
			case IRON:
				return 30;
			case STONE:
				return 40;
			case WOOD:
				return 50;
			default:
				return 0;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
	{
    	IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
		{
    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
    		{
    			infoList.add(s);
    		}
		}
	}
	
	@Override
	public boolean canHarvestBlock(Block block)
	{
		if (ArtificeRegistry.getWildSledgeBlocks().get(block.blockID) != null)
			return true;
		for (int i=0; i<16; i++)
		{
			if (ArtificeRegistry.getSledgeBlocks().get(new IdMetaPair(block.blockID, i)) != null)
				return true;
		}
		return false;
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta) 
	{
		IdMetaPair pair = new IdMetaPair(block.blockID, meta);
		if (ArtificeRegistry.getWildSledgeBlocks().get(block.blockID) != null || ArtificeRegistry.getSledgeBlocks().get(pair) != null)
			return this.toolMaterial.getEfficiencyOnProperMaterial();
		return 1.0F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
	{
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
	{
		this.icon = reg.registerIcon("artifice:sledge_" + this.toolMaterial.toString().toLowerCase());
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		World world = player.worldObj;
		if (world.isRemote || player.capabilities.isCreativeMode)
			return super.onBlockStartBreak(stack, x, y, z, player);
		
		try
		{
			int id = world.getBlockId(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			IdMetaPair pair = new IdMetaPair(id, meta);
			
			ArrayList<ItemStack> dropped = ArtificeRegistry.getWildSledgeBlocks().get(id);
			if (dropped == null)
				dropped = ArtificeRegistry.getSledgeBlocks().get(pair);
			
			if (dropped != null)
			{
				for (ItemStack is : dropped)
				{
					if (world.rand.nextInt(100) > this.lossChance)
						world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, is.copy()));
				}
				world.setBlockToAir(x, y, z);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.onBlockStartBreak(stack, x, y, z, player);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int id, int x, int y, int z, EntityLiving entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		IdMetaPair pair = new IdMetaPair(id, meta);
		
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if (player.capabilities.isCreativeMode)
				return true;
		}
		
		if (ArtificeRegistry.getSledgeBlocks().get(pair) != null)
			stack.damageItem(1, entity);
		
		return true;
	}
}
