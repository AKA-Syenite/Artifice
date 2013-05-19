package shukaro.artifice.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

public class WorldGenFlora extends WorldGenerator
{
    public WorldGenFlora()
    {
        super();
    }
    
    public WorldGenFlora(boolean notify)
    {
        super(notify);
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int type = rand.nextInt(ArtificeCore.flora.length);
        
        for (int l = 0; l < 64; ++l)
        {
            int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            if (world.isAirBlock(x1, y1, z1) && (!world.provider.hasNoSky || y1 < 127) && ArtificeBlocks.blockFlora.canBlockStay(world, x1, y1, z1))
            {
                world.setBlock(x1, y1, z1, ArtificeBlocks.blockFlora.blockID, type, 2);
            }
        }
        
        return true;
    }
}
