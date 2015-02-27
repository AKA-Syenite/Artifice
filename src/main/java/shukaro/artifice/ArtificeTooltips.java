package shukaro.artifice;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
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
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemResource, 1, 0), localizeTooltip("sulfur", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemResource, 1, 1), localizeTooltip("niter", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemResource, 1, 2), localizeTooltip("enderdust", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 0), localizeTooltip("dye.blue", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 0), localizeTooltip("dye.blue", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 1), localizeTooltip("dye.black", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 1), localizeTooltip("dye.black", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 2), localizeTooltip("dye.brown", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 2), localizeTooltip("dye.brown", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 3), localizeTooltip("dye.white", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemDye, 1, 3), localizeTooltip("dye.white", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeWood, 1, 0), localizeTooltip("sledge", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeWood, 1, 0), FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 50%");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeWood, 1, 0), localizeTooltip("sledge", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeStone, 1, 0), localizeTooltip("sledge", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeStone, 1, 0), FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 40%");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeStone, 1, 0), localizeTooltip("sledge", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeIron, 1, 0), localizeTooltip("sledge", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeIron, 1, 0), FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 30%");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeIron, 1, 0), localizeTooltip("sledge", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeGold, 1, 0), localizeTooltip("sledge", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeGold, 1, 0), FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 25%");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeGold, 1, 0), localizeTooltip("sledge", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeDiamond, 1, 0), localizeTooltip("sledge", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeDiamond, 1, 0), FormatCodes.Italic.code + localizeTooltip("sledge.loss", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 20%");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSledgeDiamond, 1, 0), localizeTooltip("sledge", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleWood, 1, 0), localizeTooltip("sickle", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleWood, 1, 0), FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 1");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleWood, 1, 0), localizeTooltip("sickle.wood", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleStone, 1, 0), localizeTooltip("sickle", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleStone, 1, 0), FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 2");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleStone, 1, 0), localizeTooltip("sickle.stone", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleIron, 1, 0), localizeTooltip("sickle", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleIron, 1, 0), FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 3");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleIron, 1, 0), localizeTooltip("sickle.iron", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleGold, 1, 0), localizeTooltip("sickle", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleGold, 1, 0), FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 4");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleGold, 1, 0), localizeTooltip("sickle.gold", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleDiamond, 1, 0), localizeTooltip("sickle", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleDiamond, 1, 0), FormatCodes.Italic.code + localizeTooltip("sickle.radius", false) + FormatCodes.Reset.code + FormatCodes.Aqua.code + " 5");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSickleDiamond, 1, 0), localizeTooltip("sickle.diamond", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), localizeTooltip("steel.ingot", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSteelIngot, 1, 0), localizeTooltip("steel.ingot", true));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSteelIngot, 1, 1), localizeTooltip("steel.dust", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemSteelIngot, 1, 1), localizeTooltip("steel.dust", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemNugget, 1, 0), localizeTooltip("nugget.copper", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemNugget, 1, 1), localizeTooltip("nugget.silver", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemNugget, 1, 2), localizeTooltip("nugget.platinum", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 0), localizeTooltip("coin.copper", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 0), localizeTooltip("coin.copper", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 1), localizeTooltip("coin.silver", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 1), localizeTooltip("coin.silver", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 2), localizeTooltip("coin.gold", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 2), localizeTooltip("coin.gold", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 3), localizeTooltip("coin.platinum", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemCoin, 1, 3), localizeTooltip("coin.platinum", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBox, 1, 0), localizeTooltip("box", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBox, 1, 0), localizeTooltip("box", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), localizeTooltip("upgrade.type.toolweapon", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.efficiency.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKitS.maxLevel) : RomanUtil.convertToRoman(Enchantment.sharpness.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.sharpness.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.SharpeningKitE.maxLevel) : RomanUtil.convertToRoman(Enchantment.efficiency.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 0), localizeTooltip("upgrade.sharpener", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 1), localizeTooltip("upgrade.type.universal", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 1), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.unbreaking.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Reinforcement.maxLevel) : RomanUtil.convertToRoman(Enchantment.unbreaking.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 1), localizeTooltip("upgrade.reinforcement", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 2), localizeTooltip("upgrade.type.bow", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 2), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.punch.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ReinforcedLimbs.maxLevel) : RomanUtil.convertToRoman(Enchantment.punch.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 2), localizeTooltip("upgrade.reinforcedlimbs", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 3), localizeTooltip("upgrade.type.bow", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 3), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.power.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.PlaitedString.maxLevel) : RomanUtil.convertToRoman(Enchantment.power.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 3), localizeTooltip("upgrade.plaited", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 4), localizeTooltip("upgrade.type.weapon", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 4), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.knockback.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Counterweight.maxLevel) : RomanUtil.convertToRoman(Enchantment.knockback.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 4), localizeTooltip("upgrade.counterweight", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 5), localizeTooltip("upgrade.type.armor", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 5), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.thorns.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ArmorSpikes.maxLevel) : RomanUtil.convertToRoman(Enchantment.thorns.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 5), localizeTooltip("upgrade.spikes", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 6), localizeTooltip("upgrade.type.armor", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 6), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.protection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.LaminatedPadding.maxLevel) : RomanUtil.convertToRoman(Enchantment.protection.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 6), localizeTooltip("upgrade.lamination", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 7), localizeTooltip("upgrade.type.armor", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 7), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.projectileProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.QuiltedCover.maxLevel) : RomanUtil.convertToRoman(Enchantment.projectileProtection.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 7), localizeTooltip("upgrade.cover", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 8), localizeTooltip("upgrade.type.boot", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 8), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.featherFalling.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticSoles.maxLevel) : RomanUtil.convertToRoman(Enchantment.featherFalling.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 8), localizeTooltip("upgrade.soles", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 9), localizeTooltip("upgrade.type.armor", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 9), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.fireProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.Firedamp.maxLevel) : RomanUtil.convertToRoman(Enchantment.fireProtection.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 9), localizeTooltip("upgrade.firedamp", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 10), localizeTooltip("upgrade.type.armor", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 10), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.blastProtection.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ElasticLayering.maxLevel) : RomanUtil.convertToRoman(Enchantment.blastProtection.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 10), localizeTooltip("upgrade.layering", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 11), localizeTooltip("upgrade.type.helmet", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 11), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.respiration.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.ScubaTank.maxLevel) : RomanUtil.convertToRoman(Enchantment.respiration.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 11), localizeTooltip("upgrade.tank", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 12), localizeTooltip("upgrade.type.helmet", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 12), FormatCodes.Yellow.code + StatCollector.translateToLocal(Enchantment.aquaAffinity.getName()) + " (" + (ArtificeConfig.limitUpgrades.getBoolean(true) ? RomanUtil.convertToRoman(EnumUpgrades.DiveKit.maxLevel) : RomanUtil.convertToRoman(Enchantment.aquaAffinity.getMaxLevel())) + ")");
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemUpgrade, 1, 12), localizeTooltip("upgrade.divekit", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 0), localizeTooltip("flammable", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 0), localizeTooltip("bucket.oil", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 1), localizeTooltip("explosive", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 1), localizeTooltip("bucket.fuel", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 2), localizeTooltip("flammable", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 2), localizeTooltip("bucket.creosote", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 3), localizeTooltip("flammable", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeItems.itemBucket, 1, 3), localizeTooltip("bucket.bitumen", true));
    }

    private static void initBlockTooltips()
    {
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockSulfur, 1, 1), localizeTooltip("sulfur", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockNiter, 1, 1), localizeTooltip("niter", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 3), localizeTooltip("lotus", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 3), localizeTooltip("lotus.close", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 3), localizeTooltip("lotus", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLotus, 1, 0), localizeTooltip("lotus.close", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLotus, 1, 0), localizeTooltip("lily", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 0), localizeTooltip("bluebell", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 0), localizeTooltip("bluebell", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 1), localizeTooltip("orchid", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 1), localizeTooltip("orchid", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 2), localizeTooltip("iris", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFlora, 1, 2), localizeTooltip("iris", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasalt, 1, 3), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarble, 1, 3), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasalt, 1, 4), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarble, 1, 4), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 2), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 2), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasaltSlab, 1, 3), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarbleSlab, 1, 3), localizeTooltip("connected", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasalt, 1, 0), localizeTooltip("volcanic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockBasalt, 1, 1), localizeTooltip("volcanic", false));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarble, 1, 0), localizeTooltip("metamorphic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockMarble, 1, 1), localizeTooltip("metamorphic", false));

        for (int i = 0; i < ArtificeBlocks.blockLimestones.length; i++)
        {
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 0), localizeTooltip("sedimentary", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 1), localizeTooltip("sedimentary", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 3), localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestones[i], 1, 4), localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 2), localizeTooltip("connected", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLimestoneSlabs[i], 1, 3), localizeTooltip("connected", false));
        }

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockDetector, 1, 0), localizeTooltip("detector", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockDetector, 1, 0), localizeTooltip("detector", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 0), localizeTooltip("frame.basic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 0), localizeTooltip("frame.basic", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 1), localizeTooltip("frame.reinforced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 1), localizeTooltip("frame.reinforced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 2), localizeTooltip("frame.industrial", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 2), localizeTooltip("frame.industrial", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 3), localizeTooltip("frame.advanced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockFrame, 1, 3), localizeTooltip("frame.advanced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 0), localizeTooltip("glasswall.basic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 0), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(0));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 0), localizeTooltip("glasswall.basic", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 1), localizeTooltip("glasswall.reinforced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 1), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(1));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 1), localizeTooltip("glasswall.reinforced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 2), localizeTooltip("glasswall.industrial", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 2), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(2));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 2), localizeTooltip("glasswall.industrial", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 3), localizeTooltip("glasswall.advanced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 3), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(3));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWall, 1, 3), localizeTooltip("glasswall.advanced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 0), localizeTooltip("glasswall.basic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 0), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(0));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 0), localizeTooltip("glasswall.basic", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 1), localizeTooltip("glasswall.reinforced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 1), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(1));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 1), localizeTooltip("glasswall.reinforced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 2), localizeTooltip("glasswall.industrial", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 2), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(2));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 2), localizeTooltip("glasswall.industrial", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 3), localizeTooltip("glasswall.advanced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 3), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockGlassWall.getResistance(3));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlassWallDark, 1, 3), localizeTooltip("glasswall.advanced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 0), localizeTooltip("blastwall.basic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 0), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockReinforced.getResistance(0));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 0), localizeTooltip("blastwall.basic", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 1), localizeTooltip("blastwall.reinforced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 1), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockReinforced.getResistance(1));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 1), localizeTooltip("blastwall.reinforced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 2), localizeTooltip("blastwall.industrial", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 2), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockReinforced.getResistance(2));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 2), localizeTooltip("blastwall.industrial", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 3), localizeTooltip("blastwall.advanced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 3), FormatCodes.Italic.code + localizeTooltip("resistance", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + (int)ArtificeBlocks.blockReinforced.getResistance(3));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockReinforced, 1, 3), localizeTooltip("blastwall.advanced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 0), localizeTooltip("scaffold.basic", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 0), FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(0));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 0), localizeTooltip("scaffold.basic", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 1), localizeTooltip("scaffold.reinforced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 1), FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(1));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 1), localizeTooltip("scaffold.reinforced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 2), localizeTooltip("scaffold.industrial", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 2), FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(2));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 2), localizeTooltip("scaffold.industrial", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 3), localizeTooltip("scaffold.advanced", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 3), FormatCodes.Italic.code + localizeTooltip("overhang", false) + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + ArtificeBlocks.blockScaffold.getOverhang(3));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockScaffold, 1, 3), localizeTooltip("scaffold.advanced", true));

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), localizeTooltip("steel.ingot", false));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockSteel, 1, 0), localizeTooltip("steel.ingot", true));

        for (int i = 0; i < 16; i++)
        {
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLamps[i], 1, 0), localizeTooltip("lamp", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLamps[i], 1, 0), localizeTooltip("lamp", true));

            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLampsInverted[i], 1, 0), localizeTooltip("lamp", false));
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockLampsInverted[i], 1, 0), localizeTooltip("lamp", true));
        }

        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlowSand, 1, 0), localizeTooltip("glowsand", true));
        ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockGlowSand, 1, 1), localizeTooltip("glowsand", true));

        for (int i=0; i<16; i++)
            ArtificeRegistry.registerTooltip(new ItemStack(ArtificeBlocks.blockColoredTorches[i], 1, 0), localizeTooltip("coloredtorch", true));
    }
}
