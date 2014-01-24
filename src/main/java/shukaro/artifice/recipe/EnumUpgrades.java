package shukaro.artifice.recipe;

import net.minecraft.enchantment.Enchantment;
import shukaro.artifice.ArtificeConfig;

public enum EnumUpgrades
{
    SharpeningKitS(Enchantment.sharpness, ArtificeConfig.maxSharpness.getInt()),
    SharpeningKitE(Enchantment.efficiency, ArtificeConfig.maxEfficiency.getInt()),
    Reinforcement(Enchantment.unbreaking, ArtificeConfig.maxUnbreaking.getInt()),
    ReinforcedLimbs(Enchantment.punch, ArtificeConfig.maxPunch.getInt()),
    PlaitedString(Enchantment.power, ArtificeConfig.maxPower.getInt()),
    Counterweight(Enchantment.knockback, ArtificeConfig.maxKnockback.getInt()),
    ArmorSpikes(Enchantment.thorns, ArtificeConfig.maxThorns.getInt()),
    LaminatedPadding(Enchantment.protection, ArtificeConfig.maxProtection.getInt()),
    QuiltedCover(Enchantment.projectileProtection, ArtificeConfig.maxProjectileProtection.getInt()),
    ElasticSoles(Enchantment.featherFalling, ArtificeConfig.maxFeatherFalling.getInt()),
    Firedamp(Enchantment.fireProtection, ArtificeConfig.maxFireProtection.getInt()),
    ElasticLayering(Enchantment.blastProtection, ArtificeConfig.maxBlastProtection.getInt()),
    ScubaTank(Enchantment.respiration, ArtificeConfig.maxRespiration.getInt()),
    DiveKit(Enchantment.aquaAffinity, ArtificeConfig.maxAquaAffinity.getInt());

    public Enchantment enchant;
    public int maxLevel;

    private EnumUpgrades(Enchantment enchant, int maxLevel)
    {
        this.enchant = enchant;
        this.maxLevel = maxLevel;
    }
}