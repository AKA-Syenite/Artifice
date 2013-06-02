package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.gui.ArtificeCreativeTab;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemSteel extends Item
{
	public Icon icon;
	
	public ItemSteel(int id)
	{
		super(id);
		this.setUnlocalizedName("artifice.ingot.steel");
		this.setCreativeTab(ArtificeCreativeTab.tab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta)
	{
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
	{
		this.icon = reg.registerIcon("artifice:ingot_steel");
	}
}
