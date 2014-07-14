package shukaro.artifice.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WorldGenCluster
{
    private World world;
    private Random rand;
    private Block block;
    private int maxHeight;
    private Set<Block> replaced;
    private BlockCoord c;
    private List<BlockCoord> blocks;

    public WorldGenCluster(World world, Random rand, Block block, int maxHeight)
    {
        this(world, rand, block, maxHeight, ArtificeRegistry.getStoneTypes());
    }

    public WorldGenCluster(World world, Random rand, Block block, int maxHeight, Set<Block> replaced)
    {
        this.world = world;
        this.rand = rand;
        this.block = block;
        this.maxHeight = maxHeight;
        this.replaced = replaced;
        this.blocks = new ArrayList<BlockCoord>();
    }

    public boolean generate(int size, int chunkX, int chunkZ)
    {
        ChunkCoord chunk = new ChunkCoord(chunkX, chunkZ);
        boolean doGen = false;
        int threshold = size / 8;

        int tries = rand.nextInt(16) + 4;
        for (int i = 0; i < tries; i++)
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
            world.setBlockToAir(c.x, c.y, c.z);
            genned++;
            while (blocks.size() > threshold && genned < size)
            {
                if (blocks.size() == 0)
                    break;
                c.set(blocks.get(rand.nextInt(blocks.size())));
                blocks.remove(c);
                world.setBlockToAir(c.x, c.y, c.z);
                genned++;
            }
        }

        return true;
    }

    public boolean canGenHere(World world, ChunkCoord c, BlockCoord b)
    {
        return c.contains(b) && b.getBlock(world) != null && replaced.contains(b.getBlock(world));
    }
}
