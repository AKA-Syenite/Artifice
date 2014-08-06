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
    public static int rockRenderID;
    public static int oreRenderID;

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
    public static Property lampRecipes;

    public static Property enableFrames;
    public static Property enableSledges;
    public static Property enableSteel;
    public static Property enableWorldGen;
    public static Property enableBoxes;
    public static Property enableSickles;
    public static Property enableCoins;
    public static Property enableUpgrades;
    public static Property enableLamps;
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

    public static String[] rockNames = { "Basalt", "Marble", "Gray Limestone", "Light Gray Limestone", "Brown Limestone", "Tan Limestone", "Red Limestone", "Blue Limestone", "Green Limestone" };

    public static Property[] rockLayersGen = new Property[rockNames.length];
    public static Property[] rockLayersMinHeight = new Property[rockNames.length];
    public static Property[] rockLayersMaxHeight = new Property[rockNames.length];

    public static Property[] rockClustersGen = new Property[3];
    public static Property[] rockClustersSize = new Property[3];
    public static Property[] rockClustersMinHeight = new Property[3];
    public static Property[] rockClustersMaxHeight = new Property[3];
    public static Property[] rockClustersFrequency = new Property[3];

    public static Property[] rockCavesGen = new Property[3];
    public static Property[] rockCavesSize = new Property[3];
    public static Property[] rockCavesMinHeight = new Property[3];
    public static Property[] rockCavesMaxHeight = new Property[3];
    public static Property[] rockCavesFrequency = new Property[3];

    public static Property floraRecipes;
    public static Property basaltRecipes;
    public static Property marbleRecipes;
    public static Property oreRecipes;
    public static Property limestoneRecipes;
    public static Property floraBoneMeal;

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
            floraFrequency = c.get("Plant Generation", "Percentage Flower Frequency", 100);

            lotusWorldGen = c.get("Plant Generation", "Generate Lotus Lilies", true);
            lotusFrequency = c.get("Plant Generation", "Percentage Lotus Lily Frequency", 100);

            for (int i=0; i< rockNames.length; i++)
            {
                String name = rockNames[i];

                if (i == 0)
                {
                    rockLayersGen[i] = c.get("Layer Generation", "Generate " + name + "Layer", true);
                    rockLayersMinHeight[i] = c.get("Layer Generation", "Minimum " + name + " Layer Height", 0);
                    rockLayersMaxHeight[i] = c.get("Layer Generation", "Maximum " + name + " Layer Height", 10);
                }
                else
                {
                    rockLayersGen[i] = c.get("Layer Generation", "Generate " + name + "Layer", false);
                    rockLayersMinHeight[i] = c.get("Layer Generation", "Minimum " + name + " Layer Height", 60);
                    rockLayersMaxHeight[i] = c.get("Layer Generation", "Maximum " + name + " Layer Height", 70);
                }

                if (i == 0)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate " + name + " Clusters", false);
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum " + name + " Cluster Height", 0);
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum " + name + " Cluster Height", 64);
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of " + name + " Clusters", 200);
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of " + name + " Clusters", 100);
                }
                else if (i == 1)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate " + name + " Clusters", true);
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum " + name + " Cluster Height", 0);
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum " + name + " Cluster Height", 128);
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of " + name + " Clusters", 200);
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of " + name + " Clusters", 100);
                }
                else if (i == 2)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate Limestone Clusters", true);
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum Limestone Cluster Height", 0);
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum Limestone Cluster Height", 128);
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of Limestone Clusters", 200);
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of Limestone Clusters", 100);
                }

                if (i == 2)
                {
                    rockCavesGen[i] = c.get("Cave Generation", "Generate Limestone Caves", true);
                    rockCavesMinHeight[i] = c.get("Cave Generation", "Minimum Limestone Cave Height", 0);
                    rockCavesMaxHeight[i] = c.get("Cave Generation", "Maximum Limestone Cave Height", 128);
                    rockCavesSize[i] = c.get("Cave Generation", "Average Size of Limestone Caves", 2000);
                    rockCavesFrequency[i] = c.get("Cave Generation", "Percentage Frequency of Limestone Caves", 100);
                }
                if (i <= 1)
                {
                    rockCavesGen[i] = c.get("Cave Generation", "Generate " + name + " Caves", false);
                    rockCavesMinHeight[i] = c.get("Cave Generation", "Minimum " + name + " Cave Height", 0);
                    rockCavesMaxHeight[i] = c.get("Cave Generation", "Maximum " + name + " Cave Height", 128);
                    rockCavesSize[i] = c.get("Cave Generation", "Average Size of " + name + " Caves", 2000);
                    rockCavesFrequency[i] = c.get("Cave Generation", "Percentage Frequency of " + name + " Caves", 100);
                }
            }

            floraRecipes = c.get("Recipes", "Flower Recipes", true);
            basaltRecipes = c.get("Recipes", "Basalt Recipes", true);
            marbleRecipes = c.get("Recipes", "Marble Recipes", true);
            limestoneRecipes = c.get("Recipes", "Limestone Recipes", true);
            oreRecipes = c.get("Recipes", "Ore Recipes", true);
            sledgeRecipes = c.get("Recipes", "Sledge Recipes", true);
            frameRecipes = c.get("Recipes", "Frame Recipes", true);
            detectorRecipe = c.get("Recipes", "Detector Recipe", true);
            steelSmelting = c.get("Recipes", "Steel Smelting", true);
            steelSmelting.comment = "Allow the smelting of steel directly from iron";
            alternateSteel = c.get("Recipes", "Alternate Steel Recipe", false);
            alternateSteel.comment = "Enable an alternate steel recipe which crafts steel dust from an iron ingot and coal/charcoal";
            alternateSteelRequirement = c.get("Recipes", "Alternate Steel Recipe Fuel Requirement", 2);
            alternateSteelRequirement.comment = "Amount of extra coal or charcoal the alternate recipe takes. Minimum of 1, maximum of 8";
            blastWallRecipes = c.get("Recipes", "Blast Wall Recipes", true);
            glassWallRecipes = c.get("Recipes", "Glass Wall Recipes", true);
            scaffoldRecipes = c.get("Recipes", "Scaffolding Recipes", true);
            boxRecipes = c.get("Recipes", "Box Recipes", true);
            stoneCycleRecipes = c.get("Recipes", "Stone Cycling Recipes", true);
            stoneCycleRecipes.comment = "Allow cycling through types of stone as defined in the Basalt/Marble/Limestone lists";
            sickleRecipes = c.get("Recipes", "Sickle Recipes", true);
            coinMinting = c.get("Recipes", "Coin Minting", true);
            coinMinting.comment = "Allow coins to be minted from nuggets";
            coinChanging = c.get("Recipes", "Coin Changing", true);
            coinChanging.comment = "Allow coins to be traded up or down for other coins";
            coinSmelting = c.get("Recipes", "Coin Smelting", true);
            coinSmelting.comment = "Allow coins to be smelted back into nuggets";
            upgradeRecipes = c.get("Recipes", "Upgrade Recipes", true);
            convenienceRecipes = c.get("Recipes", "Convenience Recipes", true);
            convenienceRecipes.comment = "Smelt stone bricks to cracked variant, craft mossy brick/cobble variants with water bucket, craft 2 chiseled brick from 2 normal.";
            lampRecipes = c.get("Recipes", "Lamp Recipes", true);
            c.addCustomCategoryComment("Recipes", "Controls whether or not recipes are available for use");

            dimensionBlacklist = c.get("Compatibility", "Dimension Blacklist", "");
            dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in. (No spaces)";
            floraBoneMeal = c.get("Compatibility", "Bonemeal Flowers", true);
            floraBoneMeal.comment = "Allow flower growth from bonemeal";
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
                for (int i=0; i< rockNames.length; i++)
                {
                    rockLayersGen[i].set(false);
                    rockClustersGen[i].set(false);
                    rockCavesGen[i].set(false);
                }
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
            enableLamps = c.get("General", "Enable Lamps", true);
            if (!enableLamps.getBoolean(true))
            {
                lampRecipes.set(false);
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

    private static void setStoneList(boolean isMarble)
    {
        String stoneList;
        if (isMarble && marbleList != null)
        {
            ArtificeCore.logger.info("Reading the marble list");
            stoneList = marbleList.getString().trim();
        }
        else if (basaltList != null)
        {
            ArtificeCore.logger.info("Reading the basalt list");
            stoneList = basaltList.getString().trim();
        }
        else
            return;

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
            IconHandler.registerConnectedTexture(reg, ConnectedTextures.LimestonePaver, "limestone/paver");
            IconHandler.registerConnectedTexture(reg, ConnectedTextures.LimestoneAntipaver, "limestone/antipaver");

            IconHandler.registerConnectedTexture(reg, ConnectedTextures.LampNormal, "lamp/normal");
            IconHandler.registerConnectedTexture(reg, ConnectedTextures.LampInverted, "lamp/inverted");

            ArtificeConfig.connectedTexturesRegistered = true;
        }
    }
}
