package shukaro.artifice.block.frame;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockFrame extends BlockArtifice implements ITileEntityProvider
{
    protected List<String> validTiers = new ArrayList<String>();
    
    public BlockFrame(int id)
    {
        super(id, Material.rock);
        this.validTiers.add(ArtificeCore.tiers[0]);
        this.validTiers.add(ArtificeCore.tiers[1]);
        this.validTiers.add(ArtificeCore.tiers[2]);
        this.validTiers.add(ArtificeCore.tiers[3]);
    }
    
    @Override
    public abstract boolean isOpaqueCube();
    
    @Override
    public abstract boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side);
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.tiers.length; j++)
        {
            if (this.validTiers.contains(ArtificeCore.tiers[j]))
                list.add(new ItemStack(i, 1, j));
        }
    }
    
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
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return true;
    }
    
    @Override
    public int getRenderType()
    {
        return ArtificeCore.frameRenderID;
    }
}
