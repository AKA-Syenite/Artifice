package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
                    EntityXPOrb xp = new EntityXPOrb(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, 5*sslvl);
                    event.entityLiving.worldObj.spawnEntityInWorld(xp);
                }
            }
        }
    }
}
