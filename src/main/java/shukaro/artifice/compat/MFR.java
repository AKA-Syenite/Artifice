package shukaro.artifice.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.mfr.MFRHandler;

import java.util.logging.Level;

public class MFR implements ICompat
{
	public String getModID() { return "MineFactoryReloaded"; }
    public void load()
    {
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
