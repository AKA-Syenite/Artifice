package shukaro.artifice.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.render.IconHandler;

import java.util.List;
import java.util.Locale;

public class ItemResource extends ItemArtifice
{
    private IIcon[] icons = new IIcon[1];
    private String[] names = { "sulfur" };

    public ItemResource()
    {
        super();
        this.setUnlocalizedName("artifice.resource");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() >= names.length)
            return "item.artifice.resource." + names[0].toLowerCase(Locale.ENGLISH);
        return "item.artifice.resource." + names[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void getSubItems(Item id, CreativeTabs tab, List list)
    {
        for (int i=0; i<names.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        if (meta >= names.length)
            return icons[0];
        else
            return icons[meta];
    }

    @Override
    public void registerIcons(IIconRegister reg)
    {
        this.icons[0] = IconHandler.registerSingle(reg, "sulfur", "resources");
    }
}
