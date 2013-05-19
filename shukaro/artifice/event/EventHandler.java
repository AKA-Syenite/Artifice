package shukaro.artifice.event;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.ChunkCoord;

public class EventHandler
{
    private List<Integer> dimBlacklist;
    
    @ForgeSubscribe
    public void chunkSave(ChunkDataEvent.Save e)
    {
        e.getData().setString("Artifice", ArtificeConfig.regenKey.getString());
    }
    
    @ForgeSubscribe
    public void chunkLoad(ChunkDataEvent.Load e)
    {
        int dim = e.world.provider.dimensionId;
        ChunkCoordIntPair c = e.getChunk().getChunkCoordIntPair();
        
        if (dimBlacklist == null)
            dimBlacklist = ArtificeRegistry.getDimensionBlacklist();
        
        if (dimBlacklist.contains(dim))
            return;
        
        if ((!e.getData().getString("Artifice").equals(ArtificeConfig.regenKey.getString())) && (ArtificeConfig.regenFlora.getBoolean(false) || (ArtificeConfig.regenRock.getBoolean(false))))
        {
            ArtificeCore.logger.log(Level.WARNING, "World gen was never run for chunk at " + e.getChunk().getChunkCoordIntPair() + ". Adding to queue for regeneration.");
            ArrayList chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                WorldTicker.chunksToGen.put(Integer.valueOf(dim), new ArrayList());
                chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                WorldTicker.chunksToGen.put(Integer.valueOf(dim), chunks);
            }
        }
    }
}
