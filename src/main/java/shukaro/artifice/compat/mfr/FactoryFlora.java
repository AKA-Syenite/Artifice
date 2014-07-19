package shukaro.artifice.compat.mfr;

import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import shukaro.artifice.ArtificeBlocks;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Optional.Interface(iface = "powercrystals.minefactoryreloaded.api.IFactoryHarvestable", modid = "MineFactoryReloaded")
public class FactoryFlora implements IFactoryHarvestable
{
    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public Block getPlant()
    {
        return ArtificeBlocks.blockFlora;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public HarvestType getHarvestType()
    {
        return HarvestType.Normal;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public boolean breakBlock()
    {
        return true;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return true;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return ArtificeBlocks.blockFlora.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public void preHarvest(World world, int x, int y, int z)
    {
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public void postHarvest(World world, int x, int y, int z)
    {
    }
}
