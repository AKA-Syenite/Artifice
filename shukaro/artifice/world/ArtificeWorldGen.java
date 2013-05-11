package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class ArtificeWorldGen implements IWorldGenerator
{
	public static List<Integer> blacklistedDimensions;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		generateWorld(random, chunkX, chunkZ, world, true);
	}
	
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
	{
		if (blacklistedDimensions == null)
		{
			blacklistedDimensions = buildBlacklistedDimensions();
		}
		
		if (blacklistedDimensions.contains(world.provider.dimensionId))
		{
			return;
		}
		
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = random.nextInt(world.getActualHeight());
		
		if ((ArtificeCore.floraWorldGen.getBoolean(true) && newGen) || (ArtificeCore.regenFlora.getBoolean(true) && ArtificeCore.regenFlora.getBoolean(false)));
		{
			BiomeGenBase b = world.getBiomeGenForCoords(x, z);
			y = world.getHeightValue(x, z);
			
			if (!ArtificeRegistry.getFloraBlacklist().contains(b.biomeName))
			{
				if (random.nextInt(100) < 50)
				{
					new WorldGenFlora().generate(world, random, x, y, z);
				}
			}
		}
		
		if ((ArtificeCore.basaltWorldGen.getBoolean(true) && newGen) || (ArtificeCore.basaltWorldGen.getBoolean(true) && ArtificeCore.regenRock.getBoolean(false)));
		{
			int size = random.nextInt(ArtificeCore.basaltSize.getInt()) + random.nextInt(ArtificeCore.basaltSize.getInt() / 5);
			new WorldGenRock(ArtificeCore.blockBasalt.blockID, 0, size, new int[] {Block.stone.blockID}, ArtificeCore.basaltHeight.getInt()).generate(world, random, x, y, z);
		}
		
		if ((ArtificeCore.marbleWorldGen.getBoolean(true) && newGen) || (ArtificeCore.marbleWorldGen.getBoolean(true) && ArtificeCore.regenRock.getBoolean(false)));
		{
			int size = random.nextInt(ArtificeCore.marbleSize.getInt()) + random.nextInt(ArtificeCore.marbleSize.getInt() / 5);
			new WorldGenRock(ArtificeCore.blockMarble.blockID, 0, size, new int[] {Block.stone.blockID}, ArtificeCore.marbleHeight.getInt()).generate(world, random, x, y, z);
		}
		
		if (!newGen)
			world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
	}
	
	private List<Integer> buildBlacklistedDimensions()
	{
		String blacklist = ArtificeCore.dimensionBlacklist.getString();
		List<Integer> dims = new ArrayList<Integer>();
		
		if (blacklist == null)
		{
			return dims;
		}
		blacklist = blacklist.trim();
		
		for (String dim : blacklist.split(","))
		{
			try
			{
				Integer dimID = Integer.parseInt(dim);
				dims.add(dimID);
			}
			catch (Exception e)
			{
			}
		}
		
		return dims;
	}
}
