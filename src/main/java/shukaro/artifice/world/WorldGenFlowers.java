package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import net.minecraft.init.Blocks;
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

public class WorldGenFlowers implements IFeatureGenerator
{
    private int frequency;

    public WorldGenFlowers()
    {
        this.frequency = ArtificeConfig.floraFrequency;
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": Flowers";
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
            int type = rand.nextInt(ArtificeConfig.flora.length - 1);
            int xMin = chunkX << 4;
            int zMin = chunkZ << 4;

            int startX = xMin + rand.nextInt(16);
            int startZ = zMin + rand.nextInt(16);

            if (rand.nextInt(10) > 6)
                return false;

            BiomeGenBase biome = world.getBiomeGenForCoords(startX, startZ);
            boolean biomeRight = false;
            if (BiomeDictionary.isBiomeOfType(biome, Type.PLAINS) || BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE))
                biomeRight = true;
            else if ((BiomeDictionary.isBiomeOfType(biome, Type.FOREST) || BiomeDictionary.isBiomeOfType(biome, Type.HILLS)) && (type == 0 || type == 2))
                biomeRight = true;
            else if (BiomeDictionary.isBiomeOfType(biome, Type.MOUNTAIN) && (type == 0 || type == 3))
                biomeRight = true;
            else if (BiomeDictionary.isBiomeOfType(biome, Type.SWAMP) && (type == 1))
                biomeRight = true;
            if (!biomeRight)
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

                if ((world.isAirBlock(x, y, z) || (world.getBlock(x, y, z).equals(Blocks.snow_layer))) && ArtificeBlocks.blockFlora.canBlockStay(world, x, y, z))
                {
                    if (rand.nextInt(10) > 5)
                        continue;

                    if (type == 3)
                    {
                        if (world.getWorldTime() > 12000)
                            world.setBlock(x, y, z, ArtificeBlocks.blockFlora, 4, 0);
                        else
                            world.setBlock(x, y, z, ArtificeBlocks.blockFlora, 3, 0);
                    } else
                        world.setBlock(x, y, z, ArtificeBlocks.blockFlora, type, 0);
                }
            }
        }
        return true;
    }
}
