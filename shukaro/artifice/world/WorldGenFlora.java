package shukaro.artifice.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

public class WorldGenFlora extends WorldGenerator
{
    private int x1;
    private int y1;
    private int z1;
    private int type;
    
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
        type = rand.nextInt(ArtificeCore.flora.length);
        
        for (int l = 0; l < 64; ++l)
        {
            x1 = x + rand.nextInt(8) - rand.nextInt(8);
            y1 = y + rand.nextInt(4) - rand.nextInt(4);
            z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            if ((world.isAirBlock(x1, y1, z1) || (world.getBlockId(x1, y1, z1) == Block.snow.blockID)) && (!world.provider.hasNoSky || y1 < 127) && ArtificeBlocks.blockFlora.canBlockStay(world, x1, y1, z1))
            {
                world.setBlock(x1, y1, z1, ArtificeBlocks.blockFlora.blockID, type, 2);
            }
        }
        
        return true;
    }
}
