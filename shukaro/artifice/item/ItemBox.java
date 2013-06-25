package shukaro.artifice.item;

import java.util.List;

import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.util.ComparableItemStackNBT;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.IdMetaPair;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBox extends Item
{
    private Icon icon;
    
    public ItemBox(int id)
    {
        super(id);
        this.setCreativeTab(ArtificeCreativeTab.tab);
        this.setUnlocalizedName("artifice.box");
        this.setHasSubtypes(true);
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
        this.icon = reg.registerIcon("artifice:box");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return stack.getTagCompound() != null;
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
        NBTTagCompound tag = stack.getTagCompound();
        int num = 1;
        if (player.isSneaking())
            num = stack.stackSize;
        if (tag != null)
        {
            if (Block.blocksList[world.getBlockId(x, y, z)] instanceof IDeepStorageUnit)
            {
                IDeepStorageUnit unit = (IDeepStorageUnit) Block.blocksList[world.getBlockId(x, y, z)];
                ItemStack contained = unit.getStoredItemType();
                ItemStack boxed = new ItemStack(tag.getInteger("id"), tag.getInteger("size") * num, tag.getInteger("meta"));
                if (contained == null)
                {
                    unit.setStoredItemType(boxed.itemID, boxed.getItemDamage(), boxed.stackSize);
                    stack.stackSize -= num;
                    return true;
                }
                if (tag.getCompoundTag("nbt").hasNoTags() && contained.isItemEqual(boxed))
                {
                    if (unit.getMaxStoredCount() < (contained.stackSize + boxed.stackSize))
                        return false;
                    else
                    {
                        unit.setStoredItemCount(contained.stackSize + boxed.stackSize);
                        stack.stackSize -= num;
                        return true;
                    }
                }
                return false;
            }
            else
            {
                this.onItemRightClick(stack, world, player);
                return true;
            }
        }
        else
        {
            if (Block.blocksList[world.getBlockId(x, y, z)] instanceof IDeepStorageUnit)
            {
                IDeepStorageUnit unit = (IDeepStorageUnit) Block.blocksList[world.getBlockId(x, y, z)];
                ItemStack contained = unit.getStoredItemType();
                if (contained == null)
                    return false;
                
                NBTTagCompound newTag = new NBTTagCompound();
                newTag.setInteger("meta", contained.getItemDamage());
                newTag.setInteger("id", contained.itemID);
                
                boolean canFill = true;
                if (contained.stackSize > (8 * num))
                {
                    newTag.setInteger("size", 8);
                    unit.setStoredItemCount(contained.stackSize - (8 * num));
                }
                else if (contained.stackSize > 8)
                {
                    newTag.setInteger("size", 8);
                    unit.setStoredItemCount(contained.stackSize - 8);
                    canFill = false;
                }
                else
                {
                    newTag.setInteger("size", contained.stackSize);
                    unit.setStoredItemCount(0);
                    canFill = false;
                }

                ItemStack newStack;
                if (!canFill)
                {
                    newStack = new ItemStack(ArtificeItems.itemBox.itemID, 1, newTag.getInteger("size"));
                    stack.stackSize--;
                }
                else
                {
                    newStack = new ItemStack(ArtificeItems.itemBox.itemID, num, newTag.getInteger("size"));
                    stack.stackSize -= num;
                }
                newStack.setTagCompound(newTag);
                
                if (!player.inventory.addItemStackToInventory(newStack) && !world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, newStack));
                return true;
            }
            return false;
        }
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null)
        {
            // The thing in the boxes
            ItemStack thing = new ItemStack(tag.getInteger("id"), stack.getItemDamage(), tag.getInteger("meta"));
            if (!tag.getCompoundTag("nbt").hasNoTags())
                thing.setTagCompound(tag.getCompoundTag("nbt"));
            
            // We're dropping everything since the player is sneaking
            if (player.isSneaking())
            {
                // What's the total amount of items they should get?
                // Total number of boxes, times the amount of things in each box
                int numCrafted = (stack.stackSize * stack.getItemDamage());
                // If we need to give the player more than one stack's worth of items, we need to split it up
                if (numCrafted > thing.getMaxStackSize())
                {
                    int given = 0;
                    // While we haven't given them enough items
                    while (given < numCrafted)
                    {
                        ItemStack out = thing.copy();
                        // If the number we still need to give is greater than the max stack size, give them a full stack
                        // otherwise, give them them the total left to give
                        out.stackSize = (numCrafted - given) > thing.getMaxStackSize() ? thing.getMaxStackSize() : (numCrafted - given);
                        // We'll give the player whatever the output's stacksize is
                        given += out.stackSize;
                        if (out != null && out.stackSize > 0 && !player.inventory.addItemStackToInventory(out) && !player.worldObj.isRemote)
                            player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, out));
                    }
                }
                // Otherwise we just give them the extra :V
                else
                {
                    ItemStack out = thing.copy();
                    out.stackSize = numCrafted;
                    // If it won't fit in their inventory, drop it in the world
                    if (out != null && out.stackSize > 0 && !player.inventory.addItemStackToInventory(out) && !player.worldObj.isRemote)
                        player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, out));
                }
                
                // Stack is gone, we're done here folks
                stack.stackSize = 0;
                return stack;
            }
            
            // Split the stack if stack sizes won't permit normal unboxing
            ItemStack extra = null;
            if (thing.stackSize > thing.getMaxStackSize() && stack.stackSize > 1)
            {
                extra = stack.copy();
                extra.stackSize = stack.stackSize - 1;
                stack.stackSize = 1;
                thing.stackSize = stack.getItemDamage();
            }
            // Crack open the box
            ItemStack drop = null;
            if (thing.stackSize > thing.getMaxStackSize())
            {
                drop = thing.copy();
                drop.stackSize = thing.getMaxStackSize();
                stack.setItemDamage(stack.getItemDamage() - drop.stackSize);
                if (stack.getItemDamage() < 1)
                    stack.stackSize--;
                if (drop != null && !player.inventory.addItemStackToInventory(drop) && !world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, drop));
            }
            else
            {
                drop = thing.copy();
                stack.stackSize--;
                if (drop != null && !player.inventory.addItemStackToInventory(drop) && !world.isRemote)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, drop));
            }
            
            // Give the player the leftover boxes
            if (extra != null && !player.inventory.addItemStackToInventory(extra) && !world.isRemote)
                world.spawnEntityInWorld(new EntityItem(world, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, extra));
        }
        return stack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (ArtificeConfig.tooltips.getBoolean(true))
        {
            IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
            if (ArtificeRegistry.getTooltipMap().get(pair) != null)
            {
                for (String s : ArtificeRegistry.getTooltipMap().get(pair))
                {
                    if (s.startsWith(ArtificeTooltips.commentCode))
                        continue;
                    infoList.add(s);
                }
            }
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null)
        {
            infoList.add("Contains: " + FormatCodes.Aqua.format + new ItemStack(tag.getInteger("id"), 1, tag.getInteger("meta")).getDisplayName());
            infoList.add("Amount: " + FormatCodes.Aqua.format + stack.getItemDamage());
            NBTTagList enchants = (NBTTagList) tag.getCompoundTag("nbt").getTag("ench") != null ? (NBTTagList) tag.getCompoundTag("nbt").getTag("ench") : (NBTTagList) tag.getCompoundTag("nbt").getTag("StoredEnchantments");
            if (enchants != null)
            {
                for (int i=0; i<enchants.tagCount(); i++)
                {
                    short id = ((NBTTagCompound)enchants.tagAt(i)).getShort("id");
                    short lvl = ((NBTTagCompound)enchants.tagAt(i)).getShort("lvl");
                    if (Enchantment.enchantmentsList[id] != null)
                    {
                        infoList.add(FormatCodes.Yellow.format + Enchantment.enchantmentsList[id].getTranslatedName(lvl));
                    }
                }
            }
        }
        if (ArtificeConfig.tooltips.getBoolean(true))
        {
            IdMetaPair pair = new IdMetaPair(stack.itemID, 0);
            if (ArtificeRegistry.getTooltipMap().get(pair) != null)
            {
                for (String s : ArtificeRegistry.getTooltipMap().get(pair))
                {
                    if (!ArtificeConfig.flavorText.getBoolean(true) || !s.startsWith(ArtificeTooltips.commentCode))
                        continue;
                    infoList.add(s);
                }
            }
        }
    }
}
