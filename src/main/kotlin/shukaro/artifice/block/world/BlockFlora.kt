package shukaro.artifice.block.world

import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation
import shukaro.artifice.ArtificeMain
import shukaro.artifice.block.BlockArtifice

class BlockFlora: BlockArtifice(Material.GRASS, "artifice.flora", ResourceLocation("flora"), ArtificeMain.artificeTab) {
    init {
        blockHardness = 0.0F
        tickRandomly = true
    }

}
