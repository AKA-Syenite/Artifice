package shukaro.artifice.block.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;

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
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }
}
