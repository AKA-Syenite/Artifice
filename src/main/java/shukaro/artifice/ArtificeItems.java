package shukaro.artifice;

import cofh.core.util.fluid.BucketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shukaro.artifice.item.*;

public class ArtificeItems
{
    public static ItemTool itemSledgeWood;
    public static ItemTool itemSledgeStone;
    public static ItemTool itemSledgeIron;
    public static ItemTool itemSledgeGold;
    public static ItemTool itemSledgeDiamond;
    public static ItemSteel itemSteelIngot;
    public static ItemBox itemBox;
    public static ItemDye itemDye;
    public static ItemTool itemSickleWood;
    public static ItemTool itemSickleStone;
    public static ItemTool itemSickleIron;
    public static ItemTool itemSickleGold;
    public static ItemTool itemSickleDiamond;
    public static ItemNugget itemNugget;
    public static ItemCoin itemCoin;
    public static ItemUpgrade itemUpgrade;
    public static ItemResource itemResource;
    public static ItemBucket itemBucket;
    public static ItemStack bucketOil;
    public static ItemStack bucketFuel;
    public static ItemStack bucketCreosote;
    public static ItemStack bucketBitumen;

    public static void initItems()
    {
        itemSledgeWood = new ItemSledge(Item.ToolMaterial.WOOD);
        itemSledgeStone = new ItemSledge(Item.ToolMaterial.STONE);
        itemSledgeIron = new ItemSledge(Item.ToolMaterial.IRON);
        itemSledgeGold = new ItemSledge(Item.ToolMaterial.GOLD);
        itemSledgeDiamond = new ItemSledge(Item.ToolMaterial.EMERALD);

        GameRegistry.registerItem(itemSledgeWood, itemSledgeWood.getUnlocalizedName());
        GameRegistry.registerItem(itemSledgeStone, itemSledgeStone.getUnlocalizedName());
        GameRegistry.registerItem(itemSledgeIron, itemSledgeIron.getUnlocalizedName());
        GameRegistry.registerItem(itemSledgeGold, itemSledgeGold.getUnlocalizedName());
        GameRegistry.registerItem(itemSledgeDiamond, itemSledgeDiamond.getUnlocalizedName());

        BlockDispenser.dispenseBehaviorRegistry.putObject(itemSledgeWood, new DispenserBehaviorSledge());
        BlockDispenser.dispenseBehaviorRegistry.putObject(itemSledgeStone, new DispenserBehaviorSledge());
        BlockDispenser.dispenseBehaviorRegistry.putObject(itemSledgeIron, new DispenserBehaviorSledge());
        BlockDispenser.dispenseBehaviorRegistry.putObject(itemSledgeGold, new DispenserBehaviorSledge());
        BlockDispenser.dispenseBehaviorRegistry.putObject(itemSledgeDiamond, new DispenserBehaviorSledge());

        itemSteelIngot = new ItemSteel();

        GameRegistry.registerItem(itemSteelIngot, itemSteelIngot.getUnlocalizedName());

        OreDictionary.registerOre("ingotSteel", new ItemStack(itemSteelIngot, 1, 0));
        OreDictionary.registerOre("dustSteel", new ItemStack(itemSteelIngot, 1, 1));

        itemBox = new ItemBox();

        GameRegistry.registerItem(itemBox, itemBox.getUnlocalizedName());

        BlockDispenser.dispenseBehaviorRegistry.putObject(itemBox, new DispenserBehaviorBox());

        itemDye = new ItemDye();
        itemResource = new ItemResource();

        GameRegistry.registerItem(itemDye, itemDye.getUnlocalizedName());
        GameRegistry.registerItem(itemResource, itemResource.getUnlocalizedName());

        OreDictionary.registerOre("dyeBlue", new ItemStack(itemDye, 1, 0));
        OreDictionary.registerOre("dyeBlack", new ItemStack(itemDye, 1, 1));
        OreDictionary.registerOre("dyeBrown", new ItemStack(itemDye, 1, 2));
        OreDictionary.registerOre("dyeWhite", new ItemStack(itemDye, 1, 3));
        OreDictionary.registerOre("dustSulfer", new ItemStack(itemResource, 1, 0));
        OreDictionary.registerOre("dustSaltpeter", new ItemStack(itemResource, 1, 1));

        itemSickleWood = new ItemSickle(Item.ToolMaterial.WOOD);
        itemSickleStone = new ItemSickle(Item.ToolMaterial.STONE);
        itemSickleIron = new ItemSickle(Item.ToolMaterial.IRON);
        itemSickleGold = new ItemSickle(Item.ToolMaterial.GOLD);
        itemSickleDiamond = new ItemSickle(Item.ToolMaterial.EMERALD);

        GameRegistry.registerItem(itemSickleWood, itemSickleWood.getUnlocalizedName());
        GameRegistry.registerItem(itemSickleStone, itemSickleStone.getUnlocalizedName());
        GameRegistry.registerItem(itemSickleIron, itemSickleIron.getUnlocalizedName());
        GameRegistry.registerItem(itemSickleGold, itemSickleGold.getUnlocalizedName());
        GameRegistry.registerItem(itemSickleDiamond, itemSickleDiamond.getUnlocalizedName());

        itemNugget = new ItemNugget();
        itemCoin = new ItemCoin();

        GameRegistry.registerItem(itemNugget, itemNugget.getUnlocalizedName());
        GameRegistry.registerItem(itemCoin, itemCoin.getUnlocalizedName());

        OreDictionary.registerOre("nuggetCopper", new ItemStack(itemNugget, 1, 0));
        OreDictionary.registerOre("nuggetSilver", new ItemStack(itemNugget, 1, 1));
        OreDictionary.registerOre("nuggetGold", new ItemStack(Items.gold_nugget, 1, 0));
        OreDictionary.registerOre("nuggetPlatinum", new ItemStack(itemNugget, 1, 2));
        OreDictionary.registerOre("coinCopper", new ItemStack(itemCoin, 1, 0));
        OreDictionary.registerOre("coinSilver", new ItemStack(itemCoin, 1, 1));
        OreDictionary.registerOre("coinGold", new ItemStack(itemCoin, 1, 2));
        OreDictionary.registerOre("coinPlatinum", new ItemStack(itemCoin, 1, 3));

        itemUpgrade = new ItemUpgrade();

        GameRegistry.registerItem(itemUpgrade, itemUpgrade.getUnlocalizedName());

        itemBucket = (ItemBucket) new ItemBucket("artifice").setUnlocalizedName("bucket").setCreativeTab(ArtificeCore.mainTab);
        bucketOil = itemBucket.addItem(0, "bucketOil");
        bucketFuel = itemBucket.addItem(1, "bucketFuel");
        bucketCreosote = itemBucket.addItem(2, "bucketCreosote");
        bucketBitumen = itemBucket.addItem(3, "bucketBitumen");

        BucketHandler.registerBucket(ArtificeBlocks.blockOil, 0, bucketOil);
        BucketHandler.registerBucket(ArtificeBlocks.blockFuel, 0, bucketFuel);
        BucketHandler.registerBucket(ArtificeBlocks.blockCreosote, 0, bucketCreosote);
        BucketHandler.registerBucket(ArtificeBlocks.blockBitumen, 0, bucketBitumen);

        FluidContainerRegistry.registerFluidContainer(ArtificeFluids.fluidOil, bucketOil, FluidContainerRegistry.EMPTY_BUCKET);
        FluidContainerRegistry.registerFluidContainer(ArtificeFluids.fluidFuel, bucketFuel, FluidContainerRegistry.EMPTY_BUCKET);
        FluidContainerRegistry.registerFluidContainer(ArtificeFluids.fluidCreosote, bucketCreosote, FluidContainerRegistry.EMPTY_BUCKET);
        FluidContainerRegistry.registerFluidContainer(ArtificeFluids.fluidBitumen, bucketBitumen, FluidContainerRegistry.EMPTY_BUCKET);
    }
}
