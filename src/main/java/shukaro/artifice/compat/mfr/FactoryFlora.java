package shukaro.artifice.compat.mfr;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import shukaro.artifice.ArtificeBlocks;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class FactoryFlora implements IFactoryHarvestable
{
    @Override
    public int getPlantId()
    {
        return ArtificeBlocks.blockFlora;
    }

    @Override
    public HarvestType getHarvestType()
    {
        return HarvestType.Normal;
    }

    @Override
    public boolean breakBlock()
    {
        return true;
    }

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return ArtificeBlocks.blockFlora.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    }

    @Override
    public void preHarvest(World world, int x, int y, int z)
    {
    }

    @Override
    public void postHarvest(World world, int x, int y, int z)
    {
    }
}
