package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.util.NameMetaPair;

import java.util.List;

public class ItemBucket extends cofh.core.item.ItemBucket
{
    public ItemBucket(String modName)
    {
        super(modName);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips.getBoolean(true))
            return;
        NameMetaPair pair = new NameMetaPair(stack.getItem(), stack.getItemDamage());
        if (ArtificeRegistry.getTooltipMap().get(pair) != null)
        {
            for (String s : ArtificeRegistry.getTooltipMap().get(pair))
            {
                if (!ArtificeConfig.flavorText.getBoolean(true) && s.startsWith(ArtificeTooltips.commentCode))
                    continue;
                infoList.add(s);
            }
        }
    }
}
