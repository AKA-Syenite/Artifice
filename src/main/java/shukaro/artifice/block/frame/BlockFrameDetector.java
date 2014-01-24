package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;

import java.util.Random;

public class BlockFrameDetector extends BlockFrame
{
    private Icon icon;

    public BlockFrameDetector(int id)
    {
        super(id);
        setUnlocalizedName("artifice.detector");
        for (int i = 1; i <= 3; i++)
            this.validTiers.remove(ArtificeCore.tiers[i]);
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return blockAccess.getBlockMetadata(x, y, z) == 1 ? 15 : 0;
    }

    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return isProvidingStrongPower(blockAccess, x, y, z, side);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }

    public int tickRate()
    {
        return 2;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        if (neighborID != this.blockID)
        {
            if (world.getBlockMetadata(x, y, z) == 0)
            {
                world.scheduleBlockUpdate(x, y, z, this.blockID, tickRate());
            }
        }
    }

    private void updateRedstone(World par1World, int par2, int par3, int par4)
    {
        par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
        par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
        par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
        par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate());
        }
        else if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, tickRate());
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
        }

        updateRedstone(par1World, par2, par3, par4);
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.icon = IconHandler.registerSingle(reg, "detector", "misc");
    }

    @Override
    public Icon getIcon(int side, int meta)
    {
        return this.icon;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        return this.icon;
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return true;
    }
}
