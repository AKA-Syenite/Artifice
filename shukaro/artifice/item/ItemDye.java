package shukaro.artifice.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.util.IdMetaPair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDye extends Item
{
    public Icon[] icons = new Icon[4];
    public String[] names = { "Blue", "Black", "Brown", "White" };
    
    public ItemDye(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.dye");
        this.setHasSubtypes(true);
        this.setCreativeTab(ArtificeCreativeTab.main);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
    	if (meta > icons.length)
    		meta = 0;
        return icons[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        for (int i=0; i<names.length; i++)
        {
            icons[i] = IconHandler.registerSingle(reg, names[i].toLowerCase(), "dye");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i=0; i<names.length; i++)
            list.add(new ItemStack(id, 1, i));
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.artifice.dye." + names[stack.getItemDamage()].toLowerCase();
    }
    
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
