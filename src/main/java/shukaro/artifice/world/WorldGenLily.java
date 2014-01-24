package shukaro.artifice.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.util.ChunkCoord;

import java.util.Random;

public class WorldGenLily
{
    private World world;
    private Random rand;

    public WorldGenLily(World world, Random rand)
    {
        this.world = world;
        this.rand = rand;
    }

    public boolean generate(int chunkX, int chunkZ)
    {
        int xMin = chunkX << 4;
        int zMin = chunkZ << 4;

        int startX = xMin + rand.nextInt(16);
        int startZ = zMin + rand.nextInt(16);

        if (rand.nextInt(10) > 4)
            return false;

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
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 1, 0);
                else
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 0, 0);
            }
        }

        return true;
    }
}
