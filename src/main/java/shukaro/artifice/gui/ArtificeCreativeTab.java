package shukaro.artifice.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeItems;

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
            itemList.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(ArtificeCore.enchantmentInvisible.effectId, ArtificeCore.enchantmentInvisible.getMaxLevel())));
            for (int i=1; i<ArtificeCore.enchantmentSoulstealing.getMaxLevel()+1; i++)
                itemList.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(ArtificeCore.enchantmentSoulstealing.effectId, i)));
        }
    }
}
