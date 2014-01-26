package shukaro.artifice.event;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ArtificeEventHandler
{
    private List<Integer> dimBlacklist;

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void preTextureStitch(TextureStitchEvent.Pre e)
    {
        ArtificeConfig.connectedTexturesRegistered = false;
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void worldUnload(WorldEvent.Unload e)
    {
        if (ArtificeCore.textureCache.size() > 0)
            ArtificeCore.textureCache.clear();
    }

    @ForgeSubscribe
    @SideOnly(Side.CLIENT)
    public void chunkWatch(ChunkWatchEvent.Watch e)
    {
        ChunkCoord chunk = new ChunkCoord(e.chunk);
        for (ChunkCoord sector : ArtificeCore.textureCache.keySet())
        {
            if ((sector.chunkX + 1 == chunk.chunkX || sector.chunkX == chunk.chunkX || sector.chunkX - 1 == chunk.chunkX) && (sector.chunkZ + 1 == chunk.chunkZ || sector.chunkX == chunk.chunkX || sector.chunkZ - 1 == chunk.chunkZ))
            {
                for (BlockCoord c : ArtificeCore.textureCache.get(sector).keySet())
                {
                    if (c.x == (chunk.chunkX << 4) - 1 || c.x == (chunk.chunkX << 4) + 16)
                    {
                        if (c.z < (chunk.chunkZ << 4) + 16 && c.z > (chunk.chunkZ <<4) - 1)
                        {
                            ArtificeCore.textureCache.get(sector).remove(c);
                            e.player.worldObj.markBlockForRenderUpdate(c.x, c.y, c.z);
                        }
                    }
                    if (c.z == (chunk.chunkZ << 4) - 1 || c.z == (chunk.chunkZ << 4) + 16)
                    {
                        if (c.x > (chunk.chunkX << 4) - 1 && c.x < (chunk.chunkX <<4) + 16)
                        {
                            ArtificeCore.textureCache.get(sector).remove(c);
                            e.player.worldObj.markBlockForRenderUpdate(c.x, c.y, c.z);
                        }
                    }
                }
            }
        }
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

        int dim = e.world.provider.dimensionId;
        ChunkCoordIntPair c = e.getChunk().getChunkCoordIntPair();

        if (dimBlacklist == null)
            dimBlacklist = ArtificeRegistry.getDimensionBlacklist();

        if (dimBlacklist.contains(dim))
            return;

        if ((!e.getData().getString("Artifice").equals(ArtificeConfig.regenKey.getString())) && (ArtificeConfig.regenLotus.getBoolean(false) || ArtificeConfig.regenFlora.getBoolean(false) || ArtificeConfig.regenBasaltLayer.getBoolean(false) || ArtificeConfig.regenMarbleLayer.getBoolean(false) || ArtificeConfig.regenBasaltClusters.getBoolean(false) || ArtificeConfig.regenMarbleClusters.getBoolean(false) || ArtificeConfig.regenBasaltCaves.getBoolean(false) || ArtificeConfig.regenMarbleCaves.getBoolean(false)))
        {
            ArtificeCore.logger.log(Level.WARNING, "World gen was never run for chunk at " + e.getChunk().getChunkCoordIntPair() + ". Adding to queue for regeneration.");
            ArrayList chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                WorldTicker.chunksToGen.put(dim, new ArrayList());
                chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                WorldTicker.chunksToGen.put(dim, chunks);
            }
        }
    }
}
