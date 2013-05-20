package shukaro.artifice.world;

import java.util.Random;

import shukaro.artifice.ArtificeBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLotus extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        for (int l = 0; l < 10; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            if (world.isAirBlock(i1, j1, k1) && ArtificeBlocks.blockLotus.canPlaceBlockAt(world, i1, j1, k1))
            {
                if (world.getWorldTime() > 12000)
                    world.setBlock(i1, j1, k1, ArtificeBlocks.blockLotus.blockID, 1, 2);
                else
                    world.setBlock(i1, j1, k1, ArtificeBlocks.blockLotus.blockID, 0, 2);
            }
        }
        return true;
    }
}
