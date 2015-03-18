package shukaro.artifice;

import shukaro.artifice.enchant.EnchantmentInvisible;
import shukaro.artifice.enchant.EnchantmentResistance;
import shukaro.artifice.enchant.EnchantmentSoulstealing;

public class ArtificeEnchants
{
    public static EnchantmentInvisible enchantmentInvisible;
    public static EnchantmentSoulstealing enchantmentSoulstealing;
    public static EnchantmentResistance enchantmentResistance;

    public static void initEnchants()
    {
        if (ArtificeConfig.enchantmentInvisibleEnable)
        {
            int id = ArtificeConfig.enchantmentStartID;
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
            int id = ArtificeConfig.enchantmentStartID;
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
        if (ArtificeConfig.enchantmentResistanceEnable)
        {
            int id = ArtificeConfig.enchantmentStartID;
            while (id < 256)
            {
                try
                {
                    enchantmentResistance = new EnchantmentResistance(id, ArtificeConfig.enchantmentResistanceWeight);
                    ArtificeCore.logger.info("Registered resistance enchant to ID " + id);
                    break;
                }
                catch (IllegalArgumentException e)
                {
                    id++;
                }
            }
            if (enchantmentResistance == null)
                ArtificeCore.logger.warn("No available enchantment IDs for resistance enchant!");
        }
    }
}
