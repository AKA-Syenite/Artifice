package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import gnu.trove.set.hash.THashSet;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.event.ArtificeTickHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.MiscUtils;
import shukaro.artifice.util.NameMetaPair;

import java.util.*;

public class WorldGenVolcano implements IFeatureGenerator
{
    private int frequency;
    protected Set<NameMetaPair> replaced;

    public WorldGenVolcano()
    {
        this.frequency = ArtificeConfig.volcanoFrequency;
        this.replaced = new HashSet<NameMetaPair>();
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
            if (biome == null)
                continue;
            if (biome.topBlock != null)
                this.replaced.add(new NameMetaPair(biome.topBlock));
            if (biome.fillerBlock != null)
                this.replaced.add(new NameMetaPair(biome.fillerBlock));
        }
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": Volcanos";
    }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean newGen)
    {
        int dim = world.provider.dimensionId;
        if (ArtificeRegistry.getDimensionBlacklist().contains(Integer.valueOf(dim)))
            return false;
        String worldType = world.provider.terrainType.getWorldTypeName();
        if (ArtificeRegistry.getWorldTypeBlacklist().contains(worldType))
            return false;
        if (rand.nextInt(20) != 0)
            return false;
        int total;
        if (frequency >= 100)
            total = (rand.nextInt(100) < Math.abs(frequency % (int) Math.pow(10, (int) Math.log10(frequency)))) ? (frequency / 100) + 1 : (frequency / 100);
        else
            total = rand.nextInt(100) < frequency ? 0 : 1;
        for (int j=0; j<total; j++)
        {
            boolean doGen = false;
            BlockCoord b = new BlockCoord();

            int tries = rand.nextInt(8);
            for (int i = 0; i < tries; i++)
            {
                int x = (chunkX << 4) + rand.nextInt(16);
                int z = (chunkZ << 4) + rand.nextInt(16);
                int y = VolcanoHelper.getHighestSolidOrFillBlock(world, x, z);
                b.set(x, y, z);
                if (VolcanoHelper.canGenHere(world, b))
                {
                    doGen = true;
                    break;
                }
            }
            if (!doGen || rand.nextInt(10) != 0)
                return false;

            VolcanoGen volcano = new VolcanoGen(world, b, rand.nextBoolean());
            ArtificeTickHandler.queueGeneration(world.provider.dimensionId, volcano);
        }
        return true;
    }

    private class VolcanoGen implements IDelayedGen
    {
        private World world;
        private Random rand;
        private boolean active;
        private BlockCoord start;

        private static final int CHAMBER_SIZE = 32768;
        private static final int CHAMBER_DEPTH = 32;
        private static final int CHAMBER_THICKNESS = 2;
        private static final int LAYERS = 6;
        private static final int CINDER_PASSES = 3;
        private static final int CINDERS = 64;
        private static final int FLOW_PASSES = 2;
        private static final int FLOW_SIZE = 8;
        private static final int LAVA_BITS = 64;

        public VolcanoGen(World world, BlockCoord start, boolean active)
        {
            this.world = world;
            this.rand = world.rand;
            this.active = active;
            this.start = start;
        }

        @Override
        public ChunkCoord getChunk()
        {
            return new ChunkCoord(start);
        }

        @Override
        public boolean doGeneration()
        {
            BlockCoord blobCenter = start.copy().offset(ForgeDirection.DOWN, MiscUtils.randWithin50(rand, CHAMBER_DEPTH));
            int blobSize = MiscUtils.randWithin50(rand, CHAMBER_SIZE);
            genBlob(blobCenter, blobSize);
            encaseBlob(blobCenter, CHAMBER_THICKNESS);
            genTube(blobCenter);
            int numLayers = LAYERS;
            int flowSize = MiscUtils.randWithin50(rand, FLOW_SIZE);
            for (int i=0; i<numLayers; i++)
            {
                int cp = CINDER_PASSES;
                int fp = FLOW_PASSES;
                while (cp > 0 || fp > 0)
                {
                    boolean task = rand.nextBoolean();
                    if (task && cp > 0)
                    {
                        //genTephra(start, MiscUtils.randWithin50(rand, CINDERS));
                        cp--;
                    }
                    else if (!task && fp > 0)
                    {
                        genFlow(start, flowSize);
                        flowSize *= (1.0D + (0.65D*rand.nextDouble()+0.1D));
                        fp--;
                    }
                }
            }
            if (active)
                genLavaBits(start, MiscUtils.randWithin50(rand, LAVA_BITS));
            //else
                //coverFeature(c);
            return true;
        }

        // Generation functions
        /**
         * Generates a blob of lava or obsidian
         * @param center beginning point
         * @param size number of blocks to generate
         */
        private void genBlob(BlockCoord center, int size)
        {
            int filled = 0;
            BlockCoord b = null;
            ArrayList<BlockCoord> workplace = new ArrayList<BlockCoord>();
            THashSet<BlockCoord> done = new THashSet<BlockCoord>();
            workplace.add(center);
            while (filled < size && !workplace.isEmpty())
            {
                b = workplace.remove(rand.nextInt(workplace.size()));
                VolcanoHelper.setVolcanoFill(world, b, active);
                done.add(b);
                for (BlockCoord t : b.getAdjacent())
                {
                    if (!done.contains(t))
                        workplace.add(t);
                }
                filled++;
            }
        }

        /**
         * Encases a blob of lava or obsidian in basalt
         * @param origin beginning point
         * @param thickness average thickness for encasement
         */
        private void encaseBlob(BlockCoord origin, int thickness)
        {
            ArrayList<BlockCoord> workplace = new ArrayList<BlockCoord>();
            THashSet<BlockCoord> done = new THashSet<BlockCoord>();
            BlockCoord b;
            workplace.add(origin);
            while (!workplace.isEmpty())
            {
                b = workplace.remove(rand.nextInt(workplace.size()));
                done.add(b);
                for (BlockCoord t : b.getRadiusBlocks(1 + rand.nextInt(thickness - 1)))
                {
                    Block k = t.getBlock(world);
                    if (b.getDistance(t) <= thickness && !VolcanoHelper.isVolcanicFill(k) && !VolcanoHelper.isVolcanicBlock(k))
                        VolcanoHelper.setVolcanoBlock(world, t);
                    else if (VolcanoHelper.isVolcanicFill(k) && !done.contains(t) && !workplace.contains(t))
                        workplace.add(t);
                }
            }
        }

        /**
         * Generates a lava tube composed of basalt from start to the surface
         * @param start beginning point
         */
        private List<BlockCoord> genTube(BlockCoord start)
        {
            BlockCoord b = start.copy();
            int height = VolcanoHelper.getHighestSolidOrFillBlock(world, b.x, b.z)+1;
            int tubeSize = rand.nextInt(5)+1;
            List<BlockCoord> tubeBlocks = new ArrayList<BlockCoord>();
            tubeBlocks.add(b);
            while (tubeBlocks.size() < tubeSize)
            {
                int side = rand.nextInt(4)+2;
                if (!tubeBlocks.contains(b.getAdjacent()[side]))
                    tubeBlocks.add(b.getAdjacent()[side]);
            }
            while (b.y < height)
            {
                for (BlockCoord t : tubeBlocks)
                {
                    if (!VolcanoHelper.isVolcanicFill(t.getBlock(world)))
                        VolcanoHelper.setVolcanoFill(world, t, active);
                    for (BlockCoord n : t.getNearby())
                    {
                        if (n.y > t.y || VolcanoHelper.isVolcanicFill(n.getBlock(world)))
                            continue;
                        VolcanoHelper.setVolcanoBlock(world, n);
                    }
                    t.y++;
                }
            }
            return tubeBlocks;
        }

        /**
         * Generate a simulated lava flow
         * @param start beginning point
         */
        private void genFlow(BlockCoord start, int flowSize)
        {
            int flowed = 0;
            BlockCoord b;
            BlockCoord c;
            List<BlockCoord> open = VolcanoHelper.getOpenSides(world, start);
            while (open.size() == 0)
            {
                start.offset(ForgeDirection.UP);
                open = VolcanoHelper.getOpenSides(world, start);
                VolcanoHelper.setVolcanoFill(world, start, active);
                for (BlockCoord n : start.getAdjacent())
                {
                    if (n.y == start.y && VolcanoHelper.isVolcanicFill(n.copy().offset(ForgeDirection.DOWN).getBlock(world)))
                        VolcanoHelper.setVolcanoFill(world, n, active);
                }
            }
            while (flowed < flowSize && open.size() > 0)
            {
                b = open.get(0);
                c = b.copy();
                VolcanoHelper.setVolcanoBlock(world, b);
                while (VolcanoHelper.isReplaceable(world, c.offset(ForgeDirection.DOWN)))
                    VolcanoHelper.setVolcanoBlock(world, c);
                open = VolcanoHelper.getOpenSides(world, c);
                flowed++;
            }
        }

        /**
         * Simulate the piling of tephra (volcanic debris)
         * @param start point of ejection
         * @param amount number of blocks to pile
         */
        private void genTephra(BlockCoord start, int amount)
        {
            int dropped = 0;
            int radius = amount/3;
            BlockCoord b;
            List<BlockCoord> done = new ArrayList<BlockCoord>();
            while (dropped < amount)
            {
                b = VolcanoHelper.snapToHighest(world, VolcanoHelper.get2dINVERSEDistributedCoord(world, start, MiscUtils.randWithin50(rand, amount)));
                if (!done.contains(b) && !VolcanoHelper.isVolcanicFill(b.getBlock(world)))
                {
                    VolcanoHelper.setVolcanoTephra(world, b.offset(ForgeDirection.UP));
                    done.add(b);
                    dropped++;
                }
            }
        }

        /**
         * Generate random pieces of lava
         * @param start center point
         * @param amount max number to place
         */
        private void genLavaBits(BlockCoord start, int amount)
        {
            BlockCoord b = start.copy();
            while (VolcanoHelper.isVolcanicFill(b.getBlock(world)) || VolcanoHelper.isVolcanicBlock(b.getBlock(world)))
            {
                if (b.y > 255)
                    return;
                if (!(VolcanoHelper.isVolcanicFill(b.copy().offset(ForgeDirection.UP).getBlock(world)) || VolcanoHelper.isVolcanicBlock(b.copy().offset(ForgeDirection.UP).getBlock(world))))
                    break;
                else
                    b.offset(ForgeDirection.UP);
            }
            ArrayList<BlockCoord> toCover = new ArrayList<BlockCoord>();
            ArrayList<BlockCoord> done = new ArrayList<BlockCoord>();
            int num = 0;
            toCover.add(b);
            while (num < amount && !toCover.isEmpty())
            {
                b = toCover.remove(rand.nextInt(toCover.size()));
                if (world.isAirBlock(b.x, b.y+1, b.z) && rand.nextInt(16) == 0)
                {
                    world.setBlock(b.x, b.y, b.z, Blocks.lava, 0, 3);
                    num++;
                }
                done.add(b);
                for (BlockCoord t : b.getNearby())
                {
                    if (!toCover.contains(t) && !done.contains(t) && VolcanoHelper.isVolcanicBlock(t.getBlock(world)) && !t.copy().offset(ForgeDirection.UP).getBlock(world).isOpaqueCube())
                        toCover.add(t);
                }
            }
        }

        /**
         * Give an existing volcano formation a caldera
         * @param start beginning point
         * @param amount number of blocks to excise
         */
        private void genCaldera(BlockCoord start, int amount)
        {

        }

        /**
         * Replace top blocks of a volcanic feature with biome-appropriate substitutes, and decorates them with flora
         * @param start beginning point
         */
        private void coverFeature(BlockCoord start)
        {
            BlockCoord b = start.copy();
            BiomeGenBase biome;
            while ((VolcanoHelper.isVolcanicFill(b.getBlock(world)) || VolcanoHelper.isVolcanicBlock(b.getBlock(world))))
            {
                if (b.y > 255)
                    return;
                if (!(VolcanoHelper.isVolcanicFill(b.copy().offset(ForgeDirection.UP).getBlock(world)) || VolcanoHelper.isVolcanicBlock(b.copy().offset(ForgeDirection.UP).getBlock(world))))
                    break;
                else
                    b.offset(ForgeDirection.UP);
            }
            ArrayDeque<BlockCoord> toCover = new ArrayDeque<BlockCoord>();
            toCover.add(b);
            while (!toCover.isEmpty())
            {
                b = toCover.pop();
                biome = world.getBiomeGenForCoords(b.x, b.y);
                if (biome.topBlock != null && !b.copy().offset(ForgeDirection.UP).getBlock(world).isOpaqueCube() && b.copy().offset(ForgeDirection.UP).isAir(world))
                    b.setBlock(world, biome.topBlock, 0, false);
                else if (biome.fillerBlock != null && !b.copy().offset(ForgeDirection.UP).getBlock(world).isOpaqueCube())
                    b.setBlock(world, biome.fillerBlock, 0, false);
                if (world.isAirBlock(b.x, b.y+1, b.z))
                {
                    if (rand.nextInt(16) == 0)
                        biome.getRandomWorldGenForGrass(rand).generate(world, rand, b.x, b.y, b.z);
                    else if (rand.nextInt(16) == 0)
                        biome.plantFlower(world, rand, b.x, b.y+1, b.z);
                }
                for (BlockCoord t : b.getNearby())
                {
                    if (!toCover.contains(t) && VolcanoHelper.isVolcanicBlock(t.getBlock(world)) && !t.copy().offset(ForgeDirection.UP).getBlock(world).isOpaqueCube())
                        toCover.add(t);
                }
            }
        }
    }
}
