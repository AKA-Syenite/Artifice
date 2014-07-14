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
    @SideOnly(Side.CLIENT)
    public void keyTicker(ClientTickEvent cte)
    {
    	if(cte.phase != TickEvent.Phase.START) return;
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null)
            return;
        Integer playerID = mc.thePlayer.getEntityId();

        if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && !PlayerTracking.sneaks.contains(playerID))
        {
            PlayerTracking.sneaks.add(playerID);
            PacketSender.sendSneakEvent(playerID, true);
        }
        else if (!Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && PlayerTracking.sneaks.contains(playerID))
        {
            PlayerTracking.sneaks.remove(playerID);
            PacketSender.sendSneakEvent(playerID, false);
        }
    }
    
    public static HashMap chunksToGen = new HashMap();
    private int chunkCount = 0;
    
    @SubscribeEvent
    public void worldTicker(WorldTickEvent wte) {
    	if(wte.phase != TickEvent.Phase.END) return;
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
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preTextureStitch(TextureStitchEvent.Pre e)
    {
        ArtificeConfig.connectedTexturesRegistered = false;
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void worldUnload(WorldEvent.Unload e)
    {
        if (ArtificeCore.textureCache.size() > 0)
            ArtificeCore.textureCache.clear();
    }

    @SubscribeEvent
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
                            e.player.worldObj.markBlockRangeForRenderUpdate(c.x, c.y, c.z, c.x, c.y, c.z);
                        }
                    }
                    if (c.z == (chunk.chunkZ << 4) - 1 || c.z == (chunk.chunkZ << 4) + 16)
                    {
                        if (c.x > (chunk.chunkX << 4) - 1 && c.x < (chunk.chunkX <<4) + 16)
                        {
                            ArtificeCore.textureCache.get(sector).remove(c);
                            e.player.worldObj.markBlockRangeForRenderUpdate(c.x, c.y, c.z, c.x, c.y, c.z);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void chunkUnWatch(ChunkWatchEvent.UnWatch e)
    {
        ArtificeCore.textureCache.remove(new ChunkCoord(e.chunk));
    }

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
            ArrayList chunks = (ArrayList)chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                chunksToGen.put(dim, new ArrayList());
                chunks = (ArrayList)chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                chunksToGen.put(dim, chunks);
            }
        }
    }
}
