package shukaro.artifice.block.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.TextureHandler;

import java.util.Random;

public class BlockCharredLog extends BlockLog
{
    private IIcon bark;
    private IIcon core;

    public BlockCharredLog()
    {
        super();
        this.setBlockName("artifice.charredlog");
        this.setCreativeTab(ArtificeCore.worldTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.bark = TextureHandler.registerIcon(reg, "charredlogbark", "misc");
        this.core = TextureHandler.registerIcon(reg, "charredlogcore", "misc");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta)
    {
        return this.bark;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta)
    {
        return this.core;
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public int getExpDrop(IBlockAccess world, int meta, int fortune)
    {
        return Blocks.coal_ore.getExpDrop(world, meta, fortune);
    }

    @Override
    public int damageDropped(int meta)
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return Items.coal;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return Blocks.coal_ore.quantityDropped(meta, fortune, rand);
    }
}
