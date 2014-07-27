package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class IDMetaPair
{
    private int id;
    private int meta;

    public IDMetaPair(Item item, int meta)
    {
        this(Item.getIdFromItem(item), meta);
    }

    public IDMetaPair(Block block, int meta)
    {
        this(Block.getIdFromBlock(block), meta);
    }

    public IDMetaPair(int id, int meta)
    {
        this.id = id;
        this.meta = meta;
    }

    public Block getBlock()
    {
        return Block.getBlockById(id);
    }

    public Item getItem()
    {
        return Item.getItemById(id);
    }

    public int getMetadata()
    {
        return meta;
    }

    public boolean isValidBlock()
    {
        return getBlock() != null && (meta >= 0 && meta < 65536);
    }

    public boolean isValidItem() { return getItem() != null && (meta >= 0 && meta < 65536); }

    public int hashCode()
    {
        return (id * 16) | meta;
    }

    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof IDMetaPair)) return false;
        IDMetaPair imp = (IDMetaPair) o;
        return this.id == imp.id && this.meta == imp.meta;
    }

    public String toString()
    {
        return isValidItem() ? getItem().getUnlocalizedName() + "|" + this.meta : (isValidBlock() ? getBlock().getUnlocalizedName() + "|" + this.meta : this.id + "|" + this.meta);
    }
}
