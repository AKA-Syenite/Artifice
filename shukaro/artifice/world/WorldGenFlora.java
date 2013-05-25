package shukaro.artifice.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

public class WorldGenFlora
{
    private World world;
    private Random rand;
    private int startX;
    private int startY;
    private int startZ;
    
    private int x;
    private int y;
    private int z;
    private int type;
    
    public WorldGenFlora(World world, Random rand, int x, int y, int z)
    {
        this.world = world;
        this.rand = rand;
        this.startX = x;
        this.startY = y;
        this.startZ = z;
    }
    
    public boolean generate()
    {
        type = rand.nextInt(ArtificeCore.flora.length - 1);
        
        for (int l = 0; l < rand.nextInt(48) + 8; ++l)
        {
            x = startX + rand.nextInt(8) - rand.nextInt(8);
            y = startY + rand.nextInt(4) - rand.nextInt(4);
            z = startZ + rand.nextInt(8) - rand.nextInt(8);
            
            if ((world.isAirBlock(x, y, z) || (world.getBlockId(x, y, z) == Block.snow.blockID)) && (!world.provider.hasNoSky || y < 127) && ArtificeBlocks.blockFlora.canBlockStay(world, x, y, z))
            {
                if (type == 3)
                {
                    if (world.getWorldTime() > 12000)
                        world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, 4, 2);
                    else
                        world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, 3, 2);
                }
                else
                    world.setBlock(x, y, z, ArtificeBlocks.blockFlora.blockID, type, 2);
            }
        }
        
        return true;
    }
}
