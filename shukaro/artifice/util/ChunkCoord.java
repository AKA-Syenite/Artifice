package shukaro.artifice.util;

import java.io.Serializable;

public class ChunkCoord implements Serializable
{
	public final int chunkX;
	public final int chunkZ;
	
	public ChunkCoord(BlockCoord c)
	{
		this(c.x >> 4, c.z >> 4);
	}
	
	public ChunkCoord(int x, int z)
	{
		this.chunkX = x;
		this.chunkZ = z;
	}
	
	public boolean equals(ChunkCoord chunk)
	{
		return (chunk.chunkX == this.chunkX) && (chunk.chunkZ == this.chunkZ);
	}
	
	public int getCenterX()
	{
		return (this.chunkX << 4) + 8;
	}
	
	public int getCenterZ()
	{
		return (this.chunkZ << 4) + 8;
	}
	
	public String toString()
	{
		return "[" + this.chunkX + ", " + this.chunkZ + "]";
	}
}
