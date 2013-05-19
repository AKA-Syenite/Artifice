package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;

public class WorldGenRock extends WorldGenerator
{
    private final int blockID;
    private final int blockMeta;
    private final int size;
    private final List<Integer> replacedIDs;
    private final int maxHeight;
    private int x1;
    private int y1;
    private int z1;
    private BlockCoord coord = new BlockCoord();
    private List<BlockCoord> primary = new ArrayList<BlockCoord>();
    private List<BlockCoord> secondary = new ArrayList<BlockCoord>();
    
    public WorldGenRock(int id, int size)
    {
        this(id, 0, size, ArtificeRegistry.getStoneTypes(), 255);
    }
    
    public WorldGenRock(int id, int size, int maxHeight)
    {
        this(id, 0, size, ArtificeRegistry.getStoneTypes(), maxHeight);
    }
    
    public WorldGenRock(int id, int meta, int size, List<Integer> replaced, int maxHeight)
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
        primary.clear();
        secondary.clear();
        
        for (int i = 0; i < 64; i++)
        {
            x1 = x + rand.nextInt(8) - rand.nextInt(8);
            y1 = y + rand.nextInt(4) - rand.nextInt(4);
            z1 = z + rand.nextInt(8) - rand.nextInt(8);
            coord.set(x1, y1, z1);
            
            if (canGenHere(world, coord, false) && coord.y < maxHeight)
            {
                world.setBlock(coord.x, coord.y, coord.z, blockID, blockMeta, 2);
                for (int j = 0; j < size; j++)
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
