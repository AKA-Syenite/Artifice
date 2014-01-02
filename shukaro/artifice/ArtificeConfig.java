package shukaro.artifice;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArtificeConfig
{
    public static int frameRenderID;
    public static int lotusRenderID;
	
    public static Property idStart;
    
    public static Property blockFrameID;
    public static Property blockDetectorID;
    public static Property blockFloraID;
    public static Property blockLotusID;
    public static Property blockBasaltID;
    public static Property blockMarbleID;
    public static Property blockBasaltBrickStairsID;
    public static Property blockMarbleBrickStairsID;
    public static Property blockBasaltCobbleStairsID;
    public static Property blockMarbleCobbleStairsID;
    public static Property blockBasaltSlabID;
    public static Property blockBasaltDoubleSlabID;
    public static Property blockMarbleSlabID;
    public static Property blockMarbleDoubleSlabID;
    public static Property blockSteelID;
    public static Property blockReinforcedID;
    public static Property blockGlassWallID;
    public static Property blockScaffoldID;
    
    public static Property idStartItem;
    
    public static Property itemSledgeWoodID;
    public static Property itemSledgeStoneID;
    public static Property itemSledgeIronID;
    public static Property itemSledgeGoldID;
    public static Property itemSledgeDiamondID;
    public static Property itemSteelIngotID;
    public static Property itemBoxID;
    public static Property itemDyeID;
    public static Property itemSickleWoodID;
    public static Property itemSickleStoneID;
    public static Property itemSickleIronID;
    public static Property itemSickleGoldID;
    public static Property itemSickleDiamondID;
    public static Property itemNuggetID;
    public static Property itemCoinID;
    public static Property itemUpgradeID;
    
    public static Property sledgeRecipes;
    public static Property frameRecipes;
    public static Property detectorRecipe;
    public static Property steelSmelting;
    public static Property alternateSteel;
    public static Property alternateSteelRequirement;
    public static Property blastWallRecipes;
    public static Property glassWallRecipes;
    public static Property scaffoldRecipes;
    public static Property boxRecipes;
    public static Property stoneCycleRecipes;
    public static Property sickleRecipes;
    public static Property coinMinting;
    public static Property coinChanging;
    public static Property coinSmelting;
    public static Property upgradeRecipes;
    public static Property convenienceRecipes;
    
    public static Property enableFrames;
    public static Property enableSledges;
    public static Property enableSteel;
    public static Property enableWorldGen;
    public static Property enableBoxes;
    public static Property enableSickles;
    public static Property enableCoins;
    public static Property enableUpgrades;
    public static Property limitUpgrades;
    public static Property marbleList;
    public static Property basaltList;
    
    public static Property floraWorldGen;
    public static Property floraFrequency;
    public static Property lotusWorldGen;
    public static Property lotusFrequency;
    public static Property basaltLayerWorldGen;
    public static Property basaltLayerMinHeight;
    public static Property basaltLayerMaxHeight;
    public static Property basaltClusterWorldGen;
    public static Property basaltClusterSize;
    public static Property basaltClusterHeight;
    public static Property basaltClusterFrequency;
    public static Property marbleLayerWorldGen;
    public static Property marbleLayerMinHeight;
    public static Property marbleLayerMaxHeight;
    public static Property marbleClusterWorldGen;
    public static Property marbleClusterSize;
    public static Property marbleClusterHeight;
    public static Property marbleClusterFrequency;
    public static Property basaltCaveWorldGen;
    public static Property basaltCaveSize;
    public static Property basaltCaveHeight;
    public static Property basaltCaveFrequency;
    public static Property basaltCaveAdherence;
    public static Property marbleCaveWorldGen;
    public static Property marbleCaveSize;
    public static Property marbleCaveHeight;
    public static Property marbleCaveFrequency;
    public static Property marbleCaveAdherence;
    public static Property floraRecipes;
    public static Property basaltRecipes;
    public static Property marbleRecipes;
    public static Property floraBoneMeal;
    public static Property regenBasaltLayer;
    public static Property regenMarbleLayer;
    public static Property regenBasaltClusters;
    public static Property regenMarbleClusters;
    public static Property regenBasaltCaves;
    public static Property regenMarbleCaves;
    public static Property regenFlora;
    public static Property regenLotus;
    public static Property regenKey;
    
    public static Property flavorText;
    public static Property tooltips;
    
    public static Property dimensionBlacklist;
    
    public static File configFolder;
    
    public static boolean connectedTexturesRegistered = false;
    
    public static void initCommon(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();
            idStart = c.get(Configuration.CATEGORY_BLOCK, "Block Starting ID", 3000);
            idStart.comment = "The block ID to use as the starting point for assignment, delete the other IDs to reassign";
            
            int s = idStart.getInt();
            
            blockFrameID = c.getBlock("blockFrame", s++);
            blockDetectorID = c.getBlock("blockDetector", s++);
            blockFloraID = c.getBlock("blockFlora", s++);
            blockBasaltID = c.getBlock("blockBasalt", s++);
            blockMarbleID = c.getBlock("blockMarble", s++);
            blockBasaltBrickStairsID = c.getBlock("blockBasaltBrickStairs", s++);
            blockMarbleBrickStairsID = c.getBlock("blockMarbleBrickStairs", s++);
            blockBasaltCobbleStairsID = c.getBlock("blockBasaltCobbleStairs", s++);
            blockMarbleCobbleStairsID = c.getBlock("blockMarbleCobbleStairs", s++);
            blockBasaltSlabID = c.getBlock("blockBasaltSlab", s++);
            blockBasaltDoubleSlabID = c.getBlock("blockBasaltDoubleSlab", s++);
            blockMarbleSlabID = c.getBlock("blockMarbleSlab", s++);
            blockMarbleDoubleSlabID = c.getBlock("blockMarbleDoubleSlab", s++);
            blockLotusID = c.getBlock("blockLotus", s++);
            blockSteelID = c.getBlock("blockSteel", s++);
            blockReinforcedID = c.getBlock("blockReinforced", s++);
            blockGlassWallID = c.getBlock("blockGlassWall", s++);
            blockScaffoldID = c.getBlock("blockScaffold", s++);
            
            idStartItem = c.get(Configuration.CATEGORY_ITEM, "Item Starting ID", 5000);
            idStartItem.comment = "The item ID to use as the starting point for assignment, delete the other IDs to reassign";
            
            int i = idStartItem.getInt();
            
            itemSledgeWoodID = c.getItem("itemSledgeWood", i++);
            itemSledgeStoneID = c.getItem("itemSledgeStone", i++);
            itemSledgeIronID = c.getItem("itemSledgeIron", i++);
            itemSledgeGoldID = c.getItem("itemSledgeGold", i++);
            itemSledgeDiamondID = c.getItem("itemSledgeDiamond", i++);
            itemSteelIngotID = c.getItem("itemSteelIngot", i++);
            itemBoxID = c.getItem("itemBox", i++);
            itemDyeID = c.getItem("itemDye", i++);
            itemSickleWoodID = c.getItem("itemSickleWood", i++);
            itemSickleStoneID = c.getItem("itemSickleStone", i++);
            itemSickleIronID = c.getItem("itemSickleIron", i++);
            itemSickleGoldID = c.getItem("itemSickleGold", i++);
            itemSickleDiamondID = c.getItem("itemSickleDiamond", i++);
            itemNuggetID = c.getItem("itemNugget", i++);
            itemCoinID = c.getItem("itemCoin", i++);
            itemUpgradeID = c.getItem("itemUpgrade", i++);
            
            floraWorldGen = c.get("Plant Generation", "Generate Flowers", true);
            floraWorldGen.comment = "Whether or not to generate flowers";
            floraFrequency = c.get("Plant Generation", "Flower Frequency", 1);
            floraFrequency.comment = "Number of times to attempt to place flowers in each chunk (Default 1)";
            lotusWorldGen = c.get("Plant Generation", "Generate Lotus Lilies", true);
            lotusWorldGen.comment = "Whether or not to generate lotus lilies";
            lotusFrequency = c.get("Plant Generation", "Lotus Liy Frequency", 1);
            lotusFrequency.comment = "Number of times to attempt to place lotus lilies in each chunk (Default 1)";
            
            basaltLayerWorldGen = c.get("Layer Generation - Basalt", "Generate Basalt Layer", true);
            basaltLayerWorldGen.comment = "Whether or not to generate a basalt layer (Default True)";
            basaltLayerMinHeight = c.get("Layer Generation - Basalt", "Basalt Layer Min Height", -2);
            basaltLayerMinHeight.comment = "Average minimum height for basalt layer (Default -2)";
            basaltLayerMaxHeight = c.get("Layer Generation - Basalt", "Basalt Layer Max Height", 7);
            basaltLayerMaxHeight.comment = "Average maximum height for basalt layer (Default 7)";
            
            marbleLayerWorldGen = c.get("Layer Generation - Marble", "Generate Marble Layer", false);
            marbleLayerWorldGen.comment = "Whether or not to generate a marble layer (Default false)";
            marbleLayerMinHeight = c.get("Layer Generation - Marble", "Marble Layer Min Height", -2);
            marbleLayerMinHeight.comment = "Average minimum height for marble layer (Default -2)";
            marbleLayerMaxHeight = c.get("Layer Generation - Marble", "Marble Layer Max Height", 14);
            marbleLayerMaxHeight.comment = "Average maximum height for marble layer (Default 14)";
            
            basaltClusterWorldGen = c.get("Cluster Generation - Basalt", "Generate Basalt Clusters", false);
            basaltClusterWorldGen.comment = "Whether or not to generate basalt clusters (Default false)";
            basaltClusterSize = c.get("Cluster Generation - Basalt", "Basalt Cluster Size", 200);
            basaltClusterSize.comment = "Average size of basalt clusters in the world (Default 200)";
            basaltClusterHeight = c.get("Cluster Generation - Basalt", "Basalt Cluster Height", 64);
            basaltClusterHeight.comment = "Max height to begin basalt cluster generation (Default 64)";
            basaltClusterFrequency = c.get("Cluster Generation - Basalt", "Basalt Cluster Frequency", 4);
            basaltClusterFrequency.comment = "Number of times to attempt to place basalt clusters in each chunk (Default 4)";
            
            marbleClusterWorldGen = c.get("Cluster Generation - Marble", "Generate Marble Clusters", true);
            marbleClusterWorldGen.comment = "Whether or not to generate marble clusters (Default true)";
            marbleClusterSize = c.get("Cluster Generation - Marble", "Marble Cluster Size", 200);
            marbleClusterSize.comment = "Average size of marble clusters in the world (Default 200)";
            marbleClusterHeight = c.get("Cluster Generation - Marble", "Marble Cluster Height", 128);
            marbleClusterHeight.comment = "Max height to begin marble cluster generation (Default 128)";
            marbleClusterFrequency = c.get("Cluster Generation - Marble", "Marble Cluster Frequency", 4);
            marbleClusterFrequency.comment = "Number of times to attempt to place marble clusters in each chunk (Default 4)";
            
            basaltCaveWorldGen = c.get("Cave Generation - Basalt", "Basalt Cave Worldgen", false);
            basaltCaveWorldGen.comment = "Whether or not to generate basalt caves (Default false) [WARNING]: This loads many chunks, I suggest using regeneration to generate after initial map creation";
            basaltCaveSize = c.get("Cave Generation - Basalt", "Basalt Cave Size", 2000);
            basaltCaveSize.comment = "Average size of basalt caves (Default 400)";
            basaltCaveHeight = c.get("Cave Generation - Basalt", "Basalt Cave Height", 64);
            basaltCaveHeight.comment = "Maximum height to begin basalt cave generation (Default 64)";
            basaltCaveFrequency = c.get("Cave Generation - Basalt", "Basalt Cave Frequency", 4);
            basaltCaveFrequency.comment = "Number of times to attempt to generate basalt caves in each chunk";
            basaltCaveAdherence = c.get("Cave Generation - Basalt", "Basalt Cave Adherence", 75);
            basaltCaveAdherence.comment = "How much generation 'sticks' to walls, 0-100, higher numbers lead to more extensive caves with thinner walls (Default 75)";
            
            marbleCaveWorldGen = c.get("Cave Generation - Marble", "Marble Cave Worldgen", false);
            marbleCaveWorldGen.comment = "Whether or not to generate marble caves (Default false) [WARNING]: This loads many chunks, I suggest using regeneration to generate after initial map creation";
            marbleCaveSize = c.get("Cave Generation - Marble", "Marble Cave Size", 2000);
            marbleCaveSize.comment = "Average size of marble caves (Default 400)";
            marbleCaveHeight = c.get("Cave Generation - Marble", "Marble Cave Height", 64);
            marbleCaveHeight.comment = "Maximum height to begin marble cave generation (Default 128)";
            marbleCaveFrequency = c.get("Cave Generation - Marble", "Marble Cave Frequency", 4);
            marbleCaveFrequency.comment = "Number of times to attempt to generate marble caves";
            marbleCaveAdherence = c.get("Cave Generation - Marble", "Marble Cave Adherence", 75);
            marbleCaveAdherence.comment = "How much generation 'sticks' to walls, 0-100, higher numbers lead to more extensive caves with thinner walls (Default 75)";
            
            dimensionBlacklist = c.get("World Generation", "Dimension Blacklist", "");
            dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in. (No spaces)";
            
            regenBasaltLayer = c.get("World Regeneration", "Regenerate Basalt Layer", false);
            regenBasaltLayer.comment = "Set to true to regenerate the basalt layer";
            regenMarbleLayer = c.get("World Regeneration", "Regenerate Marble Layer", false);
            regenMarbleLayer.comment = "Set to true to regenerate the marble layer";
            regenBasaltClusters = c.get("World Regeneration", "Regenerate Basalt Clusters", false);
            regenBasaltClusters.comment = "Set to true to regenerate basalt clusters";
            regenMarbleClusters = c.get("World Regeneration", "Regenerate Marble Clusters", false);
            regenMarbleClusters.comment = "Set to true to regenerate marble clusters";
            regenBasaltCaves = c.get("World Regeneration", "Regenerate Basalt Caves", false);
            regenBasaltCaves.comment = "Set to true to regenerate basalt caves";
            regenMarbleCaves = c.get("World Regeneration", "Regenerate Marble Caves", false);
            regenMarbleCaves.comment = "Set to true to regenerate marble caves";
            regenFlora = c.get("World Regeneration", "Regenerate Flora", false);
            regenFlora.comment = "Set to true to regenerate flowers";
            regenLotus = c.get("World Regeneration", "Regenerate Lotus Lilies", false);
            regenLotus.comment = "Set to true to regenerate lotus lilies";
            regenKey = c.get("World Regeneration", "Regen Key", "DEFAULT");
            regenKey.comment = "This key is used to keep track of which chunk have been generated/regenerated. Changing it will cause the regeneration code to run again, so only change it if you want it to happen. Useful to regen only one world feature at a time.";
            
            floraRecipes = c.get("Recipes", "Flower Recipes", true);
            floraRecipes.comment = "Set to false to disable flower-related recipes";
            basaltRecipes = c.get("Recipes", "Basalt Recipes", true);
            basaltRecipes.comment = "Set to false to disable basalt recipes";
            marbleRecipes = c.get("Recipes", "Marble Recipes", true);
            marbleRecipes.comment = "Set to false to disable marble recipes";
            sledgeRecipes = c.get("Recipes", "Sledge Recipes", true);
            sledgeRecipes.comment = "Set to false to prevent crafting of sledges";
            frameRecipes = c.get("Recipes", "Frame Recipes", true);
            frameRecipes.comment = "Set to false to prevent crafting of frames";
            detectorRecipe = c.get("Recipes", "Detector Recipe", true);
            detectorRecipe.comment = "Set to false to prevent crafting of detectors";
            steelSmelting = c.get("Recipes", "Steel Smelting", true);
            steelSmelting.comment = "Set to false to prevent the smelting of steel";
            alternateSteel = c.get("Recipes", "Alternate Steel Recipe", false);
            alternateSteel.comment = "Set to true to enable an alternate steel recipe which uses additional fuel";
            alternateSteelRequirement = c.get("Recipes", "Alternate Steel Recipe Fuel Requirement", 2);
            alternateSteelRequirement.comment = "The amount of extra fuel each ingot of steel will require. Minimum of 1, maximum of 8";
            blastWallRecipes = c.get("Recipes", "Blast Wall Recipes", true);
            blastWallRecipes.comment = "Set to false to prevent crafting of blast walls";
            glassWallRecipes = c.get("Recipes", "Glass Wall Recipes", true);
            glassWallRecipes.comment = "Set to false to prevent crafting of glass blast walls";
            scaffoldRecipes = c.get("Recipes", "Scaffolding Recipes", true);
            scaffoldRecipes.comment = "Set to false to prevent crafting of scaffolding";
            boxRecipes = c.get("Recipes", "Box Recipes", true);
            boxRecipes.comment = "Set to false to prevent crafting of boxes";
            stoneCycleRecipes = c.get("Recipes", "Stone Cycling Recipes", true);
            stoneCycleRecipes.comment = "Set to false to prevent cycling through types of stone";
            sickleRecipes = c.get("Recipes", "Sickle Recipes", true);
            sickleRecipes.comment = "Set to false to prevent crafting of sickles";
            coinMinting = c.get("Recipes", "Coin Minting", true);
            coinMinting.comment = "Whether or not to allow coins to be minted from nuggets";
            coinChanging = c.get("Recipes", "Coin Changing", true);
            coinChanging.comment = "Whether or not to allow coins to be traded up or down for other coins";
            coinSmelting = c.get("Recipes", "Coin Smelting", false);
            coinSmelting.comment = "Whether or not to allow coins to be smelted back into nuggets";
            upgradeRecipes = c.get("Recipes", "Upgrade Recipes", true);
            upgradeRecipes.comment = "Set to false to prevent crafting of upgrades";
            convenienceRecipes = c.get("Recipes", "Convenience Recipes", true);
            convenienceRecipes.comment = "Set to false to prevent convenience crafting (smashing stone bricks, smelting cracked bricks, etc).";
            
            limitUpgrades = c.get("General", "Limit Upgrades", true);
            limitUpgrades.comment = "If true, caps the maximum enchant level that can be applied through upgrades to below the natural cap (Shown in Tooltips)";
            floraBoneMeal = c.get("General", "Bonemeal Flowers", true);
            floraBoneMeal.comment = "Set to false to disable random flower growth from bonemeal";
            marbleList = c.get("General", "Marble List", "");
            marbleList.comment = "Comma-seperated list of ID:Meta pairs to add to a cycling chain of 1:1 marble exchange recipes. No spaces.";
            basaltList = c.get("General", "Basalt List", "");
            basaltList.comment = "Comma-seperated list of ID:Meta pairs to add to a cycling chain of 1:1 basalt exchange recipes. No spaces.";
            
            
            enableFrames = c.get("General", "Enable Frames", true);
            enableFrames.comment = "Set to false to stop frames from initializing";
            if (!enableFrames.getBoolean(true))
            {
                frameRecipes.set(false);
                detectorRecipe.set(false);
                blastWallRecipes.set(false);
                glassWallRecipes.set(false);
                scaffoldRecipes.set(false);
            }
            enableSledges = c.get("General", "Enable Sledges", true);
            enableSledges.comment = "Set to false to stop sledges from initializing";
            if (!enableSledges.getBoolean(true))
            {
                sledgeRecipes.set(false);
            }
            enableSteel = c.get("General", "Enable Steel", true);
            enableSteel.comment = "Set to false to stop steel from initializing";
            if (!enableSteel.getBoolean(true))
            {
                steelSmelting.set(false);
                alternateSteel.set(false);
            }
            enableWorldGen = c.get("General", "Enable Worldgen", true);
            enableWorldGen.comment = "Set to false to stop worldgen blocks from initializing or generating";
            if (!enableWorldGen.getBoolean(true))
            {
                floraWorldGen.set(false);
                lotusWorldGen.set(false);
                basaltLayerWorldGen.set(false);
                basaltClusterWorldGen.set(false);
                basaltCaveWorldGen.set(false);
                marbleLayerWorldGen.set(false);
                marbleClusterWorldGen.set(false);
                marbleCaveWorldGen.set(false);
                floraRecipes.set(false);
                basaltRecipes.set(false);
                marbleRecipes.set(false);
                floraBoneMeal.set(false);
            }
            enableBoxes = c.get("General", "Enable Boxes", true);
            enableBoxes.comment = "Set to false to stop boxes from initializing";
            if (!enableBoxes.getBoolean(true))
            {
                boxRecipes.set(false);
            }
            enableSickles = c.get("General", "Enable Sickles", true);
            enableSickles.comment = "Set to false to stop sickles from initializing";
            if (!enableSickles.getBoolean(true))
            {
                sickleRecipes.set(false);
            }
            enableCoins = c.get("General", "Enable Coins", true);
            enableCoins.comment = "Set to false to stop coins from initializing";
            if (!enableCoins.getBoolean(true))
            {
            	coinMinting.set(false);
            	coinChanging.set(false);
            	coinSmelting.set(false);
            }
            enableUpgrades = c.get("General", "Enable Upgrades", true);
            enableUpgrades.comment = "Set to false to stop upgrades from initializing";
            if (!enableUpgrades.getBoolean(true))
            {
            	upgradeRecipes.set(false);
            }
            
        }
        catch (Exception e)
        {
            ArtificeCore.logger.log(Level.SEVERE, "Artifice couldn't load the config file");
            e.printStackTrace();
        }
        finally
        {
            c.save();
        }
        
        setDimBlacklist();
        setStoneList(true);
        setStoneList(false);
    }
    
    public static void initClient(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();
            tooltips = c.get("Client", "Tooltips", true);
            tooltips.comment = "Set to false to turn off tooltips";
            flavorText = c.get("Client", "Flavor Text", true);
            flavorText.comment = "Set to false to turn off flavor text in tooltips";
        }
        catch (Exception e)
        {
            ArtificeCore.logger.log(Level.SEVERE, "Artifice couldn't load the config file");
            e.printStackTrace();
        }
        finally
        {
            c.save();
        }
    }
    
    private static void setDimBlacklist()
    {
        String blacklist = dimensionBlacklist.getString().trim();
        
        for (String dim : blacklist.split(","))
        {
            try
            {
                Integer dimID = Integer.parseInt(dim);
                ArtificeRegistry.registerDimensionBlacklist(dimID);
            }
            catch (Exception e)
            {
            	ArtificeCore.logger.log(Level.WARNING, "Artifice couldn't load the dimension blacklist from string");
            }
        }
    }
    
    private static void setStoneList(boolean isMarble)
    {
    	String stoneList;
    	if (isMarble)
    		stoneList = marbleList.getString().trim();
    	else
    		stoneList = basaltList.getString().trim();
    	
		for (String pair : stoneList.split(","))
		{
			try
			{
				Integer ID = Integer.parseInt(pair.split(":")[0]);
				Integer meta = Integer.parseInt(pair.split(":")[1]);
				if (isMarble)
					ArtificeRegistry.registerMarbleType(ID, meta);
				else
					ArtificeRegistry.registerBasaltType(ID, meta);
			}
			catch (Exception e)
			{
				if (isMarble)
					ArtificeCore.logger.log(Level.WARNING, "Artifice couldn't load the marble list from string");
				else
					ArtificeCore.logger.log(Level.WARNING, "Artifice couldn't load the basalt list from string");
			}
		}
    }
    
    public static void setConfigFolderBase(File folder)
    {
        configFolder = new File(folder.getAbsolutePath() + "/shukaro/artifice/");
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerConnectedTextures(IconRegister reg)
    {
    	if (!ArtificeConfig.connectedTexturesRegistered)
    	{
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasicFrame, "frame/basic");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.ReinforcedFrame, "frame/reinforced");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.IndustrialFrame, "frame/industrial");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.AdvancedFrame, "frame/advanced");
    		
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasicScaffold, "scaffold/basic");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.ReinforcedScaffold, "scaffold/reinforced");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.IndustrialScaffold, "scaffold/industrial");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.AdvancedScaffold, "scaffold/advanced");
    		
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasicBlastWall, "blastwall/basic");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.ReinforcedBlastWall, "blastwall/reinforced");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.IndustrialBlastWall, "blastwall/industrial");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.AdvancedBlastWall, "blastwall/advanced");
    		
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasicGlassWall, "glasswall/basic");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.ReinforcedGlassWall, "glasswall/reinforced");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.IndustrialGlassWall, "glasswall/industrial");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.AdvancedGlassWall, "glasswall/advanced");
    		
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.MarblePaver, "marble/paver");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.MarbleAntipaver, "marble/antipaver");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasaltPaver, "basalt/paver");
    		IconHandler.registerConnectedTexture(reg, ConnectedTextures.BasaltAntipaver, "basalt/antipaver");
    		
    		ArtificeConfig.connectedTexturesRegistered = true;
    	}
    }
}
