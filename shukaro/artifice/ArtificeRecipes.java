package shukaro.artifice;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ArtificeRecipes
{
	public static void registerRecipes()
	{
		if (ArtificeCore.basaltRecipes.getBoolean(true))
			registerBasaltRecipes();
		if (ArtificeCore.marbleRecipes.getBoolean(true))
			registerMarbleRecipes();
		if (ArtificeCore.floraRecipes.getBoolean(true))
			registerDyeRecipes();
	}
	
	private static void registerDyeRecipes()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 0), new ItemStack(ArtificeCore.blockFlora.blockID, 1, 1)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 2), new ItemStack(Item.dyePowder.itemID, 1, 4), new ItemStack(Item.dyePowder.itemID, 1, 11)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 3), new ItemStack(ArtificeCore.blockFlora.blockID, 1, 2)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 4), new ItemStack(ArtificeCore.blockFlora.blockID, 1, 0)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder.itemID, 2, 15), new ItemStack(ArtificeCore.blockFlora.blockID, 1, 3)));
	}
	
	private static void registerBasaltRecipes()
	{
		FurnaceRecipes.smelting().addSmelting(ArtificeCore.blockBasalt.blockID, 1, new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 0), 0.1F);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasalt.blockID, 4, 2), new Object[]
				{
					"XX",
					"XX",
					'X', new ItemStack(ArtificeCore.blockBasalt, 1, 0)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasalt.blockID, 4, 3), new Object[]
				{
					"XX",
					"XX",
					'X', new ItemStack(ArtificeCore.blockBasalt, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasalt.blockID, 2, 5), new Object[]
				{
					"X",
					"X",
					'X', new ItemStack(ArtificeCore.blockBasalt, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 4), new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 3)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 3), new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 4)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltBrickStairs, 4, 0), new Object[]
				{
					"X  ",
					"XX ",
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltCobbleStairs.blockID, 4, 0), new Object[]
				{
					"X  ",
					"XX ",
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 1)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 6, 0), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 6, 1), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 1)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 6, 2), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 3)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 6, 3), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockBasalt.blockID, 1, 4)
				}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 1, 2), new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 1, 3)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 1, 3), new ItemStack(ArtificeCore.blockBasaltSlab.blockID, 1, 2)));
	}
	
	private static void registerMarbleRecipes()
	{
		FurnaceRecipes.smelting().addSmelting(ArtificeCore.blockMarble.blockID, 1, new ItemStack(ArtificeCore.blockMarble.blockID, 1, 0), 0.1F);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarble.blockID, 4, 2), new Object[]
				{
					"XX",
					"XX",
					'X', new ItemStack(ArtificeCore.blockMarble, 1, 0)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarble.blockID, 4, 3), new Object[]
				{
					"XX",
					"XX",
					'X', new ItemStack(ArtificeCore.blockMarble, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarble.blockID, 2, 5), new Object[]
				{
					"X",
					"X",
					'X', new ItemStack(ArtificeCore.blockMarble, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockMarble.blockID, 1, 4), new ItemStack(ArtificeCore.blockMarble.blockID, 1, 3)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockMarble.blockID, 1, 3), new ItemStack(ArtificeCore.blockMarble.blockID, 1, 4)));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleBrickStairs, 4, 0), new Object[]
				{
					"X  ",
					"XX ",
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleCobbleStairs.blockID, 4, 0), new Object[]
				{
					"X  ",
					"XX ",
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 1)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 6, 0), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 2)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 6, 1), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 1)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 6, 2), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 3)
				}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 6, 3), new Object[]
				{
					"XXX",
					'X', new ItemStack(ArtificeCore.blockMarble.blockID, 1, 4)
				}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 1, 2), new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 1, 3)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 1, 3), new ItemStack(ArtificeCore.blockMarbleSlab.blockID, 1, 2)));
	}
}
