package shukaro.artifice;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraftforge.oredict.OreDictionary;
import shukaro.artifice.item.DispenserBehaviorBox;
import shukaro.artifice.item.ItemBox;
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
	}
}
