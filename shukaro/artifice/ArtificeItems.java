package shukaro.artifice;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.oredict.OreDictionary;
import shukaro.artifice.item.DispenserBehaviorBox;
import shukaro.artifice.item.DispenserBehaviorSledge;
import shukaro.artifice.item.ItemBox;
import shukaro.artifice.item.ItemDye;
import shukaro.artifice.item.ItemSickle;
import shukaro.artifice.item.ItemSledge;
import shukaro.artifice.item.ItemSteel;
import cpw.mods.fml.common.registry.GameRegistry;

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
    
    public static void initItems()
    {
        if (ArtificeConfig.enableSledges.getBoolean(true))
        {
            itemSledgeWood = new ItemSledge(ArtificeConfig.itemSledgeWoodID.getInt(), EnumToolMaterial.WOOD);
            itemSledgeStone = new ItemSledge(ArtificeConfig.itemSledgeStoneID.getInt(), EnumToolMaterial.STONE);
            itemSledgeIron = new ItemSledge(ArtificeConfig.itemSledgeIronID.getInt(), EnumToolMaterial.IRON);
            itemSledgeGold = new ItemSledge(ArtificeConfig.itemSledgeGoldID.getInt(), EnumToolMaterial.GOLD);
            itemSledgeDiamond = new ItemSledge(ArtificeConfig.itemSledgeDiamondID.getInt(), EnumToolMaterial.EMERALD);
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
        }
        
        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            itemSteelIngot = new ItemSteel(ArtificeConfig.itemSteelIngotID.getInt());
            GameRegistry.registerItem(itemSteelIngot, itemSteelIngot.getUnlocalizedName());
            OreDictionary.registerOre("ingotSteel", itemSteelIngot);
        }
        
        if (ArtificeConfig.enableBoxes.getBoolean(true))
        {
            itemBox = new ItemBox(ArtificeConfig.itemBoxID.getInt());
            GameRegistry.registerItem(itemBox, itemBox.getUnlocalizedName());
            BlockDispenser.dispenseBehaviorRegistry.putObject(itemBox, new DispenserBehaviorBox());
        }
        
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            itemDye = new ItemDye(ArtificeConfig.itemDyeID.getInt());
            GameRegistry.registerItem(itemDye, itemDye.getUnlocalizedName());
            OreDictionary.registerOre("dyeBlue", new ItemStack(itemDye.itemID, 1, 0));
            OreDictionary.registerOre("dyeBlack", new ItemStack(itemDye.itemID, 1, 1));
            OreDictionary.registerOre("dyeBrown", new ItemStack(itemDye.itemID, 1, 2));
            OreDictionary.registerOre("dyeWhite", new ItemStack(itemDye.itemID, 1, 3));
        }
        
        if (ArtificeConfig.enableSickles.getBoolean(true))
        {
            itemSickleWood = new ItemSickle(ArtificeConfig.itemSickleWoodID.getInt(), EnumToolMaterial.WOOD);
            itemSickleStone = new ItemSickle(ArtificeConfig.itemSickleStoneID.getInt(), EnumToolMaterial.STONE);
            itemSickleIron = new ItemSickle(ArtificeConfig.itemSickleIronID.getInt(), EnumToolMaterial.IRON);
            itemSickleGold = new ItemSickle(ArtificeConfig.itemSickleGoldID.getInt(), EnumToolMaterial.GOLD);
            itemSickleDiamond = new ItemSickle(ArtificeConfig.itemSickleDiamondID.getInt(), EnumToolMaterial.EMERALD);
            GameRegistry.registerItem(itemSickleWood, itemSickleWood.getUnlocalizedName());
            GameRegistry.registerItem(itemSickleStone, itemSickleStone.getUnlocalizedName());
            GameRegistry.registerItem(itemSickleIron, itemSickleIron.getUnlocalizedName());
            GameRegistry.registerItem(itemSickleGold, itemSickleGold.getUnlocalizedName());
            GameRegistry.registerItem(itemSickleDiamond, itemSickleDiamond.getUnlocalizedName());
        }
    }
}
