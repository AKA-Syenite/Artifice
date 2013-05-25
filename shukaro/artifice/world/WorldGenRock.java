package shukaro.artifice.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;

public class WorldGenRock
{
    private World world;
    private Random rand;
    private int startX;
    private int startY;
    private int startZ;
    private int id;
    private int meta;
    private int size;
    private List<Integer> replaced;
    
    private int maxX;
    private int minX;
    private int maxZ;
    private int minZ;
    
    private int maxY;
    private int minY;
    
    private BlockCoord coord = new BlockCoord();
    private Set<BlockCoord> list = new HashSet<BlockCoord>();
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int size)
    {
        this(world, rand, x, y, z, id, 0, size, ArtificeRegistry.getStoneTypes());
    }
    
    public WorldGenRock(World world, Random rand, int x, int y, int z, int id, int meta, int size, List<Integer> replaced)
    {
        this.world = world;
        this.rand = rand;
        this.startX = x;
        this.startY = y;
        this.startZ = z;
        this.id = id;
        this.meta = meta;
        this.size = size;
        this.replaced = replaced;
    }
    
    public boolean generate()
    {
        float f = rand.nextFloat() * (float) Math.PI;
        double d0 = startX + 8 + MathHelper.sin(f) * size / 8.0F;
        double d1 = startX + 8 - MathHelper.sin(f) * size / 8.0F;
        double d2 = startZ + 8 + MathHelper.cos(f) * size / 8.0F;
        double d3 = startZ + 8 - MathHelper.cos(f) * size / 8.0F;
        double d4 = startY + rand.nextInt(3) - 2;
        double d5 = startY + rand.nextInt(3) - 2;
        
        for (int l = 0; l <= size; ++l)
        {
            double d6 = d0 + (d1 - d0) * l / size;
            double d7 = d4 + (d5 - d4) * l / size;
            double d8 = d2 + (d3 - d2) * l / size;
            double d9 = rand.nextDouble() * size / 16.0D;
            double d10 = (MathHelper.sin(l * (float) Math.PI / size) + 1.0F) * d9 + 1.0D;
            double d11 = (MathHelper.sin(l * (float) Math.PI / size) + 1.0F) * d9 + 1.0D;
            int i1 = (int)(d6 - d10 / 2.0D);
            int j1 = (int)(d7 - d11 / 2.0D);
            int k1 = (int)(d8 - d10 / 2.0D);
            int l1 = (int)(d6 + d10 / 2.0D);
            int i2 = (int)(d7 + d11 / 2.0D);
            int j2 = (int)(d8 + d10 / 2.0D);

            for (int k2 = i1; k2 <= l1; ++k2)
            {
                double d12 = (k2 + 0.5D - d6) / (d10 / 2.0D);
                if (d12 * d12 < 1.0D)
                {
                    for (int l2 = j1; l2 <= i2; ++l2)
                    {
                        double d13 = (l2 + 0.5D - d7) / (d11 / 2.0D);
                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int i3 = k1; i3 <= j2; ++i3)
                            {
                                double d14 = (i3 + 0.5D - d8) / (d10 / 2.0D);
                                Block block = Block.blocksList[world.getBlockId(k2, l2, i3)];
                                if (block != null)
                                {
                                    if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && replaced.contains(block.blockID))
                                    {
                                        world.setBlock(k2, l2, i3, id, meta, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
