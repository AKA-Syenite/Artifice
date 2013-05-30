package shukaro.artifice.compat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.util.IdMetaPair;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public abstract class ArtificeRegistry
{
    private static List<String> floraBlacklist = new ArrayList<String>();
    private static List<Integer> dimensionBlacklist = new ArrayList<Integer>();
    private static List<Integer> stoneTypes = new ArrayList<Integer>();
    private static List<String> worldTypeBlacklist = new ArrayList<String>();
    private static List<String> lotusWhitelist = new ArrayList<String>();
    private static Map<IdMetaPair, ArrayList<ItemStack>> sledgeBlocks = new HashMap<IdMetaPair, ArrayList<ItemStack>>();
    
    public static void registerSledgeBlock(int id, int meta, ArrayList<ItemStack> drops)
    {
    	IdMetaPair pair = new IdMetaPair(id, meta);
    	if (!pair.isValidBlock())
    		ArtificeCore.logger.log(Level.WARNING, "Tried to register non-block id-meta pair in the sledgeBlock map: " + pair.toString());
    	else if (!(sledgeBlocks.get(pair) != null))
    		sledgeBlocks.put(pair, drops);
    }
    
    public static Map<IdMetaPair, ArrayList<ItemStack>> getSledgeBlocks()
    {
    	return sledgeBlocks;
    }
    
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
