package shukaro.artifice.world;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.ChunkCoord;

import java.util.Random;

public class WorldGenFlowers
{
    private World world;
    private Random rand;

    public WorldGenFlowers(World world, Random rand)
    {
        this.world = world;
        this.rand = rand;
    }

    public boolean generate(int chunkX, int chunkZ)
    {
        int type = rand.nextInt(ArtificeCore.flora.length - 1);
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

            if ((world.isAirBlock(x, y, z) || (world.getBlockId(x, y, z) == Block.snow.blockID)) && ArtificeBlocks.blockFlora.canBlockStay(world, x, y, z))
            {
                if (rand.nextInt(10) > 5)
                    continue;

                if (type == 3)
                {
                    if (world.getWorldTime() > 12000)
                        world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, 4, 0);
                    else
                        world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, 3, 0);
                }
                else
                    world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, type, 0);
            }
        }

        return true;
    }
}
