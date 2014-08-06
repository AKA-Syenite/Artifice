package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import cofh.world.WorldHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.block.decorative.BlockOre;
import shukaro.artifice.util.NameMetaPair;
import shukaro.artifice.util.XSRandom;

import java.util.Random;
import java.util.Set;

public class WorldGenLayer implements IFeatureGenerator
{
    private Block block;
    private int meta;
    private Set<NameMetaPair> replaced;

    private int minHeight;
    private int maxHeight;

    public WorldGenLayer(Block block, int meta, int minHeight, int maxHeight)
    {
        this.block = block;
        this.meta = meta;
        this.replaced = ArtificeRegistry.getStoneTypes();
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": " + new ItemStack(block, 1, meta).getDisplayName() + " Layer";
    }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean newGen)
    {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int xMax = x + 16;
        int zMax = z + 16;
        int min;
        int max;

        for (int i = x; i < xMax; i++)
        {
            for (int j = z; j < zMax; j++)
            {
                min = this.minHeight + (rand.nextInt(3) - rand.nextInt(3));
                max = this.maxHeight + (rand.nextInt(3) - rand.nextInt(3));
                if (min < 0)
                    min = 0;
                if (max > 256)
                    max = 256;
                for (int t = min; t < max; t++)
                {
                    NameMetaPair pair = new NameMetaPair(world.getBlock(i, t, j), world.getBlockMetadata(i, t, j));
                    if (replaced.contains(pair) || ArtificeBlocks.oreSet.contains(pair))
                    {
                        NameMetaPair ore = new NameMetaPair(world.getBlock(i, t, j), world.getBlockMetadata(i, t, j));
                        if (replaced.contains(ore))
                            world.setBlock(i, t, j, block, meta, 0);
                        else if (ArtificeBlocks.oreSet.contains(ore))
                        {
                            NameMetaPair newOre = BlockOre.getOre(ore, block);
                            if (newOre != null)
                                world.setBlock(i, t, j, newOre.getBlock(), newOre.getMetadata(), 0);
                        }
                    }
                }
            }
        }
        return true;
    }
}
