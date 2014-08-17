package shukaro.artifice.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeItems;

public class ArtificeCreativeTab extends CreativeTabs
{
    public ArtificeCreativeTab(String label)
    {
        super(label);
    }

    @Override
    public Item getTabIconItem()
    {
        if (this.getTabLabel().equals(ArtificeCore.mainTab.getTabLabel()))
            return Item.getItemFromBlock(ArtificeBlocks.blockFrame);
        else if (this.getTabLabel().equals(ArtificeCore.worldTab.getTabLabel()))
            return Item.getItemFromBlock(ArtificeBlocks.blockBasalt);
        return null;
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
}
