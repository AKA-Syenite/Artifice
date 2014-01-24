package shukaro.artifice.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.gui.ArtificeCreativeTab;

import java.util.List;

public abstract class BlockArtifice extends Block
{
    protected BlockArtifice(int id, Material mat)
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
