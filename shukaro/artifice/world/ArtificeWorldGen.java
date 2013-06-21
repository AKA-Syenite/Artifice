package shukaro.artifice.world;

import java.util.List;
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
import cpw.mods.fml.common.IWorldGenerator;

public class ArtificeWorldGen implements IWorldGenerator
{
    private static List<Integer> blacklistedDimensions;
    private static List<String> blacklistedWorldTypes;
    private static int x;
    private static int y;
    private static int z;
    private static int size;
    private static BiomeGenBase b;
    private static int maxHeight;
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        generateWorld(random, chunkX, chunkZ, world, true);
    }
    
    public void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
    {    
        if (blacklistedDimensions == null)
            blacklistedDimensions = ArtificeRegistry.getDimensionBlacklist();
        
        if (blacklistedWorldTypes == null)
            blacklistedWorldTypes = ArtificeRegistry.getWorldTypeBlacklist();
        
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
        
        if ((ArtificeConfig.marbleLayerWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.marbleLayerWorldGen.getBoolean(true) && ArtificeConfig.regenMarbleLayer.getBoolean(false)))
        {
            new WorldGenLayer(world, ArtificeBlocks.blockMarble.blockID, ArtificeConfig.marbleLayerMinHeight.getInt(), ArtificeConfig.marbleLayerMaxHeight.getInt()).generate(chunkX, chunkZ);
        }
        
        if ((ArtificeConfig.basaltClusterWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.basaltClusterWorldGen.getBoolean(true) && ArtificeConfig.regenBasaltClusters.getBoolean(false)))
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
        
        if (!newGen)
            world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }
}
