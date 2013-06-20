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
		
		// Is there a box in the grid?
		boolean hasBox = false;
		boolean isEmpty = true;
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
			{
				hasBox = true;
				// Is it an empty box?
				if (stack.getTagCompound() != null)
					isEmpty = false;
				break;
			}
		}
		if (!hasBox)
			return false;
		
		// The box isn't empty
		if (!isEmpty)
		{
			// Is there anything other than boxes in the grid?
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID != ArtificeItems.itemBox.itemID)
					return false;
			}
			// Is there more than 1 box in the grid?
			int c = 0;
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
					c++;
			}
			if (c != 1)
				return false;
			// There one full box in the grid, set it
			for (int i=0; i<craft.getSizeInventory(); i++)
			{
				ItemStack stack = craft.getStackInSlot(i);
				if (stack != null && stack.itemID == ArtificeItems.itemBox.itemID)
					this.box = stack;
			}
			
			// Read the nbt data of the box
			NBTTagCompound tag = this.box.getTagCompound();
			// Get the thing's ID
			int id = tag.getInteger("id");
			// Get the thing's meta
			int meta = tag.getInteger("meta");
			// Get the number of things
			int size = this.box.getItemDamage();
			// Get the the nbt data of the thing
			NBTTagCompound nbt = tag.getCompoundTag("nbt");
			
			// This is our thing
			this.thing = new ItemStack(id, 1, meta);
			if (nbt != null)
				this.thing.setTagCompound(nbt);
			
			// Is it legal to output a stack of this size?
			int outAmount = size;
			if (outAmount > thing.getMaxStackSize())
				outAmount = thing.getMaxStackSize();
			
			// Set our final output
			this.output = new ItemStack(id, outAmount, meta);
			System.out.println(nbt);
			if (!nbt.hasNoTags())
				this.output.setTagCompound(nbt);
			
			return true;
		}
		
		// Get the thing to place in the empty box
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID != ArtificeItems.itemBox.itemID)
			{
				this.thing = stack;
				break;
			}
		}
		
		// There's nothing to put in the box
		if (this.thing == null)
			return false;
		
		// Count how many things we need to put in the box
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
		
		// Set up the new tag
		NBTTagCompound tag = new NBTTagCompound();
		// Write the thing's meta
		tag.setInteger("meta", thing.getItemDamage());
		// Write the thing's id
		tag.setInteger("id", thing.itemID);
		// Does the thing have nbt data?
		if (thing.getTagCompound() != null && !thing.getTagCompound().hasNoTags())
			tag.setCompoundTag("nbt", thing.getTagCompound());
		
		// Create our final output
		this.output = new ItemStack(ArtificeItems.itemBox.itemID, 1, num);
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
