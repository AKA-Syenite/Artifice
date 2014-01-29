package shukaro.artifice.compat.mfr;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;

public class MFRHandler
{
    public static void handle()
    {
        FactoryRegistry.registerHarvestable(new FactoryFlora());
    }
}
