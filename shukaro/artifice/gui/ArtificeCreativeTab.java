package shukaro.artifice.gui;

import shukaro.artifice.ArtificeCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ArtificeCreativeTab extends CreativeTabs
{
	public static final ArtificeCreativeTab tab = new ArtificeCreativeTab("Artifice");

	public ArtificeCreativeTab(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(ArtificeCore.blockFrame, 1, 0);
	}
	
	@Override
	public String getTranslatedTabLabel()
	{
		return this.getTabLabel();
	}
}
