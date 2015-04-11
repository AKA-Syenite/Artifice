package shukaro.artifice.block.frame;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.render.TextureHandler;

public class BlockFrameBlastWall extends BlockFrame
{
    private IIcon[] icons = new IIcon[4];

    public BlockFrameBlastWall()
    {
        super();
        setBlockName("artifice.reinforced");
        GameRegistry.registerBlock(this, ItemBlockFrame.class, this.getUnlocalizedName());
    }

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
                return 20.0F;
            case 1:
                return 30.0F;
            case 2:
                return 50.0F;
            case 3:
                return 80.0F;
            default:
                return 10.0F;
        }
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return super.getBlockHardness(world, x, y, z) + 5;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        TextureHandler.registerConnectedTexture(reg, this, 0, "basic", "blastwall");
        icons[0] = TextureHandler.getConnectedTexture(this, 0, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 1, "reinforced", "blastwall");
        icons[1] = TextureHandler.getConnectedTexture(this, 1, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 2, "industrial", "blastwall");
        icons[2] = TextureHandler.getConnectedTexture(this, 2, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 3, "advanced", "blastwall");
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
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public int getRenderType() { return ArtificeConfig.ctmRenderID; }
}
