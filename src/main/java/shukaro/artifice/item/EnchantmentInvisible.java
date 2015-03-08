package shukaro.artifice.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;
import shukaro.artifice.ArtificeCore;

import java.util.Locale;

public class EnchantmentInvisible extends Enchantment
{
    public EnchantmentInvisible(int eid, int weight)
    {
        super(eid, weight, EnumEnchantmentType.armor);
        this.setName(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ".invisible");
        Enchantment.addToBookList(this);
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public int getMinEnchantability(int level)
    {
        return 10;
    }

    @Override
    public int getMaxEnchantability(int level)
    {
        return this.getMinEnchantability(level) + 60;
    }
}
