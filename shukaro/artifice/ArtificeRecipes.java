package shukaro.artifice;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ArtificeRecipes
{
    public static void registerRecipes()
    {
        if (ArtificeConfig.basaltRecipes.getBoolean(true))
            registerBasaltRecipes();
        if (ArtificeConfig.marbleRecipes.getBoolean(true))
            registerMarbleRecipes();
        if (ArtificeConfig.floraRecipes.getBoolean(true))
            registerDyeRecipes();
    }
    
    private static void registerDyeRecipes()
    {
        // Black flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 0), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 1)));
        
        // Yellow + blue = green
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 2), new ItemStack(Item.dyePowder.itemID, 1, 4), new ItemStack(Item.dyePowder.itemID, 1, 11)));
        
        // Brown flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 3), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 2)));
        
        // Blue flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 4), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 0)));
        
        // White flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 15), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 3)));
    }
    
    private static void registerBasaltRecipes()
    {
        FurnaceRecipes.smelting().addSmelting(ArtificeBlocks.blockBasalt.blockID, 1, new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 0), 0.1F);
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt.blockID, 4, 2), new Object[] {
            "XX",
            "XX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt.blockID, 4, 3), new Object[] {
            "XX",
            "XX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt.blockID, 2, 5), new Object[] {
            "X",
            "X",
            'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 4), new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 3)));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 3), new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 4)));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltBrickStairs, 4, 0), new Object[] {
            "X  ",
            "XX ",
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltCobbleStairs.blockID, 4, 0), new Object[] {
            "X  ",
            "XX ",
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 6, 0), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 6, 1), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 6, 2), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 3) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 6, 3), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockBasalt.blockID, 1, 4) }));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 1, 2), new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 1, 3)));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 1, 3), new ItemStack(ArtificeBlocks.blockBasaltSlab.blockID, 1, 2)));
    }
    
    private static void registerMarbleRecipes()
    {
        FurnaceRecipes.smelting().addSmelting(ArtificeBlocks.blockMarble.blockID, 1, new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 0), 0.1F);
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble.blockID, 4, 2), new Object[] {
            "XX",
            "XX",
            'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble.blockID, 4, 3), new Object[] {
            "XX",
            "XX",
            'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble.blockID, 2, 5), new Object[] {
            "X",
            "X",
            'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 4), new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 3)));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 3), new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 4)));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleBrickStairs, 4, 0), new Object[] {
            "X  ",
            "XX ",
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleCobbleStairs.blockID, 4, 0), new Object[] {
            "X  ",
            "XX ",
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 6, 0), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 2) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 6, 1), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 6, 2), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 3) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 6, 3), new Object[] {
            "XXX",
            'X', new ItemStack(ArtificeBlocks.blockMarble.blockID, 1, 4) }));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 1, 2), new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 1, 3)));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 1, 3), new ItemStack(ArtificeBlocks.blockMarbleSlab.blockID, 1, 2)));
    }
}
