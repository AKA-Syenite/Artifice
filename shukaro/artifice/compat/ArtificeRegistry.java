package shukaro.artifice.compat;

import java.util.ArrayList;
import java.util.List;

public abstract class ArtificeRegistry
{
    private static List<String> floraBlacklist = new ArrayList<String>();
    private static List<Integer> dimensionBlacklist = new ArrayList<Integer>();
    
    public static void registerFloraBlacklist(String biome)
    {
        floraBlacklist.add(biome);
    }
    
    public static List<String> getFloraBlacklist()
    {
        return floraBlacklist;
    }
    
    public static void registerDimensionBlacklist(int dimID)
    {
        if (!dimensionBlacklist.contains(dimID))
            dimensionBlacklist.add(dimID);
    }
    
    public static List<Integer> getDimensionBlacklist()
    {
        return dimensionBlacklist;
    }
}
