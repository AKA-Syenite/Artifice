package shukaro.artifice.block.decorative;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;

public class ItemBlockSlab extends ItemSlab
{
	public ItemBlockSlab(int id, BlockHalfSlab halfSlab, BlockHalfSlab doubleSlab, boolean isDouble)
	{
		super(id, halfSlab, doubleSlab, isDouble);
	}
}
