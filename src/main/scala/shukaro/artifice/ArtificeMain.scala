package shukaro.artifice

import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event._
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import org.apache.logging.log4j.core.Logger
import shukaro.artifice.sided.CommonProxy


@Mod(modid = "Artifice", name = "Artifice", version = "2.0A", modLanguage = "Scala")
object ArtificeMain {

  @SidedProxy(clientSide = "shukaro.artifice.sided.ClientProxy", serverSide = "shukaro.artifice.sided.ServerProxy")
  var proxy: CommonProxy = _

  val logger = new Logger()

  @Mod.Instance
  var instance: ArtificeMain.type = _

  @EventHandler
  def serverStarting(event:FMLServerStartingEvent): Unit = {

  }

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {

  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {

  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {

  }

  @EventHandler
  def imcRequest(event: FMLInterModComms): Unit = {

  }

}