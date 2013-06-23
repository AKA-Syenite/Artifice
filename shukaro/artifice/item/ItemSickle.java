package shukaro.artifice.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.IdMetaPair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSickle extends ItemTool
{
	public Icon icon;
	public int radius;
	
	public ItemSickle(int id, EnumToolMaterial mat)
	{
		super(id, 3, mat, null);
		this.setCreativeTab(ArtificeCreativeTab.tab);
		this.setMaxDamage(mat.getMaxUses()/2);
		this.setUnlocalizedName("artifice.sickle." + this.toolMaterial.toString().toLowerCase());
		this.radius = getRadius(mat);
	}
	
	public int getRadius(EnumToolMaterial mat)
	{
		switch (mat)
		{
			case EMERALD:
				return 5;
			case GOLD:
				return 4;
			case IRON:
				return 3;
			case STONE:
				return 2;
			case WOOD:
				return 1;
			default:
				return 0;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
	{
		if (!ArtificeConfig.tooltips.getBoolean(true))
    		return;
    	IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
		{
    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
    		{
    			if (!ArtificeConfig.flavorText.getBoolean(true) && s.startsWith(ArtificeTooltips.commentCode))
    				continue;
    			infoList.add(s);
    		}
		}
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
		this.icon = reg.registerIcon("artifice:sickle_" + this.toolMaterial.toString().toLowerCase());
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{
		return 1.0F;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
	{
		BlockCoord coord = new BlockCoord(x, y, z);
		World world = player.worldObj;
		Block block = coord.getBlock(world);
		int radius = getRadius(this.toolMaterial);
		
		if (block instanceof BlockFlower)
		{
			for (BlockCoord c : coord.getRadiusBlocks(world, radius))
			{
				Block b = Block.blocksList[world.getBlockId(c.x, c.y, c.z)];
				int meta = world.getBlockMetadata(c.x, c.y, c.z);
				if (b instanceof BlockFlower && coord.getDistance(c) <= radius && b.canHarvestBlock(player, meta))
				{
					b.harvestBlock(world, player, c.x, c.y, c.z, meta);
					world.setBlockToAir(c.x, c.y, c.z);
				}
			}
			if (!world.isRemote)
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, block.stepSound.getBreakSound(), 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
			stack.damageItem(1, player);
			return true;
		}
		
		else if (block != null && block.isLeaves(world, x, y, z))
		{
			for (BlockCoord c : coord.getRadiusBlocks(world, radius))
			{
				Block b = Block.blocksList[world.getBlockId(c.x, c.y, c.z)];
				int meta = world.getBlockMetadata(c.x, c.y, c.z);
				if (b != null && coord.getDistance(c) <= radius && b.isLeaves(world, c.x, c.y, c.z))
				{
					if (b.canHarvestBlock(player, meta))
						b.harvestBlock(world, player, c.x, c.y, c.z, meta);
					world.setBlockToAir(c.x, c.y, c.z);
				}
			}
			if (!world.isRemote)
				world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, block.stepSound.getBreakSound(), 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
			stack.damageItem(1, player);
			return true;
		}
		
		return super.onBlockStartBreak(stack, x, y, z, player);
	}
}
