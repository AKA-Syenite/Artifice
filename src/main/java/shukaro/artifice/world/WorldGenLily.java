package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.util.ChunkCoord;

import java.util.Random;

public class WorldGenLily implements IFeatureGenerator
{
    private int frequency;

    public WorldGenLily()
    {
        this.frequency = ArtificeConfig.lotusFrequency;
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": Lilies";
    }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean newGen)
    {
        int dim = world.provider.dimensionId;
        if (ArtificeRegistry.getDimensionBlacklist().contains(Integer.valueOf(dim)))
            return false;
        String worldType = world.provider.terrainType.getWorldTypeName();
        if (ArtificeRegistry.getWorldTypeBlacklist().contains(worldType))
            return false;
        int total;
        if (frequency >= 100)
            total = (rand.nextInt(100) < Math.abs(frequency % (int) Math.pow(10, (int) Math.log10(frequency)))) ? (frequency / 100) + 1 : (frequency / 100);
        else
            total = rand.nextInt(100) < frequency ? 0 : 1;
        for (int j=0; j<total; j++)
        {
            int xMin = chunkX << 4;
            int zMin = chunkZ << 4;

            int startX = xMin + rand.nextInt(16);
            int startZ = zMin + rand.nextInt(16);

            BiomeGenBase biome = world.getBiomeGenForCoords(startX, startZ);
            if (!BiomeDictionary.isBiomeOfType(biome, Type.SWAMP))
                return false;

            int tries = rand.nextInt(16) + 8;

            ChunkCoord c = new ChunkCoord(chunkX, chunkZ);

            for (int l = 0; l < tries; l++)
            {
                int x = startX + rand.nextInt(8) - rand.nextInt(8);
                int z = startZ + rand.nextInt(8) - rand.nextInt(8);
                int y = world.getHeightValue(x, z);

                if (!c.contains(x, z))
                    continue;

                if (world.isAirBlock(x, y, z) && ArtificeBlocks.blockLotus.canPlaceBlockAt(world, x, y, z))
                {
                    if (world.getWorldTime() > 12000)
                        world.setBlock(x, y, z, ArtificeBlocks.blockLotus, 1, 0);
                    else
                        world.setBlock(x, y, z, ArtificeBlocks.blockLotus, 0, 0);
                }
            }
        }
        return true;
    }
}
