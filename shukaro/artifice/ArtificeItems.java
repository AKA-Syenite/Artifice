package shukaro.artifice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.item.ItemSledge;
import shukaro.artifice.item.ItemSteel;
import shukaro.artifice.util.IdMetaPair;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraftforge.oredict.OreDictionary;

public class ArtificeItems
{
	public static ItemTool itemSledgeWood;
	public static ItemTool itemSledgeStone;
	public static ItemTool itemSledgeIron;
	public static ItemTool itemSledgeGold;
	public static ItemTool itemSledgeDiamond;
	public static ItemSteel itemSteelIngot;
	
	public static void initItems()
	{
		itemSledgeWood = new ItemSledge(ArtificeConfig.itemSledgeWoodID.getInt(), EnumToolMaterial.WOOD);
		itemSledgeStone = new ItemSledge(ArtificeConfig.itemSledgeStoneID.getInt(), EnumToolMaterial.STONE);
		itemSledgeIron = new ItemSledge(ArtificeConfig.itemSledgeIronID.getInt(), EnumToolMaterial.IRON);
		itemSledgeGold = new ItemSledge(ArtificeConfig.itemSledgeGoldID.getInt(), EnumToolMaterial.GOLD);
		itemSledgeDiamond = new ItemSledge(ArtificeConfig.itemSledgeDiamondID.getInt(), EnumToolMaterial.EMERALD);
		itemSteelIngot = new ItemSteel(ArtificeConfig.itemSteelIngotID.getInt());
		
		GameRegistry.registerItem(itemSledgeWood, itemSledgeWood.getUnlocalizedName());
		GameRegistry.registerItem(itemSledgeStone, itemSledgeStone.getUnlocalizedName());
		GameRegistry.registerItem(itemSledgeIron, itemSledgeIron.getUnlocalizedName());
		GameRegistry.registerItem(itemSledgeGold, itemSledgeGold.getUnlocalizedName());
		GameRegistry.registerItem(itemSledgeDiamond, itemSledgeDiamond.getUnlocalizedName());
		GameRegistry.registerItem(itemSteelIngot, itemSteelIngot.getUnlocalizedName());
		
		OreDictionary.registerOre("ingotSteel", itemSteelIngot);
	}
}
