package shukaro.artifice.event;

import java.util.ArrayList;
import java.util.logging.Level;

import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.ChunkCoord;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent;

public class EventHandler
{
	@ForgeSubscribe
	public void chunkSave(ChunkDataEvent.Save e)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean(ArtificeConfig.regenKey.getString(), true);
		e.getData().setTag("Artifice", tag);
	}
	
	@ForgeSubscribe
	public void chunkLoad(ChunkDataEvent.Load e)
	{
		int dim = e.world.provider.dimensionId;
		Chunk c = e.getChunk();
		
		if ((e.getData().getTag("Artifice") == null || !e.getData().getTag("Artifice").equals(ArtificeConfig.regenKey)) && (ArtificeConfig.regenFlora.getBoolean(false) || (ArtificeConfig.regenRock.getBoolean(false))))
		{
			ArtificeCore.logger.log(Level.WARNING, "World gen was never run for chunk at " + new ChunkCoord(c.xPosition, c.zPosition).toString() + ". Adding to queue for regeneration.");
			ArrayList chunks = (ArrayList)WorldTicker.chunksToGen.get(Integer.valueOf(dim));
			if (chunks == null)
			{
				WorldTicker.chunksToGen.put(Integer.valueOf(dim), new ArrayList());
				chunks = (ArrayList)WorldTicker.chunksToGen.get(Integer.valueOf(dim));
			}
			if (chunks != null)
			{
				chunks.add(new ChunkCoord(c.xPosition, c.zPosition));
				WorldTicker.chunksToGen.put(Integer.valueOf(dim), chunks);
			}
		}
	}
}
