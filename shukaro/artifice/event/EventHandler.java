package shukaro.artifice.event;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.ChunkDataEvent;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

public class EventHandler
{
    private List<Integer> dimBlacklist;
    private int dim;
    private ChunkCoordIntPair c;
    private ArrayList chunks;
    
    @ForgeSubscribe
    public void preTextureStitch(TextureStitchEvent.Pre e)
    {
    	ArtificeConfig.connectedTexturesRegistered = false;
    }
    
    @ForgeSubscribe
    public void chunkSave(ChunkDataEvent.Save e)
    {
        e.getData().setString("Artifice", ArtificeConfig.regenKey.getString());
    }
    
    @ForgeSubscribe
    public void chunkLoad(ChunkDataEvent.Load e)
    {
        if (!ArtificeConfig.enableWorldGen.getBoolean(true))
            return;
        
        dim = e.world.provider.dimensionId;
        c = e.getChunk().getChunkCoordIntPair();
        
        if (dimBlacklist == null)
            dimBlacklist = ArtificeRegistry.getDimensionBlacklist();
        
        if (dimBlacklist.contains(dim))
            return;
        
        if ((!e.getData().getString("Artifice").equals(ArtificeConfig.regenKey.getString())) && (ArtificeConfig.regenLotus.getBoolean(false) || ArtificeConfig.regenFlora.getBoolean(false) || ArtificeConfig.regenBasaltLayer.getBoolean(false) || ArtificeConfig.regenMarbleLayer.getBoolean(false) || ArtificeConfig.regenBasaltClusters.getBoolean(false) || ArtificeConfig.regenMarbleClusters.getBoolean(false) || ArtificeConfig.regenBasaltCaves.getBoolean(false) || ArtificeConfig.regenMarbleCaves.getBoolean(false)))
        {
            ArtificeCore.logger.log(Level.WARNING, "World gen was never run for chunk at " + e.getChunk().getChunkCoordIntPair() + ". Adding to queue for regeneration.");
            chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
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
