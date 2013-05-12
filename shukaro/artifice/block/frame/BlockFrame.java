package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
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
}
