package shukaro.artifice.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;

import java.util.List;

public abstract class BlockArtifice extends Block
{
    protected BlockArtifice(Material mat)
    {
        super(mat);
        setCreativeTab(ArtificeCore.mainTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerBlockIcons(IIconRegister reg);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract IIcon getIcon(int side, int meta);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract IIcon getIcon(IBlockAccess block, int x, int y, int z, int side);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void getSubBlocks(Item i, CreativeTabs tabs, List list);

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
