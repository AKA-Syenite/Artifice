package shukaro.artifice;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import shukaro.artifice.util.BlockMetaPair;
import shukaro.artifice.util.IDMetaPair;

import java.util.*;

public abstract class ArtificeRegistry
{
    private static final List<Integer> dimensionBlacklist = new ArrayList<Integer>();
    private static final Set<Block> stoneTypes = new HashSet<Block>();
    private static final List<String> worldTypeBlacklist = new ArrayList<String>();
    private static final Map<BlockMetaPair, ArrayList<ItemStack>> sledgeBlocks = new HashMap<BlockMetaPair, ArrayList<ItemStack>>();
    private static final Map<Block, ArrayList<ItemStack>> wildSledgeBlocks = new HashMap<Block, ArrayList<ItemStack>>();
    private static final Map<IDMetaPair, List<String>> tooltipMap = new HashMap<IDMetaPair, List<String>>();
    private static final List<ItemStack> marbleTypes = new ArrayList<ItemStack>();
    private static final List<ItemStack> basaltTypes = new ArrayList<ItemStack>();

    public static void registerMarbleType(Block block, int meta)
    {
        ArtificeCore.logger.info("Registering marble type " + Block.blockRegistry.getNameForObject(block) + ":" + meta);
        ItemStack stack = new ItemStack(block, 1, meta);
        if (marbleTypes.isEmpty())
            marbleTypes.add(stack);
        else if (!marbleTypes.contains(stack))
        {
            marbleTypes.add(stack);
            if (!ArtificeConfig.stoneCycleRecipes.getBoolean(true))
                return;
            if (marbleTypes.size() < 3)
            {
                IRecipe inner = new ShapelessOreRecipe(marbleTypes.get(1), marbleTypes.get(0));
                IRecipe wrap = new ShapelessOreRecipe(marbleTypes.get(0), marbleTypes.get(1));
                CraftingManager.getInstance().getRecipeList().add(inner);
                CraftingManager.getInstance().getRecipeList().add(wrap);
            }
            else
            {
                IRecipe inner = new ShapelessOreRecipe(marbleTypes.get(marbleTypes.size() - 1), marbleTypes.get(marbleTypes.size() - 2));
                IRecipe wrap = new ShapelessOreRecipe(marbleTypes.get(0), marbleTypes.get(marbleTypes.size() - 1));
                for (Object o : CraftingManager.getInstance().getRecipeList().toArray())
                {
                    if (o instanceof ShapelessOreRecipe)
                    {
                        ItemStack output = ((ShapelessOreRecipe) o).getRecipeOutput();
                        if (!output.isItemEqual(marbleTypes.get(0)))
                            continue;
                        ArrayList<Object> input = ((ShapelessOreRecipe) o).getInput();
                        if (input.size() > 1)
                            continue;
                        else if (input.get(0) instanceof ItemStack && ((ItemStack) input.get(0)).isItemEqual(marbleTypes.get(marbleTypes.size() - 2)))
                            CraftingManager.getInstance().getRecipeList().remove(o);
                    }
                }
                CraftingManager.getInstance().getRecipeList().add(inner);
                CraftingManager.getInstance().getRecipeList().add(wrap);
            }
        }
    }

    public static List<ItemStack> getMarbleTypes()
    {
        return marbleTypes;
    }

    public static void registerBasaltType(Block block, int meta)
    {
        ArtificeCore.logger.info("Registering basalt type " + Block.blockRegistry.getNameForObject(block) + ":" + meta);
        ItemStack stack = new ItemStack(block, 1, meta);
        if (basaltTypes.isEmpty())
            basaltTypes.add(stack);
        else if (!basaltTypes.contains(stack))
        {
            basaltTypes.add(stack);
            if (!ArtificeConfig.stoneCycleRecipes.getBoolean(true))
                return;
            if (basaltTypes.size() < 3)
            {
                IRecipe inner = new ShapelessOreRecipe(basaltTypes.get(1), basaltTypes.get(0));
                IRecipe wrap = new ShapelessOreRecipe(basaltTypes.get(0), basaltTypes.get(1));
                CraftingManager.getInstance().getRecipeList().add(inner);
                CraftingManager.getInstance().getRecipeList().add(wrap);
            }
            else
            {
                IRecipe inner = new ShapelessOreRecipe(basaltTypes.get(basaltTypes.size() - 1), basaltTypes.get(basaltTypes.size() - 2));
                IRecipe wrap = new ShapelessOreRecipe(basaltTypes.get(0), basaltTypes.get(basaltTypes.size() - 1));
                for (Object o : CraftingManager.getInstance().getRecipeList().toArray())
                {
                    if (o instanceof ShapelessOreRecipe)
                    {
                        ItemStack output = ((ShapelessOreRecipe) o).getRecipeOutput();
                        if (!output.isItemEqual(basaltTypes.get(0)))
                            continue;
                        ArrayList<Object> input = ((ShapelessOreRecipe) o).getInput();
                        if (input.size() > 1)
                            continue;
                        else if ((input.get(0) instanceof ItemStack) && ((ItemStack) input.get(0)).isItemEqual(basaltTypes.get(basaltTypes.size() - 2)))
                            CraftingManager.getInstance().getRecipeList().remove(o);
                    }
                }
                CraftingManager.getInstance().getRecipeList().add(inner);
                CraftingManager.getInstance().getRecipeList().add(wrap);
            }
        }
    }

    public static List<ItemStack> getBasaltTypes()
    {
        return basaltTypes;
    }

    public static void registerTooltip(Item item, int meta, String line)
    {
        IDMetaPair pair = new IDMetaPair(item, meta);
        if (tooltipMap.get(pair) == null)
        {
            List<String> temp = new ArrayList<String>();
            temp.add(line);
            tooltipMap.put(pair, temp);
        }
        else
        {
            tooltipMap.get(pair).add(line);
        }
    }

    public static void registerTooltip(Block block, int meta, String line)
    {
        registerTooltip(Item.getItemFromBlock(block), meta, line);
    }

    public static Map<IDMetaPair, List<String>> getTooltipMap()
    {
        return tooltipMap;
    }

    public static void registerSledgeBlock(Block block, int meta, ArrayList<ItemStack> drops)
    {
        if (block == null)
        {
            ArtificeCore.logger.warn("Tried to register null block in the sledge blocks list!");
            return;
        }
        BlockMetaPair pair = new BlockMetaPair(block, meta);
        if (!pair.isValid())
            ArtificeCore.logger.warn("Tried to register non-block id-meta pair in the sledgeBlock map: " + pair.toString());
        else if (sledgeBlocks.get(pair) == null)
        {
            ArtificeCore.logger.info("Registering sledgeable block " + pair);
            sledgeBlocks.put(pair, drops);
        }
    }

    public static void registerWildSledgeBlock(Block block, ArrayList<ItemStack> drops)
    {
        if (block == null)
        {
            ArtificeCore.logger.warn("Tried to register null block in the non-meta'd sledge blocks list!");
            return;
        }
        if (wildSledgeBlocks.get(block) == null)
        {
            ArtificeCore.logger.info("Registering non-meta'd sledgeable block with name " + block.getUnlocalizedName());
            wildSledgeBlocks.put(block, drops);
        }
    }

    public static Map<Block, ArrayList<ItemStack>> getWildSledgeBlocks()
    {
        return wildSledgeBlocks;
    }

    public static Map<BlockMetaPair, ArrayList<ItemStack>> getSledgeBlocks()
    {
        return sledgeBlocks;
    }

    public static void registerDimensionBlacklist(int dimID)
    {
        if (!dimensionBlacklist.contains(dimID))
        {
            ArtificeCore.logger.info("Registering " + dimID + " to dimension blacklist");
            dimensionBlacklist.add(dimID);
        }
    }

    public static List<Integer> getDimensionBlacklist()
    {
        return dimensionBlacklist;
    }

    public static void registerStoneType(Block stone)
    {
        if (!stoneTypes.contains(stone))
        {
            ArtificeCore.logger.info("Registering stone type with name " + Block.blockRegistry.getNameForObject(stone));
            stoneTypes.add(stone);
        }
    }

    public static Set<Block> getStoneTypes()
    {
        return stoneTypes;
    }

    public static void registerWorldTypeBlacklist(String type)
    {
        if (!worldTypeBlacklist.contains(type))
        {
            ArtificeCore.logger.info("Registering " + type + " to world type blacklist");
            worldTypeBlacklist.add(type);
        }
    }

    public static List<String> getWorldTypeBlacklist()
    {
        return worldTypeBlacklist;
    }
}
