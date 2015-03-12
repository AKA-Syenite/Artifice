package shukaro.artifice.block.frame;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.BlockArtifice;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockFrame extends BlockArtifice
{
    protected List<String> validTiers = new ArrayList<String>();

    public BlockFrame()
    {
        super(Material.rock);
        this.validTiers.add(ArtificeConfig.tiers[0]);
        this.validTiers.add(ArtificeConfig.tiers[1]);
        this.validTiers.add(ArtificeConfig.tiers[2]);
        this.validTiers.add(ArtificeConfig.tiers[3]);
    }

    @Override
    public abstract boolean isOpaqueCube();

    @Override
    public abstract boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side);

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeConfig.tiers.length; j++)
        {
            if (this.validTiers.contains(ArtificeConfig.tiers[j]))
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
}
