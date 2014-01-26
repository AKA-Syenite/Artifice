package shukaro.artifice.compat.mfr;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|MFR", name = "Artifice Compat: MFR", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:MineFactoryReloaded")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class MFR
{
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("MineFactoryReloaded"))
        {
            ArtificeCore.logger.log(Level.INFO, "MineFactoryReloaded not installed, skipping compat");
            return;
        }
        try
        {
            FactoryRegistry.registerHarvestable(new FactoryFlora());
            ArtificeCore.logger.log(Level.INFO, "MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.log(Level.WARNING, "Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
