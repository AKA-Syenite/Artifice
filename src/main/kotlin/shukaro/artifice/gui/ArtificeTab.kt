package shukaro.artifice.gui

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

class ArtificeTab(label: String): CreativeTabs(label) {
  override fun getTabIconItem() = Items.COOKED_FISH
  override fun displayAllRelevantItems(itemList : List<ItemStack>) = super.displayAllRelevantItems(itemList)
}

