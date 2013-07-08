package shukaro.artifice.compat.mfr;

import java.util.logging.Level;

import net.minecraft.block.Block;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ArtificeCompat|MFR", name = "Artifice Compat: MFR", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:MineFactoryReloaded")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class MFR
{
    @Init
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("MineFactoryReloaded"))
        {
            ArtificeCore.logger.warning("MFR missing, not loading compat");
            return;
        }
        try
        {
            if (ArtificeConfig.enableWorldGen.getBoolean(true))
                FarmingRegistry.registerHarvestable(new FactoryFlora());
            
            Class<?> main = Class.forName("powercrystals.minefactoryreloaded.MineFactoryReloadedCore");
            Block stone = (Block) main.getField("factoryDecorativeStoneBlock").get(null);
            int id = stone.blockID;
            
            ArtificeRegistry.registerBasaltType(id, 0);
            ArtificeRegistry.registerMarbleType(id, 1);
            
            ArtificeCore.logger.log(Level.INFO, "MFR Compat Initialized");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}