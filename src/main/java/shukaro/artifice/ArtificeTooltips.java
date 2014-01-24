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

    public static String localizeTooltip(String name, Boolean flavor)
    {
        return flavor ? commentCode + StatCollector.translateToLocal("tooltip.artifice." + name + ".flavor") : StatCollector.translateToLocal("tooltip.artifice." + name);
    }

    public static void initTooltips()
    {
        initBlockTooltips();
        initItemTooltips();
    }

    private static void initItemTooltips()
    {
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 0, localizeTooltip("dye.blue", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 0, localizeTooltip("dye.blue", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 1, localizeTooltip("dye.black", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 1, localizeTooltip("dye.black", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 2, localizeTooltip("dye.brown", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 2, localizeTooltip("dye.brown", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 3, localizeTooltip("dye.white", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemDye.itemID, 3, localizeTooltip("dye.white", true));
        }

        if (ArtificeConfig.enableSledges.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, localizeTooltip("sledge", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 50%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeWood.itemID, 0, localizeTooltip("sledge", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, localizeTooltip("sledge", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 40%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeStone.itemID, 0, localizeTooltip("sledge", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, localizeTooltip("sledge", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 30%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeIron.itemID, 0, localizeTooltip("sledge", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, localizeTooltip("sledge", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 25%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeGold.itemID, 0, localizeTooltip("sledge", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, localizeTooltip("sledge", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 20%");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSledgeDiamond.itemID, 0, localizeTooltip("sledge", true));
        }

        if (ArtificeConfig.enableSickles.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, localizeTooltip("sickle", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 1");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleWood.itemID, 0, localizeTooltip("sickle.wood", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, localizeTooltip("sickle", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 2");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleStone.itemID, 0, localizeTooltip("sickle.stone", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, localizeTooltip("sickle", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 3");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleIron.itemID, 0, localizeTooltip("sickle.iron", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, localizeTooltip("sickle", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 4");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleGold.itemID, 0, localizeTooltip("sickle.gold", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, localizeTooltip("sickle", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 5");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSickleDiamond.itemID, 0, localizeTooltip("sickle.diamond", true));
        }

        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, localizeTooltip("steel.ingot", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 0, localizeTooltip("steel.ingot", true));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 1, localizeTooltip("steel.dust", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemSteelIngot.itemID, 1, localizeTooltip("steel.dust", true));
        }

        if (ArtificeConfig.enableBoxes.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, localizeTooltip("box", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemBox.itemID, 0, localizeTooltip("box", true));
        }

        if (ArtificeConfig.enableUpgrades.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, localizeTooltip("upgrade.type.toolweapon", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.efficiency.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.sharpness.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.sharpness.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.efficiency.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 0, localizeTooltip("upgrade.sharpener", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, localizeTooltip("upgrade.type.universal", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.unbreaking.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Reinforcement.maxLevel) : RomanUtil.convertToRoman(Enchantment.unbreaking.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 1, localizeTooltip("upgrade.reinforcement", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, localizeTooltip("upgrade.type.bow", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.punch.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ReinforcedLimbs.maxLevel) : RomanUtil.convertToRoman(Enchantment.punch.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 2, localizeTooltip("upgrade.reinforcedlimbs", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, localizeTooltip("upgrade.type.bow", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.power.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.PlaitedString.maxLevel) : RomanUtil.convertToRoman(Enchantment.power.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 3, localizeTooltip("upgrade.plaited", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, localizeTooltip("upgrade.type.weapon", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.knockback.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Counterweight.maxLevel) : RomanUtil.convertToRoman(Enchantment.knockback.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 4, localizeTooltip("upgrade.counterweight", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, localizeTooltip("upgrade.type.armor", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.thorns.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ArmorSpikes.maxLevel) : RomanUtil.convertToRoman(Enchantment.thorns.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 5, localizeTooltip("upgrade.spikes", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, localizeTooltip("upgrade.type.armor", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.protection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.LaminatedPadding.maxLevel) : RomanUtil.convertToRoman(Enchantment.protection.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 6, localizeTooltip("upgrade.lamination", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, localizeTooltip("upgrade.type.armor", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.projectileProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.QuiltedCover.maxLevel) : RomanUtil.convertToRoman(Enchantment.projectileProtection.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 7, localizeTooltip("upgrade.cover", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, localizeTooltip("upgrade.type.boot", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.featherFalling.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticSoles.maxLevel) : RomanUtil.convertToRoman(Enchantment.featherFalling.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 8, localizeTooltip("upgrade.soles", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, localizeTooltip("upgrade.type.armor", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.fireProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Firedamp.maxLevel) : RomanUtil.convertToRoman(Enchantment.fireProtection.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 9, localizeTooltip("upgrade.firedamp", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, localizeTooltip("upgrade.type.armor", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.blastProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticLayering.maxLevel) : RomanUtil.convertToRoman(Enchantment.blastProtection.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 10, localizeTooltip("upgrade.layering", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, localizeTooltip("upgrade.type.helmet", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.respiration.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ScubaTank.maxLevel) : RomanUtil.convertToRoman(Enchantment.respiration.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 11, localizeTooltip("upgrade.tank", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, localizeTooltip("upgrade.type.helmet", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.aquaAffinity.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.DiveKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.aquaAffinity.getMaxLevel())) + ")");
            ArtificeRegistry.registerTooltip(ArtificeItems.itemUpgrade.itemID, 12, localizeTooltip("upgrade.divekit", true));
        }
    }

    private static void initBlockTooltips()
    {
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, localizeTooltip("lotus", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, localizeTooltip("lotus.close", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 3, localizeTooltip("lotus", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockLotus.blockID, 0, localizeTooltip("lotus.close", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockLotus.blockID, 0, localizeTooltip("lily", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 0, localizeTooltip("bluebell", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 0, localizeTooltip("bluebell", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 1, localizeTooltip("orchid", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 1, localizeTooltip("orchid", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 2, localizeTooltip("iris", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFlora.blockID, 2, localizeTooltip("iris", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 3, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 3, localizeTooltip("connected.snug", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 3, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 3, localizeTooltip("connected.snug", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 4, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 4, localizeTooltip("connected.carving", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 4, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 4, localizeTooltip("connected.carving", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 2, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 2, localizeTooltip("connected.snug", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 2, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 2, localizeTooltip("connected.snug", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 3, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 3, localizeTooltip("connected.carving", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 3, localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 3, localizeTooltip("connected.carving", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 0, localizeTooltip("volcanic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 0, localizeTooltip("basalt.smooth", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 1, localizeTooltip("basalt.cobble", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 2, localizeTooltip("basalt.brick", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasalt.blockID, 5, localizeTooltip("basalt.chiseled", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 0, localizeTooltip("metamorphic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 0, localizeTooltip("marble.smooth", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 1, localizeTooltip("marble.cobble", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 2, localizeTooltip("marble.brick", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarble.blockID, 5, localizeTooltip("marble.chiseled", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltBrickStairs.blockID, 0, localizeTooltip("basalt.brick.stairs", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleBrickStairs.blockID, 0, localizeTooltip("marble.brick.stairs", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltCobbleStairs.blockID, 0, localizeTooltip("basalt.cobble.stairs", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleCobbleStairs.blockID, 0, localizeTooltip("marble.cobble.stairs", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 0, localizeTooltip("basalt.brick.slab", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 0, localizeTooltip("marble.brick.slab", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockBasaltSlab.blockID, 1, localizeTooltip("basalt.cobble.slab", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockMarbleSlab.blockID, 1, localizeTooltip("marble.cobble.slab", true));
        }

        if (ArtificeConfig.enableFrames.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockDetector.blockID, 0, localizeTooltip("detector", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockDetector.blockID, 0, localizeTooltip("detector", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 0, localizeTooltip("frame.basic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 0, localizeTooltip("frame.basic", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 1, localizeTooltip("frame.reinforced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 1, localizeTooltip("frame.reinforced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 2, localizeTooltip("frame.industrial", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 2, localizeTooltip("frame.industrial", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 3, localizeTooltip("frame.advanced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockFrame.blockID, 3, localizeTooltip("frame.advanced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, localizeTooltip("glasswall.basic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 0, localizeTooltip("glasswall.basic", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, localizeTooltip("glasswall.reinforced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 1, localizeTooltip("glasswall.reinforced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, localizeTooltip("glasswall.industrial", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 2, localizeTooltip("glasswall.industrial", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, localizeTooltip("glasswall.advanced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockGlassWall.getResistance(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockGlassWall.blockID, 3, localizeTooltip("glasswall.advanced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, localizeTooltip("blastwall.basic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 0, localizeTooltip("blastwall.basic", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, localizeTooltip("blastwall.reinforced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 1, localizeTooltip("blastwall.reinforced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, localizeTooltip("blastwall.industrial", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 2, localizeTooltip("blastwall.industrial", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, localizeTooltip("blastwall.advanced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int) ArtificeBlocks.blockReinforced.getResistance(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockReinforced.blockID, 3, localizeTooltip("blastwall.advanced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, localizeTooltip("scaffold.basic", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(0));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 0, localizeTooltip("scaffold.basic", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, localizeTooltip("scaffold.reinforced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(1));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 1, localizeTooltip("scaffold.reinforced", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, localizeTooltip("scaffold.industrial", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(2));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 2, localizeTooltip("scaffold.industrial", true));

            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, localizeTooltip("scaffold.advanced", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(3));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockScaffold.blockID, 3, localizeTooltip("scaffold.advanced", true));
        }

        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, localizeTooltip("steel.ingot", false));
            ArtificeRegistry.registerTooltip(ArtificeBlocks.blockSteel.blockID, 0, localizeTooltip("steel.ingot", true));
        }

        if (ArtificeConfig.enableCoins.getBoolean(true))
        {
            ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 0, localizeTooltip("nugget.copper", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 1, localizeTooltip("nugget.silver", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemNugget.itemID, 2, localizeTooltip("nugget.platinum", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 0, localizeTooltip("coin.copper", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 0, localizeTooltip("coin.copper", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 1, localizeTooltip("coin.silver", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 1, localizeTooltip("coin.silver", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 2, localizeTooltip("coin.gold", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 2, localizeTooltip("coin.gold", true));

            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 3, localizeTooltip("coin.platinum", false));
            ArtificeRegistry.registerTooltip(ArtificeItems.itemCoin.itemID, 3, localizeTooltip("coin.platinum", true));
        }
    }
}
