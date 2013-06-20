package shukaro.artifice;

import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.FormatCodes;

public class ArtificeTooltips
{
	public final static String commentCode = FormatCodes.DarkGrey.format + FormatCodes.Italic.format;
	
	public static void initTooltips()
	{
		initBlockTooltips();
		initItemTooltips();
	}

	private static void initItemTooltips()
	{
		if (ArtificeConfig.enableSledges.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, "Smash blocks into parts");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, "Loss Chance: " + FormatCodes.Aqua.format + "50%");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, commentCode + "When all you've got is a hammer...");
			
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, "Smash blocks into parts");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, "Loss Chance: " + FormatCodes.Aqua.format + "40%");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, commentCode + "When all you've got is a hammer...");
			
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, "Smash blocks into parts");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, "Loss Chance: " + FormatCodes.Aqua.format + "30%");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, commentCode + "When all you've got is a hammer...");
			
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, "Smash blocks into parts");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, "Loss Chance: " + FormatCodes.Aqua.format + "25%");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, commentCode + "When all you've got is a hammer...");
			
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, "Smash blocks into parts");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, "Loss Chance: " + FormatCodes.Aqua.format + "20%");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, commentCode + "When all you've got is a hammer...");
		}
		
		if (ArtificeConfig.enableSteel.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, "A strong iron alloy");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, commentCode + "Steel is progress");
		}
		
		if (ArtificeConfig.enableBoxes.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, "Stores items");
			ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, commentCode + "Watch out for splinters");
		}
	}

	private static void initBlockTooltips()
	{
		if (ArtificeConfig.enableWorldGen.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, "Alabaster white");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, "Closes in low light");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, commentCode + "What a delightful flower");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockLotus.blockID, 0, "Closes in low light");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockLotus.blockID, 0, commentCode + "Reminiscent of candles on the water");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 0, "A pretty shade of blue");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 0, commentCode + "Has a pleasant aroma");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 1, "Luxuriously dark");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 1, commentCode + "Velvety soft");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 2, "Earthy and subdued");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 2, commentCode + "A hardy flower");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 3, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 3, commentCode + "The trick is snug surfaces");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 3, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 3, commentCode + "The trick is snug surfaces");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 4, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 4, commentCode + "The secret is careful carving");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 4, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 4, commentCode + "The secret is careful carving");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 2, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 2, commentCode + "The trick is snug surfaces");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 2, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 2, commentCode + "The trick is snug surfaces");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 3, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 3, commentCode + "The secret is careful carving");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 3, "Connected Textured");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 3, commentCode + "The secret is careful carving");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 0, "Volcanic stone");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 0, commentCode + "You can see shiny specks on the surface");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 1, commentCode + "Covered in jagged edges");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 2, commentCode + "Basalt lends itself well to smooth surfaces");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 5, commentCode + "Precise edges define geometric patterns");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 0, "Metamorphic stone");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 0, commentCode + "Filled with interesting patterns");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 1, commentCode + "As it turns out, rather soft");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 2, commentCode + "Polishing really brings out the glow");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 5, commentCode + "There's a reason marble is prized");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltBrickStairs.blockID, 0, commentCode + "The pieces slot easily into place");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleBrickStairs.blockID, 0, commentCode + "Marble wears easily");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltCobbleStairs.blockID, 0, commentCode + "It can be tricky to get the desired angles");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleCobbleStairs.blockID, 0, commentCode + "Lends a rustic air");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 0, commentCode + "Thin layers of basalt can fracture easily");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 0, commentCode + "Marble is often used as a facade");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 1, commentCode + "Basalt easily shears apart");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 1, commentCode + "Cutting marble generates a lot of dust");
		}
		
		if (ArtificeConfig.enableFrames.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockDetector.blockID, 0, "Pulses on adjacent block updates");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockDetector.blockID, 0, commentCode + "A rather delicate contraption");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 0, "Sturdy wooden frame");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 0, commentCode + "You just put that bit here...");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 1, "Sturdy frame reinforced with iron");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 1, commentCode + "A little ingenuity goes a long way");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 2, "Strong iron frame");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 2, commentCode + "With a firm foundation...");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 3, "Very strong steel frame");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 3, commentCode + "...anything is possible");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, "Reinforced Glass");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, "Resistance: " + FormatCodes.Aqua.format + "10");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, commentCode + "Strengthening glass is tricky");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, "Ceramic Glass");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, "Resistance: " + FormatCodes.Aqua.format + "20");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, commentCode + "Ceramics make the world go round");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, "Rock Glass");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, "Resistance: " + FormatCodes.Aqua.format + "40");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, commentCode + "Volcanic glass is naturally strong");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, "Laminated Glass");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, "Resistance: " + FormatCodes.Aqua.format + "70");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, commentCode + "Filled with thin steel threads");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Reinforced Stone Bricks");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Resistance: " + FormatCodes.Aqua.format + "20");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, commentCode + "A good first attempt");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Reinforced Bricks");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Resistance: " + FormatCodes.Aqua.format + "30");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, commentCode + "Hardened bricks, eh?");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Obsidian Bricks");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Resistance: " + FormatCodes.Aqua.format + "50");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, commentCode + "There is always progress to be made...");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Steel Plating");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Resistance: " + FormatCodes.Aqua.format + "80");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, commentCode + "...and progress always provides");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, "Simple scaffold");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, "Max Overhang: " + FormatCodes.Aqua.format + ArtificeBlocks.blockScaffold.getOverhang(0));
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, commentCode + "Handy to have");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, "Composite scaffold");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, "Max Overhang: " + FormatCodes.Aqua.format + ArtificeBlocks.blockScaffold.getOverhang(1));
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, commentCode + "A bit less rickety");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, "Construction scaffold");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, "Max Overhang: " + FormatCodes.Aqua.format + ArtificeBlocks.blockScaffold.getOverhang(2));
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, commentCode + "100% safe");
			
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, "Rugged scaffold");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, "Max Overhang: " + FormatCodes.Aqua.format + ArtificeBlocks.blockScaffold.getOverhang(3));
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, commentCode + "Precisely machined");
		}

		if (ArtificeConfig.enableSteel.getBoolean(true))
		{
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, "A strong iron alloy");
			ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, commentCode + "Steel is progress");
		}
	}
}
