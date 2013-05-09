package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.BlockCoord;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRock extends WorldGenerator
{
	private int blockID;
	private int blockMeta;
	private int size;
	private int[] replacedIDs;
	private int maxHeight;
	
	public WorldGenRock(int id, int size)
	{
		this(id, 0, size, new int[] {Block.stone.blockID}, 255);
	}

	public WorldGenRock(int id, int meta, int size, int[] replaced, int maxHeight)
	{
		this.blockID = id;
		this.blockMeta = meta;
		this.size = size;
		this.replacedIDs = replaced;
		this.maxHeight = maxHeight;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		List<BlockCoord> primary = new ArrayList<BlockCoord>();
        List<BlockCoord> secondary = new ArrayList<BlockCoord>();
		
		for (int i=0; i<64; i++)
		{
			int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            BlockCoord coord = new BlockCoord(x1, y1, z1);
            
            if (canGenHere(world, coord, false) && coord.y < maxHeight)
            {
            	world.setBlock(coord.x, coord.y, coord.z, blockID, blockMeta, 2);
            	for (int j=0; j<size; j++)
            	{
            		for (BlockCoord c : coord.getAdjacent())
            		{
            			if (canGenHere(world, c, false))
            				primary.add(c);
            			if (canGenHere(world, c, true))
            				secondary.add(c);
            		}
            		if (primary.isEmpty() && secondary.isEmpty())
            			return true;
            		else if (primary.isEmpty())
            			coord.set(secondary.get(rand.nextInt(secondary.size())));
            		else if (secondary.isEmpty())
            			coord.set(primary.get(rand.nextInt(primary.size())));
            		else
            		{
            			if (rand.nextInt(100) < 60)
            				coord.set(primary.get(rand.nextInt(primary.size())));
            			else
            				coord.set(secondary.get(rand.nextInt(secondary.size())));
            		}
            		world.setBlock(coord.x, coord.y, coord.z, blockID, blockMeta, 2);
            	}
            	return true;
            }
		}
		
		return true;
	}
	
	private boolean canGenHere(World world, BlockCoord c, boolean includeSelf)
	{
		for (int id : replacedIDs)
		{
			if (id == world.getBlockId(c.x, c.y, c.z))
			{
				for (BlockCoord near : c.getNearby())
				{
					if (Block.blocksList[world.getBlockId(near.x, near.y, near.z)] == null || !Block.blocksList[world.getBlockId(near.x, near.y, near.z)].isOpaqueCube())
						return true;
					if (includeSelf && world.getBlockId(near.x, near.y, near.z) == this.blockID)
						return true;
				}
			}
		}
		return false;
	}
}
