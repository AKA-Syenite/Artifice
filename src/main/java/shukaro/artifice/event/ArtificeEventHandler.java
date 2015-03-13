package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeEnchants;

public class ArtificeEventHandler
{
    @SubscribeEvent
    public void onDeath(LivingDeathEvent event)
    {
        if (event.source.getEntity() instanceof EntityPlayer && ArtificeConfig.enchantmentSoulstealingEnable)
        {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            ItemStack equipped = player.getCurrentEquippedItem();
            if (equipped != null)
            {
                int sslvl = EnchantmentHelper.getEnchantmentLevel(ArtificeEnchants.enchantmentSoulstealing.effectId, equipped);
                if (sslvl > 0)
                {
                    EntityXPOrb xp = new EntityXPOrb(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ArtificeConfig.enchantmentSoulstealingBonus*sslvl);
                    event.entityLiving.worldObj.spawnEntityInWorld(xp);
                }
            }
        }
    }

    @SubscribeEvent
    public void onDamage(LivingHurtEvent event)
    {
        if ((event.source.isMagicDamage() || event.source.getDamageType().equals("wither")) && ArtificeConfig.enchantmentResistanceEnable)
        {
            int reslvl = 0;
            for (int i=1; i<=4; i++)
                reslvl += EnchantmentHelper.getEnchantmentLevel(ArtificeEnchants.enchantmentResistance.effectId, event.entityLiving.getEquipmentInSlot(i));
            double reduction = reslvl * 7;
            reduction /= 100;
            if (event.entityLiving.getRNG().nextDouble() < reduction)
            {
                int toDamage = event.entityLiving.worldObj.rand.nextInt(4)+1;
                event.entityLiving.getEquipmentInSlot(toDamage).damageItem(1, event.entityLiving);
                event.setCanceled(true);
            }
        }
    }
}
