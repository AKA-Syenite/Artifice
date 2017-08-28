package shukaro.artifice.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.ResourceLocation

open class BlockArtifice(var material: Material, var unlocalName: String, var regName: ResourceLocation, var tab: CreativeTabs): Block(material) {
    init {
        unlocalizedName = unlocalName
        registryName = regName
        setCreativeTab(tab)
    }
}
