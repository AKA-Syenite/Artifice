package shukaro.artifice.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeEnchants;

import java.util.List;

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

    @Override
    public void displayAllReleventItems(List itemList)
    {
        super.displayAllReleventItems(itemList);
        if (this.getTabLabel().equals(ArtificeCore.mainTab.getTabLabel()))
        {
            if (ArtificeConfig.enchantmentInvisibleEnable)
                itemList.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(ArtificeEnchants.enchantmentInvisible.effectId, ArtificeEnchants.enchantmentInvisible.getMaxLevel())));
            if (ArtificeConfig.enchantmentSoulstealingEnable)
            {
                for (int i=1; i<ArtificeEnchants.enchantmentSoulstealing.getMaxLevel()+1; i++)
                    itemList.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(ArtificeEnchants.enchantmentSoulstealing.effectId, i)));
            }
        }
    }
}
