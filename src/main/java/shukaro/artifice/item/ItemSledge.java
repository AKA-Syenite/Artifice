package shukaro.artifice.item;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.NameMetaPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ItemSledge extends ItemTool
{
    private IIcon icon;
    private int lossChance;

    public ItemSledge(Item.ToolMaterial mat)
    {
        super(2, mat, null);
        this.setCreativeTab(ArtificeCore.mainTab);
        if (mat == ToolMaterial.EMERALD)
            this.setMaxDamage(mat.getMaxUses() / 32);
        else
            this.setMaxDamage(mat.getMaxUses() / 16);
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

    // canHarvestBlock
    @Override
    public boolean func_150897_b(Block block)
    {
        return true;
    }

    // getStrVsBlock
    @Override
    public float func_150893_a(ItemStack stack, Block block)
    {
        return this.toolMaterial.getEfficiencyOnProperMaterial();
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
        this.icon = TextureHandler.registerIcon(reg, "sledge_" + this.toolMaterial.toString().toLowerCase(Locale.ENGLISH), "sledge");
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        World world = player.worldObj;
        if (world.isRemote || player.capabilities.isCreativeMode)
            return super.onBlockStartBreak(stack, x, y, z, player);

        try
        {
            ArrayList<ItemStack> dropped = getIngredientsForBlock(world, x, y, z, player.getHeldItem());
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
        if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.capabilities.isCreativeMode)
                return true;
        }
        stack.damageItem(1, entity);
        return true;
    }

    public static ArrayList<ItemStack> getIngredientsForBlock(World world, int x, int y, int z, ItemStack held)
    {
        if (world.isAirBlock(x, y, z))
            return null;
        Block b = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        ArrayList<ItemStack> drops = b.getDrops(world, x, y, z, meta, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, held));
        ArrayList<ItemStack> smashedDrops = new ArrayList<ItemStack>();
        for (ItemStack normalDrop : drops)
        {
            ArrayList<ItemStack> ingredients = getIngredientsForItemStack(normalDrop, world.rand);
            if (ingredients != null)
                smashedDrops.addAll(ingredients);
            else
                smashedDrops.add(normalDrop);
        }
        return smashedDrops;
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<ItemStack> getIngredientsForItemStack(ItemStack input, Random rand)
    {
        if (input != null)
        {
            for (IRecipe recipe : (List<IRecipe>)CraftingManager.getInstance().getRecipeList())
            {
                if ((recipe instanceof ShapedRecipes || recipe instanceof ShapedOreRecipe) &&
                        recipe.getRecipeOutput() != null &&
                        recipe.getRecipeOutput().getUnlocalizedName().equals(input.getUnlocalizedName()) &&
                        ((!recipe.getRecipeOutput().getHasSubtypes()) || (recipe.getRecipeOutput().getItemDamage() == input.getItemDamage())))
                {
                    if (recipe.getRecipeOutput().stackSize > 1 && rand.nextInt(recipe.getRecipeOutput().stackSize) == 0)
                        return getIngredientsForRecipe(recipe);
                    return getIngredientsForRecipe(recipe);
                }
            }
        }
        return null;
    }

    private static ArrayList<ItemStack> getIngredientsForRecipe(IRecipe recipe)
    {
        if (recipe instanceof ShapedRecipes)
            return getIngredientsForShapedRecipe((ShapedRecipes)recipe);
        if (recipe instanceof ShapedOreRecipe)
            return getIngredientsForShapedOreRecipe((ShapedOreRecipe)recipe);
        return null;
    }

    private static ArrayList<ItemStack> getIngredientsForShapedRecipe(ShapedRecipes recipe)
    {
        ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
        for (ItemStack input : recipe.recipeItems)
            ingredients.add(input);
        return ingredients;
    }

    private static ArrayList<ItemStack> getIngredientsForShapedOreRecipe(ShapedOreRecipe recipe)
    {
        try
        {
            ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
            Object[] objects = ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, recipe, 3);
            ItemStack[] items = new ItemStack[objects.length];
            for (int i = 0; i < objects.length; i++)
            {
                if ((objects[i] instanceof ItemStack)) {
                    items[i] = ((ItemStack)objects[i]);
                }
                if (((objects[i] instanceof ArrayList)) && (((ArrayList)objects[i]).size() > 0)) {
                    items[i] = ((ItemStack)((ArrayList)objects[i]).get(0));
                }
            }
            for (ItemStack input : items)
            {
                if (input != null)
                    ingredients.add(input);
            }
            return ingredients;
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
