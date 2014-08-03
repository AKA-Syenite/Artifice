package shukaro.artifice.world;

import shukaro.artifice.util.ChunkCoord;

import java.util.Set;

public interface ISuspendableGen
{
    public Set<ChunkCoord> getChunksToGen();

    public boolean doGeneration(int chunkX, int chunkZ);
}
