package shukaro.artifice.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;

public class ArtificeCreativeTab extends CreativeTabs
{
    public static final ArtificeCreativeTab tab = new ArtificeCreativeTab(
            "Artifice");
    
    public ArtificeCreativeTab(String label)
    {
        super(label);
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        return new ItemStack(ArtificeBlocks.blockFrame, 1, 0);
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
}
