package shukaro.artifice.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.render.TextureHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockNiter extends BlockFalling
{
    private IIcon[] icons = new IIcon[2];

    public BlockNiter()
    {
        super(Material.sand);
        setStepSound(Block.soundTypeSand);
        setBlockName("artifice.niter");
        setCreativeTab(ArtificeCore.worldTab);
        setHardness(0.5f);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icons[0] = TextureHandler.registerIcon(reg, "oreNiter", "ores/niter");
        this.icons[1] = TextureHandler.registerIcon(reg, "blockNiter", "ores/niter");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta > 1)
            meta = 0;
        return this.icons[meta];
    }

    @Override
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(i, 1, 0));
        list.add(new ItemStack(i, 1, 1));
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
        {
            drops.add(new ItemStack(Blocks.sand));
            drops.add(new ItemStack(ArtificeItems.itemResource, quantityDropped(meta, fortune, world.rand), 1));
        }
        else
            drops.add(new ItemStack(ArtificeBlocks.blockNiter, 1, 1));
        return drops;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return meta == 0 ? (1 + rand.nextInt(3) + rand.nextInt(fortune + 1)) : 1;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return meta == 0 ? ArtificeItems.itemResource : Item.getItemFromBlock(this);
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) { return world.getBlockMetadata(x, y, z); }

    @Override
    public int damageDropped(int meta)
    {
        return 1;
    }
}
