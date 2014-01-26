package shukaro.artifice.compat.buildcraft;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkMod;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|Buildcraft", name = "Artifice Compat: Buildcraft", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:BuildCraft|Core")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Buildcraft
{
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("BuildCraft|Core"))
        {
            ArtificeCore.logger.log(Level.INFO, "BuildCraft not installed, skipping compat");
            return;
        }
        try
        {
            for (int i = 0; i < ArtificeCore.tiers.length; i++)
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockReinforced.blockID + "@" + i);
            for (int i = 0; i < ArtificeCore.rocks.length; i++)
            {
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockBasalt.blockID + "@" + i);
                FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockMarble.blockID + "@" + i);
            }
            FMLInterModComms.sendMessage("BuildCraft|Core", "add-facade", ArtificeBlocks.blockSteel.blockID + "@0");
            ArtificeCore.logger.log(Level.INFO, "BuildCraft Compat Initialized");
        }
        catch (Exception ex)
        {
            ArtificeCore.logger.log(Level.WARNING, "Couldn't initialize BuildCraft compat");
            ex.printStackTrace();
        }
    }
}
