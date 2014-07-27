package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockMetaPair
{
    private Block block;
    private int meta;

    public BlockMetaPair(Block block, int meta)
    {
        this.block = block;
        this.meta = meta;
    }

    public Block getBlock()
    {
        return block;
    }

    public Item getItem()
    {
        return Item.getItemFromBlock(block);
    }

    public int getMetadata()
    {
        return meta;
    }

    public boolean isValid()
    {
        return block != null && (meta >= 0 && meta < 65536);
    }

    public int hashCode()
    {
        return (Block.getIdFromBlock(block) * 16) | meta;
    }

    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof BlockMetaPair)) return false;
        BlockMetaPair imp = (BlockMetaPair) o;
        if (this.block == null && imp.block == null) return true;
        return this.meta == imp.meta && this.block.equals(imp.block);
    }

    public String toString()
    {
        return this.block.getUnlocalizedName() + "|" + this.meta;
    }
}
