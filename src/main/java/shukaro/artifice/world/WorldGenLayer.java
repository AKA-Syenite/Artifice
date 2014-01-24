package shukaro.artifice.world;

import net.minecraft.world.World;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.XSRandom;

import java.util.Random;
import java.util.Set;

public class WorldGenLayer
{
    private World world;
    private Random rand;
    private int minHeight;
    private int maxHeight;
    private int id;
    private Set<Integer> replaced;

    public WorldGenLayer(World world, int id, int minHeight, int maxHeight)
    {
        this(world, id, minHeight, maxHeight, ArtificeRegistry.getStoneTypes());
    }

    public WorldGenLayer(World world, int id, int minHeight, int maxHeight, Set<Integer> replaced)
    {
        this.world = world;
        this.id = id;
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
                    if (replaced.contains(world.getBlockId(i, t, j)))
                        world.setBlock(i, t, j, this.id, 0, 0);
                }
            }
        }
        return true;
    }
}
