package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.block.world.BlockOre;
import shukaro.artifice.event.ArtificeTickHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.NameMetaPair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class WorldGenSulfur implements IFeatureGenerator
{
    protected Block block;
    protected int meta;
    private int minHeight;
    private int maxHeight;
    private int size;
    private int frequency;
    protected Set<NameMetaPair> replaced;

    public WorldGenSulfur(Block block, int meta, int minHeight, int maxHeight, int size, int frequency)
    {
        this.block = block;
        this.meta = meta;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.size = size;
        this.frequency = frequency;
        this.replaced = ArtificeRegistry.getStoneTypes();
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": Sulfur";
    }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean newGen)
    {
        int total;
        if (frequency >= 100)
            total = (rand.nextInt(100) < Math.abs(frequency % (int) Math.pow(10, (int) Math.log10(frequency)))) ? (frequency / 100) + 1 : (frequency / 100);
        else
            total = rand.nextInt(100) < frequency ? 0 : 1;
        total += rand.nextInt(4);
        for (int j=0; j<total; j++)
        {
            boolean doGen = false;
            BlockCoord b = new BlockCoord();
            int dim = world.provider.dimensionId;

            int tries = rand.nextInt(16) + 10;
            for (int i = 0; i < tries; i++)
            {
                int x = (chunkX << 4) + rand.nextInt(16);
                int y = rand.nextInt((maxHeight - minHeight) + 1) + minHeight;
                int z = (chunkZ << 4) + rand.nextInt(16);
                b.set(x, y, z);
                if (canGenHere(world, b))
                {
                    doGen = true;
                    break;
                }
            }
            if (!doGen)
                return false;

            int num = rand.nextInt((int) (size * 1.5) - (int) (size * 0.5) + 1) + (int) (size * 0.5);
            SulferGen sulfur = new SulferGen(world, rand, b, num);
            sulfur.doGeneration(chunkX, chunkZ);
            if (!ArtificeTickHandler.toGen.containsKey(Integer.valueOf(dim)))
                ArtificeTickHandler.toGen.put(dim, new ArrayDeque<ISuspendableGen>());
            if (sulfur.getChunksToGen().size() > 0)
                ArtificeTickHandler.toGen.get(Integer.valueOf(dim)).add(sulfur);
        }
        return true;
    }

    protected boolean canGenHere(World world, BlockCoord b)
    {
        NameMetaPair pair = new NameMetaPair(b.getBlock(world), b.getMeta(world));
        if (!b.isAir(world) && replaced.contains(pair))
        {
            for (BlockCoord n : b.getNearby())
            {
                if (n.getBlock(world).equals(Blocks.lava) || n.getBlock(world).equals(Blocks.flowing_lava) || n.getBlock(world).equals(block))
                    return true;
            }
        }
        return false;
    }

    private class SulferGen implements ISuspendableGen
    {
        private World world;
        private Random rand;
        private BlockCoord coord;
        private TMap<ChunkCoord, ArrayList<BlockCoord>> toGen;
        private int size;

        public SulferGen(World world, Random rand, BlockCoord start, int size)
        {
            this.world = world;
            this.rand = rand;
            this.coord = start;
            this.toGen = new THashMap<ChunkCoord, ArrayList<BlockCoord>>();
            this.size = size;
        }

        @Override
        public Set<ChunkCoord> getChunksToGen()
        {
            return toGen.keySet();
        }

        @Override
        public boolean doGeneration(int chunkX, int chunkZ)
        {
            ChunkCoord chunk = new ChunkCoord(chunkX, chunkZ);
            int genned = 0;
            int threshold = size / 8;
            while (genned < size)
            {
                for (BlockCoord t : coord.getAdjacent())
                {
                    ChunkCoord c = new ChunkCoord(t);
                    if (!toGen.containsKey(c))
                        toGen.put(c, new ArrayList<BlockCoord>());
                    if (!chunk.contains(t) && !world.blockExists(t.x, t.y, t.z))
                        return true;
                    else if (canGenHere(world, t) && !toGen.get(chunk).contains(t))
                    {
                        if (toGen.get(chunk) == null)
                            toGen.put(chunk, new ArrayList<BlockCoord>());
                        toGen.get(chunk).add(t);
                    }
                }
                if (toGen.get(chunk).size() == 0)
                    break;
                coord.set(toGen.get(chunk).get(rand.nextInt(toGen.get(chunk).size())));
                toGen.get(chunk).remove(coord);

                world.setBlock(coord.x, coord.y, coord.z, block, meta, 0);

                genned++;
                while (toGen.get(chunk).size() > threshold && genned < size)
                {
                    if (toGen.get(chunk).size() == 0)
                        break;
                    coord.set(toGen.get(chunk).get(rand.nextInt(toGen.get(chunk).size())));
                    toGen.get(chunk).remove(coord);

                    world.setBlock(coord.x, coord.y, coord.z, block, meta, 0);

                    genned++;
                }
            }
            return true;
        }
    }
}
