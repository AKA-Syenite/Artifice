package shukaro.artifice.recipe;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.item.BoxCraftingHandler;
import net.minecraft.block.Block;
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
        if (ArtificeConfig.sledgeRecipes.getBoolean(true))
        	registerSledgeRecipes();
        if (ArtificeConfig.frameRecipes.getBoolean(true))
        	registerFrameRecipes();
        if (ArtificeConfig.detectorRecipe.getBoolean(true))
        {
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockDetector, 1, 0), new Object[] {
        		" Q ",
        		"YXY",
        		" Y ",
        		'Y', new ItemStack(Item.silk.itemID, 1, 0),
        		'X', new ItemStack(ArtificeBlocks.blockFrame.blockID, 1, 0),
        		'Q', new ItemStack(Item.redstone.itemID, 1, 0) }));
        }
        if (ArtificeConfig.steelSmelting.getBoolean(true))
        	registerSteelRecipes();
    	if (ArtificeConfig.blastWallRecipes.getBoolean(true))
    		registerBlastWallRecipes();
    	if (ArtificeConfig.glassWallRecipes.getBoolean(true))
    		registerGlassWallRecipes();
    	if (ArtificeConfig.scaffoldRecipes.getBoolean(true))
    		registerScaffoldRecipes();
    	if (ArtificeConfig.boxRecipes.getBoolean(true))
    	{
    		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemBox, 32, 0), new Object[] {
    			" Y ",
    			"YXY",
    			" Y ",
    			'Y', "plankWood",
    			'X', "stickWood" }));
    		
    		GameRegistry.addRecipe(new RecipeBox());
    		GameRegistry.registerCraftingHandler(new BoxCraftingHandler());
    	}
    }
    
    private static void registerScaffoldRecipes()
    {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 0), new Object[] {
			" Y ",
			" X ",
			" Y ",
			'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
			'Y', "plankWood" }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 1), new Object[] {
			" Y ",
			" X ",
			" Y ",
			'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
			'Y', "plankWood" }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 2), new Object[] {
			" Y ",
			" X ",
			" Y ",
			'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
			'Y', new ItemStack(Item.ingotIron.itemID, 1, 0) }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 3), new Object[] {
			" Y ",
			" X ",
			" Y ",
			'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
			'Y', "ingotSteel" }));
	}

	private static void registerGlassWallRecipes()
    {
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 0), new Object[] {
    		"   ",
    		"YXY",
    		" Z ",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
    		'Y', Item.clay,
    		'Z', Block.glass }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 1), new Object[] {
    		"   ",
    		"YXY",
    		"YZY",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
    		'Y', Item.clay,
    		'Z', Block.glass }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 2), new Object[] {
    		" Q ",
    		"YXY",
    		" Z ",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
    		'Q', Block.glass,
    		'Y', Block.obsidian,
    		'Z', Item.clay }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 3), new Object[] {
    		" Q ",
    		"YXY",
    		"YYY",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
    		'Q', Block.glass,
    		'Y', "ingotSteel" }));
    }
    
    private static void registerBlastWallRecipes()
    {
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 0), new Object[] {
    		"   ",
    		"YXY",
    		" Z ",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
    		'Y', Block.stoneBrick,
    		'Z', Item.clay }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 1), new Object[] {
    		"   ",
    		"YXY",
    		"YZY",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
    		'Y', Item.brick,
    		'Z', Item.clay }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 2), new Object[] {
    		"   ",
    		"YXY",
    		" Z ",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
    		'Y', Block.obsidian,
    		'Z', Item.clay }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 3), new Object[] {
    		"   ",
    		"YXY",
    		"YYY",
    		'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
    		'Y', "ingotSteel" }));
    }
    
    private static void registerSteelRecipes()
    {
    	GameRegistry.addSmelting(Item.ingotIron.itemID, new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), 1.0F);
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), new Object[] {
    		"XXX",
    		"XXX",
    		"XXX",
    		'X', "ingotSteel" }));
    	
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 9, 0), new ItemStack(ArtificeBlocks.blockSteel, 1, 0)));
    }
    
    private static void registerFrameRecipes()
    {
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 8, 0), new Object[] {
    		"XYX",
    		"YYY",
    		"XYX",
    		'X', "plankWood",
    		'Y', "stickWood" }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 8, 1), new Object[] {
    		"XYX",
    		"YYY",
    		"XYX",
    		'X', "plankWood",
    		'Y', new ItemStack(Item.ingotIron.itemID, 1, 0) }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 16, 2), new Object[] {
    		"X X",
    		" Y ",
    		"X X",
    		'X', new ItemStack(Item.ingotIron.itemID, 1, 0),
    		'Y', new ItemStack(Block.blockIron.blockID, 1, 0) }));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 16, 3), new Object[] {
    		"X X",
    		" Y ",
    		"X X",
    		'X', "ingotSteel",
    		'Y', "blockSteel" }));
	}

	private static void registerSledgeRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeWood.itemID, 1, 0), new Object[] {
			" YX",
			" XY",
			"X  ",
			'X', "stickWood",
			'Y', "plankWood" }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeStone.itemID, 1, 0), new Object[] {
			" YX",
			" XY",
			"X  ",
			'X', "stickWood",
			'Y', new ItemStack(Block.cobblestone.blockID, 1, 0) }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeIron.itemID, 1, 0), new Object[] {
			" YX",
			" XY",
			"X  ",
			'X', "stickWood",
			'Y', new ItemStack(Item.ingotIron.itemID, 1, 0) }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeGold.itemID, 1, 0), new Object[] {
			" YX",
			" XY",
			"X  ",
			'X', "stickWood",
			'Y', new ItemStack(Item.ingotGold.itemID, 1, 0) }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeDiamond.itemID, 1, 0), new Object[] {
			" YX",
			" XY",
			"X  ",
			'X', "stickWood",
			'Y', new ItemStack(Item.diamond.itemID, 1, 0) }));
	}

	private static void registerDyeRecipes()
    {
        // Black flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 0), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 1)));
        
        // Yellow + blue = green
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 2), "dyeBlue", "dyeYellow"));
        
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
