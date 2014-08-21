package shukaro.artifice.compat;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.ValuedItem;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.mfr.DrinkHandlerToxic;
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
            FactoryRegistry.sendMessage("registerLiquidDrinkHandler", new ValuedItem("oil", new DrinkHandlerToxic(1)));
            FactoryRegistry.sendMessage("registerLiquidDrinkHandler", new ValuedItem("fuel", new DrinkHandlerToxic(2)));
            FactoryRegistry.sendMessage("registerLiquidDrinkHandler", new ValuedItem("creosote", new DrinkHandlerToxic(2)));
            FactoryRegistry.sendMessage("registerLiquidDrinkHandler", new ValuedItem("bitumen", new DrinkHandlerToxic(3)));
            ArtificeCore.logger.info("MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
