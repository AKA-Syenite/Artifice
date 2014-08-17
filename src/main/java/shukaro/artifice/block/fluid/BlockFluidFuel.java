package shukaro.artifice.block.fluid;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.lib.util.helpers.ServerHelper;
import gnu.trove.set.hash.THashSet;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeFluids;
import shukaro.artifice.util.BlockCoord;

import java.util.Locale;

public class BlockFluidFuel extends BlockFluidCoFHBase
{
    public static THashSet<Block> ignitionSources = new THashSet<Block>();

    public BlockFluidFuel()
    {
        super(ArtificeCore.modID.toLowerCase(Locale.ENGLISH), ArtificeFluids.fluidFuel, Material.water, "fuel");
        setCreativeTab(ArtificeCore.mainTab);
        setQuantaPerBlock(8);
        setTickRate(20);
        setHardness(100F);
        setLightOpacity(4);
        setParticleColor(0.957F, 0.886F, 0.176F);

        ignitionSources.add(Blocks.fire);
        ignitionSources.add(Blocks.lava);
        ignitionSources.add(Blocks.flowing_lava);
        ignitionSources.add(Blocks.torch);
        ignitionSources.add(Blocks.lit_furnace);
    }

    public MapColor getMapColor(int meta) { return MapColor.yellowColor; }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (ServerHelper.isClientWorld(world))
            return;
        if (world.getTotalWorldTime() % 8 != 0)
            return;
        if (entity instanceof EntityLivingBase)
        {
            if (entity.isBurning() || entity instanceof EntityBlaze)
                doIgnition(world, x, y, z);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (ignitionSources.contains(neighbor))
            doIgnition(world, x, y, z);
    }

    private void doIgnition(World world, int x, int y, int z)
    {
        world.setBlockToAir(x, y, z);
        world.newExplosion(null, x, y, z, 4.0F, true, true);
        BlockCoord c = new BlockCoord(x, y, z);
        for (BlockCoord n : c.getAdjacent())
        {
            if (n.getBlock(world).equals(ArtificeBlocks.blockFuel))
                doIgnition(world, n.x, n.y, n.z);
        }
    }
}