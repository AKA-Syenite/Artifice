package shukaro.artifice.block.fluid;

import cofh.core.fluid.BlockFluidCoFHBase;
import cofh.lib.util.helpers.ServerHelper;
import cpw.mods.fml.common.registry.GameRegistry;
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
import shukaro.artifice.block.ItemBlockArtifice;

import java.util.Locale;

public class BlockFluidBitumen extends BlockFluidCoFHBase
{
    public BlockFluidBitumen()
    {
        super(ArtificeCore.modID.toLowerCase(Locale.ENGLISH), ArtificeFluids.fluidBitumen, Material.water, "bitumen");
        setCreativeTab(ArtificeCore.mainTab);
        setQuantaPerBlock(2);
        setTickRate(50);
        setHardness(100F);
        setLightOpacity(7);
        setParticleColor(0.0F, 0.0F, 0.0F);
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    public MapColor getMapColor(int meta) { return MapColor.blackColor; }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (ServerHelper.isClientWorld(world))
            return;
        if (world.getTotalWorldTime() % 8 != 0)
            return;
        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 6 * 20, 5));
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.jump.id, 6 * 20, -4));
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 5;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 5;
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
