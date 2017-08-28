package shukaro.artifice

import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.Logger
import shukaro.artifice.gui.ArtificeTab
import shukaro.artifice.sided.CommonProxy


@Mod(modid = ArtificeMain.MODID, modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", useMetadata = true)
object ArtificeMain {

    const val MODID = "artifice"

    lateinit var config: Configuration
    lateinit var logger: Logger

    @SidedProxy(clientSide = "shukaro.artifice.sided.ClientProxy", serverSide = "shukaro.artifice.sided.ServerProxy")
    lateinit var proxy: CommonProxy

    val artificeTab: ArtificeTab = ArtificeTab("artifice")

    @Mod.EventHandler
    fun serverStarting(event: FMLServerStartingEvent) {}

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {}

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {}

    @Mod.EventHandler
    fun imcRequest(event: FMLInterModComms) {}

}