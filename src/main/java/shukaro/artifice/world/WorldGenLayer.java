package shukaro.artifice.world;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.util.XSRandom;

import java.util.Random;
import java.util.Set;

public class WorldGenLayer
{
    private World world;
    private Random rand;
    private int minHeight;
    private int maxHeight;
    private Block block;
    private Set<Block> replaced;

    public WorldGenLayer(World world, Block block, int minHeight, int maxHeight)
    {
        this(world, block, minHeight, maxHeight, ArtificeRegistry.getStoneTypes());
    }

    public WorldGenLayer(World world, Block block, int minHeight, int maxHeight, Set<Block> replaced)
    {
        this.world = world;
        this.block = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.replaced = replaced;
        this.rand = new XSRandom(world.getSeed());
    }

    public boolean generate(int chunkX, int chunkZ)
    {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int xMax = x + 16;
        int zMax = z + 16;
        int min;
        int max;

        for (int i = x; i < xMax; i++)
        {
            for (int j = z; j < zMax; j++)
            {
                min = this.minHeight + rand.nextInt(2) - rand.nextInt(2);
                max = this.maxHeight + rand.nextInt(2) - rand.nextInt(2);
                if (min < 0)
                    min = 0;
                if (max > 256)
                    max = 256;
                for (int t = min; t < max; t++)
                {
                    if (replaced.contains(world.getBlock(i, t, j)))
                        world.setBlock(i, t, j, this.block, 0, 0);
                }
            }
        }
        return true;
    }
}
