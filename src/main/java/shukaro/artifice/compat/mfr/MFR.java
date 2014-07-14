package shukaro.artifice.compat.mfr;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|MFR", name = "Artifice Compat: MFR", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:MineFactoryReloaded")
public class MFR
{
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("MineFactoryReloaded"))
        {
            ArtificeCore.logger.info("MineFactoryReloaded not installed, skipping compat");
            return;
        }
        try
        {
            MFRHandler.handle();
            ArtificeCore.logger.info("MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
