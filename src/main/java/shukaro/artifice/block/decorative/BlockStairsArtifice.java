package shukaro.artifice.block.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeCore;

import java.util.Locale;

public class BlockStairsArtifice extends BlockStairs
{
    private int color = 16777215;

    public BlockStairsArtifice(Block block, int meta)
    {
        super(block, meta);
        setLightOpacity(0);
        setCreativeTab(ArtificeCore.worldTab);
        String name = block.getUnlocalizedName() + ".stairs." + ArtificeCore.rocks[meta].toLowerCase(Locale.ENGLISH);
        name = name.replace("tile.", "");
        setBlockName(name);
    }

    public BlockStairsArtifice(Block block, int meta, int color)
    {
        this(block, meta);
        this.color = color;
    }

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
}
