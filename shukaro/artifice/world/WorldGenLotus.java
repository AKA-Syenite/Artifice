package shukaro.artifice.world;

import java.util.Random;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.XSRandom;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLotus
{
	private World world;
    private Random rand;
    private int x;
    private int y;
    private int z;
    private int startX;
    private int startZ;
    private int xMin;
    private int zMin;
    private int xMax;
    private int zMax;
    
    public WorldGenLotus(World world, Random rand)
    {
        this.world = world;
        this.rand = rand;
    }
    
    public boolean generate(int chunkX, int chunkZ)
    {
    	this.xMin = chunkX << 4;
    	this.zMin = chunkZ << 4;
    	this.xMax = xMin + 16;
    	this.zMax = zMax + 16;
    	
    	this.startX = xMin + rand.nextInt(16);
    	this.startZ = zMin + rand.nextInt(16);
    	
    	if (rand.nextInt(10) > 6)
    		return false;
    	
    	if (!ArtificeRegistry.getLotusWhitelist().contains(world.getBiomeGenForCoords(startX, startZ).biomeName))
    		return false;
        
    	int tries = rand.nextInt(16) + 8;
    	
    	ChunkCoord c = new ChunkCoord(chunkX, chunkZ);
    	
        for (int l=0; l < tries; l++)
        {
            x = startX + rand.nextInt(8) - rand.nextInt(8);
            z = startZ + rand.nextInt(8) - rand.nextInt(8);
            y = world.getHeightValue(x, z);
            
            if (!c.contains(x, z))
            	continue;
            
            if (world.isAirBlock(x, y, z) && ArtificeBlocks.blockLotus.canPlaceBlockAt(world, x, y, z))
            {
                if (world.getWorldTime() > 12000)
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 1, 0);
                else
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 0, 0);
            }
        }
        
        return true;
    }
}
