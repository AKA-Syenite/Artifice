package shukaro.artifice.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * This class allows for OreDictionary-compatible ItemStack comparisons.
 * <p/>
 * The intended purpose of this is for things such as Recipe Handlers or HashMaps of ItemStacks.
 *
 * @author King Lemming
 */
public class ComparableItemStack
{

    public Item item;
    public int metadata = -1;
    public int stackSize = -1;
    public int oreID = -1;

    public ComparableItemStack(ItemStack stack)
    {

        if (stack != null)
        {
            item = stack.getItem();
            metadata = stack.getItemDamage();
            stackSize = stack.stackSize;
            oreID = OreDictionary.getOreID(stack);
        }
    }

    public ComparableItemStack(Item item, int damage, int stackSize)
    {

        this.item = item;
        this.metadata = damage;
        this.stackSize = stackSize;
        this.oreID = OreDictionary.getOreID(this.toItemStack());
    }

    public ComparableItemStack(ComparableItemStack stack)
    {

        this.item = stack.getItem();
        this.metadata = stack.metadata;
        this.stackSize = stack.stackSize;
        this.oreID = stack.oreID;
    }

    public ComparableItemStack(String oreName)
    {

        if (!OreDictionary.getOres(oreName).isEmpty())
        {
            ItemStack ore = OreDictionary.getOres(oreName).get(0);
            item = ore.getItem();
            metadata = ore.getItemDamage();
            stackSize = 1;
            oreID = OreDictionary.getOreID(oreName);
        }
    }

    public boolean isItemEqual(ComparableItemStack other)
    {

        return other != null && (oreID != -1 && oreID == other.oreID || item.equals(other.getItem()) && metadata == other.metadata);
    }

    public boolean isStackEqual(ComparableItemStack other)
    {

        return isItemEqual(other) && stackSize == other.stackSize;
    }

    public boolean isStackValid()
    {
        return item != null;
    }

    @Deprecated
    public Item getItem()
    {
        return item;
    }

    public ItemStack toItemStack()
    {
        return isStackValid() ? new ItemStack(item, stackSize, metadata) : null;
    }

    @Override
    public ComparableItemStack clone()
    {
        return new ComparableItemStack(this);
    }

    @Override
    public int hashCode()
    {
        return oreID != -1 ? oreID : metadata | (Item.getIdFromItem(item) << 16);
    }

    @Override
    public boolean equals(Object o)
    {

        return o instanceof ComparableItemStack && isItemEqual((ComparableItemStack) o);
    }

}
