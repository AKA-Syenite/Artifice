package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

public class ArtificeClientEventHandler
{
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
                        if (c.z < (chunk.chunkZ << 4) + 16 && c.z > (chunk.chunkZ << 4) - 1)
                        {
                            ArtificeCore.textureCache.get(sector).remove(c);
                            e.player.worldObj.markBlockRangeForRenderUpdate(c.x, c.y, c.z, c.x, c.y, c.z);
                        }
                    }
                    if (c.z == (chunk.chunkZ << 4) - 1 || c.z == (chunk.chunkZ << 4) + 16)
                    {
                        if (c.x > (chunk.chunkX << 4) - 1 && c.x < (chunk.chunkX << 4) + 16)
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
}
