package shukaro.artifice;

import shukaro.artifice.enchant.EnchantmentInvisible;
import shukaro.artifice.enchant.EnchantmentSoulstealing;

public class ArtificeEnchants
{
    public static EnchantmentInvisible enchantmentInvisible;
    public static EnchantmentSoulstealing enchantmentSoulstealing;

    public static void initEnchants()
    {
        if (ArtificeConfig.enchantmentInvisibleEnable)
            enchantmentInvisible = new EnchantmentInvisible(ArtificeConfig.enchantmentInvisibleID, ArtificeConfig.enchantmentInvisibleWeight);
        if (ArtificeConfig.enchantmentSoulstealingEnable)
            enchantmentSoulstealing = new EnchantmentSoulstealing(ArtificeConfig.enchantmentSoulstealingID, ArtificeConfig.enchantmentSoulstealingWeight);
    }
}
