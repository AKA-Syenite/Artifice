package shukaro.artifice.world;

import java.util.Random;

import shukaro.artifice.ArtificeBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLotus
{
    private World world;
    private Random rand;
    private int startX;
    private int startY;
    private int startZ;
    
    private int x;
    private int y;
    private int z;
    
    public WorldGenLotus(World world, Random rand, int x, int y, int z)
    {
        this.world = world;
        this.rand = rand;
        this.startX = x;
        this.startY = y;
        this.startZ = z;
    }
    
    public boolean generate()
    {
        for (int l = 0; l < rand.nextInt(8) + 4; ++l)
        {
            x = startX + rand.nextInt(8) - rand.nextInt(8);
            y = startY + rand.nextInt(4) - rand.nextInt(4);
            z = startZ + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(x, y, z) && ArtificeBlocks.blockLotus.canPlaceBlockAt(world, x, y, z))
            {
                if (world.getWorldTime() > 12000)
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 1, 2);
                else
                    world.setBlock(x, y, z, ArtificeBlocks.blockLotus.blockID, 0, 2);
            }
        }
        return true;
    }
}
