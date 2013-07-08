package shukaro.artifice.compat.forestry;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ArtificeCompat|Forestry", name = "Artifice Compat: Forestry", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:Forestry")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Forestry
{
    @Init
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("Forestry"))
        {
            ArtificeCore.logger.warning("Forestry missing, not loading compat");
            return;
        }
        try
        {
            Class<?> flowerClass = Class.forName("forestry.api.apiculture.FlowerManager");
            List<ItemStack> flowerList = (List<ItemStack>) flowerClass.getField("plainFlowers").get(null);
            
            if (ArtificeConfig.enableWorldGen.getBoolean(true))
            {
                for (int i=0; i<3; i++)
                	flowerList.add(new ItemStack(ArtificeBlocks.blockFlora, 1, i));
            }
            
            ArtificeCore.logger.log(Level.INFO, "Forestry Compat Initialized");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}