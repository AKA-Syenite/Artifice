package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import shukaro.artifice.ArtificeBlocks;
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
            if (reslvl == 0)
                return;
            double maxResistance = (reslvl * 7) / (float)100;
            double minResistance = maxResistance / (float)2;
            double finalResistance = minResistance + (event.entityLiving.getRNG().nextDouble() * (maxResistance - minResistance));
            double reduction = 1.0F - finalResistance;
            event.ammount *= reduction;
            if (event.ammount % 1 != 0)
            {
                double floor = Math.floor(event.ammount);
                double remainder = event.ammount - floor;
                if (event.entityLiving.getRNG().nextDouble() > remainder)
                    event.ammount = (float)floor;
                else
                    event.ammount = (float)floor+1;
            }
            while (true)
            {
                int toDamage = event.entityLiving.worldObj.rand.nextInt(4) + 1;
                if (event.entityLiving.getEquipmentInSlot(toDamage) != null)
                {
                    event.entityLiving.getEquipmentInSlot(toDamage).damageItem(1, event.entityLiving);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public void onAnvil(AnvilRepairEvent event)
    {
        if (event.output == null || event.output.getItem() == null || event.output.getItem().getUnlocalizedName() == null)
            return;
        else if (event.output.getItem().getUnlocalizedName().contains("tile.artifice.attunedredstone") && event.output.hasTagCompound())
            event.output.getTagCompound().removeTag("RepairCost");
    }
}
