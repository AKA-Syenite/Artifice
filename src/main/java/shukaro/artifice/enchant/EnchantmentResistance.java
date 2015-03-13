package shukaro.artifice.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import shukaro.artifice.ArtificeCore;

import java.util.Locale;

public class EnchantmentResistance extends Enchantment
{
    public EnchantmentResistance(int eid, int weight)
    {
        super(eid, weight, EnumEnchantmentType.armor);
        this.setName(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ".resistance");
        Enchantment.addToBookList(this);
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public int getMinEnchantability(int level)
    {
        return 10 + 20 * (level-1);
    }

    @Override
    public int getMaxEnchantability(int level)
    {
        return this.getMinEnchantability(level) + 50;
    }
}
