package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
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
    
    private int maxX;
    private int minX;
    private int maxZ;
    private int minZ;
    
    private int maxY;
    private int minY;
    
    private BlockCoord coord = new BlockCoord();
    private List<BlockCoord> list = new ArrayList<BlockCoord>();
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int size)
    {
        this(world, rand, x, y, z, id, 0, size, ArtificeRegistry.getStoneTypes());
    }
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int meta, int size, List<Integer> replaced)
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
        this.coord.set(startX, startY, startZ);
        this.maxX = coord.x + 16;
        this.minX = coord.x - 16;
        this.maxY = coord.y + 16;
        this.minY = coord.y - 16;
        this.maxZ = coord.z + rand.nextInt(32);
        this.minZ = coord.z - rand.nextInt(32);
    }
    
    public boolean generate()
    {
        list.clear();
        
        if (canGenHere(coord))
        {
            int numGenned = 0;
            list.add(coord);
            
            while (!list.isEmpty() && numGenned < size)
            {
                coord.set(list.get(rand.nextInt(list.size())));
                world.setBlock(coord.x, coord.y, coord.z, id, meta, 2);
                list.remove(coord);
                numGenned++;
                
                for (BlockCoord c : coord.getAdjacent())
                {
                    if (canGenHere(c))
                        list.add(c);
                }
                
                if (list.size() > (numGenned/3))
                {
                    while (list.size() > (numGenned/6))
                    {
                        coord.set(list.get(rand.nextInt(list.size())));
                        world.setBlock(coord.x, coord.y, coord.z, id, meta, 2);
                        list.remove(coord);
                        numGenned++;
                    }
                }
            }
            list.clear();
            return true;
        }
        return false;
    }
    
    private boolean canGenHere(BlockCoord c)
    {
        if (Block.blocksList[world.getBlockId(c.x, c.y, c.z)] == null)
            return false;
        
        if (c.x > this.maxX || c.x < this.minX)
            return false;
        
        if (c.y > this.maxY || c.y < this.minY)
            return false;
        
        if (c.z > this.maxZ || c.z < this.minZ)
            return false;
        
        for (int i : replaced)
        {
            if (i == world.getBlockId(c.x, c.y, c.z))
                return true;
        }
        
        return false;
    }
}
