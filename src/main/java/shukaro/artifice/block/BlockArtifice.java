package shukaro.artifice.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.util.BlockCoord;

public abstract class BlockArtifice extends Block
{
    public BlockArtifice(int id, Material mat)
    {
        super(id, mat);
        setCreativeTab(ArtificeCreativeTab.main);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IconRegister reg);
    
    @Override
    @SideOnly(Side.CLIENT)
    public abstract Icon getIcon(int side, int meta);
    
    @Override
    @SideOnly(Side.CLIENT)
    public abstract Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side);
    
    @Override
    @SideOnly(Side.CLIENT)
    public abstract void getSubBlocks(int i, CreativeTabs tabs, List list);
    
    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
}
