package shukaro.artifice.recipe;

import net.minecraft.enchantment.Enchantment;
import shukaro.artifice.ArtificeConfig;

public enum EnumUpgrades
{
    SharpeningKitS(Enchantment.sharpness, ArtificeConfig.maxSharpness),
    SharpeningKitE(Enchantment.efficiency, ArtificeConfig.maxEfficiency),
    Reinforcement(Enchantment.unbreaking, ArtificeConfig.maxUnbreaking),
    ReinforcedLimbs(Enchantment.punch, ArtificeConfig.maxPunch),
    PlaitedString(Enchantment.power, ArtificeConfig.maxPower),
    Counterweight(Enchantment.knockback, ArtificeConfig.maxKnockback),
    ArmorSpikes(Enchantment.thorns, ArtificeConfig.maxThorns),
    LaminatedPadding(Enchantment.protection, ArtificeConfig.maxProtection),
    QuiltedCover(Enchantment.projectileProtection, ArtificeConfig.maxProjectileProtection),
    ElasticSoles(Enchantment.featherFalling, ArtificeConfig.maxFeatherFalling),
    Firedamp(Enchantment.fireProtection, ArtificeConfig.maxFireProtection),
    ElasticLayering(Enchantment.blastProtection, ArtificeConfig.maxBlastProtection),
    ScubaTank(Enchantment.respiration, ArtificeConfig.maxRespiration),
    DiveKit(Enchantment.aquaAffinity, ArtificeConfig.maxAquaAffinity);

    public Enchantment enchant;
    public int maxLevel;

    private EnumUpgrades(Enchantment enchant, int maxLevel)
    {
        this.enchant = enchant;
        this.maxLevel = maxLevel;
    }
}