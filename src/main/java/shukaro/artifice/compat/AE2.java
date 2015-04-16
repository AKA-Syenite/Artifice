package shukaro.artifice.compat;

import cpw.mods.fml.common.event.FMLInterModComms;
import shukaro.artifice.ArtificeCore;

public class AE2 implements ICompat
{
    @Override
    public String getModID()
    {
        return "appliedenergistics2";
    }

    @Override
    public void load()
    {
        try
        {
            FMLInterModComms.sendMessage(getModID(), "whitelist-spatial", "shukaro.artifice.tile.TileEntityAttuned");
            FMLInterModComms.sendMessage(getModID(), "whitelist-spatial", "shukaro.artifice.tile.TileEntityHeatingCoil");
            FMLInterModComms.sendMessage(getModID(), "whitelist-spatial", "shukaro.artifice.tile.TileEntityLogic");
            FMLInterModComms.sendMessage(getModID(), "whitelist-spatial", "shukaro.artifice.tile.TileEntityNuclearBattery");
            ArtificeCore.logger.info("AE2 Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize AE2 compat");
            ex.printStackTrace();
        }
    }
}
