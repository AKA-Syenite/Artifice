package shukaro.artifice.recipe;

import net.minecraft.enchantment.Enchantment;

public enum EnumUpgrades
{
    SharpeningKit(Enchantment.sharpness, 3),
    Reinforcement(Enchantment.unbreaking, 2),
    ReinforcedLimbs(Enchantment.punch, 2),
    PlaitedString(Enchantment.power, 3),
    Counterweight(Enchantment.knockback, 2),
    ArmorSpikes(Enchantment.thorns, 2),
    LaminatedPadding(Enchantment.protection, 3),
    QuiltedCover(Enchantment.projectileProtection, 3),
    ElasticSoles(Enchantment.featherFalling, 3),
    Firedamp(Enchantment.fireProtection, 3),
    ElasticLayering(Enchantment.blastProtection, 3),
    ScubaTank(Enchantment.respiration, 3),
    DiveKit(Enchantment.aquaAffinity, 1);

    public Enchantment enchant;
    public int maxLevel;

    private EnumUpgrades(Enchantment enchant, int maxLevel)
    {
        this.enchant = enchant;
        this.maxLevel = maxLevel;
    }
}