package shukaro.artifice.block.decorative;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.render.EntityColoredFlameFX;
import shukaro.artifice.render.TextureHandler;

import java.util.Random;

public class BlockColoredTorch extends BlockTorch
{
    private IIcon icon;
    public IIcon particleIcon;
    private int color;

    public BlockColoredTorch(int color)
    {
        this.color = color;
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setLightLevel(1.0F);
        this.setBlockName("artifice.coloredtorch" + color);
        this.setStepSound(Block.soundTypeWood);
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icon = TextureHandler.registerIcon(reg, "coloredtorch" + this.color, "torches");
        this.particleIcon = TextureHandler.registerIcon(reg, "coloredparticle" + this.color, "torches");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int l = world.getBlockMetadata(x, y, z);
        double d0 = (double)((float)x + 0.5F);
        double d1 = (double)((float)y + 0.7F);
        double d2 = (double)((float)z + 0.5F);
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            world.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            EntityColoredFlameFX flame = new EntityColoredFlameFX(world, this.color, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(flame);
        }
        else if (l == 2)
        {
            world.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            EntityColoredFlameFX flame = new EntityColoredFlameFX(world, this.color, d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(flame);
        }
        else if (l == 3)
        {
            world.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            EntityColoredFlameFX flame = new EntityColoredFlameFX(world, this.color, d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(flame);
        }
        else if (l == 4)
        {
            world.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            EntityColoredFlameFX flame = new EntityColoredFlameFX(world, this.color, d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(flame);
        }
        else
        {
            world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
            EntityColoredFlameFX flame = new EntityColoredFlameFX(world, this.color, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(flame);
        }
    }
}
