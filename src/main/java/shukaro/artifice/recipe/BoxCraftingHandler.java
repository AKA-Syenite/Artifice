package shukaro.artifice.recipe;

import cpw.mods.fml.common.ICraftingHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.net.PlayerTracking;

public class BoxCraftingHandler implements ICraftingHandler
{
    // Called when an item is crafted
    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craft)
    {
        for (int i = 0; i < craft.getSizeInventory(); i++)
        {
            // Is the item in the grid a box?
            // Is the output NOT a box?
            ItemStack slot = craft.getStackInSlot(i);
            if (slot != null && slot.itemID == ArtificeItems.itemBox.itemID && item.itemID != ArtificeItems.itemBox.itemID)
            {
                // Did the player shift-click the output? (This makes me a little bit uncomfortable, but eh)
                boolean shift = false;
                if (PlayerTracking.sneaks.contains(player.getEntityId()))
                    shift = true;

                // Number of boxes to unbox
                int num;
                if (shift)
                    num = slot.stackSize;
                else
                    num = 1;

                // The thing in the box
                NBTTagCompound tag = slot.getTagCompound();
                ItemStack thing = new ItemStack(tag.getInteger("id"), 1, tag.getInteger("meta"));
                if (!tag.getCompoundTag("nbt").hasNoTags())
                    thing.setTagCompound(tag.getCompoundTag("nbt"));

                // Number of things per box
                int size = slot.getItemDamage();

                // Number of things per craft
                int numPer = thing.getMaxStackSize() < size ? thing.getMaxStackSize() : size;

                // If we're only crafting one, we'll need to set up a new stack
                // The itemstack for the box that we'll take something out of
                ItemStack damaged = null;
                if (!shift)
                {
                    // If the thing's max stack size is less than the amount in the box
                    if (thing.getMaxStackSize() < size)
                    {
                        // We'll need this stack, take 1 of the boxes in the slot
                        damaged = slot.copy();
                        damaged.stackSize = 1;
                        // And remove a number of things from it equal to thing's max stack size (1 craft's worth)
                        damaged.setItemDamage(damaged.getItemDamage() - thing.getMaxStackSize());
                    }
                    // Remove 1 box from the grid
                    slot.stackSize--;
                }
                // If we're shift-crafting we use up every box in the grid
                else
                    slot.stackSize = 0;

                // Give the player the damaged box, if it exists
                if (damaged != null && damaged.stackSize > 0 && !player.inventory.addItemStackToInventory(damaged) && !player.worldObj.isRemote)
                    player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, damaged));

                // If the player shift clicked, they'll only get one craft's worth of result
                if (shift)
                {
                    // What's the total amount of items they should get?
                    // Total number of boxes, times the amount of things in each box, all minus one craft's worth of items
                    int numCrafted = (num * size) - numPer;
                    // If we need to give the player more than one stack's worth of items, we need to split it up
                    if (numCrafted > thing.getMaxStackSize())
                    {
                        int given = 0;
                        // While we haven't given them enough items
                        while (given < numCrafted)
                        {
                            ItemStack out = thing.copy();
                            // If the number we still need to give is greater than the max stack size, give them a full stack
                            // otherwise, give them them the total left to give
                            out.stackSize = (numCrafted - given) > thing.getMaxStackSize() ? thing.getMaxStackSize() : (numCrafted - given);
                            // We'll give the player whatever the output's stacksize is
                            given += out.stackSize;
                            if (out != null && out.stackSize > 0 && !player.inventory.addItemStackToInventory(out) && !player.worldObj.isRemote)
                                player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, out));
                        }
                    }
                    // Otherwise we just give them the extra :V
                    else
                    {
                        ItemStack out = thing.copy();
                        out.stackSize = numCrafted;
                        // If it won't fit in their inventory, drop it in the world
                        if (out != null && out.stackSize > 0 && !player.inventory.addItemStackToInventory(out) && !player.worldObj.isRemote)
                            player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, out));
                    }
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {

    }
}
