package shukaro.artifice.recipe;

import shukaro.artifice.ArtificeItems;
import shukaro.artifice.util.ComparableItemStackNBT;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeBox implements IRecipe
{
	private ItemStack output;
	private ItemStack thing;
	private ItemStack box;

	@Override
	public boolean matches(InventoryCrafting craft, World world)
	{
		this.output = null;
		this.box = null;
		this.thing = null;
		
		boolean hasBox = false;
		boolean isEmpty = true;
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
			{
				hasBox = true;
				if (stack.getTagCompound() != null)
					isEmpty = false;
				break;
			}
		}
		if (!hasBox)
			return false;
		
		if (!isEmpty)
		{
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID != ArtificeItems.itemBox.itemID)
					return false;
			}
			int c = 0;
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
					c++;
			}
			if (c != 1)
				return false;
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
					this.box = stack;
			}
			
			NBTTagCompound tag = this.box.getTagCompound();
			int meta = tag.getInteger("meta");
			int size = tag.getInteger("size");
			NBTTagCompound nbt = tag.getCompoundTag("nbt");
			this.thing = new ItemStack(this.box.getItemDamage(), 1, meta);
			if (size > thing.getMaxStackSize())
				size = thing.getMaxStackSize();
			this.output = new ItemStack(this.box.getItemDamage(), size, meta);
			if (nbt != null)
				this.output.setTagCompound(nbt);
			return true;
		}
		
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID != ArtificeItems.itemBox.itemID)
			{
				this.thing = stack;
				break;
			}
		}
		
		if (this.thing == null || this.thing.getMaxStackSize() < 8)
			return false;
		
		ComparableItemStackNBT contents = new ComparableItemStackNBT(this.thing);
		int num = 0;
		
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null)
			{
				ComparableItemStackNBT t = new ComparableItemStackNBT(stack);
				if (stack.itemID == ArtificeItems.itemBox.itemID)
					continue;
				else if (t.equals(contents))
					num++;
				else
					return false;
			}
		}
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("meta", thing.getItemDamage());
		tag.setInteger("size", num);
		if (thing.getTagCompound() != null)
			tag.setCompoundTag("nbt", thing.getTagCompound());
		this.output = new ItemStack(ArtificeItems.itemBox.itemID, 1, this.thing.itemID);
		if (tag.getCompoundTag("nbt") != null)
			this.output.setTagCompound(tag);
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting)
	{
		return this.output.copy();
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return this.output;
	}

}
