package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsArtifice extends BlockStairs
{
	public BlockStairsArtifice(int id, Block block, int meta)
	{
		super(id, block, meta);
		setLightOpacity(0);
		setUnlocalizedName(block.getUnlocalizedName() + ".stairs." + ArtificeCore.rocks[meta]);
	}
}
