package shukaro.artifice.recipe;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;

public class RecipeUpgrade implements IRecipe
{
	private ItemStack output;
	
	@Override
	public boolean matches(InventoryCrafting craft, World world)
	{
		this.output = null;
		
		// Make sure there's only one target in the grid
		int numTools = 0;
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack == null)
				continue;
			// If it isn't a tool and it's not an upgrade, false
			if (!stack.getItem().isItemTool(stack) && stack.itemID != ArtificeItems.itemUpgrade.itemID)
				return false;
			else if (stack.getItem().isItemTool(stack))
				numTools++;
		}
		if (numTools != 1)
			return false;
		
		// Get our target
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.getItem().isItemTool(stack))
				this.output = stack.copy();
		}
		
		int numUpgrades = 0;
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID == ArtificeItems.itemUpgrade.itemID)
				numUpgrades++;
		}
		if (numUpgrades == 0)
			return false;
		
		if (this.output == null)
			return false;
		
		// Apply the enchants
		for (int i=0; i<craft.getSizeInventory(); i++)
		{
			ItemStack stack = craft.getStackInSlot(i);
			if (stack != null && stack.itemID == ArtificeItems.itemUpgrade.itemID)
			{
				Enchantment enchant;
				int maxLevel = 0;
				
				// Sharpening Kit
				if (stack.getItemDamage() == 0)
				{
					if (ArtificeConfig.limitUpgrades.getBoolean(true))
						maxLevel = EnumUpgrades.SharpeningKit.maxLevel;
					else
						maxLevel = EnumUpgrades.SharpeningKit.enchant.getMaxLevel();
					
					if (Enchantment.sharpness.canApply(this.output))
						enchant = Enchantment.sharpness;
					else if (Enchantment.efficiency.canApply(this.output))
						enchant = Enchantment.efficiency;
					else
						return false;
				}
				else
				{
					enchant = EnumUpgrades.values()[stack.getItemDamage()].enchant;
					if (ArtificeConfig.limitUpgrades.getBoolean(true))
						maxLevel = EnumUpgrades.values()[stack.getItemDamage()].maxLevel;
					else
						maxLevel = EnumUpgrades.values()[stack.getItemDamage()].enchant.getMaxLevel();
				}
				
				if (enchant.canApply(this.output) && this.output.getItem().getItemEnchantability() > 0)
				{
					if (EnchantmentHelper.getEnchantmentLevel(enchant.effectId, this.output) < maxLevel)
					{
						if (EnchantmentHelper.getEnchantmentLevel(enchant.effectId, this.output) == 0)
						{
							if (this.output.stackTagCompound != null)
							{
								NBTTagList enchants = (NBTTagList)this.output.stackTagCompound.getTag("ench");
								if (enchants != null && enchants.tagCount() > 0)
								{
									for (int k=0; k<enchants.tagCount(); k++)
									{
										NBTTagCompound tag = (NBTTagCompound) enchants.tagAt(k);
										Enchantment other = Enchantment.enchantmentsList[tag.getShort("id")];
										if (!enchant.canApplyTogether(other))
											return false;
									}
								}
							}
							this.output.addEnchantment(enchant, 1);
						}
						else
						{
							NBTTagList enchants = (NBTTagList)this.output.stackTagCompound.getTag("ench");
							for (int j=0; j<enchants.tagCount(); j++)
							{
								NBTTagCompound tag = (NBTTagCompound) enchants.tagAt(j);
								if (tag.getShort("id") == enchant.effectId)
									tag.setShort("lvl", (short)(tag.getShort("lvl") + 1));
							}
						}
					}
					else
						return false;
				}
				else
					return false;
			}
		}
		
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craft)
	{
		return this.output.copy();
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return this.output;
	}
}
