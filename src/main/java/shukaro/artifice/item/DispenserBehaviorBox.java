package shukaro.artifice.item;

import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityDispenser;

public class DispenserBehaviorBox extends BehaviorDefaultDispenseItem
{
    private final BehaviorDefaultDispenseItem defaultDispense = new BehaviorDefaultDispenseItem();

    @Override
    public ItemStack dispenseStack(IBlockSource dispenser, ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null)
        {
            // The thing in the boxes
            ItemStack thing = new ItemStack(tag.getInteger("id"), stack.getItemDamage(), tag.getInteger("meta"));
            if (!tag.getCompoundTag("nbt").hasNoTags())
                thing.setTagCompound(tag.getCompoundTag("nbt"));

            // Split the stack if stack sizes won't permit normal unboxing
            ItemStack extra = null;
            if (thing.stackSize > thing.getMaxStackSize() && stack.stackSize > 1)
            {
                extra = stack.copy();
                extra.stackSize = stack.stackSize - 1;
                stack.stackSize = 1;
                thing.stackSize = stack.getItemDamage();
            }
            // Crack open the box
            ItemStack drop = null;
            if (thing.stackSize > thing.getMaxStackSize())
            {
                drop = thing.copy();
                drop.stackSize = thing.getMaxStackSize();
                stack.setItemDamage(stack.getItemDamage() - drop.stackSize);
                if (stack.getItemDamage() < 1)
                    stack.stackSize--;
                if (drop != null)
                    defaultDispense.dispense(dispenser, drop);
            } else
            {
                drop = thing.copy();
                stack.stackSize--;
                if (drop != null)
                    defaultDispense.dispense(dispenser, drop);
            }

            TileEntityDispenser d = (TileEntityDispenser) dispenser.getBlockTileEntity();
            // Give the dispenser the leftover boxes
            // If they won't fit dispense them
            if (extra != null && d.addItem(extra) == -1)
                defaultDispense.dispense(dispenser, extra);
        }
        return stack;
    }

    @Override
    protected void playDispenseSound(IBlockSource dispenser)
    {
        dispenser.getWorld().playAuxSFX(1002, dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt(), 0);
    }
}
