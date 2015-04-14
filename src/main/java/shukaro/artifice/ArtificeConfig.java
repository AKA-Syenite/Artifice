package shukaro.artifice;

import cofh.lib.util.helpers.MathHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ArtificeConfig
{
    public static final String[] tiers = {"Basic", "Reinforced", "Industrial", "Advanced"};
    public static final String[] flora = {"Bluebell", "Orchid", "Iris", "Lotus", "LotusClosed"};
    public static final String[] rocks = {"", "Cobblestone", "Brick", "Paver", "Antipaver", "Chiseled"};
    public static final String[] rockNames = { "Basalt", "Marble", "Gray Limestone", "Light Gray Limestone", "Brown Limestone", "Tan Limestone", "Red Limestone", "Blue Limestone", "Green Limestone" };

    public static int frameRenderID;
    public static int lotusRenderID;
    public static int oreRenderID;
    public static int ctmRenderID;

    public static boolean sledgeRecipes;
    public static boolean frameRecipes;
    public static boolean detectorRecipe;
    public static boolean steelSmelting;
    public static boolean alternateSteel;
    public static int alternateSteelRequirement;
    public static boolean blastWallRecipes;
    public static boolean glassWallRecipes;
    public static boolean scaffoldRecipes;
    public static boolean boxRecipes;
    public static boolean stoneCycleRecipes;
    public static boolean sickleRecipes;
    public static boolean coinMinting;
    public static boolean coinChanging;
    public static boolean coinSmelting;
    public static boolean upgradeRecipes;
    public static boolean convenienceRecipes;
    public static boolean lampRecipes;
    public static boolean craftKitRecipe;

    public static boolean limitUpgrades;
    public static int maxSharpness;
    public static int maxEfficiency;
    public static int maxUnbreaking;
    public static int maxPunch;
    public static int maxPower;
    public static int maxKnockback;
    public static int maxThorns;
    public static int maxProtection;
    public static int maxProjectileProtection;
    public static int maxFeatherFalling;
    public static int maxFireProtection;
    public static int maxBlastProtection;
    public static int maxRespiration;
    public static int maxAquaAffinity;

    public static boolean floraWorldGen;
    public static int floraFrequency;

    public static boolean lotusWorldGen;
    public static int lotusFrequency;

    public static boolean sulfurGen;
    public static int sulfurSize;
    public static int sulfurFrequency;

    public static boolean niterGen;
    public static int niterSize;
    public static int niterFrequency;

    public static boolean oilLakeGen;
    public static int oilLakeFrequency;

    public static boolean[] rockLayersGen = new boolean[rockNames.length];
    public static int[] rockLayersMinHeight = new int[rockNames.length];
    public static int[] rockLayersMaxHeight = new int[rockNames.length];

    public static boolean[] rockClustersGen = new boolean[3];
    public static int[] rockClustersSize = new int[3];
    public static int[] rockClustersMinHeight = new int[3];
    public static int[] rockClustersMaxHeight = new int[3];
    public static int[] rockClustersFrequency = new int[3];

    public static boolean[] rockCavesGen = new boolean[3];
    public static int[] rockCavesSize = new int[3];
    public static int[] rockCavesMinHeight = new int[3];
    public static int[] rockCavesMaxHeight = new int[3];
    public static int[] rockCavesFrequency = new int[3];

    public static boolean volcanoGen;
    public static int volcanoFrequency;

    public static boolean floraRecipes;
    public static boolean basaltRecipes;
    public static boolean marbleRecipes;
    public static boolean oreRecipes;
    public static boolean limestoneRecipes;
    public static boolean resourceRecipes;
    public static boolean floraBoneMeal;

    public static boolean flavorText;
    public static boolean tooltips;

    public static boolean spawnEndermen;
    public static boolean enderOreRecipe;
    public static boolean enderOreGen;
    public static int enderOreFrequency;
    public static int enderOreSize;
    public static int enderOreMinHeight;
    public static int enderOreMaxHeight;

    public static boolean glowSandRecipe;
    public static boolean coloredTorchRecipes;
    public static boolean attunedRecipes;
    public static boolean logicRecipes;

    public static int enchantmentStartID;
    public static int enchantmentInvisibleWeight;
    public static boolean enchantmentInvisibleEnable;
    public static int enchantmentSoulstealingWeight;
    public static int enchantmentSoulstealingBonus;
    public static boolean enchantmentSoulstealingEnable;
    public static int enchantmentResistanceWeight;
    public static boolean enchantmentResistanceEnable;

    private static String marbleList;
    private static String basaltList;
    private static String dimensionBlacklist;

    public static void initCommon(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();

            enderOreGen = c.get("Resource Generation", "Generate Ender Ore", true).getBoolean();
            enderOreFrequency = c.get("Resource Generation", "Percentage Ender Ore Frequency", 150).getInt();
            enderOreSize = c.get("Resource Generation", "Ender Ore Deposit Size", 10).getInt();
            enderOreMinHeight = c.get("Resource Generation", "Ender Ore Minimum Height", 0).getInt();
            enderOreMaxHeight = c.get("Resource Generation", "Ender Ore Maximum Height", 40).getInt();
            spawnEndermen = c.get("General", "Spawn Endermen from Ender Ore", true).getBoolean();
            enderOreRecipe = c.get("Recipes", "Allow crafting of ender pearls from dust", true).getBoolean();

            glowSandRecipe = c.get("Recipes", "Allow crafting of glowsand", true).getBoolean();
            coloredTorchRecipes = c.get("Recipes", "Allow crafting of colored torches", true).getBoolean();
            attunedRecipes = c.get("Recipes", "Allow crafting of attuned redstone blocks", true).getBoolean();
            logicRecipes = c.get("Recipes", "Allow crafting of logical redstone blocks", true).getBoolean();

            floraWorldGen = c.get("Plant Generation", "Generate Flowers", true).getBoolean();
            floraFrequency = c.get("Plant Generation", "Percentage Flower Frequency", 100).getInt();

            lotusWorldGen = c.get("Plant Generation", "Generate Lotus Lilies", true).getBoolean();
            lotusFrequency = c.get("Plant Generation", "Percentage Lotus Lily Frequency", 100).getInt();

            sulfurGen = c.get("Resource Generation", "Generate Sulfur", true).getBoolean();
            sulfurFrequency = c.get("Resource Generation", "Percentage Sulfur Frequency", 100).getInt();
            sulfurSize = c.get("Resource Generation", "Sulfur Deposit Size", 64).getInt();

            niterGen = c.get("Resource Generation", "Generate Niter", true).getBoolean();
            niterFrequency = c.get("Resource Generation", "Percentage Niter Frequency", 100).getInt();
            niterSize = c.get("Resource Generation", "Niter Deposit Size", 64).getInt();

            oilLakeGen = c.get("Resource Generation", "Generate Oil Lakes", true).getBoolean();
            oilLakeFrequency = c.get("Resource Generation", "Percentage Oil lake Frequency", 100).getInt();

            volcanoGen = c.get("Volcano Generation", "Generate Volcanoes", true).getBoolean();
            volcanoFrequency = c.get("Volcano Generation", "Percentage Volcano Frequency", 100).getInt();

            for (int i=0; i< rockNames.length; i++)
            {
                String name = rockNames[i];

                if (i == 0)
                {
                    rockLayersGen[i] = c.get("Layer Generation", "Generate " + name + " Layer", true).getBoolean();
                    rockLayersMinHeight[i] = c.get("Layer Generation", "Minimum " + name + " Layer Height", 0).getInt();
                    rockLayersMaxHeight[i] = c.get("Layer Generation", "Maximum " + name + " Layer Height", 10).getInt();
                }
                else
                {
                    rockLayersGen[i] = c.get("Layer Generation", "Generate " + name + " Layer", false).getBoolean();
                    rockLayersMinHeight[i] = c.get("Layer Generation", "Minimum " + name + " Layer Height", 60).getInt();
                    rockLayersMaxHeight[i] = c.get("Layer Generation", "Maximum " + name + " Layer Height", 70).getInt();
                }

                if (i == 0)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate " + name + " Clusters", false).getBoolean();
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum " + name + " Cluster Height", 0).getInt();
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum " + name + " Cluster Height", 64).getInt();
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of " + name + " Clusters", 200).getInt();
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of " + name + " Clusters", 100).getInt();
                }
                else if (i == 1)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate " + name + " Clusters", true).getBoolean();
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum " + name + " Cluster Height", 0).getInt();
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum " + name + " Cluster Height", 128).getInt();
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of " + name + " Clusters", 200).getInt();
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of " + name + " Clusters", 100).getInt();
                }
                else if (i == 2)
                {
                    rockClustersGen[i] = c.get("Cluster Generation", "Generate Limestone Clusters", true).getBoolean();
                    rockClustersMinHeight[i] = c.get("Cluster Generation", "Minimum Limestone Cluster Height", 0).getInt();
                    rockClustersMaxHeight[i] = c.get("Cluster Generation", "Maximum Limestone Cluster Height", 128).getInt();
                    rockClustersSize[i] = c.get("Cluster Generation", "Average Size of Limestone Clusters", 200).getInt();
                    rockClustersFrequency[i] = c.get("Cluster Generation", "Percentage Frequency of Limestone Clusters", 100).getInt();
                }

                if (i == 2)
                {
                    rockCavesGen[i] = c.get("Cave Generation", "Generate Limestone Caves", true).getBoolean();
                    rockCavesMinHeight[i] = c.get("Cave Generation", "Minimum Limestone Cave Height", 0).getInt();
                    rockCavesMaxHeight[i] = c.get("Cave Generation", "Maximum Limestone Cave Height", 128).getInt();
                    rockCavesSize[i] = c.get("Cave Generation", "Average Size of Limestone Caves", 2000).getInt();
                    rockCavesFrequency[i] = c.get("Cave Generation", "Percentage Frequency of Limestone Caves", 100).getInt();
                }
                if (i <= 1)
                {
                    rockCavesGen[i] = c.get("Cave Generation", "Generate " + name + " Caves", false).getBoolean();
                    rockCavesMinHeight[i] = c.get("Cave Generation", "Minimum " + name + " Cave Height", 0).getInt();
                    rockCavesMaxHeight[i] = c.get("Cave Generation", "Maximum " + name + " Cave Height", 128).getInt();
                    rockCavesSize[i] = c.get("Cave Generation", "Average Size of " + name + " Caves", 2000).getInt();
                    rockCavesFrequency[i] = c.get("Cave Generation", "Percentage Frequency of " + name + " Caves", 100).getInt();
                }
            }

            floraRecipes = c.get("Recipes", "Flower Recipes", true).getBoolean();
            basaltRecipes = c.get("Recipes", "Basalt Recipes", true).getBoolean();
            marbleRecipes = c.get("Recipes", "Marble Recipes", true).getBoolean();
            limestoneRecipes = c.get("Recipes", "Limestone Recipes", true).getBoolean();
            oreRecipes = c.get("Recipes", "Ore Recipes", true).getBoolean();
            resourceRecipes = c.get("Recipes", "Resource Recipes", true).getBoolean();
            sledgeRecipes = c.get("Recipes", "Sledge Recipes", true).getBoolean();
            frameRecipes = c.get("Recipes", "Frame Recipes", true).getBoolean();
            detectorRecipe = c.get("Recipes", "Detector Recipe", true).getBoolean();
            steelSmelting = c.get("Recipes", "Steel Smelting", true).getBoolean();
            c.get("Recipes", "Steel Smelting", true).comment = "Allow the smelting of steel directly from iron";
            alternateSteel = c.get("Recipes", "Alternate Steel Recipe", false).getBoolean();
            c.get("Recipes", "Alternate Steel Recipe", false).comment = "Enable an alternate steel recipe which crafts steel dust from an iron ingot and coal/charcoal";
            alternateSteelRequirement = c.get("Recipes", "Alternate Steel Recipe Fuel Requirement", 2).getInt();
            c.get("Recipes", "Alternate Steel Recipe Fuel Requirement", 2).comment = "Amount of extra coal or charcoal the alternate recipe takes. Minimum of 1, maximum of 8";
            blastWallRecipes = c.get("Recipes", "Blast Wall Recipes", true).getBoolean();
            glassWallRecipes = c.get("Recipes", "Glass Wall Recipes", true).getBoolean();
            scaffoldRecipes = c.get("Recipes", "Scaffolding Recipes", true).getBoolean();
            boxRecipes = c.get("Recipes", "Box Recipes", true).getBoolean();
            stoneCycleRecipes = c.get("Recipes", "Stone Cycling Recipes", true).getBoolean();
            c.get("Recipes", "Stone Cycling Recipes", true).comment = "Allow cycling through types of stone as defined in the Basalt/Marble/Limestone lists";
            sickleRecipes = c.get("Recipes", "Sickle Recipes", true).getBoolean();
            coinMinting = c.get("Recipes", "Coin Minting", true).getBoolean();
            c.get("Recipes", "Coin Minting", true).comment = "Allow coins to be minted from nuggets";
            coinChanging = c.get("Recipes", "Coin Changing", true).getBoolean();
            c.get("Recipes", "Coin Changing", true).comment = "Allow coins to be traded up or down for other coins";
            coinSmelting = c.get("Recipes", "Coin Smelting", true).getBoolean();
            c.get("Recipes", "Coin Smelting", true).comment = "Allow coins to be smelted back into nuggets";
            upgradeRecipes = c.get("Recipes", "Upgrade Recipes", true).getBoolean();
            convenienceRecipes = c.get("Recipes", "Convenience Recipes", true).getBoolean();
            c.get("Recipes", "Convenience Recipes", true).comment = "Smelt stone bricks to cracked variant, craft mossy brick/cobble variants with water bucket, craft 2 chiseled brick from 2 normal.";
            lampRecipes = c.get("Recipes", "Lamp Recipes", true).getBoolean();
            craftKitRecipe = c.get("Recipes", "Crafting Kit Recipe", true).getBoolean();
            c.addCustomCategoryComment("Recipes", "Controls whether or not recipes are available for use");

            dimensionBlacklist = c.get("Compatibility", "Dimension Blacklist", "").getString();
            c.get("Compatibility", "Dimension Blacklist", "").comment = "A comma-separated list of dimension IDs to disable worldgen in. (No spaces)";
            floraBoneMeal = c.get("Compatibility", "Bonemeal Flowers", true).getBoolean();
            c.get("Compatibility", "Bonemeal Flowers", true).comment = "Allow flower growth from bonemeal";
            marbleList = c.get("Compatibility", "Marble List", "").getString();
            c.get("Compatibility", "Marble List", "").comment = "Comma-seperated list of Name:Meta pairs to add to a cycling chain of 1:1 marble exchange recipes. No spaces or quotes, meta is required.";
            basaltList = c.get("Compatibility", "Basalt List", "").getString();
            c.get("Compatibility", "Basalt List", "").comment = "Comma-seperated list of Name:Meta pairs to add to a cycling chain of 1:1 basalt exchange recipes. No spaces or quotes, meta is required.";

            limitUpgrades = c.get("Upgrades", "Limit Upgrades", true).getBoolean();
            c.get("Upgrades", "Limit Upgrades", true).comment = "If true, caps the maximum enchant level that can be applied through upgrades to the configured levels, if not set they will cap at the natural level cap";
            maxSharpness = c.get("Upgrades", "Max Sharpness Level", 3).getInt();
            maxEfficiency = c.get("Upgrades", "Max Efficiency Level", 3).getInt();
            maxUnbreaking = c.get("Upgrades", "Max Unbreaking Level", 2).getInt();
            maxPunch = c.get("Upgrades", "Max Punch Level", 2).getInt();
            maxPower = c.get("Upgrades", "Max Power Level", 3).getInt();
            maxKnockback = c.get("Upgrades", "Max Knockback Level", 2).getInt();
            maxThorns = c.get("Upgrades", "Max Thorns Level", 2).getInt();
            maxProtection = c.get("Upgrades", "Max Protection Level", 3).getInt();
            maxProjectileProtection = c.get("Upgrades", "Max Projectile Protection Level", 3).getInt();
            maxFeatherFalling = c.get("Upgrades", "Max Feather Falling Level", 3).getInt();
            maxFireProtection = c.get("Upgrades", "Max Fire Protection Level", 3).getInt();
            maxBlastProtection = c.get("Upgrades", "Max Blast Protection Level", 3).getInt();
            maxRespiration = c.get("Upgrades", "Max Respiration Level", 3).getInt();
            maxAquaAffinity = c.get("Upgrades", "Max Aqua Affinity Level", 1).getInt();
            c.addCustomCategoryComment("Upgrades", "Maximum level of enchantment allowed for each enchantment via upgrades");

            Property eid = c.get("Enchantments", "Starting ID for EID range", 70);
            eid.set(MathHelper.clampI(eid.getInt(), 0, 255));
            enchantmentStartID = eid.getInt();
            enchantmentInvisibleWeight = c.get("Enchantments", "Invisible Enchant Weight", 4).getInt();
            enchantmentInvisibleEnable = c.get("Enchantments", "Enable invisible enchant", true).getBoolean();
            enchantmentSoulstealingWeight = c.get("Enchantments", "Soul Stealing Enchant Weight", 2).getInt();
            enchantmentSoulstealingBonus = c.get("Enchantments", "Bonus XP per level of Soul Stealing", 5).getInt();
            enchantmentSoulstealingEnable = c.get("Enchantments", "Enable soul stealing enchant", true).getBoolean();
            enchantmentResistanceWeight = c.get("Enchantments", "Resistance Enchant weight", 1).getInt();
            enchantmentResistanceEnable = c.get("Enchantments", "Enable resistance enchant", true).getBoolean();
            c.addCustomCategoryComment("Enchantments", "Higher weight values equate to higher occurrence");
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
        setDimBlacklist();
    }

    public static void initClient(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();
            tooltips = c.get("Client", "Enable Tooltips", true).getBoolean();
            c.get("Client", "Enable Tooltips", true).comment = "Set to false to turn off tooltips";
            flavorText = c.get("Client", "Tooltip Flavor Text", true).getBoolean();
            c.get("Client", "Tooltip Flavor Text", true).comment = "Set to false to turn off flavor text in tooltips";
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
        if (dimensionBlacklist != null)
        {
            String blacklist = dimensionBlacklist.trim();
            for (String dim : blacklist.split(","))
            {
                try
                {
                    int dimID = Integer.parseInt(dim);
                    ArtificeRegistry.registerDimensionBlacklist(dimID);
                }
                catch (Exception e)
                {
                    ArtificeCore.logger.warn("Failed to load the dimension blacklist");
                }
            }
        }
    }

    private static void setStoneList(boolean isMarble)
    {
        String stoneList;
        if (isMarble && marbleList != null)
            stoneList = marbleList.trim();
        else if (basaltList != null)
            stoneList = basaltList.trim();
        else
            return;

        for (String pair : stoneList.split(","))
        {
            try
            {
                String itemName = pair.split(";")[0];
                Integer meta = Integer.parseInt(pair.split(";")[1]);
                if (isMarble)
                    ArtificeRegistry.registerMarbleType(new ItemStack((Block) Block.blockRegistry.getObject(itemName), 1, meta));
                else
                    ArtificeRegistry.registerBasaltType(new ItemStack((Block) Block.blockRegistry.getObject(itemName), 1, meta));
            }
            catch (Exception e)
            {
                if (isMarble)
                    ArtificeCore.logger.warn("Failed to load the marble list");
                else
                    ArtificeCore.logger.warn("Failed to load the basalt list");
            }
        }
    }
}
