package shukaro.artifice.compat;

import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

import java.util.ArrayList;

public class CarpentersBlocks implements ICompat
{
    @Override
    public String getModID()
    {
        return "CarpentersBlocks";
    }

    @Override
    public void load()
    {
        try
        {
            Class<?> featureClass = Class.forName("com.carpentersblocks.util.registry.FeatureRegistry");
            ArrayList<String> coverExceptions = (ArrayList<String>) featureClass.getField("coverExceptions").get(null);

            for (int i = 0; i < ArtificeConfig.tiers.length; i++)
            {
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockReinforced, 1, i).getDisplayName());
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockGlassWall, 1, i).getDisplayName());
            }
            for (int i = 0; i < ArtificeConfig.rocks.length; i++)
            {
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockBasalt, 1, i).getDisplayName());
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockMarble, 1, i).getDisplayName());
                for (int j = 0; j < ArtificeBlocks.blockLimestones.length; j++)
                    coverExceptions.add(new ItemStack(ArtificeBlocks.blockLimestones[j], 1, i).getDisplayName());
            }
            for (int i = 0; i < ArtificeBlocks.blockLamps.length; i++)
            {
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockLamps[i], 1, 0).getDisplayName());
                coverExceptions.add(new ItemStack(ArtificeBlocks.blockLampsInverted[i], 1, 0).getDisplayName());
            }
            coverExceptions.add(new ItemStack(ArtificeBlocks.blockSteel, 1, 0).getDisplayName());

            ArtificeCore.logger.info("Carpenter's Blocks compat initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't load Carpenter's Blocks compat");
            ex.printStackTrace();
        }
    }
}
