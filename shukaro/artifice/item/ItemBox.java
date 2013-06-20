package shukaro.artifice.item;

import java.util.List;

import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.util.ComparableItemStackNBT;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.IdMetaPair;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBox extends Item
{
	private Icon icon;
	
	public ItemBox(int id)
	{
		super(id);
		this.setCreativeTab(ArtificeCreativeTab.tab);
		this.setUnlocalizedName("artifice.box");
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
		this.icon = reg.registerIcon("artifice:box");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
	{
		return stack.getTagCompound() != null;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
	{
		NBTTagCompound tag = stack.getTagCompound();
		int num = 1;
		if (player.isSneaking())
			num = stack.stackSize;
		if (tag != null)
		{
			if (Block.blocksList[world.getBlockId(x, y, z)] instanceof IDeepStorageUnit)
			{
				IDeepStorageUnit unit = (IDeepStorageUnit) Block.blocksList[world.getBlockId(x, y, z)];
				ItemStack contained = unit.getStoredItemType();
				ItemStack boxed = new ItemStack(tag.getInteger("id"), tag.getInteger("size") * num, tag.getInteger("meta"));
				if (contained == null)
				{
					unit.setStoredItemType(boxed.itemID, boxed.getItemDamage(), boxed.stackSize);
					stack.stackSize -= num;
					return true;
				}
				if (tag.getCompoundTag("nbt").hasNoTags() && contained.isItemEqual(boxed))
				{
					if (unit.getMaxStoredCount() < (contained.stackSize + boxed.stackSize))
						return false;
					else
					{
						unit.setStoredItemCount(contained.stackSize + boxed.stackSize);
						stack.stackSize -= num;
						return true;
					}
				}
				return false;
			}
			else
			{
				this.onItemRightClick(stack, world, player);
				return true;
			}
		}
		else
		{
			if (Block.blocksList[world.getBlockId(x, y, z)] instanceof IDeepStorageUnit)
			{
				IDeepStorageUnit unit = (IDeepStorageUnit) Block.blocksList[world.getBlockId(x, y, z)];
				ItemStack contained = unit.getStoredItemType();
				if (contained == null)
					return false;
				
				NBTTagCompound newTag = new NBTTagCompound();
				newTag.setInteger("meta", contained.getItemDamage());
				newTag.setInteger("id", contained.itemID);
				
				boolean canFill = true;
				if (contained.stackSize > (8 * num))
				{
					newTag.setInteger("size", 8);
					unit.setStoredItemCount(contained.stackSize - (8 * num));
				}
				else if (contained.stackSize > 8)
				{
					newTag.setInteger("size", 8);
					unit.setStoredItemCount(contained.stackSize - 8);
					canFill = false;
				}
				else
				{
					newTag.setInteger("size", contained.stackSize);
					unit.setStoredItemCount(0);
					canFill = false;
				}

				ItemStack newStack;
				if (!canFill)
				{
					newStack = new ItemStack(ArtificeItems.itemBox.itemID, 1, newTag.getInteger("size"));
					stack.stackSize--;
				}
				else
				{
					newStack = new ItemStack(ArtificeItems.itemBox.itemID, num, newTag.getInteger("size"));
					stack.stackSize -= num;
				}
				newStack.setTagCompound(newTag);
				
				if (!player.inventory.addItemStackToInventory(newStack) && !world.isRemote)
					world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, newStack));
				return true;
			}
			return false;
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null)
		{
			// Do stuff here at a later date
		}
		return stack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
	{
		if (ArtificeConfig.tooltips.getBoolean(true))
		{
	    	IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
	    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
			{
	    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
	    		{
	    			if (s.startsWith(ArtificeTooltips.commentCode))
	    				continue;
	    			infoList.add(s);
	    		}
			}
		}
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null)
		{
			infoList.add("Contains: " + FormatCodes.Aqua.format + new ItemStack(tag.getInteger("id"), 1, tag.getInteger("meta")).getDisplayName());
			infoList.add("Amount: " + FormatCodes.Aqua.format + stack.getItemDamage());
		}
		if (ArtificeConfig.tooltips.getBoolean(true))
		{
	    	IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
	    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
			{
	    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
	    		{
	    			if (!ArtificeConfig.flavorText.getBoolean(true) || !s.startsWith(ArtificeTooltips.commentCode))
	    				continue;
	    			infoList.add(s);
	    		}
			}
		}
	}
}
