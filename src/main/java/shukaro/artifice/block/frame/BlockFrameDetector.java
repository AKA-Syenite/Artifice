package shukaro.artifice.block.frame;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.render.TextureHandler;

import java.util.Random;

public class BlockFrameDetector extends BlockFrame
{
    private static final int tickRate = 2;

    private IIcon icon;

    public BlockFrameDetector()
    {
        super();
        setBlockName("artifice.detector");
        for (int i = 1; i <= 3; i++)
            this.validTiers.remove(ArtificeConfig.tiers[i]);
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icon = TextureHandler.registerIcon(reg, "detector", "misc");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        return this.icon;
    }

    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return blockAccess.getBlockMetadata(x, y, z) == 1 ? 15 : 0;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) { return true; }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!neighbor.equals(this))
        {
            if (world.getBlockMetadata(x, y, z) == 0)
                world.scheduleBlockUpdate(x, y, z, this, tickRate);
        }
    }

    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (world.getBlockMetadata(x, y, z) == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 1, 3);
            world.scheduleBlockUpdate(x, y, z, this, tickRate);
        }
        else if (world.getBlockMetadata(x, y, z) == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 3);
            world.scheduleBlockUpdate(x, y, z, this, tickRate);
        }
        else
            world.setBlockMetadataWithNotify(x, y, z, 0, 3);

        world.notifyBlocksOfNeighborChange(x, y, z, this);
    }
}
