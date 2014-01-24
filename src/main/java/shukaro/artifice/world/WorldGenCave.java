package shukaro.artifice.world;

import net.minecraft.world.World;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WorldGenCave
{
    private World world;
    private Random rand;
    private int id;
    private int meta;
    private int maxHeight;
    private int size;
    private int adherence;
    private int frequency;
    private Set<Integer> replaced;
    private BlockCoord coord;
    private List<BlockCoord> lining;
    private List<BlockCoord> extra;

    public WorldGenCave(World world, Random rand, int id, int maxHeight, int frequency, int size, int adherence)
    {
        this(world, rand, id, 0, maxHeight, size, adherence, ArtificeRegistry.getStoneTypes());
    }

    public WorldGenCave(World world, Random rand, int id, int meta, int maxHeight, int size, int adherence, Set<Integer> replaced)
    {
        this.world = world;
        this.rand = rand;
        this.id = id;
        this.meta = meta;
        this.maxHeight = maxHeight;
        this.size = size;
        this.adherence = adherence;
        this.replaced = replaced;
        this.coord = new BlockCoord();
        this.lining = new ArrayList<BlockCoord>();
        this.extra = new ArrayList<BlockCoord>();
    }

    public boolean generate(int chunkX, int chunkZ)
    {
        lining.clear();
        extra.clear();

        int tries = rand.nextInt(16) + 4;
        for (int i = 0; i < tries; i++)
        {
            int x = (chunkX << 4) + rand.nextInt(16);
            int y = rand.nextInt(maxHeight);
            int z = (chunkZ << 4) + rand.nextInt(16);
            coord.set(x, y, z);

            if (canGenHere(world, coord, true))
            {
                world.setBlock(coord.x, coord.y, coord.z, id, meta, 0);
                for (int j = 0; j < size; j++)
                {
                    for (BlockCoord c : coord.getNearby())
                    {
                        if (canGenHere(world, c, true))
                            lining.add(c);
                        if (canGenHere(world, c, false))
                            extra.add(c);
                    }
                    if (lining.isEmpty() && extra.isEmpty())
                        return true;
                    else if (lining.isEmpty())
                    {
                        coord.set(extra.get(rand.nextInt(extra.size())));
                        extra.remove(coord);
                    }
                    else if (extra.isEmpty())
                    {
                        coord.set(lining.get(rand.nextInt(lining.size())));
                        lining.remove(coord);
                    }
                    else
                    {
                        if (rand.nextInt(100) < adherence)
                        {
                            coord.set(lining.get(rand.nextInt(lining.size())));
                            lining.remove(coord);
                        }
                        else
                        {
                            coord.set(extra.get(rand.nextInt(extra.size())));
                            extra.remove(coord);
                        }
                    }
                    world.setBlock(coord.x, coord.y, coord.z, id, meta, 0);
                }
                return true;
            }
        }
        return true;
    }

    private boolean canGenHere(World world, BlockCoord c, boolean strict)
    {
        if (c.getBlock(world) != null && replaced.contains(c.getBlockID(world)))
        {
            for (BlockCoord n : c.getNearby())
            {
                if (n.getBlock(world) == null || !n.getBlock(world).isOpaqueCube())
                    return true;
                if (!strict && n.getBlock(world) != null && n.getBlockID(world) == this.id)
                    return true;
            }
        }
        return false;
    }
}
