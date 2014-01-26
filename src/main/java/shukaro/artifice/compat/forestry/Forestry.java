package shukaro.artifice.compat.forestry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

import java.util.List;
import java.util.logging.Level;

@Mod(modid = "ArtificeCompat|Forestry", name = "Artifice Compat: Forestry", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:Forestry")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Forestry
{
    @EventHandler
    public static void load(FMLInitializationEvent e0)
    {
        if (!Loader.isModLoaded("Forestry"))
        {
            ArtificeCore.logger.log(Level.INFO, "Forestry not installed, skipping compat");
            return;
        }
        try
        {
            Class<?> flowerClass = Class.forName("forestry.api.apiculture.FlowerManager");
            List<ItemStack> flowerList = (List<ItemStack>) flowerClass.getField("plainFlowers").get(null);

            if (ArtificeConfig.enableWorldGen.getBoolean(true))
            {
                ArtificeCore.logger.log(Level.INFO, "Adding flowers to the Flower Manager");
                for (int i = 0; i < 4; i++)
                    flowerList.add(new ItemStack(ArtificeBlocks.blockFlora, 1, i));
            }

            ArtificeCore.logger.log(Level.INFO, "Forestry Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.log(Level.WARNING, "Couldn't initialize Forestry compat");
            ex.printStackTrace();
        }
    }
}