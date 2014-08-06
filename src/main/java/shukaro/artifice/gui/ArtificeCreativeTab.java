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
        {
            if (ArtificeConfig.enableFrames.getBoolean(true))
                return Item.getItemFromBlock(ArtificeBlocks.blockFrame);
            if (ArtificeConfig.enableUpgrades.getBoolean(true))
                return ArtificeItems.itemUpgrade;
            if (ArtificeConfig.enableSledges.getBoolean(true))
                return ArtificeItems.itemSledgeDiamond;
            if (ArtificeConfig.enableCoins.getBoolean(true))
                return ArtificeItems.itemCoin;
            if (ArtificeConfig.enableBoxes.getBoolean(true))
                return ArtificeItems.itemBox;
            if (ArtificeConfig.enableSteel.getBoolean(true))
                return ArtificeItems.itemSteelIngot;
            return null;
        }
        else if (this.getTabLabel().equals(ArtificeCore.worldTab.getTabLabel()))
        {
            if (ArtificeConfig.enableWorldGen.getBoolean(true))
                return Item.getItemFromBlock(ArtificeBlocks.blockBasalt);
        }
        return null;
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
}
