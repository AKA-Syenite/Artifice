package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.NameMetaPair;

import java.util.List;

public class ItemBox extends ItemArtifice
{
    private IIcon icon;

    public ItemBox()
    {
        super();
        this.setUnlocalizedName("artifice.box");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item id, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(id, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
        this.icon = IconHandler.registerSingle(reg, "box", "box");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.hasTagCompound() ? EnumRarity.uncommon : EnumRarity.common;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass)
    {
        return stack.hasTagCompound();
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
            this.onItemRightClick(stack, world, player);
            return true;
        }
        else
        {
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
            ItemStack thing = new ItemStack(Item.getItemById(tag.getInteger("id")), stack.getItemDamage(), tag.getInteger("meta"));
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
            NameMetaPair pair = new NameMetaPair(stack.getItem(), 0);
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
            if (Item.getItemById(tag.getInteger("id")) == null) return;
            infoList.add(FormatCodes.Italic.code + StatCollector.translateToLocal("tooltip.artifice.box.contains") + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + new ItemStack(Item.getItemById(tag.getInteger("id")), 1, tag.getInteger("meta")).getDisplayName());
            infoList.add(FormatCodes.Italic.code + StatCollector.translateToLocal("tooltip.artifice.box.amount") + " " + FormatCodes.Reset.code + FormatCodes.Aqua.code + stack.getItemDamage());
            NBTTagList enchants = tag.getCompoundTag("nbt").getTag("ench") != null ? (NBTTagList) tag.getCompoundTag("nbt").getTag("ench") : (NBTTagList) tag.getCompoundTag("nbt").getTag("StoredEnchantments");
            if (enchants != null)
            {
                for (int i = 0; i < enchants.tagCount(); i++)
                {
                    short id = enchants.getCompoundTagAt(i).getShort("id");
                    short lvl = enchants.getCompoundTagAt(i).getShort("lvl");
                    if (Enchantment.enchantmentsList[id] != null)
                    {
                        infoList.add(FormatCodes.Yellow.code + Enchantment.enchantmentsList[id].getTranslatedName(lvl));
                    }
                }
            }
        }
        if (ArtificeConfig.tooltips.getBoolean(true))
        {
            NameMetaPair pair = new NameMetaPair(stack.getItem(), 0);
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
