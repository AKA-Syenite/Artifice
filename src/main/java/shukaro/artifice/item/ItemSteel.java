package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.render.IconHandler;

import java.util.List;
import java.util.Locale;

public class ItemSteel extends ItemArtifice
{
    private Icon icon;
    private Icon dustIcon;
    private String[] names = {"ingot", "dust"};

    public ItemSteel(int id)
    {
        super(id);
        this.setUnlocalizedName("artifice.steel");
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
            return "item.artifice.steel." + names[0].toLowerCase(Locale.ENGLISH);
        return "item.artifice.steel." + names[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
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
}
