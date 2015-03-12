package shukaro.artifice;

import cofh.core.world.WorldHandler;
import shukaro.artifice.world.*;

public class ArtificeWorld
{
    public static void initWorldGen()
    {
        if (ArtificeConfig.oilLakeGen)
            WorldHandler.addFeature(new WorldGenLake(ArtificeBlocks.blockOil, ArtificeConfig.oilLakeFrequency));
        if (ArtificeConfig.sulfurGen)
            WorldHandler.addFeature(new WorldGenSulfur(ArtificeBlocks.blockSulfur, 0, ArtificeConfig.sulfurSize, ArtificeConfig.sulfurFrequency));
        if (ArtificeConfig.niterGen)
            WorldHandler.addFeature(new WorldGenDesert(ArtificeBlocks.blockNiter, 0, ArtificeConfig.niterSize, ArtificeConfig.niterFrequency));
        if (ArtificeConfig.enderOreGen)
            WorldHandler.addFeature(new WorldGenCluster(ArtificeBlocks.blockEnderOre, 0, ArtificeConfig.enderOreMinHeight, ArtificeConfig.enderOreMaxHeight, ArtificeConfig.enderOreSize, ArtificeConfig.enderOreFrequency));
        for (int i=0; i<ArtificeConfig.rockNames.length; i++)
        {
            if (ArtificeConfig.rockLayersGen[i])
                WorldHandler.addFeature(new WorldGenLayer(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockLayersMinHeight[i], ArtificeConfig.rockLayersMaxHeight[i]));
        }
        for (int i=0; i<3; i++)
        {
            if (ArtificeConfig.rockClustersGen[i])
                WorldHandler.addFeature(new WorldGenCluster(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockClustersMinHeight[i], ArtificeConfig.rockClustersMaxHeight[i], ArtificeConfig.rockClustersSize[i], ArtificeConfig.rockClustersFrequency[i]));
            if (ArtificeConfig.rockCavesGen[i])
                WorldHandler.addFeature(new WorldGenCave(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockCavesMinHeight[i], ArtificeConfig.rockCavesMaxHeight[i], ArtificeConfig.rockCavesSize[i], ArtificeConfig.rockCavesFrequency[i]));
        }
        if (ArtificeConfig.floraWorldGen)
            WorldHandler.addFeature(new WorldGenFlowers());
        if (ArtificeConfig.lotusWorldGen)
            WorldHandler.addFeature(new WorldGenLily());
    }
}
