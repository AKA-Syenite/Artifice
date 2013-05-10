package shukaro.artifice.block.decorative;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsArtifice extends BlockStairs
{
	public BlockStairsArtifice(int id, Block block, int meta)
	{
		super(id, block, meta);
		setLightOpacity(0);
		setCreativeTab(ArtificeCreativeTab.tab);
		setUnlocalizedName(block.getUnlocalizedName() + ".stairs." + ArtificeCore.rocks[meta]);
	}
}
