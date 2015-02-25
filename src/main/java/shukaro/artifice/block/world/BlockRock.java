package shukaro.artifice.block.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.render.TextureHandler;

import java.util.List;

public class BlockRock extends BlockArtifice
{
    private IIcon[] icons = new IIcon[ArtificeCore.rocks.length];
    private String name;
    private int color = 16777215;

    public BlockRock(String name)
    {
        super(Material.rock);
        setHardness(1.5F);
        setResistance(10.0F);
        setBlockName("artifice." + name);
        setCreativeTab(ArtificeCore.worldTab);
        this.name = name;
    }

    public BlockRock(String name, int color)
    {
        this(name);
        this.name = name.split("[.]")[0];
        this.color = color;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote)
        {
            ItemStack held = player.getHeldItem();
            if (held == null && player.isSneaking())
            {
                if (world.getBlockMetadata(x, y, z) == 3)
                    return world.setBlock(x, y, z, this, 4, 3);
                else if (world.getBlockMetadata(x, y, z) == 4)
                    return world.setBlock(x, y, z, this, 3, 3);
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        icons[0] = TextureHandler.registerIcon(reg, name, name);
        icons[1] = TextureHandler.registerIcon(reg, "cobblestone", name);
        icons[2] = TextureHandler.registerIcon(reg, "bricks", name);
        TextureHandler.registerConnectedTexture(reg, this, 3, "paver", name);
        icons[3] = TextureHandler.getConnectedTexture(this, 3, 0).icon;
        TextureHandler.registerConnectedTexture(reg, this, 4, "antipaver", name);
        icons[4] = TextureHandler.getConnectedTexture(this, 3, 0).icon;
        icons[5] = TextureHandler.registerIcon(reg, "chiseled", name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.rocks.length)
            meta = 0;
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        return this.getIcon(side, meta);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta == 0 ? 1 : meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.rocks.length; j++)
        {
            list.add(new ItemStack(i, 1, j));
        }
    }

    @Override
    public boolean isOpaqueCube() { return true; }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta)
    {
        return color;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess block, int x, int y, int z)
    {
        return color;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public int getRenderType() { return ArtificeConfig.ctmRenderID; }

    @Override
    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target)
    {
        return target == this || target == Blocks.stone;
    }
}
