package shukaro.artifice.compat;

import cpw.mods.fml.common.event.FMLInterModComms;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.mfr.FactoryFlora;

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
            FactoryRegistry.sendMessage("registerHarvestable", new FactoryFlora());
            ArtificeCore.logger.info("MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
