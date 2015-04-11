package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NameMetaPair
{
    private String name;
    private int meta;

    public NameMetaPair(Item item, int meta)
    {
        this(Item.itemRegistry.getNameForObject(item), meta);
    }

    public NameMetaPair(Block block, int meta)
    {
        this(Block.blockRegistry.getNameForObject(block), meta);
    }

    public NameMetaPair(String name, int meta)
    {
        this.name = name;
        this.meta = meta;
    }

    public NameMetaPair(Item item)
    {
        this(item, -1);
    }

    public NameMetaPair(Block block)
    {
        this(block, -1);
    }

    public NameMetaPair(String name)
    {
        this(name, -1);
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public Block getBlock()
    {
        return (Block)Block.blockRegistry.getObject(name);
    }

    public Item getItem()
    {
        return (Item)Item.itemRegistry.getObject(name);
    }

    public int getMetadata()
    {
        return meta;
    }

    public void setMeta(int meta) { this.meta = meta; }

    public ItemStack getStack()
    {
        if (isValidBlock())
            return new ItemStack(getBlock(), 1, getMetadata());
        else if (isValidItem())
            return new ItemStack(getItem(), 1, getMetadata());
        else
            return null;
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
        NameMetaPair imp = (NameMetaPair)o;
        return this.name.equals(imp.name) && (this.meta == imp.meta || (this.meta == -1 || imp.meta == -1));
    }

    public boolean equals(NameMetaPair o)
    {
        return this.name.equals(o.name) && (this.meta == o.meta || (this.meta == -1 || o.meta == -1));
    }

    public String toString()
    {
        return this.name + "@" + this.meta;
    }
}
