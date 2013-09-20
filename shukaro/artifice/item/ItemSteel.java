package shukaro.artifice.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.util.IdMetaPair;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemSteel extends Item
{
    public Icon icon;
    public Icon dustIcon;
    private String[] names = { "ingot", "dust" };
    
    public ItemSteel(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.steel");
        this.setCreativeTab(ArtificeCreativeTab.main);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
        if (meta == 0)
        	return icon;
        else
        	return dustIcon;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
    	if (stack.getItemDamage() > 1)
    		return "item.artifice.steel." + names[0].toLowerCase();
        return "item.artifice.steel." + names[stack.getItemDamage()].toLowerCase();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
    	list.add(new ItemStack(id, 1, 0));
    	if (ArtificeConfig.alternateSteel.getBoolean(false))
    		list.add(new ItemStack(id, 1, 1));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        this.icon = IconHandler.registerSingle(reg, "ingot_steel", "steel");
        this.dustIcon = IconHandler.registerSingle(reg, "dust_steel", "steel");
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
