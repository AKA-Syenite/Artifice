package shukaro.artifice.event;

import java.util.ArrayList;
import java.util.logging.Level;

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
		e.getData().setTag("Artifice", tag);
		tag.setBoolean(ArtificeCore.regenKey.getString(), true);
	}
	
	@ForgeSubscribe
	public void chunkLoad(ChunkDataEvent.Load e)
	{
		int dim = e.world.provider.dimensionId;
		Chunk c = e.getChunk();
		
		if ((!e.getData().getTag("Artifice").equals(ArtificeCore.regenKey)) && (ArtificeCore.regenFlora.getBoolean(false) || (ArtificeCore.regenRock.getBoolean(false))))
		{
			ArtificeCore.logger.log(Level.WARNING, "[Artifice] World gen was never run for chunk at " + e.getChunk().toString() + ". Adding to queue for regeneration.");
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
