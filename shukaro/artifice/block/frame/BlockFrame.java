package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFrame extends BlockArtifice
{
    public BlockFrame(int id)
    {
        super(id, Material.rock);
        this.textureName = "frame";
    }
    
    public abstract Block getInnerBlock(int meta);
    
    public abstract int getInnerMeta(int meta);
    
    @Override
    public abstract boolean isOpaqueCube();
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        return (float) meta + 5;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y,
            int z, int side)
    {
        return true;
    }
    
    @Override
    public int getRenderType()
    {
        return ArtificeCore.frameRenderID;
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof IMultiblockPart)
        {
            ((IMultiblockPart)te).onBlockAdded(world, x, y, z);
        }
    }
    
    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TileEntityMultiblock();
    }
}
