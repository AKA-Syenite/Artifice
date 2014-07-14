package shukaro.artifice.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

import java.util.List;
import java.util.logging.Level;

public class EE3 implements ICompat
{
	public String getModID() { return "EE3"; }
    public void load()
    {
        try
        {
            Class<?> recipeClass = Class.forName("com.pahimar.ee3.recipe.RecipesTransmutationStone");
            List<ItemStack> stoneList = (List<ItemStack>) recipeClass.getField("transmutationStones").get(null);

            if (ArtificeConfig.enableWorldGen.getBoolean(true))
            {
                ArtificeCore.logger.info("Adding transmutation recipes");
                for (ItemStack stone : stoneList)
                {
                    GameRegistry.addShapelessRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 2, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), new ItemStack(Blocks.cobblestone, 1, 0));
                    GameRegistry.addShapelessRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 1, 1), stone, new ItemStack(ArtificeBlocks.blockMarble, 1, 1));
                    GameRegistry.addShapelessRecipe(new ItemStack(ArtificeBlocks.blockMarble, 1, 1), stone, new ItemStack(ArtificeBlocks.blockBasalt, 1, 1));
                }
            }

            ArtificeCore.logger.info("EE3 Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.info("Couldn't initialize EE3 compat");
            ex.printStackTrace();
        }
    }
}
