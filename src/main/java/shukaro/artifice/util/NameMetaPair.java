package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class NameMetaPair
{
    private String name;
    private int meta;

    public NameMetaPair(Item item, int meta)
    {
        this(item.getUnlocalizedName(), meta);
    }

    public NameMetaPair(Block block, int meta)
    {
        this(block.getUnlocalizedName(), meta);
    }

    public NameMetaPair(String name, int meta)
    {
        this.name = name;
        this.meta = meta;
    }

    public Block getBlock()
    {
        return Block.getBlockFromName(name);
    }

    public Item getItem()
    {
        return (Item)Item.itemRegistry.getObject(name);
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
        return name.hashCode() | meta;
    }

    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof NameMetaPair)) return false;
        NameMetaPair imp = (NameMetaPair) o;
        return this.name.equals(imp.name) && this.meta == imp.meta;
    }

    public String toString()
    {
        return isValidItem() ? getItem().getUnlocalizedName() + "|" + this.meta : (isValidBlock() ? getBlock().getUnlocalizedName() + "|" + this.meta : this.name + "|" + this.meta);
    }
}
