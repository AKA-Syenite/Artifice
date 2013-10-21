package shukaro.artifice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;

public class CoordObjectMap<T>
{
	private Map<Integer, Map<ChunkCoord, Map<BlockCoord, T>>> objectMap;
	
	public CoordObjectMap()
	{
		this.objectMap = new HashMap<Integer, Map<ChunkCoord, Map<BlockCoord, T>>>();
	}
	
	public void add(Integer worldID, ChunkCoord chunk, BlockCoord coord, T entry)
	{
		if (this.objectMap.get(worldID) == null)
			this.objectMap.put(worldID, new HashMap<ChunkCoord, Map<BlockCoord, T>>());
		if (this.objectMap.get(worldID).get(chunk) == null)
			this.objectMap.get(worldID).put(chunk, new HashMap<BlockCoord, T>());
		if (this.objectMap.get(worldID) != null && this.objectMap.get(worldID).get(chunk) != null)
			this.objectMap.get(worldID).get(chunk).put(coord, entry);
	}
	
	public void clear()
	{
		this.objectMap = new HashMap<Integer, Map<ChunkCoord, Map<BlockCoord, T>>>();
	}
	
	public boolean contains(Integer worldID)
	{
		return this.objectMap.get(worldID) != null;
	}
	
	public boolean contains(Integer worldID, ChunkCoord chunk)
	{
		if (this.objectMap.get(worldID) == null)
			return false;
		return this.objectMap.get(worldID).get(chunk) != null;
	}
	
	public boolean contains(Integer worldID, ChunkCoord chunk, BlockCoord coord)
	{
		if (this.objectMap.get(worldID) == null || this.objectMap.get(worldID).get(chunk) == null)
			return false;
		return this.objectMap.get(worldID).get(chunk).get(coord) != null;
	}
	
	public boolean contains(Integer worldID, ChunkCoord chunk, BlockCoord coord, T entry)
	{
		if (this.objectMap.get(worldID) == null || this.objectMap.get(worldID).get(chunk) == null || this.objectMap.get(worldID).get(chunk).get(coord) == null)
			return false;
		return this.objectMap.get(worldID).get(chunk).get(coord) != null;
	}
	
	public boolean contains(Integer worldID, BlockCoord coord)
	{
		if (this.objectMap.get(worldID) == null)
			return false;
		for (ChunkCoord chunk : this.objectMap.get(worldID).keySet())
		{
			if (this.objectMap.get(worldID).get(chunk).get(coord) != null)
				return true;
		}
		return false;
	}
	
	public boolean contains(Integer worldID, T entry)
	{
		if (this.objectMap.get(worldID) == null)
			return false;
		for (ChunkCoord chunk : this.objectMap.get(worldID).keySet())
		{
			for (BlockCoord coord : this.objectMap.get(worldID).get(chunk).keySet())
			{
				if (this.objectMap.get(worldID).get(chunk).get(coord) != null)
					return true;
			}
		}
		return false;
	}
	
	public boolean contains(T entry)
	{
		for (Integer worldID : this.objectMap.keySet())
		{
			for (ChunkCoord chunk : this.objectMap.get(worldID).keySet())
			{
				for (BlockCoord coord : this.objectMap.get(worldID).get(chunk).keySet())
				{
					if (this.objectMap.get(worldID).get(chunk).get(coord) != null)
						return true;
				}
			}
		}
		return false;
	}
	
	public Map<ChunkCoord, Map<BlockCoord, T>> get(Integer worldID)
	{
		if (this.contains(worldID))
			return this.objectMap.get(worldID);
		return null;
	}
	
	public Map<BlockCoord, T> get(Integer worldID, ChunkCoord chunk)
	{
		if (this.contains(worldID, chunk))
			return this.objectMap.get(worldID).get(chunk);
		return null;
	}
	
	public T get(Integer worldID, ChunkCoord chunk, BlockCoord coord)
	{
		if (this.contains(worldID, chunk, coord))
			return this.objectMap.get(worldID).get(chunk).get(coord);
		return null;
	}
	
	public void remove(Integer worldID)
	{
		if (this.contains(worldID))
			this.objectMap.remove(worldID);
	}
	
	public void remove(Integer worldID, ChunkCoord chunk)
	{
		if (this.contains(worldID, chunk))
			this.objectMap.get(worldID).remove(chunk);
	}
	
	public void remove(Integer worldID, ChunkCoord chunk, BlockCoord coord)
	{
		if (this.contains(worldID, chunk, coord))
			this.objectMap.get(worldID).get(chunk).remove(coord);
	}
}
