package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemMetaPair
{
    private Item item;
    private int id;
    private int meta;

    public ItemMetaPair(Item item, int meta)
    {
        this.item = item;
        this.meta = meta;
    }

    public ItemMetaPair(Block block, int meta)
    {
        this(Item.getItemFromBlock(block), meta);
        if (this.item == null)
            this.id = Block.getIdFromBlock(block);
    }

    public Block getBlock()
    {
        return Block.getBlockFromItem(item);
    }

    public Item getItem()
    {
        return item;
    }

    public int getMetadata()
    {
        return meta;
    }

    public boolean isValidBlock()
    {
        return getBlock() != null;
    }

    public boolean isValid()
    {
        return item != null && (meta >= 0 && meta < 65536);
    }

    public int hashCode()
    {
        return (Item.getIdFromItem(item) * 16) | meta;
    }

    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof ItemMetaPair)) return false;
        ItemMetaPair imp = (ItemMetaPair) o;
        if (this.item != null && imp.item == null || this.item == null && imp.item != null) return false;
        if (this.item == null && imp.item == null) return true;
        return this.meta == imp.meta && this.item.equals(imp.item) && this.id == imp.id;
    }

    public String toString()
    {
        if (this.item != null && this.item.getUnlocalizedName() != null)
            return this.item.getUnlocalizedName() + "|" + this.meta;
        else if (Block.getBlockById(this.id).getUnlocalizedName() != null)
            return Block.getBlockById(this.id).getUnlocalizedName() + "|" + this.meta;
        else
            return this.id + "|" + this.meta;
    }
}
