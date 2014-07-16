package shukaro.artifice.block.frame;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
/*import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;
import powercrystals.minefactoryreloaded.api.rednet.RedNetConnectionType;*/
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;

import java.util.Random;

//@Optional.Interface(iface = "powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet", modid = "MineFactoryReloaded")
//public class BlockFrameDetector extends BlockFrame implements IConnectableRedNet
public class BlockFrameDetector extends BlockFrame
{
    private IIcon icon;

    public BlockFrameDetector()
    {
        super();
        setBlockName("artifice.detector");
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
        if (!neighbor.equals(this))
        {
            if (world.getBlockMetadata(x, y, z) == 0)
            {
                world.scheduleBlockUpdate(x, y, z, this, tickRate());
            }
        }
    }

    private void updateRedstone(World par1World, int par2, int par3, int par4)
    {
        par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this);
        par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this);
        par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this);
        par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this);
        par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this);
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
            par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate());
        }
        else if (par1World.getBlockMetadata(par2, par3, par4) == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
            par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate());
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
        }

        updateRedstone(par1World, par2, par3, par4);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icon = IconHandler.registerSingle(reg, "detector", "misc");
    }

    @Override
    public IIcon getIcon(int side, int meta)
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
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        return this.icon;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }

 /*   @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side)
    {
        return RedNetConnectionType.CableSingle;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public int[] getOutputValues(World world, int x, int y, int z, ForgeDirection side)
    {
        return new int[0];
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet)
    {
        return world.getBlockMetadata(x, y, z) == 1 ? 15 : 0;
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues)
    {
    }

    @Override
    @Optional.Method(modid = "MineFactoryReloaded")
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue)
    {
    } */
}
