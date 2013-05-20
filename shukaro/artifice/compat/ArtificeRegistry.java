package shukaro.artifice.compat;

import java.util.ArrayList;
import java.util.List;

public abstract class ArtificeRegistry
{
    private static List<String> floraBlacklist = new ArrayList<String>();
    private static List<Integer> dimensionBlacklist = new ArrayList<Integer>();
    private static List<Integer> stoneTypes = new ArrayList<Integer>();
    private static List<String> worldTypeBlacklist = new ArrayList<String>();
    private static List<String> lotusWhitelist = new ArrayList<String>();
    
    public static void registerFloraBlacklist(String biome)
    {
        if (!floraBlacklist.contains(biome))
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
    
    public static void registerStoneType(int stoneID)
    {
        if (!stoneTypes.contains(stoneID))
            stoneTypes.add(stoneID);
    }
    
    public static List<Integer> getStoneTypes()
    {
        return stoneTypes;
    }
    
    public static void registerWorldTypeBlacklist(String type)
    {
        if (!worldTypeBlacklist.contains(type))
            worldTypeBlacklist.add(type);
    }
    
    public static List<String> getWorldTypeBlacklist()
    {
        return worldTypeBlacklist;
    }
    
    public static void registerLotusWhitelist(String biome)
    {
        if (!lotusWhitelist.contains(biome))
            lotusWhitelist.add(biome);
    }
    
    public static List<String> getLotusWhitelist()
    {
        return lotusWhitelist;
    }
}
