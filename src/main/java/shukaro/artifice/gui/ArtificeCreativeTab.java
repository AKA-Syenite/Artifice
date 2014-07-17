package shukaro.artifice.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;

public class ArtificeCreativeTab extends CreativeTabs
{
    public static final ArtificeCreativeTab main = new ArtificeCreativeTab("Artifice");
    //public static final ArtificeCreativeTab documents = new ArtificeCreativeTab("Documents");

    private ArtificeCreativeTab(String label)
    {
        super(label);
    }

    @Override
    public Item getTabIconItem()
    {
        /*
        if (this.getTabLabel() == documents.getTabLabel())
    	{
    		return new ItemStack(Item.writtenBook);
    	}
    	*/
        if (this.getTabLabel().equals(main.getTabLabel()))
        {
            if (ArtificeConfig.enableFrames.getBoolean(true))
                return Item.getItemFromBlock(ArtificeBlocks.blockFrame);
            if (ArtificeConfig.enableWorldGen.getBoolean(true))
                return Item.getItemFromBlock(ArtificeBlocks.blockBasalt);
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
        else
            return null;
    }

    @Override
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
}
