package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;

import java.util.Locale;

public class BlockFrameBase extends BlockFrame
{
    private Icon[] icons = new Icon[ArtificeCore.tiers.length];

    public BlockFrameBase(int id)
    {
        super(id);
        setUnlocalizedName("artifice.frame");
        setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        for (int i = 0; i < ArtificeCore.tiers.length; i++)
            icons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(Locale.ENGLISH), "frame");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        return icons[block.getBlockMetadata(x, y, z)];
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return ArtificeConfig.frameRenderID;
    }
}
