package shukaro.artifice.recipe;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
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
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemBox, 16, 0), new Object[] {
                " Y ",
                "YXY",
                " Y ",
                'Y', "plankWood",
                'X', "stickWood" }));
            
            GameRegistry.addRecipe(new RecipeBox());
            GameRegistry.registerCraftingHandler(new BoxCraftingHandler());
        }
        if (ArtificeConfig.sickleRecipes.getBoolean(true))
            registerSickleRecipes();
        if (ArtificeConfig.coinMinting.getBoolean(true))
        	registerCoinMinting();
        if (ArtificeConfig.coinChanging.getBoolean(true))
        	registerCoinChanging();
        if (ArtificeConfig.coinSmelting.getBoolean(false))
        	registerCoinSmelting();
        if (ArtificeConfig.upgradeRecipes.getBoolean(true))
        {
        	registerUpgrades();
        	GameRegistry.addRecipe(new RecipeUpgrade());
        }
        if (ArtificeConfig.alternateSteel.getBoolean(false))
        {
        	registerAlternateSteelRecipes();
        }
        if (ArtificeConfig.convenienceRecipes.getBoolean(true))
        {
        	registerConvenienceRecipes();
        }
    }
    
    private static void registerConvenienceRecipes()
    {
    	FurnaceRecipes.smelting().addSmelting(Block.stoneBrick.blockID, 2, new ItemStack(Block.stoneBrick, 1, 0), 0f);
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneBrick, 2, 3), new Object[] {
    		"X",
    		"X",
    		'X', new ItemStack(Block.stoneBrick, 1, 0)
    	}));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneBrick, 8, 1), new Object[] {
    		"XXX",
    		"XYX",
    		"XXX",
    		'X', new ItemStack(Block.stoneBrick, 1, 0),
    		'Y', new ItemStack(Item.bucketWater)
    	}));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestoneMossy, 8, 0), new Object[] {
    		"XXX",
    		"XYX",
    		"XXX",
    		'X', new ItemStack(Block.cobblestone),
    		'Y', new ItemStack(Item.bucketWater)
    	}));
    }
    
    private static void registerAlternateSteelRecipes()
    {
    	int cost = ArtificeConfig.alternateSteelRequirement.getInt();
    	if (cost < 1)
    		cost = 1;
    	if (cost > 8)
    		cost = 8;
    	
    	Object[] reagents = new Object[cost+1];
    	ItemStack fuel = new ItemStack(Item.coal, 1, OreDictionary.WILDCARD_VALUE);
    	
    	reagents[0] = Item.ingotIron;
    	for (int i=1; i<cost+1; i++)
    		reagents[i] = fuel.copy();
    	
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 1, 1), reagents));
    	
    	FurnaceRecipes.smelting().addSmelting(ArtificeItems.itemSteelIngot.itemID, 1, new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), 1.0f);
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), new Object[] {
            "XXX",
            "XXX",
            "XXX",
            'X', "ingotSteel" }));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 9, 0), new ItemStack(ArtificeBlocks.blockSteel, 1, 0)));
    }
    
    private static void registerUpgrades()
    {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), new Object[] {
			" Z ",
			"YXQ",
			"   ",
			'X', Item.coal,
			'Y', Item.ingotIron,
			'Q', Block.stone,
			'Z', Item.flint
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 1), new Object[] {
			"   ",
			"XQZ",
			" Y ",
			'X', Item.silk,
			'Q', Item.leather,
			'Z', Item.ingotIron,
			'Y', "stickWood"
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 2), new Object[] {
			"   ",
			"YXZ",
			" X ",
			'X', "stickWood",
			'Y', Item.leather,
			'Z', Item.ingotIron
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 3), new Object[] {
			"  X",
			" X ",
			"X  ",
			'X', Item.silk
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 4), new Object[] {
			" X ",
			" X ",
			" Y ",
			'X', Item.ingotIron,
			'Y', Block.stone
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 5), new Object[] {
			"   ",
			"XXX",
			" Y ",
			'X', Block.fenceIron,
			'Y', Item.ingotIron
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 6), new Object[] {
			"   ",
			"YXY",
			" X ",
			'X', Item.leather,
			'Y', Item.silk
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 7), new Object[] {
			"   ",
			"YXY",
			" X ",
			'X', Block.cloth,
			'Y', Item.silk
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 8), new Object[] {
			"   ",
			"X X",
			"Y Y",
			'X', Item.leather,
			'Y', Item.slimeBall
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 9), new Object[] {
			" X ",
			" Y ",
			" Q ",
			'Q', Item.bowlEmpty,
			'Y', Item.slimeBall,
			'X', new ItemStack(Item.dyePowder, 1, 15)
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 10), new Object[] {
			"   ",
			"YXQ",
			" X ",
			'X', Item.slimeBall,
			'Y', Item.leather,
			'Q', Item.silk
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 11), new Object[] {
			" Q ",
			" Y ",
			" X ",
			'Q', Item.silk,
			'Y', Item.leather,
			'X', Item.bucketEmpty
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 12), new Object[] {
			" Q ",
			"XYX",
			" Z ",
			'Y', Block.glass,
			'X', Item.leather,
			'Z', Item.ingotIron,
			'Q', Item.silk
		}));
	}

	private static void registerCoinMinting()
    {
    	String[] nuggets = { "nuggetCopper", "nuggetSilver", "nuggetGold", "nuggetPlatinum" };
    	
    	for (int i=0; i<nuggets.length; i++)
    	{
    		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 3, i), nuggets[i], nuggets[i], nuggets[i]));
    	}
    }
    
    private static void registerCoinChanging()
    {
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 1, 1), "coinCopper", "coinCopper", "coinCopper", "coinCopper"));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 1, 2), "coinSilver", "coinSilver", "coinSilver", "coinSilver"));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 1, 3), "coinGold", "coinGold", "coinGold", "coinGold"));
    	
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 4, 0), "coinSilver"));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 4, 1), "coinGold"));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemCoin, 4, 2), "coinPlatinum"));
    }
    
    private static void registerCoinSmelting()
    {
    	FurnaceRecipes.smelting().addSmelting(ArtificeItems.itemCoin.itemID, 0, new ItemStack(ArtificeItems.itemNugget, 1, 0), 0);
    	FurnaceRecipes.smelting().addSmelting(ArtificeItems.itemCoin.itemID, 1, new ItemStack(ArtificeItems.itemNugget, 1, 1), 0);
    	FurnaceRecipes.smelting().addSmelting(ArtificeItems.itemCoin.itemID, 2, new ItemStack(Item.goldNugget, 1, 0), 0);
    	FurnaceRecipes.smelting().addSmelting(ArtificeItems.itemCoin.itemID, 3, new ItemStack(ArtificeItems.itemNugget, 1, 2), 0);
    }
    
    private static void registerSickleRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleWood, 1, 0), new Object[] {
            "X ",
            " X",
            "Y ",
            'X', "plankWood",
            'Y', "stickWood" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleStone, 1, 0), new Object[] {
            "X ",
            " X",
            "Y ",
            'X', Block.cobblestone,
            'Y', "stickWood" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleIron, 1, 0), new Object[] {
            "X ",
            " X",
            "Y ",
            'X', Item.ingotIron,
            'Y', "stickWood" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleGold, 1, 0), new Object[] {
            "X ",
            " X",
            "Y ",
            'X', Item.ingotGold,
            'Y', "stickWood" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleDiamond, 1, 0), new Object[] {
            "X ",
            " X",
            "Y ",
            'X', Item.diamond,
            'Y', "stickWood" }));
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
            " Z ",
            'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
            'Y', Block.brick,
            'Z', Item.clay }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 4, 2), new Object[] {
            "   ",
            "YXY",
            " Z ",
            'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
            'Y', Block.obsidian,
            'Z', Item.bucketLava }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 4, 3), new Object[] {
            "YYY",
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
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye.itemID, 2, 1), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 1)));
        
        // Yellow + blue = green
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 2), "dyeBlue", "dyeYellow"));
        
        // Brown flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye.itemID, 2, 2), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 2)));
        
        // Blue flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye.itemID, 2, 0), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 0)));
        
        // White flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye.itemID, 2, 3), new ItemStack(ArtificeBlocks.blockFlora.blockID, 1, 3)));
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
