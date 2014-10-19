package shukaro.artifice.block.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import shukaro.artifice.ArtificeCore;

public class BlockGlowSand extends BlockSand
{
    public BlockGlowSand()
    {
        super();
        this.setBlockName("artifice.glowsand");
        this.setCreativeTab(ArtificeCore.worldTab);
        this.setLightLevel(0.5f);
        this.setStepSound(Block.soundTypeSand);
        this.setHardness(0.5f);
    }
}
