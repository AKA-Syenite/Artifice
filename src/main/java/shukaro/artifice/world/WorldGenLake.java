package shukaro.artifice.world;

import cofh.api.world.IFeatureGenerator;
import cofh.core.world.WorldHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.BiomeDictionary;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;

import java.util.Random;

public class WorldGenLake implements IFeatureGenerator
{
    private Block block;
    private int frequency;
    private WorldGenLakes lakeGen;

    public WorldGenLake(Block block, int frequency)
    {
        this.block = block;
        this.frequency = frequency;
        this.lakeGen = new WorldGenLakes(block);
    }

    @Override
    public String getFeatureName()
    {
        return ArtificeCore.modName + ": " + this.block.getUnlocalizedName() + " Lakes";
    }

    @Override
    public boolean generateFeature(Random rand, int chunkX, int chunkZ, World world, boolean newGen)
    {
        int dim = world.provider.dimensionId;
        if (ArtificeRegistry.getDimensionBlacklist().contains(Integer.valueOf(dim)))
            return false;
        String worldType = world.provider.terrainType.getWorldTypeName();
        if (ArtificeRegistry.getWorldTypeBlacklist().contains(worldType))
            return false;
        int total;
        if (frequency >= 100)
            total = (rand.nextInt(100) < Math.abs(frequency % (int) Math.pow(10, (int) Math.log10(frequency)))) ? (frequency / 100) + 1 : (frequency / 100);
        else
            total = rand.nextInt(100) < frequency ? 0 : 1;
        for (int j=0; j<total; j++)
        {
            if (rand.nextInt(100) > 10)
                return true;

            int x = chunkX * 16 + rand.nextInt(16);
            int z = chunkZ * 16 + rand.nextInt(16);
            int y = world.getHeightValue(x, z);

            BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
            if (block.equals(ArtificeBlocks.blockOil) && (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SAVANNA) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WASTELAND)))
                this.lakeGen.generate(world, rand, x, y, z);
        }
        return true;
    }
}
