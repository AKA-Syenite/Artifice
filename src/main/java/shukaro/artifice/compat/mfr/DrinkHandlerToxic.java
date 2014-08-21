package shukaro.artifice.compat.mfr;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import powercrystals.minefactoryreloaded.api.ILiquidDrinkHandler;

public class DrinkHandlerToxic implements ILiquidDrinkHandler
{
    public int toxicity;

    public DrinkHandlerToxic(int toxicity)
    {
        this.toxicity = toxicity;
    }

    @Override
    public void onDrink(EntityLivingBase player)
    {
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, (5 * toxicity) * 20, toxicity));
        player.addPotionEffect(new PotionEffect(Potion.poison.id, (5 * toxicity) * 20, toxicity));
    }
}
