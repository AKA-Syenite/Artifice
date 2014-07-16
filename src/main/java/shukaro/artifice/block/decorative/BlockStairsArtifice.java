package shukaro.artifice.block.decorative;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;

import java.util.Locale;

public class BlockStairsArtifice extends BlockStairs
{
    public BlockStairsArtifice(Block block, int meta)
    {
        super(block, meta);
        setLightOpacity(0);
        setCreativeTab(ArtificeCreativeTab.main);
        String name = block.getUnlocalizedName() + ".stairs." + ArtificeCore.rocks[meta].toLowerCase(Locale.ENGLISH);
        name = name.replace("tile.", "");
        setBlockName(name);
    }
}
