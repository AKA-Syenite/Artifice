package shukaro.artifice;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;

public class ArtificeConfig
{
    public static int frameRenderID;
    public static int lotusRenderID;

    private static Property idStart;

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
    private static Property marbleList;
    private static Property basaltList;
    public static Property maxSharpness;
    public static Property maxEfficiency;
    public static Property maxUnbreaking;
    public static Property maxPunch;
    public static Property maxPower;
    public static Property maxKnockback;
    public static Property maxThorns;
    public static Property maxProtection;
    public static Property maxProjectileProtection;
    public static Property maxFeatherFalling;
    public static Property maxFireProtection;
    public static Property maxBlastProtection;
    public static Property maxRespiration;
    public static Property maxAquaAffinity;

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

    private static Property dimensionBlacklist;

    public static boolean connectedTexturesRegistered = false;

    public static void initCommon(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();
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

            regenBasaltLayer = c.get("World Regeneration", "Regenerate Basalt Layer", false);
            regenMarbleLayer = c.get("World Regeneration", "Regenerate Marble Layer", false);
            regenBasaltClusters = c.get("World Regeneration", "Regenerate Basalt Clusters", false);
            regenMarbleClusters = c.get("World Regeneration", "Regenerate Marble Clusters", false);
            regenBasaltCaves = c.get("World Regeneration", "Regenerate Basalt Caves", false);
            regenMarbleCaves = c.get("World Regeneration", "Regenerate Marble Caves", false);
            regenFlora = c.get("World Regeneration", "Regenerate Flora", false);
            regenLotus = c.get("World Regeneration", "Regenerate Lotus Lilies", false);
            regenKey = c.get("World Regeneration", "Regen Key", "DEFAULT");
            c.addCustomCategoryComment("World Regeneration", "Set the features you want to regenerate or not to true/false, and change the Regen Key to begin regeneration on next startup");

            floraRecipes = c.get("Recipes", "Flower Recipes", true);
            basaltRecipes = c.get("Recipes", "Basalt Recipes", true);
            marbleRecipes = c.get("Recipes", "Marble Recipes", true);
            sledgeRecipes = c.get("Recipes", "Sledge Recipes", true);
            frameRecipes = c.get("Recipes", "Frame Recipes", true);
            detectorRecipe = c.get("Recipes", "Detector Recipe", true);
            steelSmelting = c.get("Recipes", "Steel Smelting", true);
            steelSmelting.comment = "Set to false to prevent the smelting of steel";
            alternateSteel = c.get("Recipes", "Alternate Steel Recipe", false);
            alternateSteel.comment = "Set to true to enable an alternate steel recipe crafts steel dust from an iron ingot and coal/charcoal";
            alternateSteelRequirement = c.get("Recipes", "Alternate Steel Recipe Fuel Requirement", 2);
            alternateSteelRequirement.comment = "The amount of extra coal/charcoal each pile of steel dust will require to craft on a workbench. Minimum of 1, maximum of 8";
            blastWallRecipes = c.get("Recipes", "Blast Wall Recipes", true);
            glassWallRecipes = c.get("Recipes", "Glass Wall Recipes", true);
            scaffoldRecipes = c.get("Recipes", "Scaffolding Recipes", true);
            boxRecipes = c.get("Recipes", "Box Recipes", true);
            stoneCycleRecipes = c.get("Recipes", "Stone Cycling Recipes", true);
            stoneCycleRecipes.comment = "Set to false to prevent cycling through types of stone as defined in the Basalt/Marble lists";
            sickleRecipes = c.get("Recipes", "Sickle Recipes", true);
            coinMinting = c.get("Recipes", "Coin Minting", true);
            coinMinting.comment = "Whether or not to allow coins to be minted from nuggets";
            coinChanging = c.get("Recipes", "Coin Changing", true);
            coinChanging.comment = "Whether or not to allow coins to be traded up or down for other coins";
            coinSmelting = c.get("Recipes", "Coin Smelting", false);
            coinSmelting.comment = "Whether or not to allow coins to be smelted back into nuggets";
            upgradeRecipes = c.get("Recipes", "Upgrade Recipes", true);
            convenienceRecipes = c.get("Recipes", "Convenience Recipes", true);
            convenienceRecipes.comment = "Set to false to prevent convenience crafting (Smelt stone bricks to cracked variant, craft mossy brick/cobble variants with water bucket, craft 2 chiseled brick from 2 normal).";
            c.addCustomCategoryComment("Recipes", "Controls whether or not to make recipes available for use");

            dimensionBlacklist = c.get("Compatibility", "Dimension Blacklist", "");
            dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in. (No spaces)";
            floraBoneMeal = c.get("Compatibility", "Bonemeal Flowers", true);
            floraBoneMeal.comment = "Set to false to disable random flower growth from bonemeal";
            marbleList = c.get("Compatibility", "Marble List", "");
            marbleList.comment = "Comma-seperated list of ID:Meta pairs to add to a cycling chain of 1:1 marble exchange recipes. No spaces or quotes, meta is required.";
            basaltList = c.get("Compatibility", "Basalt List", "");
            basaltList.comment = "Comma-seperated list of ID:Meta pairs to add to a cycling chain of 1:1 basalt exchange recipes. No spaces or quotes, meta is required.";

            limitUpgrades = c.get("Upgrades", "Limit Upgrades", true);
            limitUpgrades.comment = "If true, caps the maximum enchant level that can be applied through upgrades to the configured levels, if not set they will cap at the natural level cap";
            maxSharpness = c.get("Upgrades", "Max Sharpness Level", 3);
            maxEfficiency = c.get("Upgrades", "Max Efficiency Level", 3);
            maxUnbreaking = c.get("Upgrades", "Max Unbreaking Level", 2);
            maxPunch = c.get("Upgrades", "Max Punch Level", 2);
            maxPower = c.get("Upgrades", "Max Power Level", 3);
            maxKnockback = c.get("Upgrades", "Max Knockback Level", 2);
            maxThorns = c.get("Upgrades", "Max Thorns Level", 2);
            maxProtection = c.get("Upgrades", "Max Protection Level", 3);
            maxProjectileProtection = c.get("Upgrades", "Max Projectile Protection Level", 3);
            maxFeatherFalling = c.get("Upgrades", "Max Feather Falling Level", 3);
            maxFireProtection = c.get("Upgrades", "Max Fire Protection Level", 3);
            maxBlastProtection = c.get("Upgrades", "Max Blast Protection Level", 3);
            maxRespiration = c.get("Upgrades", "Max Respiration Level", 3);
            maxAquaAffinity = c.get("Upgrades", "Max Aqua Affinity Level", 1);
            c.addCustomCategoryComment("Upgrades", "Maximum level of enchantment allowed for each enchantment via upgrades");

            enableFrames = c.get("General", "Enable Frames", true);
            if (!enableFrames.getBoolean(true))
            {
                frameRecipes.set(false);
                detectorRecipe.set(false);
                blastWallRecipes.set(false);
                glassWallRecipes.set(false);
                scaffoldRecipes.set(false);
            }
            enableSledges = c.get("General", "Enable Sledges", true);
            if (!enableSledges.getBoolean(true))
            {
                sledgeRecipes.set(false);
            }
            enableSteel = c.get("General", "Enable Steel", true);
            if (!enableSteel.getBoolean(true))
            {
                steelSmelting.set(false);
                alternateSteel.set(false);
            }
            enableWorldGen = c.get("General", "Enable Worldgen", true);
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
            if (!enableBoxes.getBoolean(true))
            {
                boxRecipes.set(false);
            }
            enableSickles = c.get("General", "Enable Sickles", true);
            if (!enableSickles.getBoolean(true))
            {
                sickleRecipes.set(false);
            }
            enableCoins = c.get("General", "Enable Coins", true);
            if (!enableCoins.getBoolean(true))
            {
                coinMinting.set(false);
                coinChanging.set(false);
                coinSmelting.set(false);
            }
            enableUpgrades = c.get("General", "Enable Upgrades", true);
            if (!enableUpgrades.getBoolean(true))
            {
                upgradeRecipes.set(false);
            }
            c.addCustomCategoryComment("General", "Enable or disable mod features completely");
        }
        catch (Exception e)
        {
            ArtificeCore.logger.error("Couldn't load the config file");
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
            tooltips = c.get("Client", "Enable Tooltips", true);
            tooltips.comment = "Set to false to turn off tooltips";
            flavorText = c.get("Client", "Tooltip Flavor Text", true);
            flavorText.comment = "Set to false to turn off flavor text in tooltips";
        }
        catch (Exception e)
        {
            ArtificeCore.logger.error("Couldn't load the config file");
            e.printStackTrace();
        }
        finally
        {
            c.save();
        }
    }

    private static void setDimBlacklist()
    {
        ArtificeCore.logger.info("Reading the dimension blacklist");
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
                ArtificeCore.logger.warn("Couldn't load the dimension blacklist from string");
            }
        }
    }

    private static void setStoneList(boolean isMarble)
    {
        String stoneList;
        if (isMarble)
        {
            ArtificeCore.logger.info("Reading the marble list");
            stoneList = marbleList.getString().trim();
        }
        else
        {
            ArtificeCore.logger.info("Reading the basalt list");
            stoneList = basaltList.getString().trim();
        }

        for (String pair : stoneList.split(","))
        {
            try
            {
                String itemName = pair.split(";")[0];
                Integer meta = Integer.parseInt(pair.split(";")[1]);
                if (isMarble)
                    ArtificeRegistry.registerMarbleType((Block) Block.blockRegistry.getObject(itemName), meta);
                else
                    ArtificeRegistry.registerBasaltType((Block) Block.blockRegistry.getObject(itemName), meta);
            }
            catch (Exception e)
            {
                if (isMarble)
                    ArtificeCore.logger.warn("Couldn't load the marble list from string");
                else
                    ArtificeCore.logger.warn("Couldn't load the basalt list from string");
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerConnectedTextures(IIconRegister reg)
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
