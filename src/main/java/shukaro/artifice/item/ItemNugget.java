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

public class ItemNugget extends ItemArtifice
{
    private Icon[] icons = new Icon[3];
    private String[] nuggetTypes = {"Copper", "Silver", "Platinum"};

    public ItemNugget(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.nugget");
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
        for (int i = 0; i < nuggetTypes.length; i++)
        {
            icons[i] = IconHandler.registerSingle(reg, nuggetTypes[i].toLowerCase(Locale.ENGLISH), "nugget");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < nuggetTypes.length; i++)
            list.add(new ItemStack(id, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item.artifice.nugget." + nuggetTypes[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}
