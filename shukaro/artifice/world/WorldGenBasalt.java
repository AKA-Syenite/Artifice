package shukaro.artifice.world;

import java.util.Random;
import java.util.Set;

import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.XSRandom;

public class WorldGenBasalt
{
    private World world;
    private Random rand;
    private int x;
    private int z;
    private int xMax;
    private int zMax;
    private int maxHeight;
    private int id;
    private Set<Integer> replaced;
    
    public WorldGenBasalt(World world, int id)
    {
    	this(world, id, ArtificeConfig.basaltHeight.getInt(), ArtificeRegistry.getStoneTypes());
    }
    
    public WorldGenBasalt(World world, int id, int maxHeight, Set<Integer> replaced)
    {
    	this.world = world;
    	this.id = id;
    	this.maxHeight = maxHeight;
    	this.replaced = replaced;
    	this.rand = new XSRandom(world.getSeed());
    }
    
    public boolean generate(int chunkX, int chunkZ)
    {
        this.x = chunkX * 16;
        this.z = chunkZ * 16;
        this.xMax = x + 16;
        this.zMax = z + 16;
        int r;
        
    	for (int i=x; i<xMax; i++)
    	{
    		for (int j=z; j<zMax; j++)
    		{
    			r = this.maxHeight + rand.nextInt(2) - rand.nextInt(2);
    			for (int t=0; t<r; t++)
    			{
    				if (replaced.contains(world.getBlockId(i, t, j)))
    					world.setBlock(i, t, j, this.id, 0, 0);
    			}
    		}
    	}
    	return true;
    }
}
