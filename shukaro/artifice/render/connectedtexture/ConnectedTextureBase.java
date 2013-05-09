package shukaro.artifice.render.connectedtexture;

import java.util.Arrays;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class ConnectedTextureBase
{
	private static ConnectedTextureBase[] renderers = new ConnectedTextureBase[Block.blocksList.length];
	public final int blockID;
	private BlockCoord coord = new BlockCoord();
	
	public ConnectedTextureBase(int blockID)
	{
		this.blockID = blockID;
		renderers[blockID] = this;
	}
	
	public static ConnectedTextureBase getRenderer(int blockID)
	{
		return renderers[blockID];
	}
	
	public int getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
	{
		int[][] sideSideMap = { { 2, 5, 3, 4 }, { 2, 5, 3, 4 }, { 1, 4, 0, 5 }, { 1, 5, 0, 4 }, { 1, 3, 0, 2 }, { 1, 2, 0, 3 } };
		
		int map = 0;
		for (int i=0;i<4;i++)
		{
			int side0 = sideSideMap[side][((i + 3) % 4)];
			int side1 = sideSideMap[side][i];
			if (!canConnectOnSide(block, this.coord.set(x, y, z), sideSideMap[side][i], side))
				map |= (7 << i * 2) % 256 | 7 >>> 8 - i * 2;
			else if ((!canConnectOnSide(block, this.coord.set(x, y, z).offset(side0), side1, side)) || (!canConnectOnSide(block, this.coord.set(x, y, z).offset(side1), side0, side)))
				map |= 1 << i * 2;
		}
		return getTextureFromMap(map);
	}
	
	public abstract int getTextureFromMap(int map);

	public abstract boolean canConnectOnSide(IBlockAccess block, BlockCoord coord, int side, int face);
}
