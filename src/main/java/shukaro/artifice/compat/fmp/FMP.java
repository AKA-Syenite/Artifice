package shukaro.artifice.compat.fmp;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|ForgeMicroblock", name = "Artifice Compat: ForgeMicroblock", version = ArtificeCore.modVersion, dependencies = "after:Artifice")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class FMP
{
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("ForgeMicroblock"))
        {
            ArtificeCore.logger.log(Level.INFO, "ForgeMultiPart not installed, skipping compat");
            return;
        }
        try
        {
            for (int i = 0; i < ArtificeCore.tiers.length; i++)
            {
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockReinforced.blockID, 1, i));
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockGlassWall.blockID, 1, i));
            }
            for (int i = 0; i < ArtificeCore.rocks.length; i++)
            {
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, i));
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, i));
            }
            FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockSteel.blockID, 1, 0));
            ArtificeCore.logger.log(Level.INFO, "ForgeMultiPart Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.log(Level.WARNING, "Couldn't initialize ForgeMultiPart compat");
            ex.printStackTrace();
        }
    }
}