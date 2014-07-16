package shukaro.artifice.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

import java.util.List;
import java.util.logging.Level;

public class Forestry implements ICompat
{
	public String getModID() { return "Forestry"; }
    public void load()
    {
        try
        {
            Class<?> flowerClass = Class.forName("forestry.api.apiculture.FlowerManager");
            List<ItemStack> flowerList = (List<ItemStack>) flowerClass.getField("plainFlowers").get(null);

            if (ArtificeConfig.enableWorldGen.getBoolean(true))
            {
                ArtificeCore.logger.info("Adding flowers to the Flower Manager");
                for (int i = 0; i < 4; i++)
                    flowerList.add(new ItemStack(ArtificeBlocks.blockFlora, 1, i));
            }

            ArtificeCore.logger.info("Forestry Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize Forestry compat");
            ex.printStackTrace();
        }
    }
}