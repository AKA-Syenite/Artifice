package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;

import java.util.Locale;

public class BlockFrameBase extends BlockFrame
{
    private IIcon[] icons = new IIcon[ArtificeCore.tiers.length];

    public BlockFrameBase()
    {
        super();
        setBlockName("artifice.frame");
        setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        for (int i = 0; i < ArtificeCore.tiers.length; i++)
            icons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(Locale.ENGLISH), "frame");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        return icons[block.getBlockMetadata(x, y, z)];
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return ArtificeConfig.frameRenderID;
    }
}
