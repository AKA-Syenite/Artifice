package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import shukaro.artifice.render.IconHandler;

import java.util.List;
import java.util.Locale;

public class ItemCoin extends ItemArtifice
{
    private Icon[] icons = new Icon[4];
    private String[] coinTypes = {"Copper", "Silver", "Gold", "Platinum"};

    public ItemCoin(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.coin");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        for (int i = 0; i < coinTypes.length; i++)
        {
            icons[i] = IconHandler.registerSingle(reg, coinTypes[i].toLowerCase(Locale.ENGLISH), "coin");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < coinTypes.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.artifice.coin." + coinTypes[stack.getItemDamage()].toLowerCase();
    }
}
