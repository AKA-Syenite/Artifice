package shukaro.artifice.event

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import shukaro.artifice.ArtificeVals


@Mod.EventBusSubscriber
object CommonHandler {

    @JvmStatic
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.getRegistry().registerAll(*ArtificeVals.BLOCKS)
    }

    @JvmStatic
    @SubscribeEvent
    fun registerItemBlocks(event: RegistryEvent.Register<Item>) {
        event.getRegistry().registerAll(*ArtificeVals.BLOCKS.map { ItemBlock(it).setRegistryName(it.registryName) }.toTypedArray())
    }

}
