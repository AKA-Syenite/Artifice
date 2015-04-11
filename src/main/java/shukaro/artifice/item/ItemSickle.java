package shukaro.artifice.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.NameMetaPair;

import java.util.List;
import java.util.Locale;

public class ItemSickle extends ItemTool
{
    private IIcon icon;
    private int radius;

    public ItemSickle(Item.ToolMaterial mat)
    {
        super(3, mat, null);
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setMaxDamage(mat.getMaxUses() / 2);
        this.setUnlocalizedName("artifice.sickle." + this.toolMaterial.toString().toLowerCase(Locale.ENGLISH));
        this.radius = getRadius(mat);
        GameRegistry.registerItem(this, this.getUnlocalizedName());
    }

    public int getRadius(Item.ToolMaterial mat)
    {
        switch (mat)
        {
            case EMERALD:
                return 5;
            case GOLD:
                return 4;
            case IRON:
                return 3;
            case STONE:
                return 2;
            case WOOD:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips)
            return;
        NameMetaPair pair = new NameMetaPair(stack.getItem(), 0);
        if (ArtificeRegistry.getTooltipMap().get(pair) != null)
        {
            for (String s : ArtificeRegistry.getTooltipMap().get(pair))
            {
                if (!ArtificeConfig.flavorText && s.startsWith(ArtificeTooltips.commentCode))
                    continue;
                infoList.add(s);
            }
        }
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
        this.icon = TextureHandler.registerIcon(reg, "sickle_" + this.toolMaterial.toString().toLowerCase(Locale.ENGLISH), "sickle");
    }

    // getStrVsBlock
    @Override
    public float func_150893_a(ItemStack stack, Block block)
    {
        return 1.0F;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        BlockCoord coord = new BlockCoord(x, y, z);
        World world = player.worldObj;
        Block block = coord.getBlock(world);
        int radius = getRadius(this.toolMaterial);

        if (block instanceof IPlantable)
        {
            int count = 0;
            for (int i = -radius; i < radius; i++)
            {
                for (int k = -radius; k < radius; k++)
                {
                    Block b = world.getBlock(x + i, y, z + k);
                    int meta = world.getBlockMetadata(x + i, y, z + k);
                    if (b instanceof IPlantable && coord.getDistance(x + i, y, z + k) <= radius && b.canHarvestBlock(player, meta))
                    {
                        b.harvestBlock(world, player, x + i, y, z + k, meta);
                        world.setBlockToAir(x + i, y, z + k);
                        count++;
                    }
                }
            }
            if (!world.isRemote)
                world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, block.stepSound.getBreakSound(), 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
            if (stack.attemptDamageItem(count / 2, itemRand))
                player.destroyCurrentEquippedItem();
            return true;
        }
        else if (block != null && block.isLeaves(world, x, y, z))
        {
            int count = 0;
            for (BlockCoord c : coord.getRadiusBlocks(radius))
            {
                Block b = world.getBlock(c.x, c.y, c.z);
                int meta = world.getBlockMetadata(c.x, c.y, c.z);
                if (b != null && coord.getDistance(c) <= radius && b.isLeaves(world, c.x, c.y, c.z))
                {
                    if (b.canHarvestBlock(player, meta))
                        b.harvestBlock(world, player, c.x, c.y, c.z, meta);
                    world.setBlockToAir(c.x, c.y, c.z);
                    count++;
                }
            }
            if (!world.isRemote)
                world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, block.stepSound.getBreakSound(), 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
            stack.damageItem(count / 4, player);
            if (stack.stackSize <= 0)
                player.destroyCurrentEquippedItem();
            return true;
        }

        return super.onBlockStartBreak(stack, x, y, z, player);
    }
}
