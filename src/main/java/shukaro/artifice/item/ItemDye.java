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

public class ItemDye extends ItemArtifice
{
    private Icon[] icons = new Icon[4];
    private String[] names = {"Blue", "Black", "Brown", "White"};

    public ItemDye(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.dye");
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
        for (int i = 0; i < names.length; i++)
        {
            icons[i] = IconHandler.registerSingle(reg, names[i].toLowerCase(Locale.ENGLISH), "dye");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < names.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.artifice.dye." + names[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}
