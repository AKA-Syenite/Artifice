package shukaro.artifice.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import cpw.mods.fml.common.IWorldGenerator;

public class ArtificeWorldGen implements IWorldGenerator
{
    private static List<Integer> blacklistedDimensions = ArtificeRegistry.getDimensionBlacklist();
    private static List<String> blacklistedWorldTypes = ArtificeRegistry.getWorldTypeBlacklist();
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        generateWorld(random, chunkX, chunkZ, world, true);
    }
    
    public void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {
        if (blacklistedDimensions.contains(world.provider.dimensionId))
            return;
        
        if (blacklistedWorldTypes.contains(world.getWorldInfo().getTerrainType().getWorldTypeName()))
            return;
        
        if ((ArtificeConfig.floraWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.floraWorldGen.getBoolean(true) && ArtificeConfig.regenFlora.getBoolean(false)))
        {
            for (int i=0; i < ArtificeConfig.floraFrequency.getInt(); i++)
            {
                new WorldGenFlowers(world, random).generate(chunkX, chunkZ);
            }
        }
        
        if ((ArtificeConfig.lotusWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.lotusWorldGen.getBoolean(true) && ArtificeConfig.regenLotus.getBoolean(false)))
        {
            for (int i=0; i < ArtificeConfig.lotusFrequency.getInt(); i++)
            {
                new WorldGenLily(world, random).generate(chunkX, chunkZ);
            }
        }
        
        if ((ArtificeConfig.basaltLayerWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.basaltLayerWorldGen.getBoolean(true) && ArtificeConfig.regenBasaltLayer.getBoolean(false)))
        {
            new WorldGenLayer(world, ArtificeBlocks.blockBasalt.blockID, ArtificeConfig.basaltLayerMinHeight.getInt(), ArtificeConfig.basaltLayerMaxHeight.getInt()).generate(chunkX, chunkZ);
        }
        
        if ((ArtificeConfig.marbleLayerWorldGen.getBoolean(false) && newGen) || (ArtificeConfig.marbleLayerWorldGen.getBoolean(false) && ArtificeConfig.regenMarbleLayer.getBoolean(false)))
        {
            new WorldGenLayer(world, ArtificeBlocks.blockMarble.blockID, ArtificeConfig.marbleLayerMinHeight.getInt(), ArtificeConfig.marbleLayerMaxHeight.getInt()).generate(chunkX, chunkZ);
        }
        
        if ((ArtificeConfig.basaltClusterWorldGen.getBoolean(false) && newGen) || (ArtificeConfig.basaltClusterWorldGen.getBoolean(false) && ArtificeConfig.regenBasaltClusters.getBoolean(false)))
        {
        	for (int i=0; i < ArtificeConfig.basaltClusterFrequency.getInt(); i++)
        	{
        		int bSize = ArtificeConfig.basaltClusterSize.getInt();
        		int size = bSize + (random.nextInt(bSize) / 2) - (random.nextInt(bSize) / 2);
        		new WorldGenCluster(world, random, ArtificeBlocks.blockBasalt.blockID, ArtificeConfig.basaltClusterHeight.getInt()).generate(size, chunkX, chunkZ);
        	}
        }
        
        if ((ArtificeConfig.marbleClusterWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.marbleClusterWorldGen.getBoolean(true) && ArtificeConfig.regenMarbleClusters.getBoolean(false)))
        {
        	for (int i=0; i < ArtificeConfig.marbleClusterFrequency.getInt(); i++)
        	{
        		int mSize = ArtificeConfig.marbleClusterSize.getInt();
	        	int size = mSize + (random.nextInt(mSize) / 2) - (random.nextInt(mSize) / 2);
	            new WorldGenCluster(world, random, ArtificeBlocks.blockMarble.blockID, ArtificeConfig.marbleClusterHeight.getInt()).generate(size, chunkX, chunkZ);
        	}
        }
        
        if ((ArtificeConfig.basaltCaveWorldGen.getBoolean(false) && newGen) || (ArtificeConfig.basaltCaveWorldGen.getBoolean(false) && ArtificeConfig.regenBasaltCaves.getBoolean(false)))
        {
        	int dSize = ArtificeConfig.basaltCaveSize.getInt();
    		int size = dSize + (random.nextInt(dSize) / 2) - (random.nextInt(dSize) / 2);
            new WorldGenCave(world, random, ArtificeBlocks.blockBasalt.blockID, ArtificeConfig.basaltCaveHeight.getInt(), ArtificeConfig.basaltCaveFrequency.getInt(), size, ArtificeConfig.basaltCaveAdherence.getInt()).generate(chunkX, chunkZ);
        }
        
        if ((ArtificeConfig.marbleCaveWorldGen.getBoolean(false) && newGen) || (ArtificeConfig.marbleCaveWorldGen.getBoolean(false) && ArtificeConfig.regenMarbleCaves.getBoolean(false)))
        {
        	int dSize = ArtificeConfig.basaltCaveSize.getInt();
    		int size = dSize + (random.nextInt(dSize) / 2) - (random.nextInt(dSize) / 2);
            new WorldGenCave(world, random, ArtificeBlocks.blockMarble.blockID, ArtificeConfig.marbleCaveHeight.getInt(), ArtificeConfig.marbleCaveFrequency.getInt(), size, ArtificeConfig.marbleCaveAdherence.getInt()).generate(chunkX, chunkZ);
        }
        
        if (!newGen)
            world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }
}
