package shukaro.artifice.world;

import shukaro.artifice.util.ChunkCoord;

public interface IDelayedGen
{
    public ChunkCoord getChunk();

    public boolean doGeneration();
}
