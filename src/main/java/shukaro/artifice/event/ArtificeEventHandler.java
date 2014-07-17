package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.net.PacketSender;
import shukaro.artifice.net.Packets;
import shukaro.artifice.net.PlayerTracking;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

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
            ArrayList chunks = (ArrayList)ArtificeTickHandler.chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                ArtificeTickHandler.chunksToGen.put(dim, new ArrayList());
                chunks = (ArrayList)ArtificeTickHandler.chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                ArtificeTickHandler.chunksToGen.put(dim, chunks);
            }
        }
    }
}
