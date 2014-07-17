package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.ChunkCoord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ArtificeTickHandler
{
    public static HashMap chunksToGen = new HashMap();
    private int chunkCount = 0;

    @SubscribeEvent
    public void worldTicker(TickEvent.WorldTickEvent wte)
    {
        if (wte.phase != TickEvent.Phase.END) return;
        World world = wte.world;
        int dim = world.provider.dimensionId;

        ArrayList chunks = (ArrayList) chunksToGen.get(Integer.valueOf(dim));

        if ((chunks != null) && (chunks.size() > 0))
        {
            chunkCount++;
            ChunkCoord c = (ChunkCoord) chunks.get(0);
            long worldSeed = world.getSeed();
            Random rand = new Random(worldSeed);
            long xSeed = rand.nextLong() >> 3;
            long zSeed = rand.nextLong() >> 3;
            rand.setSeed(xSeed * c.chunkX + zSeed * c.chunkZ ^ worldSeed);
            ArtificeCore.worldGen.generateWorld(rand, c.chunkX, c.chunkZ, world, false);
            chunks.remove(0);
            chunksToGen.put(dim, chunks);
            ArtificeCore.logger.info("Regenerated " + chunkCount + " chunks. " + Math.max(0, chunks.size()) + " chunks left");
        }
    }
}
