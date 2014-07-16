package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemMetaPair {
	private Item item;
	private int meta;
	
	public ItemMetaPair(Item item, int meta) {
		this.item = item;
		this.meta = meta;
	}
	
	public ItemMetaPair(Block block, int meta) {
		this(Item.getItemFromBlock(block), meta);
	}
	
	public Block getBlock() { return Block.getBlockFromItem(item); }
	public Item getItem() { return item; }
	public int getMetadata() { return meta; }
	public boolean isValidBlock() { return getBlock() != null; }
	public boolean isValid() { return item != null && (meta >= 0 && meta < 65536); }
	
	public int hashCode() {
		return (Item.getIdFromItem(item) * 16) | meta;
	}
	
	public boolean equals(Object o) {
		if(o == null || !(o instanceof ItemMetaPair)) return false;
		ItemMetaPair imp = (ItemMetaPair)o;
		if(this.item != null && imp.item == null || this.item == null && imp.item != null) return false;
		if(this.item == null && imp.item == null) return true;
		return this.meta == imp.meta && this.item.equals(imp.item);
	}
}
