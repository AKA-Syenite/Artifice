package shukaro.artifice.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

public class Buildcraft implements ICompat
{
	public String getModID() {
		return "BuildCraft|Core";
	}
	
    public void load()
    {
        try
        {
            for (int i = 0; i < ArtificeCore.tiers.length; i++)
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockReinforced + "@" + i);
            for (int i = 0; i < ArtificeCore.rocks.length; i++)
            {
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockBasalt + "@" + i);
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockMarble + "@" + i);
            }
            FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockSteel + "@0");
            ArtificeCore.logger.info("BuildCraft Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize BuildCraft compat");
            ex.printStackTrace();
        }
    }
}
