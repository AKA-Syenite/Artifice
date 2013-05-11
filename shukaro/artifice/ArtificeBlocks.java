package shukaro.artifice;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import shukaro.artifice.block.decorative.BlockBasalt;
import shukaro.artifice.block.decorative.BlockBasaltSlab;
import shukaro.artifice.block.decorative.BlockFlora;
import shukaro.artifice.block.decorative.BlockMarble;
import shukaro.artifice.block.decorative.BlockMarbleSlab;
import shukaro.artifice.block.decorative.BlockStairsArtifice;
import shukaro.artifice.block.decorative.ItemBlockBasalt;
import shukaro.artifice.block.decorative.ItemBlockFlora;
import shukaro.artifice.block.decorative.ItemBlockMarble;
import shukaro.artifice.block.frame.BlockFrame;
import shukaro.artifice.block.frame.BlockFrameBase;
import shukaro.artifice.block.frame.BlockFrameRefractory;
import shukaro.artifice.block.frame.ItemBlockFrame;

public class ArtificeBlocks
{
	public static BlockFrame blockFrame;
	public static BlockFlora blockFlora;
	public static BlockBasalt blockBasalt;
	public static BlockMarble blockMarble;
	public static BlockStairsArtifice blockBasaltBrickStairs;
	public static BlockStairsArtifice blockMarbleBrickStairs;
	public static BlockStairsArtifice blockBasaltCobbleStairs;
	public static BlockStairsArtifice blockMarbleCobbleStairs;
	public static BlockHalfSlab blockBasaltSlab;
	public static BlockHalfSlab blockBasaltDoubleSlab;
	public static BlockHalfSlab blockMarbleSlab;
	public static BlockHalfSlab blockMarbleDoubleSlab;
	public static BlockFrame blockRefractory;
	
	public static void initBlocks()
	{
		blockFlora = new BlockFlora(ArtificeConfig.blockFloraID.getInt());
		blockBasalt = new BlockBasalt(ArtificeConfig.blockBasaltID.getInt());
		blockMarble = new BlockMarble(ArtificeConfig.blockMarbleID.getInt());
		blockBasaltBrickStairs = new BlockStairsArtifice(ArtificeConfig.blockBasaltBrickStairsID.getInt(), blockBasalt, 2);
		blockMarbleBrickStairs = new BlockStairsArtifice(ArtificeConfig.blockMarbleBrickStairsID.getInt(), blockMarble, 2);
		blockBasaltCobbleStairs = new BlockStairsArtifice(ArtificeConfig.blockBasaltCobbleStairsID.getInt(), blockBasalt, 1);
		blockMarbleCobbleStairs = new BlockStairsArtifice(ArtificeConfig.blockMarbleCobbleStairsID.getInt(), blockMarble, 1);
		blockBasaltSlab = (BlockHalfSlab) new BlockBasaltSlab(ArtificeConfig.blockBasaltSlabID.getInt(), false);
		blockBasaltDoubleSlab = (BlockHalfSlab) new BlockBasaltSlab(ArtificeConfig.blockBasaltDoubleSlabID.getInt(), true);
		blockMarbleSlab = (BlockHalfSlab) new BlockMarbleSlab(ArtificeConfig.blockMarbleSlabID.getInt(), false);
		blockMarbleDoubleSlab = (BlockHalfSlab) new BlockMarbleSlab(ArtificeConfig.blockMarbleDoubleSlabID.getInt(), true);
		blockFrame = new BlockFrameBase(ArtificeConfig.blockFrameID.getInt());
		blockRefractory = new BlockFrameRefractory(ArtificeConfig.blockRefractoryID.getInt());
		
		Item.itemsList[ArtificeConfig.blockBasaltSlabID.getInt()] = new ItemSlab(ArtificeConfig.blockBasaltSlabID.getInt() - 256, blockBasaltSlab, blockBasaltDoubleSlab, false);
		Item.itemsList[ArtificeConfig.blockBasaltDoubleSlabID.getInt()] = new ItemSlab(ArtificeConfig.blockBasaltDoubleSlabID.getInt() - 256, blockBasaltSlab, blockBasaltDoubleSlab, true);
		Item.itemsList[ArtificeConfig.blockMarbleSlabID.getInt()] = new ItemSlab(ArtificeConfig.blockMarbleSlabID.getInt() - 256, blockMarbleSlab, blockMarbleDoubleSlab, false);
		Item.itemsList[ArtificeConfig.blockMarbleDoubleSlabID.getInt()] = new ItemSlab(ArtificeConfig.blockMarbleDoubleSlabID.getInt() - 256, blockMarbleSlab, blockMarbleDoubleSlab, true);
		
		GameRegistry.registerBlock(blockFrame, ItemBlockFrame.class, blockFrame.getUnlocalizedName());
		GameRegistry.registerBlock(blockRefractory, ItemBlockFrame.class, blockRefractory.getUnlocalizedName());
		GameRegistry.registerBlock(blockFlora, ItemBlockFlora.class, blockFlora.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasalt, ItemBlockBasalt.class, blockBasalt.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarble, ItemBlockMarble.class, blockMarble.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasaltBrickStairs, blockBasaltBrickStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarbleBrickStairs, blockMarbleBrickStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasaltCobbleStairs, blockBasaltCobbleStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarbleCobbleStairs, blockMarbleCobbleStairs.getUnlocalizedName());
	}
}
