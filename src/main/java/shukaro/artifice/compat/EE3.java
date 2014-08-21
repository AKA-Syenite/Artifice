package shukaro.artifice.compat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

import java.util.ArrayList;
import java.util.List;

public class EE3 implements ICompat
{
    public String getModID()
    {
        return "EE3";
    }

    public void load()
    {
        try
        {
            Class<?> itemClass = Class.forName("com.pahimar.ee3.init.ModItems");
            List<ItemStack> stoneList = new ArrayList<ItemStack>();
            stoneList.add((ItemStack) itemClass.getField("stoneMinium").get(null));
            stoneList.add((ItemStack)itemClass.getField("stonePhilosophers").get(null));

            ArtificeCore.logger.info("Adding transmutation recipes");
            for (ItemStack stone : stoneList)
            {
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeBlack"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeWhite"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[0], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeGray"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[1], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0)));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[2], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), Blocks.dirt));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[3], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), Blocks.sand));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[4], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeRed"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[5], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeBlue"));
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[6], 1, 1), stone, new ItemStack(Blocks.cobblestone, 1, 0), "dyeGreen"));
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
