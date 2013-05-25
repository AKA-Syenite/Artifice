package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;

public class WorldGenRock
{
    private static int x1;
    private static int y1;
    private static int z1;
    private static BlockCoord coord = new BlockCoord();
    private static List<BlockCoord> primary = new ArrayList<BlockCoord>();
    private static List<BlockCoord> secondary = new ArrayList<BlockCoord>();
    
    public static boolean generate(World world, Random rand, int x, int y, int z, int id, int meta, int size, List<Integer> replaced, int maxHeight)
    {
        primary.clear();
        secondary.clear();
        
        for (int i = 0; i < rand.nextInt(6) + 4; i++)
        {
            x1 = x + rand.nextInt(8) - rand.nextInt(8);
            y1 = y + rand.nextInt(4) - rand.nextInt(4);
            z1 = z + rand.nextInt(8) - rand.nextInt(8);
            coord.set(x1, y1, z1);
            
            if (canGenHere(world, coord, false, replaced, id) && coord.y < maxHeight)
            {
                world.setBlock(coord.x, coord.y, coord.z, id, meta, 2);
                for (int j = 0; j < size; j++)
                {
                    for (BlockCoord c : coord.getAdjacent())
                    {
                        if (canGenHere(world, c, false, replaced, id))
                            primary.add(c);
                        if (canGenHere(world, c, true, replaced, id))
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
                    world.setBlock(coord.x, coord.y, coord.z, id, meta, 2);
                }
                return true;
            }
        }
        
        return true;
    }
    
    private static boolean canGenHere(World world, BlockCoord c, boolean includeSelf, List<Integer> replaced, int id)
    {
        for (int i : replaced)
        {
            if (i == world.getBlockId(c.x, c.y, c.z))
            {
                for (BlockCoord near : c.getNearby())
                {
                    if (Block.blocksList[world.getBlockId(near.x, near.y, near.z)] == null || !Block.blocksList[world.getBlockId(near.x, near.y, near.z)].isOpaqueCube())
                        return true;
                    if (includeSelf && world.getBlockId(near.x, near.y, near.z) == id)
                        return true;
                }
            }
        }
        return false;
    }
}
