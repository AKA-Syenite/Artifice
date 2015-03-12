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
        {
            int id = 70;
            while (id < 256)
            {
                try
                {
                    enchantmentInvisible = new EnchantmentInvisible(id, ArtificeConfig.enchantmentInvisibleWeight);
                    ArtificeCore.logger.info("Registered invisible enchant to ID " + id);
                    break;
                }
                catch (IllegalArgumentException e)
                {
                    id++;
                }
            }
            if (enchantmentInvisible == null)
                ArtificeCore.logger.warn("No available enchantment IDs for invisible enchant!");
        }
        if (ArtificeConfig.enchantmentSoulstealingEnable)
        {
            int id = 70;
            while (id < 256)
            {
                try
                {
                    enchantmentSoulstealing = new EnchantmentSoulstealing(id, ArtificeConfig.enchantmentSoulstealingWeight);
                    ArtificeCore.logger.info("Registered soulstealing enchant to ID " + id);
                    break;
                }
                catch (IllegalArgumentException e)
                {
                    id++;
                }
            }
            if (enchantmentSoulstealing == null)
                ArtificeCore.logger.warn("No available enchantment IDs for soulstealing enchant!");
        }
    }
}
