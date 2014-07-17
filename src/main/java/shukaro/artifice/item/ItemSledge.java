package shukaro.artifice.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.util.ItemMetaPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemSledge extends ItemTool
{
    private IIcon icon;
    private int lossChance;

    public ItemSledge(Item.ToolMaterial mat)
    {
        super(2, mat, null);
        this.setCreativeTab(ArtificeCreativeTab.main);
        this.setMaxDamage(mat.getMaxUses() / 4);
        this.setUnlocalizedName("artifice.sledge." + this.toolMaterial.toString().toLowerCase(Locale.ENGLISH));
        this.lossChance = getLossChance(mat);
    }

    public int getLossChance(Item.ToolMaterial mat)
    {
        switch (mat)
        {
            case EMERALD:
                return 20;
            case GOLD:
                return 25;
            case IRON:
                return 30;
            case STONE:
                return 40;
            case WOOD:
                return 50;
            default:
                return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips.getBoolean(true))
            return;
        ItemMetaPair pair = new ItemMetaPair(stack.getItem(), 0);
        if (ArtificeRegistry.getTooltipMap().get(pair) != null)
        {
            for (String s : ArtificeRegistry.getTooltipMap().get(pair))
            {
                if (!ArtificeConfig.flavorText.getBoolean(true) && s.startsWith(ArtificeTooltips.commentCode))
                    continue;
                infoList.add(s);
            }
        }
    }

    // canHarvestBlock
    @Override
    public boolean func_150897_b(Block block)
    {
        if (ArtificeRegistry.getWildSledgeBlocks().get(block) != null)
            return true;
        for (int i = 0; i < 16; i++)
        {
            if (ArtificeRegistry.getSledgeBlocks().get(new ItemMetaPair(block, i)) != null)
                return true;
        }
        return false;
    }

    // getStrVsBlock
    @Override
    public float func_150893_a(ItemStack stack, Block block)
    {
        if (ArtificeRegistry.getWildSledgeBlocks().get(block) != null)
            return this.toolMaterial.getEfficiencyOnProperMaterial();
        for (int i = 0; i < 16; i++)
        {
            if (ArtificeRegistry.getSledgeBlocks().get(new ItemMetaPair(block, i)) != null)
                return this.toolMaterial.getEfficiencyOnProperMaterial();
        }
        return 1.0F;
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
        this.icon = IconHandler.registerSingle(reg, "sledge_" + this.toolMaterial.toString().toLowerCase(Locale.ENGLISH), "sledge");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        World world = player.worldObj;
        if (world.isRemote || player.capabilities.isCreativeMode)
            return super.onBlockStartBreak(stack, x, y, z, player);

        try
        {
            Block block = world.getBlock(x, y, z);
            int meta = world.getBlockMetadata(x, y, z);
            ItemMetaPair pair = new ItemMetaPair(block, meta);

            ArrayList<ItemStack> dropped = ArtificeRegistry.getWildSledgeBlocks().get(block);
            if (dropped == null)
                dropped = ArtificeRegistry.getSledgeBlocks().get(pair);

            if (dropped != null)
            {
                for (ItemStack is : dropped)
                {
                    if (world.rand.nextInt(100) > this.lossChance)
                        world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, is.copy()));
                }
                world.setBlockToAir(x, y, z);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
    {
        int meta = world.getBlockMetadata(x, y, z);
        ItemMetaPair pair = new ItemMetaPair(block, meta);

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.capabilities.isCreativeMode)
                return true;
        }

        if (ArtificeRegistry.getSledgeBlocks().get(pair) != null)
            stack.damageItem(1, entity);

        return true;
    }
}
