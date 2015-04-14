package shukaro.artifice.block.frame;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.render.TextureHandler;

public class BlockFrameGlassWall extends BlockFrame
{
    boolean isDark;
    private IIcon[] icons = new IIcon[4];

    public BlockFrameGlassWall(boolean isDark)
    {
        super();
        this.isDark = isDark;
        if (isDark)
        {
            setBlockName("artifice.glasswalldark");
            setLightOpacity(15);
        }
        else
            setBlockName("artifice.glasswall");
        GameRegistry.registerBlock(this, ItemBlockFrame.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        TextureHandler.registerConnectedTexture(reg, this, 0, "basic", "glasswall");
        icons[0] = TextureHandler.getConnectedTexture(this, 0, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 1, "reinforced", "glasswall");
        icons[1] = TextureHandler.getConnectedTexture(this, 1, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 2, "industrial", "glasswall");
        icons[2] = TextureHandler.getConnectedTexture(this, 2, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 3, "advanced", "glasswall");
        icons[3] = TextureHandler.getConnectedTexture(this, 3, 0).icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= ArtificeConfig.tiers.length)
            meta = 0;
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
    {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta > ArtificeConfig.tiers.length)
            meta = 0;
        return this.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
        return i1 != this && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public int getRenderType() { return ArtificeConfig.ctmRenderID; }

    @Override
    public boolean isOpaqueCube() { return false; }

    @Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return this.getResistance(meta);
    }

    public float getResistance(int meta)
    {
        switch (meta)
        {
            case 0:
                return 15.0F;
            case 1:
                return 25.0F;
            case 2:
                return 45.0F;
            case 3:
                return 75.0F;
            default:
                return 10.0F;
        }
    }
}
