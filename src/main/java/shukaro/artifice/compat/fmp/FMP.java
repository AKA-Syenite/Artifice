package shukaro.artifice.compat.fmp;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;

import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|ForgeMicroblock", name = "Artifice Compat: ForgeMicroblock", version = ArtificeCore.modVersion, dependencies = "after:Artifice")
public class FMP
{
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("ForgeMicroblock"))
        {
            ArtificeCore.logger.info("ForgeMultiPart not installed, skipping compat");
            return;
        }
        try
        {
            for (int i = 0; i < ArtificeCore.tiers.length; i++)
            {
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockReinforced, 1, i));
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockGlassWall, 1, i));
            }
            for (int i = 0; i < ArtificeCore.rocks.length; i++)
            {
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockBasalt, 1, i));
                FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockMarble, 1, i));
            }
            FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(ArtificeBlocks.blockSteel, 1, 0));
            ArtificeCore.logger.info("ForgeMultiPart Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize ForgeMultiPart compat");
            ex.printStackTrace();
        }
    }
}