package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.event.world.ChunkDataEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.util.ChunkCoord;

import java.util.ArrayList;
import java.util.List;

public class ArtificeEventHandler
{
    private List<Integer> dimBlacklist;

    @SubscribeEvent
    public void chunkSave(ChunkDataEvent.Save e)
    {
        e.getData().setString("Artifice", ArtificeConfig.regenKey.getString());
    }

    @SubscribeEvent
    public void chunkLoad(ChunkDataEvent.Load e)
    {
        if (!ArtificeConfig.enableWorldGen.getBoolean(true))
            return;

        int dim = e.world.provider.dimensionId;
        ChunkCoordIntPair c = e.getChunk().getChunkCoordIntPair();

        if (dimBlacklist == null)
            dimBlacklist = ArtificeRegistry.getDimensionBlacklist();

        if (dimBlacklist.contains(dim))
            return;

        if ((!e.getData().getString("Artifice").equals(ArtificeConfig.regenKey.getString())) && (ArtificeConfig.regenLotus.getBoolean(false) || ArtificeConfig.regenFlora.getBoolean(false) || ArtificeConfig.regenBasaltLayer.getBoolean(false) || ArtificeConfig.regenMarbleLayer.getBoolean(false) || ArtificeConfig.regenBasaltClusters.getBoolean(false) || ArtificeConfig.regenMarbleClusters.getBoolean(false) || ArtificeConfig.regenBasaltCaves.getBoolean(false) || ArtificeConfig.regenMarbleCaves.getBoolean(false)))
        {
            ArtificeCore.logger.warn("World gen was never run for chunk at " + e.getChunk().getChunkCoordIntPair() + ". Adding to queue for regeneration.");
            ArrayList chunks = (ArrayList) ArtificeTickHandler.chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                ArtificeTickHandler.chunksToGen.put(dim, new ArrayList());
                chunks = (ArrayList) ArtificeTickHandler.chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                ArtificeTickHandler.chunksToGen.put(dim, chunks);
            }
        }
    }
}
