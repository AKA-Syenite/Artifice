package shukaro.artifice.block.fluid;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.lib.util.helpers.ServerHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeFluids;

import java.util.Locale;

public class BlockFluidCreosote extends BlockFluidCoFHBase
{
    public BlockFluidCreosote()
    {
        super(ArtificeCore.modID.toLowerCase(Locale.ENGLISH), ArtificeFluids.fluidCreosote, Material.water, "creosote");
        setCreativeTab(ArtificeCore.mainTab);
        setQuantaPerBlock(6);
        setTickRate(20);
        setHardness(100F);
        setLightOpacity(7);
        setParticleColor(0.176F, 0.302F, 0.0F);
    }

    public MapColor getMapColor(int meta) { return MapColor.greenColor; }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (ServerHelper.isClientWorld(world))
            return;
        if (world.getTotalWorldTime() % 8 != 0)
            return;
        if (entity instanceof EntityLivingBase)
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 6 * 20, 0));
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 300;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 25;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return true;
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
    {
        return true;
    }
}