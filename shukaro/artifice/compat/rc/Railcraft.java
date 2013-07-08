package shukaro.artifice.compat.rc;

import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ArtificeCompat|Railcraft", name = "Artifice Compat: Railcraft", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:Railcraft")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Railcraft
{
    @Init
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("Railcraft"))
        {
            ArtificeCore.logger.warning("Railcraft missing, not loading compat");
            return;
        }
        try
        {
            ItemStack marble = GameRegistry.findItemStack("Railcraft", "cube.stone.quarried", 1);
            ItemStack basalt = GameRegistry.findItemStack("RailCraft", "cube.stone.abyssal", 1);
            
            ArtificeRegistry.registerMarbleType(marble.itemID, 0);
            ArtificeRegistry.registerBasaltType(basalt.itemID, 0);
            
            ArtificeCore.logger.log(Level.INFO, "Railcraft Compat Initialized");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
