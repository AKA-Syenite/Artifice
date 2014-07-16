package shukaro.artifice.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.util.ComparableItemStackNBT;

public class RecipeBox implements IRecipe
{
    private ItemStack output;

    @Override
    public boolean matches(InventoryCrafting craft, World world)
    {
        this.output = null;
        ItemStack box = null;
        ItemStack thing = null;

        // Is there a box in the grid?
        boolean hasBox = false;
        boolean isEmpty = true;
        for (int i = 0; i < craft.getSizeInventory(); i++)
        {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack != null && stack.getItem().equals(ArtificeItems.itemBox))
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
        // Is there more than 1 box in the grid?
        int c = 0;
        for (int i = 0; i < craft.getSizeInventory(); i++)
        {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack != null && stack.getItem().equals(ArtificeItems.itemBox))
                c++;
        }
        if (c != 1)
            return false;

        // The box isn't empty
        if (!isEmpty)
        {
            // Is there anything other than boxes in the grid?
            for (int i = 0; i < craft.getSizeInventory(); i++)
            {
                ItemStack stack = craft.getStackInSlot(i);
                if (stack != null && !stack.getItem().equals(ArtificeItems.itemBox))
                    return false;
            }
            // There's one full box in the grid, set it
            for (int i = 0; i < craft.getSizeInventory(); i++)
            {
                ItemStack stack = craft.getStackInSlot(i);
                if (stack != null && stack.getItem().equals(ArtificeItems.itemBox))
                    box = stack;
            }

            // Read the nbt data of the box
            NBTTagCompound tag = box.getTagCompound();
            // Get the thing's ID
            int id = tag.getInteger("id");
            // Get the thing's meta
            int meta = tag.getInteger("meta");
            // Get the number of things
            int size = box.getItemDamage();
            // Get the the nbt data of the thing
            NBTTagCompound nbt = tag.getCompoundTag("nbt");

            // This is our thing
            thing = new ItemStack(Item.getItemById(id), 1, meta);
            if (nbt != null)
                thing.setTagCompound(nbt);

            // Is it legal to output a stack of this size?
            int outAmount = size;
            if (outAmount > thing.getMaxStackSize())
                outAmount = thing.getMaxStackSize();

            // Set our final output
            this.output = new ItemStack(Item.getItemById(id), outAmount, meta);
            if (nbt != null && !nbt.hasNoTags())
                this.output.setTagCompound(nbt);

            return true;
        }

        // Get the thing to place in the empty box
        for (int i = 0; i < craft.getSizeInventory(); i++)
        {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack != null && !stack.getItem().equals(ArtificeItems.itemBox))
            {
                thing = stack;
                break;
            }
        }

        // There's nothing to put in the box
        if (thing == null)
            return false;

        // Count how many things we need to put in the box
        ComparableItemStackNBT contents = new ComparableItemStackNBT(thing);
        int num = 0;
        for (int i = 0; i < craft.getSizeInventory(); i++)
        {
            ItemStack stack = craft.getStackInSlot(i);
            if (stack != null)
            {
                ComparableItemStackNBT t = new ComparableItemStackNBT(stack);
                if (stack.getItem().equals(ArtificeItems.itemBox))
                    continue;
                else if (t.isStackEqual(contents))
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
        tag.setInteger("id", Item.getIdFromItem(thing.getItem()));
        // Does the thing have nbt data?
        if (thing.getTagCompound() != null && !thing.getTagCompound().hasNoTags())
            tag.setTag("nbt", thing.getTagCompound());

        // Create our final output
        this.output = new ItemStack(ArtificeItems.itemBox, 1, num);
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
