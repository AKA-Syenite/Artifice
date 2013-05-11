package shukaro.artifice.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class ArtificeWorldGen implements IWorldGenerator
{
	private static List<Integer> blacklistedDimensions;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		generateWorld(random, chunkX, chunkZ, world, true);
	}
	
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, boolean newGen)
	{
		if (blacklistedDimensions == null)
			blacklistedDimensions = ArtificeRegistry.getDimensionBlacklist();
		
		if (blacklistedDimensions.contains(world.provider.dimensionId))
		{
			return;
		}
		
		int x = chunkX * 16 + random.nextInt(16);
		int z = chunkZ * 16 + random.nextInt(16);
		int y = random.nextInt(world.getActualHeight());
		
		if ((ArtificeConfig.floraWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.floraWorldGen.getBoolean(true) && ArtificeConfig.regenFlora.getBoolean(false)));
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
		
		if ((ArtificeConfig.basaltWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.basaltWorldGen.getBoolean(true) && ArtificeConfig.regenRock.getBoolean(false)));
		{
			int size = random.nextInt(ArtificeConfig.basaltSize.getInt()) + random.nextInt(ArtificeConfig.basaltSize.getInt() / 5);
			new WorldGenRock(ArtificeBlocks.blockBasalt.blockID, 0, size, new int[] {Block.stone.blockID}, ArtificeConfig.basaltHeight.getInt()).generate(world, random, x, y, z);
		}
		
		if ((ArtificeConfig.marbleWorldGen.getBoolean(true) && newGen) || (ArtificeConfig.marbleWorldGen.getBoolean(true) && ArtificeConfig.regenRock.getBoolean(false)));
		{
			int size = random.nextInt(ArtificeConfig.marbleSize.getInt()) + random.nextInt(ArtificeConfig.marbleSize.getInt() / 5);
			new WorldGenRock(ArtificeBlocks.blockMarble.blockID, 0, size, new int[] {Block.stone.blockID}, ArtificeConfig.marbleHeight.getInt()).generate(world, random, x, y, z);
		}
		
		if (!newGen)
			world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
	}
}
