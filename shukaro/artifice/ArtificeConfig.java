package shukaro.artifice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ArtificeConfig
{
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
    
    public static Property enableFrames;
    public static Property enableSledges;
    public static Property enableSteel;
    public static Property enableWorldGen;
    public static Property enableBoxes;
    public static Property enableSickles;
    public static Property enableCoins;
    public static Property enableUpgrades;
    public static Property limitUpgrades;
    
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
            
            floraWorldGen = c.get("World Generation", "Generate Flowers", true);
            floraWorldGen.comment = "Whether or not to generate flowers";
            floraFrequency = c.get("World Generation", "Flower Frequency", 1);
            floraFrequency.comment = "Number of times to attempt to place flowers in each chunk (Default 1)";
            lotusWorldGen = c.get("World Generation", "Generate Lotus Lilies", true);
            lotusWorldGen.comment = "Whether or not to generate lotus lilies";
            lotusFrequency = c.get("World Generation", "Lotus Liy Frequency", 1);
            lotusFrequency.comment = "Number of times to attempt to place lotus lilies in each chunk (Default 1)";
            basaltLayerWorldGen = c.get("World Generation", "Generate Basalt Layer", true);
            basaltLayerWorldGen.comment = "Whether or not to generate a basalt layer (Default True)";
            basaltLayerMinHeight = c.get("World Generation", "Basalt Layer Min Height", -2);
            basaltLayerMinHeight.comment = "Average minimum height for basalt layer (Default -2)";
            basaltLayerMaxHeight = c.get("World Generation", "Basalt Layer Max Height", 7);
            basaltLayerMaxHeight.comment = "Average maximum height for basalt layer (Default 7)";
            basaltClusterWorldGen = c.get("World Generation", "Generate Basalt Clusters", false);
            basaltClusterWorldGen.comment = "Whether or not to generate basalt clusters (Default false)";
            basaltClusterSize = c.get("World Generation", "Basalt Cluster Size", 200);
            basaltClusterSize.comment = "Average size of basalt clusters in the world (Default 200)";
            basaltClusterHeight = c.get("World Generation", "Basalt Cluster Height", 64);
            basaltClusterHeight.comment = "Max height to begin basalt cluster generation (Default 64)";
            basaltClusterFrequency = c.get("World Generation", "Basalt Cluster Frequency", 4);
            basaltClusterFrequency.comment = "Number of times to attempt to place basalt clusters in each chunk (Default 4)";
            marbleLayerWorldGen = c.get("World Generation", "Generate Marble Layer", false);
            marbleLayerWorldGen.comment = "Whether or not to generate a marble layer (Default false)";
            marbleLayerMinHeight = c.get("World Generation", "Marble Layer Min Height", -2);
            marbleLayerMinHeight.comment = "Average minimum height for marble layer (Default -2)";
            marbleLayerMaxHeight = c.get("World Generation", "Marble Layer Max Height", 14);
            marbleLayerMaxHeight.comment = "Average maximum height for marble layer (Default 14)";
            marbleClusterWorldGen = c.get("World Generation", "Generate Marble Clusters", true);
            marbleClusterWorldGen.comment = "Whether or not to generate marble clusters (Default true)";
            marbleClusterSize = c.get("World Generation", "Marble Cluster Size", 200);
            marbleClusterSize.comment = "Average size of marble clusters in the world (Default 200)";
            marbleClusterHeight = c.get("World Generation", "Marble Cluster Height", 128);
            marbleClusterHeight.comment = "Max height to begin marble cluster generation (Default 128)";
            marbleClusterFrequency = c.get("World Generation", "Marble Cluster Frequency", 4);
            marbleClusterFrequency.comment = "Number of times to attempt to place marble clusters in each chunk (Default 4)";
            basaltCaveWorldGen = c.get("World Generation", "Basalt Cave Worldgen", false);
            basaltCaveWorldGen.comment = "Whether or not to generate basalt caves (Default false) [WARNING]: This loads many chunks, I suggest using regeneration to generate after initial map creation";
            basaltCaveSize = c.get("World Generation", "Basalt Cave Size", 2000);
            basaltCaveSize.comment = "Average size of basalt caves (Default 400)";
            basaltCaveHeight = c.get("World Generation", "Basalt Cave Height", 64);
            basaltCaveHeight.comment = "Maximum height to begin basalt cave generation (Default 64)";
            basaltCaveFrequency = c.get("World Generation", "Basalt Cave Frequency", 4);
            basaltCaveFrequency.comment = "Number of times to attempt to generate basalt caves in each chunk";
            basaltCaveAdherence = c.get("World Generation", "Basalt Cave Adherence", 75);
            basaltCaveAdherence.comment = "How much generation 'sticks' to walls, 0-100, higher numbers lead to more extensive caves with thinner walls (Default 75)";
            marbleCaveWorldGen = c.get("World Generation", "Marble Cave Worldgen", false);
            marbleCaveWorldGen.comment = "Whether or not to generate marble caves (Default false) [WARNING]: This loads many chunks, I suggest using regeneration to generate after initial map creation";
            marbleCaveSize = c.get("World Generation", "Marble Cave Size", 2000);
            marbleCaveSize.comment = "Average size of marble caves (Default 400)";
            marbleCaveHeight = c.get("World Generation", "Marble Cave Height", 64);
            marbleCaveHeight.comment = "Maximum height to begin marble cave generation (Default 128)";
            marbleCaveFrequency = c.get("World Generation", "Marble Cave Frequency", 4);
            marbleCaveFrequency.comment = "Number of times to attempt to generate marble caves";
            marbleCaveAdherence = c.get("World Generation", "Marble Cave Adherence", 75);
            marbleCaveAdherence.comment = "How much generation 'sticks' to walls, 0-100, higher numbers lead to more extensive caves with thinner walls (Default 75)";
            dimensionBlacklist = c.get("World Generation", "Dimension Blacklist", "");
            dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in. (No spaces)";
            regenBasaltLayer = c.get("World Generation", "Regenerate Basalt Layer", false);
            regenBasaltLayer.comment = "Set to true to regenerate the basalt layer";
            regenMarbleLayer = c.get("World Generation", "Regenerate Marble Layer", false);
            regenMarbleLayer.comment = "Set to true to regenerate the marble layer";
            regenBasaltClusters = c.get("World Generation", "Regenerate Basalt Clusters", false);
            regenBasaltClusters.comment = "Set to true to regenerate basalt clusters";
            regenMarbleClusters = c.get("World Generation", "Regenerate Marble Clusters", false);
            regenMarbleClusters.comment = "Set to true to regenerate marble clusters";
            regenBasaltCaves = c.get("World Generation", "Regenerate Basalt Caves", false);
            regenBasaltCaves.comment = "Set to true to regenerate basalt caves";
            regenMarbleCaves = c.get("World Generation", "Regenerate Marble Caves", false);
            regenMarbleCaves.comment = "Set to true to regenerate marble caves";
            regenFlora = c.get("World Generation", "Regenerate Flora", false);
            regenFlora.comment = "Set to true to regenerate flowers";
            regenLotus = c.get("World Generation", "Regenerate Lotus Lilies", false);
            regenLotus.comment = "Set to true to regenerate lotus lilies";
            regenKey = c.get("World Generation", "Regen Key", "DEFAULT");
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
            
            limitUpgrades = c.get("General", "Limit Upgrades", true);
            limitUpgrades.comment = "If true, caps the maximum enchant level that can be applied through upgrades to below the natural cap (Shown in Tooltips)";
            floraBoneMeal = c.get("General", "Bonemeal Flowers", true);
            floraBoneMeal.comment = "Set to false to disable random flower growth from bonemeal";
            
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
            }
        }
    }
    
    public static void setConfigFolderBase(File folder)
    {
        configFolder = new File(folder.getAbsolutePath() + "/shukaro/artifice/");
    }
    
    public static void extractLang(String[] languages)
    {
        String langResourceBase = "/shukaro/artifice/lang/";
        for (String lang : languages)
        {
            InputStream is = ArtificeCore.instance.getClass().getResourceAsStream(langResourceBase + lang + ".lang");
            try
            {
                File f = new File(configFolder.getAbsolutePath() + "/lang/" + lang + ".lang");
                if (!f.exists())
                    f.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = is.read(buffer)) != -1)
                {
                    os.write(buffer, 0, read);
                }
                is.close();
                os.flush();
                os.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void loadLang()
    {
        File f = new File(configFolder.getAbsolutePath() + "/lang/");
        for (File langFile : f.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".lang");
            }
        }))
        {
            try
            {
                Properties langPack = new Properties();
                langPack.load(new FileInputStream(langFile));
                String lang = langFile.getName().replace(".lang", "");
                LanguageRegistry.instance().addStringLocalization(langPack, lang);
            }
            catch (FileNotFoundException x)
            {
                x.printStackTrace();
            }
            catch (IOException x)
            {
                x.printStackTrace();
            }
        }
    }
        
    public static void registerConnectedTextures(IconRegister reg)
    {
    	if (!ArtificeConfig.connectedTexturesRegistered)
    	{
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.BasicFrame, "frame/basic");
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.ReinforcedFrame, "frame/reinforced");
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.IndustrialFrame, "frame/industrial");
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.AdvancedFrame, "frame/advanced");
    		
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.MarblePaver, "marble/paver");
    		ConnectedTexture.MarbleAntipaver.textureList = ConnectedTexture.MarblePaver.textureList;
    		IconHandler.registerConnectedTexture(reg, ConnectedTexture.BasaltPaver, "basalt/paver");
    		ConnectedTexture.BasaltAntipaver.textureList = ConnectedTexture.BasaltPaver.textureList;
    		
    		ArtificeConfig.connectedTexturesRegistered = true;
    	}
    }
}
