package shukaro.artifice.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;

public class WorldGenRock
{
    private World world;
    private Random rand;
    private int startX;
    private int startY;
    private int startZ;
    private int id;
    private int meta;
    private int size;
    private List<Integer> replaced;
    private int maxHeight;
    
    private int maxX;
    private int minX;
    private int maxZ;
    private int minZ;
    
    private int maxY;
    private int minY;
    
    private BlockCoord coord = new BlockCoord();
    private Set<BlockCoord> list = new HashSet<BlockCoord>();
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int size, int maxHeight)
    {
        this(world, rand, x, y, z, id, 0, size, ArtificeRegistry.getStoneTypes(), maxHeight);
    }
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int meta, int size, List<Integer> replaced, int maxHeight)
    {
        this.world = world;
        this.rand = rand;
        this.startX = x;
        this.startY = y;
        this.startZ = z;
        this.id = id;
        this.meta = meta;
        this.size = size;
        this.replaced = replaced;
        this.maxHeight = maxHeight;
    }
    
    public boolean generate()
    {
        list.clear();
        
        int tries = rand.nextInt(6) + 4;
        
        for (int i=0; i<tries; i++)
        {
            coord.x = startX + rand.nextInt(8) - rand.nextInt(8);
            coord.y = startY + rand.nextInt(4) - rand.nextInt(4);
            coord.z = startZ + rand.nextInt(8) - rand.nextInt(8);
            
            this.maxX = coord.x + 16;
            this.minX = coord.x - 16;
            this.maxZ = coord.z + 16;
            this.minZ = coord.z - 16;
            
            this.maxY = coord.y + rand.nextInt(32);
            this.minY = coord.y - rand.nextInt(32);
            
            if (canGenHere(coord) && coord.y < maxHeight)
            {
                int numGenned = 0;
                list.add(coord);
                
                while (!list.isEmpty() && numGenned < size)
                {
                    Iterator<BlockCoord> it = list.iterator();
                    coord.set(it.next());
                    list.remove(coord);
                    world.setBlock(coord.x, coord.y, coord.z, id, meta, 2);
                    
                    for (BlockCoord c : coord.getAdjacent())
                    {
                        if (canGenHere(c))
                            list.add(c);
                    }

                    numGenned++;
                }
                
                return true;
            }
        }
        
        return true;
    }
    
    private boolean canGenHere(BlockCoord c)
    {
        if (c.x > this.maxX || c.x < this.minX)
            return false;
        
        if (c.y > this.maxY || c.y < this.minY)
            return false;
        
        if (c.z > this.maxZ || c.z < this.minZ)
            return false;
        
        if (Block.blocksList[world.getBlockId(c.x, c.y, c.z)] == null)
            return false;
        
        for (int i : replaced)
        {
            if (i == world.getBlockId(c.x, c.y, c.z))
                return true;
        }
        
        return false;
    }
}
