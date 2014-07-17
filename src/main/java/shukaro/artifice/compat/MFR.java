package shukaro.artifice.compat;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.mfr.MFRHandler;

public class MFR implements ICompat
{
    public String getModID()
    {
        return "MineFactoryReloaded";
    }

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
