package shukaro.artifice;

import shukaro.artifice.compat.ArtificeRegistry;

public class ArtificeTooltips
{
	public static void initTooltips()
	{
		initBlockTooltips();
		initItemTooltips();
	}

	private static void initItemTooltips()
	{
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, "Smash blocks into parts");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, "Loss Chance: " + "\u00A7b" + "50%");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, "Smash blocks into parts");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, "Loss Chance: " + "\u00A7b" + "40%");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, "Smash blocks into parts");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, "Loss Chance: " + "\u00A7b" + "30%");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, "Smash blocks into parts");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, "Loss Chance: " + "\u00A7b" + "25%");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, "Smash blocks into parts");
		ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, "Loss Chance: " + "\u00A7b" + "20%");
	}

	private static void initBlockTooltips()
	{
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockDetector.blockID, 0, "Pulses on adjacent block updates");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, "Closes in low light");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockLotus.blockID, 0, "Closes in low light");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 3, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 3, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 4, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 4, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 2, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 2, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 3, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 3, "Connected Textured");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 0, "Sturdy wooden frame");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 1, "Sturdy frame reinforced with iron");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 2, "Strong iron frame");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 3, "Very strong steel frame");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Reinforced Stone Bricks");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Resistance: " + "\u00A7b" + "3000");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Reinforced Bricks");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Resistance: " + "\u00A7b" + "6000");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Obsidian Bricks");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Resistance: " + "\u00A7b" + "9000");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Steel Plating");
		ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Resistance: " + "\u00A7b" + "12000");
	}
}
