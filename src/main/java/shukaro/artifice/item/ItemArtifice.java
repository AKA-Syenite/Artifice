package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.gui.ArtificeCreativeTab;

import java.util.List;

public abstract class ItemArtifice extends Item
{
    public ItemArtifice()
    {
        super();
        this.setHasSubtypes(true);
        this.setCreativeTab(ArtificeCreativeTab.main);
    }

    @Override
    public abstract String getUnlocalizedName(ItemStack stack);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void getSubItems(Item id, CreativeTabs tab, List list);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract IIcon getIconFromDamage(int meta);

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IIconRegister reg);

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips.getBoolean(true))
            return;
        IdMetaPair pair = new IdMetaPair(stack.itemID, stack.getItemDamage());
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
