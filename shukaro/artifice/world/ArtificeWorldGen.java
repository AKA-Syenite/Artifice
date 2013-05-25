package shukaro.artifice.world;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
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
    private static List<Integer> stoneTypes;
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
                x = chunkX * 16 + random.nextInt(16);
                z = chunkZ * 16 + random.nextInt(16);
                y = random.nextInt(world.getActualHeight());
                b = world.getBiomeGenForCoords(x, z);
                y = world.getHeightValue(x, z);
                if (!ArtificeRegistry.getFloraBlacklist().contains(b.biomeName))
                {
                    if (random.nextInt(100) < 50)
                    {
                        new WorldGenFlora(world, random, x, y, z).generate();
                    }
                }
            }
        }
        
        if ((ArtificeConfig.lotusWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.lotusWorldGen.getBoolean(true) && ArtificeConfig.regenLotus.getBoolean(false)))
        {
            for (int i=0; i < ArtificeConfig.lotusFrequency.getInt(); i++)
            {
                x = chunkX * 16 + random.nextInt(16);
                z = chunkZ * 16 + random.nextInt(16);
                y = random.nextInt(world.getActualHeight());
                b = world.getBiomeGenForCoords(x, z);
                y = world.getHeightValue(x, z);
                if (ArtificeRegistry.getLotusWhitelist().contains(b.biomeName))
                {
                    new WorldGenLotus(world, random, x, y, z).generate();
                }
            }
        }
        
        if ((ArtificeConfig.basaltWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.basaltWorldGen.getBoolean(true) && ArtificeConfig.regenBasalt.getBoolean(false)))
        {
            maxHeight = ArtificeConfig.basaltHeight.getInt();
            for (int i=0; i < ArtificeConfig.basaltFrequency.getInt(); i++)
            {
                x = chunkX * 16 + random.nextInt(16);
                z = chunkZ * 16 + random.nextInt(16);
                y = random.nextInt(ArtificeConfig.basaltHeight.getInt());
                size = random.nextInt(ArtificeConfig.basaltSize.getInt()) + (random.nextInt(ArtificeConfig.basaltSize.getInt()) / 2);
                new WorldGenRock(world, random, x, y, z, ArtificeBlocks.blockBasalt.blockID, size).generate();
            }
        }
        
        if ((ArtificeConfig.marbleWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.marbleWorldGen.getBoolean(true) && ArtificeConfig.regenMarble.getBoolean(false)))
        {
            maxHeight = ArtificeConfig.marbleHeight.getInt();
            for (int i=0; i < ArtificeConfig.marbleFrequency.getInt(); i++)
            {
                x = chunkX * 16 + random.nextInt(16);
                z = chunkZ * 16 + random.nextInt(16);
                y = random.nextInt(ArtificeConfig.marbleHeight.getInt());
                size = random.nextInt(ArtificeConfig.marbleSize.getInt()) + (random.nextInt(ArtificeConfig.marbleSize.getInt()) / 2);
                new WorldGenRock(world, random, x, y, z, ArtificeBlocks.blockMarble.blockID, size).generate();
            }
        }
        
        if (!newGen)
            world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
    }
}
