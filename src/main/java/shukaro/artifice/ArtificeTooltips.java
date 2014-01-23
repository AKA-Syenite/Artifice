package shukaro.artifice;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.StatCollector;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.recipe.EnumUpgrades;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.RomanUtil;

public class ArtificeTooltips
{
    public final static String commentCode = FormatCodes.DarkGrey.code + FormatCodes.Italic.code;
    
    public static void initTooltips()
    {
        initBlockTooltips();
        initItemTooltips();
    }

    private static void initItemTooltips()
    {
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 0, "Blue Dye");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 0, commentCode + "Well, some seas");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 1, "Black Dye");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 1, commentCode + "Darker than soot");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 2, "Brown Dye");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 2, commentCode + "Almost reddish");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 3, "White Dye");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 3, commentCode + "Stark as bleached bones");
        }
        
        if (ArtificeConfig.enableSledges.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, "Smash blocks into parts");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, FormatCodes.Italic.code + "Loss Chance: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "50%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, commentCode + "When all you've got is a hammer...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, "Smash blocks into parts");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, FormatCodes.Italic.code + "Loss Chance: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "40%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, commentCode + "When all you've got is a hammer...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, "Smash blocks into parts");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, FormatCodes.Italic.code + "Loss Chance: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "30%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, commentCode + "When all you've got is a hammer...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, "Smash blocks into parts");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, FormatCodes.Italic.code + "Loss Chance: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "25%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, commentCode + "When all you've got is a hammer...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, "Smash blocks into parts");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, FormatCodes.Italic.code + "Loss Chance: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "20%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, commentCode + "When all you've got is a hammer...");
        }
        
        if (ArtificeConfig.enableSickles.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, "Harvest plants and leaves");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, FormatCodes.Italic.code + "Radius: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "1");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, commentCode + "Cutting branches with wood...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, "Harvest plants and leaves");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, FormatCodes.Italic.code + "Radius: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "2");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, commentCode + "Reasonably effective");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, "Harvest plants and leaves");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, FormatCodes.Italic.code + "Radius: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "3");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, commentCode + "Durable and perfect for horticulture");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, "Harvest plants and leaves");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, FormatCodes.Italic.code + "Radius: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "4");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, commentCode + "So soft you're almost smashing them...");
            
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, "Harvest plants and leaves");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, FormatCodes.Italic.code + "Radius: " + FormatCodes.Reset.code + FormatCodes.Aqua.code + "5");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, commentCode + "The ultimate in anti-plant weaponry");
        }
        
        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, "A strong iron alloy");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, commentCode + "Steel is progress");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 1, "A pile of coal dust and iron bits");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 1, commentCode + "Sooty");
        }
        
        if (ArtificeConfig.enableBoxes.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, "Stores items");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, commentCode + "Watch out for splinters");
        }
        
        if (ArtificeConfig.enableUpgrades.getBoolean(true))
        {
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, "Tool or Weapon Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.efficiency.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.sharpness.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.sharpness.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.efficiency.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, commentCode + "A simple kit to sharpen blades");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, "Universal Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.unbreaking.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Reinforcement.maxLevel) : RomanUtil.convertToRoman(Enchantment.unbreaking.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, commentCode + "The perfect materials to reinforce weak points");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, "Bow Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.punch.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ReinforcedLimbs.maxLevel) : RomanUtil.convertToRoman(Enchantment.punch.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, commentCode + "Allows for a stronger pull");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, "Bow Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.power.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.PlaitedString.maxLevel) : RomanUtil.convertToRoman(Enchantment.power.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, commentCode + "Improves accuracy and stability");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, "Weapon Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.knockback.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Counterweight.maxLevel) : RomanUtil.convertToRoman(Enchantment.knockback.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, commentCode + "Improves weapon balance");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, "Armor Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.thorns.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ArmorSpikes.maxLevel) : RomanUtil.convertToRoman(Enchantment.thorns.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, commentCode + "Sharp spikes deter attacks");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, "Armor Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.protection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.LaminatedPadding.maxLevel) : RomanUtil.convertToRoman(Enchantment.protection.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, commentCode + "Layered materials improve protection");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, "Armor Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.projectileProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.QuiltedCover.maxLevel) : RomanUtil.convertToRoman(Enchantment.projectileProtection.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, commentCode + "Thick cloth helps block arrows");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, "Boot Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.featherFalling.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticSoles.maxLevel) : RomanUtil.convertToRoman(Enchantment.featherFalling.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, commentCode + "Slime makes the perfect shock absorber");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, "Armor Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.fireProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Firedamp.maxLevel) : RomanUtil.convertToRoman(Enchantment.fireProtection.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, commentCode + "A thick slurry that helps reduce flammability");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, "Armor Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.blastProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticLayering.maxLevel) : RomanUtil.convertToRoman(Enchantment.blastProtection.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, commentCode + "Helpful in reducing impacts");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, "Helmet Upgrade");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.respiration.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ScubaTank.maxLevel) : RomanUtil.convertToRoman(Enchantment.respiration.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, commentCode + "Crude, but effective air tank");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, "Helmet Upgrade"); 
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.aquaAffinity.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.DiveKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.aquaAffinity.getMaxLevel())) + ")");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, commentCode + "Allows for clearer underwater vision");
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
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, commentCode + "Strengthening glass is tricky");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, "Ceramic Glass");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, commentCode + "Ceramics make the world go round");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, "Rock Glass");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, commentCode + "Volcanic glass is naturally strong");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, "Laminated Glass");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, commentCode + "Filled with thin steel threads");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Reinforced Stone Bricks");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, commentCode + "A good first attempt");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Reinforced Bricks");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, commentCode + "Hardened bricks, eh?");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Obsidian Bricks");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, commentCode + "There is always progress to be made...");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Steel Plating");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, "Resistance: " + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, commentCode + "...and progress always provides");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, "Simple scaffold");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, "Max Overhang: " + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, commentCode + "Handy to have");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, "Composite scaffold");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, "Max Overhang: " + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, commentCode + "A bit less rickety");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, "Construction scaffold");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, "Max Overhang: " + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, commentCode + "100% safe");
            
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, "Rugged scaffold");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, "Max Overhang: " + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, commentCode + "Precisely machined");
        }

        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, "A strong iron alloy");
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, commentCode + "Steel is progress");
        }
        
        if (ArtificeConfig.enableCoins.getBoolean(true))
        {
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 0, commentCode + "Slightly tarnished");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 1, commentCode + "Like a drop of quicksilver");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 2, commentCode + "Surprisingly heavy");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 0, "Copper Coin");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 0, commentCode + "Dependable");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 1, "Silver Coin");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 1, commentCode + "Has a mirrored finish");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 2, "Gold Coin");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 2, commentCode + "Coin of the realm");
        	
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 3, "Platinum Coin");
        	ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 3, commentCode + "A dollar saved...");
        }
    }
}
