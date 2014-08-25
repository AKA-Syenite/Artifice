package shukaro.artifice.recipe;

import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;

public class ArtificeRecipes
{
    public static void registerRecipes()
    {
        if (ArtificeConfig.basaltRecipes.getBoolean(true))
            registerBasaltRecipes();
        if (ArtificeConfig.marbleRecipes.getBoolean(true))
            registerMarbleRecipes();
        if (ArtificeConfig.limestoneRecipes.getBoolean(true))
            registerLimestoneRecipes();
        if (ArtificeConfig.oreRecipes.getBoolean(true))
            registerOreRecipes();
        if (ArtificeConfig.resourceRecipes.getBoolean(true))
            registerResourceRecipes();
        if (ArtificeConfig.floraRecipes.getBoolean(true))
            registerDyeRecipes();
        if (ArtificeConfig.sledgeRecipes.getBoolean(true))
            registerSledgeRecipes();
        if (ArtificeConfig.frameRecipes.getBoolean(true))
            registerFrameRecipes();
        if (ArtificeConfig.detectorRecipe.getBoolean(true))
        {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockDetector, 1, 0), " Q ",
                    "YXY",
                    " Y ",
                    'Y', new ItemStack(Items.string, 1, 0),
                    'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
                    'Q', new ItemStack(Items.redstone, 1, 0)));
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
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemBox, 16, 0), " Y ",
                    "YXY",
                    " Y ",
                    'Y', "plankWood",
                    'X', "stickWood"));

            GameRegistry.addRecipe(new RecipeBox());
            RecipeSorter.register("artifice:box", RecipeBox.class, Category.SHAPED, "after:minecraft:shaped before:minecraft:shapeless");

            MinecraftForge.EVENT_BUS.register(new BoxCraftingHandler());
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
            RecipeSorter.register("artifice:upgrades", RecipeUpgrade.class, Category.SHAPELESS, "after:minecraft:shapeless");
        }
        if (ArtificeConfig.alternateSteel.getBoolean(false))
        {
            registerAlternateSteelRecipes();
        }
        if (ArtificeConfig.convenienceRecipes.getBoolean(true))
        {
            registerConvenienceRecipes();
        }
        if (ArtificeConfig.lampRecipes.getBoolean(true))
            registerLampRecipes();
    }

    private static void registerConvenienceRecipes()
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(Blocks.stonebrick, 1, 2), new ItemStack(Blocks.stonebrick, 1, 0), 0f);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.stonebrick, 2, 3), "X",
                "X",
                'X', new ItemStack(Blocks.stonebrick, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.stonebrick, 8, 1), "XXX",
                "XYX",
                "XXX",
                'X', new ItemStack(Blocks.stonebrick, 1, 0),
                'Y', new ItemStack(Items.water_bucket)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.mossy_cobblestone, 8, 0), "XXX",
                "XYX",
                "XXX",
                'X', new ItemStack(Blocks.cobblestone),
                'Y', new ItemStack(Items.water_bucket)));
    }

    private static void registerAlternateSteelRecipes()
    {
        int cost = ArtificeConfig.alternateSteelRequirement.getInt();
        if (cost < 1)
            cost = 1;
        if (cost > 8)
            cost = 8;

        Object[] reagents = new Object[cost + 1];
        ItemStack fuel = new ItemStack(Items.coal, 1, OreDictionary.WILDCARD_VALUE);

        reagents[0] = Items.iron_ingot;
        for (int i = 1; i < cost + 1; i++)
            reagents[i] = fuel.copy();

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 1, 1), reagents));

        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeItems.itemSteelIngot, 1, 1), new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), 1.0f);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), "XXX",
                "XXX",
                "XXX",
                'X', "ingotSteel"));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 9, 0), new ItemStack(ArtificeBlocks.blockSteel, 1, 0)));
    }

    private static void registerUpgrades()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), " Z ",
                "YXQ",
                "   ",
                'X', Items.coal,
                'Y', Items.iron_ingot,
                'Q', Blocks.stone,
                'Z', Items.flint));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 1), "   ",
                "XQZ",
                " Y ",
                'X', Items.string,
                'Q', Items.leather,
                'Z', Items.iron_ingot,
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 2), "   ",
                "YXZ",
                " X ",
                'X', "stickWood",
                'Y', Items.leather,
                'Z', Items.iron_ingot));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 3), "  X",
                " X ",
                "X  ",
                'X', Items.string));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 4), " X ",
                " X ",
                " Y ",
                'X', Items.iron_ingot,
                'Y', Blocks.stone));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 5), "   ",
                "XXX",
                " Y ",
                'X', Blocks.iron_bars,
                'Y', Items.iron_ingot));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 6), "   ",
                "YXY",
                " X ",
                'X', Items.leather,
                'Y', Items.string));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 7), "   ",
                "YXY",
                " X ",
                'X', Blocks.wool,
                'Y', Items.string));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 8), "   ",
                "X X",
                "Y Y",
                'X', Items.leather,
                'Y', Items.slime_ball));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 9), " X ",
                " Y ",
                " Q ",
                'Q', Items.bowl,
                'Y', Items.slime_ball,
                'X', new ItemStack(Items.dye, 1, 15)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 10), "   ",
                "YXQ",
                " X ",
                'X', Items.slime_ball,
                'Y', Items.leather,
                'Q', Items.string));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 11), " Q ",
                " Y ",
                " X ",
                'Q', Items.string,
                'Y', Items.leather,
                'X', Items.bucket));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemUpgrade, 1, 12), " Q ",
                "XYX",
                " Z ",
                'Y', Blocks.glass,
                'X', Items.leather,
                'Z', Items.iron_ingot,
                'Q', Items.string));
    }

    private static void registerCoinMinting()
    {
        String[] nuggets = {"nuggetCopper", "nuggetSilver", "nuggetGold", "nuggetPlatinum"};

        for (int i = 0; i < nuggets.length; i++)
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
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeItems.itemCoin, 1, 0), new ItemStack(ArtificeItems.itemNugget, 1, 0), 0);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeItems.itemCoin, 1, 1), new ItemStack(ArtificeItems.itemNugget, 1, 1), 0);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeItems.itemCoin, 1, 2), new ItemStack(Items.gold_nugget, 1, 0), 0);
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeItems.itemCoin, 1, 3), new ItemStack(ArtificeItems.itemNugget, 1, 2), 0);
    }

    private static void registerSickleRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleWood, 1, 0), "X ",
                " X",
                "Y ",
                'X', "plankWood",
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleStone, 1, 0), "X ",
                " X",
                "Y ",
                'X', Blocks.cobblestone,
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleIron, 1, 0), "X ",
                " X",
                "Y ",
                'X', Items.iron_ingot,
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleGold, 1, 0), "X ",
                " X",
                "Y ",
                'X', Items.gold_ingot,
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSickleDiamond, 1, 0), "X ",
                " X",
                "Y ",
                'X', Items.diamond,
                'Y', "stickWood"));
    }

    private static void registerScaffoldRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 0), " Y ",
                " X ",
                " Y ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
                'Y', "plankWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 1), " Y ",
                " X ",
                " Y ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
                'Y', "plankWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 2), " Y ",
                " X ",
                " Y ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
                'Y', new ItemStack(Items.iron_ingot, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockScaffold, 8, 3), " Y ",
                " X ",
                " Y ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
                'Y', "ingotSteel"));
    }

    private static void registerGlassWallRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 0), "YYY",
                " X ",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
                'Y', Blocks.glass_pane,
                'Z', Items.clay_ball));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 0), new ItemStack(ArtificeBlocks.blockGlassWall, 1, 0), "dyeBlack"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 2, 1), "YYY",
                " X ",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
                'Z', Items.clay_ball,
                'Y', Blocks.glass));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 1), new ItemStack(ArtificeBlocks.blockGlassWall, 1, 1), "dyeBlack"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 4, 2), "QQQ",
                "YXY",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
                'Q', Blocks.glass,
                'Y', Blocks.obsidian,
                'Z', Items.lava_bucket));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 2), new ItemStack(ArtificeBlocks.blockGlassWall, 1, 2), "dyeBlack"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWall, 4, 3), "YYY",
                "QXQ",
                "YYY",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
                'Q', Blocks.glass,
                'Y', "ingotSteel"));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 3), new ItemStack(ArtificeBlocks.blockGlassWall, 1, 3), "dyeBlack"));
    }

    private static void registerBlastWallRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 0), "   ",
                "YXY",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 0),
                'Y', Blocks.stonebrick,
                'Z', Items.clay_ball));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 2, 1), "   ",
                "YXY",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 1),
                'Y', Blocks.brick_block,
                'Z', Items.clay_ball));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 4, 2), "   ",
                "YXY",
                " Z ",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 2),
                'Y', Blocks.obsidian,
                'Z', Items.lava_bucket));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockReinforced, 4, 3), "YYY",
                "YXY",
                "YYY",
                'X', new ItemStack(ArtificeBlocks.blockFrame, 1, 3),
                'Y', "ingotSteel"));
    }

    private static void registerSteelRecipes()
    {
        GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), 1.0F);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), "XXX",
                "XXX",
                "XXX",
                'X', "ingotSteel"));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemSteelIngot, 9, 0), new ItemStack(ArtificeBlocks.blockSteel, 1, 0)));
    }

    private static void registerFrameRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 8, 0), "XYX",
                "YYY",
                "XYX",
                'X', "plankWood",
                'Y', "stickWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 8, 1), "XYX",
                "YYY",
                "XYX",
                'X', "plankWood",
                'Y', new ItemStack(Items.iron_ingot, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 16, 2), "X X",
                " Y ",
                "X X",
                'X', new ItemStack(Items.iron_ingot, 1, 0),
                'Y', new ItemStack(Blocks.iron_block, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockFrame, 16, 3), "X X",
                " Y ",
                "X X",
                'X', "ingotSteel",
                'Y', "blockSteel"));
    }

    private static void registerSledgeRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeWood, 1, 0), " YX",
                " XY",
                "X  ",
                'X', "stickWood",
                'Y', "plankWood"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeStone, 1, 0), " YX",
                " XY",
                "X  ",
                'X', "stickWood",
                'Y', new ItemStack(Blocks.cobblestone, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeIron, 1, 0), " YX",
                " XY",
                "X  ",
                'X', "stickWood",
                'Y', new ItemStack(Items.iron_ingot, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeGold, 1, 0), " YX",
                " XY",
                "X  ",
                'X', "stickWood",
                'Y', new ItemStack(Items.gold_ingot, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeItems.itemSledgeDiamond, 1, 0), " YX",
                " XY",
                "X  ",
                'X', "stickWood",
                'Y', new ItemStack(Items.diamond, 1, 0)));
    }

    private static void registerDyeRecipes()
    {
        // Black flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye, 2, 1), new ItemStack(ArtificeBlocks.blockFlora, 1, 1)));

        // Yellow + blue = green
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 2, 2), "dyeBlue", "dyeYellow"));

        // Brown flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye, 2, 2), new ItemStack(ArtificeBlocks.blockFlora, 1, 2)));

        // Blue flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye, 2, 0), new ItemStack(ArtificeBlocks.blockFlora, 1, 0)));

        // White flower
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeItems.itemDye, 2, 3), new ItemStack(ArtificeBlocks.blockFlora, 1, 3)));
    }

    private static void registerBasaltRecipes()
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeBlocks.blockBasalt, 1, 1), new ItemStack(ArtificeBlocks.blockBasalt, 1, 0), 0.1F);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 4, 2), "XX",
                "XX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 4, 3), "XX",
                "XX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 2, 5), "X",
                "X",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 1, 4), new ItemStack(ArtificeBlocks.blockBasalt, 1, 3)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasalt, 1, 3), new ItemStack(ArtificeBlocks.blockBasalt, 1, 4)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltBrickStairs, 4, 0), "X  ",
                "XX ",
                "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltCobbleStairs, 4, 0), "X  ",
                "XX ",
                "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 6, 0), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 6, 1), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 6, 2), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 3)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 6, 3), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockBasalt, 1, 4)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 2), new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 3)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 3), new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 2)));
    }

    private static void registerMarbleRecipes()
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeBlocks.blockMarble, 1, 1), new ItemStack(ArtificeBlocks.blockMarble, 1, 0), 0.1F);

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 4, 2), "XX",
                "XX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 0)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 4, 3), "XX",
                "XX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 2, 5), "X",
                "X",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 1, 4), new ItemStack(ArtificeBlocks.blockMarble, 1, 3)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarble, 1, 3), new ItemStack(ArtificeBlocks.blockMarble, 1, 4)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleBrickStairs, 4, 0), "X  ",
                "XX ",
                "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleCobbleStairs, 4, 0), "X  ",
                "XX ",
                "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 6, 0), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 2)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 6, 1), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 1)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 6, 2), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 3)));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 6, 3), "XXX",
                'X', new ItemStack(ArtificeBlocks.blockMarble, 1, 4)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 2), new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 3)));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 3), new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 2)));
    }

    private static void registerLimestoneRecipes()
    {
        for (int i=0; i<ArtificeBlocks.blockLimestones.length; i++)
        {
            FurnaceRecipes.smelting().func_151394_a(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 1), new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 0), 0.1F);

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[i], 4, 2), "XX",
                    "XX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 0)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[i], 4, 3), "XX",
                    "XX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 2)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[i], 2, 5), "X",
                    "X",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 2)));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 4), new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 3)));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 3), new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 4)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneBrickStairs[i], 4, 0), "X  ",
                    "XX ",
                    "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 2)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneCobbleStairs[i], 4, 0), "X  ",
                    "XX ",
                    "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 1)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 6, 0), "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 2)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 6, 1), "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 1)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 6, 2), "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 3)));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 6, 3), "XXX",
                    'X', new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 4)));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 2), new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 3)));

            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 3), new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 2)));
        }
    }

    private static void registerLampRecipes()
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[0], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeWhite"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[1], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeOrange"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[2], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeMagenta"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[3], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeLightBlue"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[4], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeYellow"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[5], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeLime"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[6], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyePink"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[7], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeGray"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[8], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeLightGray"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[9], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeCyan"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[10], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyePurple"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[11], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeBlue"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[12], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeBrown"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[13], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeGreen"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[14], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeRed"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[15], 1, 0), "blockGlass", "dustRedstone", "dustGlowstone", "dustGlowstone", "dyeBlack"));

        for (int i=0; i<16; i++)
        {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLampsInverted[i], 1, 0), ArtificeBlocks.blockLamps[i]));
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeBlocks.blockLamps[i], 1, 0), ArtificeBlocks.blockLampsInverted[i]));
        }
    }

    private static void registerOreRecipes()
    {
        for (int i=0; i<ArtificeBlocks.blockOres.length; i++)
        {
            for (int j=0; j<ArtificeBlocks.rockBlocks.length; j++)
            {
                ItemStack stack = new ItemStack(ArtificeBlocks.blockOres[i], 1, j);
                if (ItemHelper.getOreName(stack).equals("oreCoal"))
                    ItemHelper.addSmelting(new ItemStack(Items.coal), stack, 0.1f);
                else if (ItemHelper.getOreName(stack).equals("oreIron"))
                    ItemHelper.addSmelting(new ItemStack(Items.iron_ingot), stack, 0.7f);
                else if (ItemHelper.getOreName(stack).equals("oreLapis"))
                    ItemHelper.addSmelting(new ItemStack(Items.dye, 1, 4), stack, 0.2f);
                else if (ItemHelper.getOreName(stack).equals("oreGold"))
                    ItemHelper.addSmelting(new ItemStack(Items.gold_ingot), stack, 1.0f);
                else if (ItemHelper.getOreName(stack).equals("oreDiamond"))
                    ItemHelper.addSmelting(new ItemStack(Items.diamond), stack, 1.0f);
                else if (ItemHelper.getOreName(stack).equals("oreRedstone"))
                    ItemHelper.addSmelting(new ItemStack(Items.redstone), stack, 0.7f);
                else if (ItemHelper.getOreName(stack).equals("oreEmerald"))
                    ItemHelper.addSmelting(new ItemStack(Items.emerald), stack, 1.0f);
            }
        }
    }

    private static void registerResourceRecipes()
    {
        ItemHelper.addStorageRecipe(new ItemStack(ArtificeBlocks.blockSulfur, 1, 1), new ItemStack(ArtificeItems.itemResource, 1, 0));
        ItemHelper.addReverseStorageRecipe(new ItemStack(ArtificeItems.itemResource, 1, 0), new ItemStack(ArtificeBlocks.blockSulfur, 1, 1));
        ItemHelper.addSmelting(new ItemStack(ArtificeItems.itemResource, 1, 0), new ItemStack(ArtificeBlocks.blockSulfur, 1, 0), 0.7f);
        for (int i=0; i<ArtificeBlocks.rockBlocks.length; i++)
            ItemHelper.addSmelting(new ItemStack(ArtificeItems.itemResource, 1, 0), new ItemStack(ArtificeBlocks.blockOres[12], 1, i), 0.7f);

        ItemHelper.addStorageRecipe(new ItemStack(ArtificeBlocks.blockNiter, 1, 1), new ItemStack(ArtificeItems.itemResource, 1, 1));
        ItemHelper.addReverseStorageRecipe(new ItemStack(ArtificeItems.itemResource, 1, 1), new ItemStack(ArtificeBlocks.blockNiter, 1, 1));
        ItemHelper.addSmelting(new ItemStack(ArtificeItems.itemResource, 1, 1), new ItemStack(ArtificeBlocks.blockNiter, 1, 0), 0.7f);
    }
}
