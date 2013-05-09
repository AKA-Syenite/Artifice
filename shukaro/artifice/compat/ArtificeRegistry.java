package shukaro.artifice.compat;

import java.util.ArrayList;
import java.util.List;

public abstract class ArtificeRegistry
{
	private static List<String> floraBlacklist = new ArrayList<String>();
	
	public static void registerFloraBlacklist(String biome)
	{
		floraBlacklist.add(biome);
	}
	
	public static List<String> getFloraBlacklist()
	{
		return floraBlacklist;
	}
}
