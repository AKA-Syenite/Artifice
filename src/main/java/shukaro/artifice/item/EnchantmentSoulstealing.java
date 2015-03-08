package shukaro.artifice.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import shukaro.artifice.ArtificeCore;

import java.util.Locale;

public class EnchantmentSoulstealing extends Enchantment
{
    public EnchantmentSoulstealing(int eid, int weight)
    {
        super(eid, weight, EnumEnchantmentType.weapon);
        this.setName(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ".soulstealing");
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
        return 15 + (level - 1) * 9;
    }

    @Override
    public int getMaxEnchantability(int level)
    {
        return this.getMinEnchantability(level) + 50;
    }
}
