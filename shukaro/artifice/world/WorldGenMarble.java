package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import net.minecraft.world.World;

public class WorldGenMarble
{
	private World world;
	private Random rand;
	private int id;
	private int maxHeight;
	private Set<Integer> replaced;
	private BlockCoord c;
	private List<BlockCoord> blocks;
	
	public WorldGenMarble(World world, Random rand, int id)
	{
		this(world, rand, id, ArtificeConfig.marbleHeight.getInt(), ArtificeRegistry.getStoneTypes());
	}
	
	public WorldGenMarble(World world, Random rand, int id, int maxHeight, Set<Integer> replaced)
	{
		this.world = world;
		this.rand = rand;
		this.id = id;
		this.maxHeight = maxHeight;
		this.replaced = replaced;
		this.blocks = new ArrayList<BlockCoord>();
	}
	
	public boolean generate(int size, int chunkX, int chunkZ)
	{
		ChunkCoord chunk = new ChunkCoord(chunkX, chunkZ);
		boolean doGen = false;
		
		int tries = rand.nextInt(16) + 4;
		for (int i=0; i<tries; i++)
		{
			int x = (chunkX << 4) + rand.nextInt(16);
			int y = rand.nextInt(maxHeight);
			int z = (chunkZ << 4) + rand.nextInt(16);
			c = new BlockCoord(x, y, z);
			if (canGenHere(world, chunk, c))
			{
				doGen = true;
				break;
			}
		}
		
		if (!doGen)
			return false;
		
		int genned = 0;
		while (genned < size)
		{
			for (BlockCoord t : c.getAdjacent())
			{
				if (canGenHere(world, chunk, t))
					blocks.add(t);
			}
			if (blocks.size() == 0)
				break;
			c.set(blocks.get(rand.nextInt(blocks.size())));
			blocks.remove(c);
			world.setBlock(c.x, c.y, c.z, id, 0, 0);
			genned++;
			while (blocks.size() > (size - genned))
			{
				if (blocks.size() == 0)
					break;
				c.set(blocks.get(rand.nextInt(blocks.size())));
				blocks.remove(c);
				world.setBlock(c.x, c.y, c.z, id, 0, 0);
				genned++;
			}
		}
		
		return true;
	}
	
	public boolean canGenHere(World world, ChunkCoord c, BlockCoord b)
	{
		return c.contains(b) && b.getBlock(world) != null && replaced.contains(b.getBlockID(world));
	}
}
