package shukaro.artifice.compat.chisel;

import java.util.logging.Level;

import net.minecraft.block.Block;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ArtificeCompat|Chisel", name = "Artifice Compat: Chisel", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:Chisel")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Chisel
{
    @Init
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("Chisel"))
        {
            ArtificeCore.logger.warning("Chisel missing, not loading compat");
            return;
        }
        try
        {
            Class<?> main = Class.forName("info.jbcs.minecraft.chisel.Chisel");
            Block marble = (Block) main.getField("blockMarble").get(null);
            int id = marble.blockID;
            
            ArtificeRegistry.registerMarbleType(id, 0);
            
            ArtificeCore.logger.log(Level.INFO, "Chisel Compat Initialized");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
