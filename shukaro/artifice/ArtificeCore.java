package shukaro.artifice;

import java.util.logging.Level;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.block.BlockFrame;
import shukaro.artifice.block.ItemBlockFrame;
import shukaro.artifice.block.decorative.BlockBasalt;
import shukaro.artifice.block.decorative.BlockBasaltSlab;
import shukaro.artifice.block.decorative.BlockFlora;
import shukaro.artifice.block.decorative.BlockMarble;
import shukaro.artifice.block.decorative.BlockMarbleSlab;
import shukaro.artifice.block.decorative.BlockStairsArtifice;
import shukaro.artifice.block.decorative.ItemBlockBasalt;
import shukaro.artifice.block.decorative.ItemBlockFlora;
import shukaro.artifice.block.decorative.ItemBlockMarble;
import shukaro.artifice.block.decorative.ItemBlockSlab;
import shukaro.artifice.net.ClientProxy;
import shukaro.artifice.net.CommonProxy;
import shukaro.artifice.world.ArtificeWorldGen;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion)
public class ArtificeCore
{
	@SidedProxy(clientSide = "shukaro.artifice.net.ClientProxy", serverSide = "shukaro.artifice.net.CommonProxy")
	public static CommonProxy proxy;
	
	public static final String modID = "artifice";
	public static final String modName = "Artifice";
	public static final String modVersion = "1.0";
	
	public static BlockFrame blockFrame;
	public static BlockFlora blockFlora;
	public static BlockBasalt blockBasalt;
	public static BlockMarble blockMarble;
	public static BlockStairsArtifice blockBasaltBrickStairs;
	public static BlockStairsArtifice blockMarbleBrickStairs;
	public static BlockStairsArtifice blockBasaltCobbleStairs;
	public static BlockStairsArtifice blockMarbleCobbleStairs;
	public static BlockHalfSlab blockBasaltSlab;
	public static BlockHalfSlab blockBasaltDoubleSlab;
	public static BlockHalfSlab blockMarbleSlab;
	public static BlockHalfSlab blockMarbleDoubleSlab;
	
	public static Property idStart;
	
	public static Property blockFrameID;
	public static Property blockFloraID;
	public static Property blockBasaltID;
	public static Property blockMarbleID;
	public static Property blockBasaltBrickStairsID;
	public static Property blockMarbleBrickStairsID;
	public static Property blockBasaltCobbleStairsID;
	public static Property blockMarbleCobbleStairsID;
	public static Property blockBasaltSlabID;
	public static Property blockBasaltDoubleSlabID;
	public static Property blockMarbleSlabID;
	public static Property blockMarbleDoubleSlabID;
	
	public static Property floraWorldGen;
	public static Property basaltWorldGen;
	public static Property basaltSize;
	public static Property basaltHeight;
	public static Property marbleWorldGen;
	public static Property marbleSize;
	public static Property marbleHeight;
	public static Property floraRecipes;
	public static Property basaltRecipes;
	public static Property marbleRecipes;
	public static Property floraBoneMeal;
	
	public static Property dimensionBlacklist;
	
	public static int frameRenderID;
	
	public static String[] tiers = {"Basic","Reinforced","Industrial","Advanced"};
	public static String[] flora = {"Bluebell","Orchid","Iris","Lotus"};
	public static String[] rocks = {"","Cobblestone","Brick","Paver","Antipaver","Chiseled"};
	
	@Instance(modID)
	public static ArtificeCore instance;
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent evt)
	{
		
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
		try
		{
			c.load();
			idStart = c.get(Configuration.CATEGORY_BLOCK, "Block.IDStart", 3000);
			idStart.comment = "The block ID to use as the starting point for assignment, there are currently 12 IDs needed";
			
			int s = idStart.getInt();
			
			blockFrameID = c.getBlock("blockFrame", s++);
			blockFloraID = c.getBlock("blockFlora", s++);
			blockBasaltID = c.getBlock("blockBasalt", s++);
			blockMarbleID = c.getBlock("blockMarble", s++);
			blockBasaltBrickStairsID = c.getBlock("blockBasaltBrickStairs", s++);
			blockMarbleBrickStairsID = c.getBlock("blockMarbleBrickStairs", s++);
			blockBasaltCobbleStairsID = c.getBlock("blockBasaltCobbleStairs", s++);
			blockMarbleCobbleStairsID = c.getBlock("blockMarbleCobbleStairs", s++);
			blockBasaltSlabID = c.getBlock("blockBasaltSlab", s++);
			blockBasaltDoubleSlabID = c.getBlock("blockBasaltDoubleSlab", s++);
			blockMarbleSlabID = c.getBlock("blockMarbleSlab", s++);
			blockMarbleDoubleSlabID = c.getBlock("blockMarbleDoubleSlab", s++);
			
			floraWorldGen = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.Flora", true);
			floraWorldGen.comment = "Whether or not to generate flora during map generation";
			basaltWorldGen = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.Basalt", true);
			basaltWorldGen.comment = "Whether or not to generate basalt during map generation";
			basaltSize = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.BasaltSize", 20000);
			basaltSize.comment = "Absolute maximum size of basalt deposits in the world";
			basaltHeight = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.BasaltHeight", 64);
			basaltHeight.comment = "Max height to begin basalt generation";
			marbleWorldGen = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.Marble", true);
			marbleWorldGen.comment = "Whether or not to generate marble during map generation";
			marbleSize = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.MarbleSize", 20000);
			marbleSize.comment = "Absolute maximum size of marble deposits in the world";
			marbleHeight = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.MarbleHeight", 64);
			marbleHeight.comment = "Max height to begin marble generation";
			dimensionBlacklist = c.get(Configuration.CATEGORY_GENERAL, "WorldGen.DimensionBlacklist", "");
			dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in.";
			
			floraRecipes = c.get(Configuration.CATEGORY_GENERAL, "Recipes.Flora", true);
			floraRecipes.comment = "Set to false to disable flower-related recipes";
			basaltRecipes = c.get(Configuration.CATEGORY_GENERAL, "Recipes.Basalt", true);
			basaltRecipes.comment = "Set to false to disable basalt recipes";
			marbleRecipes = c.get(Configuration.CATEGORY_GENERAL, "Recipes.Marble", true);
			marbleRecipes.comment = "Set to false to disable marble recipes";
			floraBoneMeal = c.get(Configuration.CATEGORY_GENERAL, "Bonemeal.Flora", true);
			floraBoneMeal.comment = "Set to false to disable random flower growth from bonemeal";
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Artifice couldn't load the config file");
		}
		finally
		{
			c.save();
		}
	}
	
	@Init
	public void init(FMLInitializationEvent evt)
	{
		blockFlora = new BlockFlora(blockFloraID.getInt());
		blockBasalt = new BlockBasalt(blockBasaltID.getInt());
		blockMarble = new BlockMarble(blockMarbleID.getInt());
		blockBasaltBrickStairs = new BlockStairsArtifice(blockBasaltBrickStairsID.getInt(), blockBasalt, 2);
		blockMarbleBrickStairs = new BlockStairsArtifice(blockMarbleBrickStairsID.getInt(), blockMarble, 2);
		blockBasaltCobbleStairs = new BlockStairsArtifice(blockBasaltCobbleStairsID.getInt(), blockBasalt, 1);
		blockMarbleCobbleStairs = new BlockStairsArtifice(blockMarbleCobbleStairsID.getInt(), blockMarble, 1);
		blockBasaltSlab = (BlockHalfSlab) new BlockBasaltSlab(blockBasaltSlabID.getInt(), false);
		blockBasaltDoubleSlab = (BlockHalfSlab) new BlockBasaltSlab(blockBasaltDoubleSlabID.getInt(), true);
		blockMarbleSlab = (BlockHalfSlab) new BlockMarbleSlab(blockMarbleSlabID.getInt(), false);
		blockMarbleDoubleSlab = (BlockHalfSlab) new BlockMarbleSlab(blockMarbleDoubleSlabID.getInt(), true);
		blockFrame = new BlockFrame(blockFrameID.getInt());
		
		Item.itemsList[blockBasaltSlabID.getInt()] = new ItemBlockSlab(blockBasaltSlabID.getInt() - 256, blockBasaltSlab, blockBasaltDoubleSlab, false);
		Item.itemsList[blockBasaltDoubleSlabID.getInt()] = new ItemBlockSlab(blockBasaltDoubleSlabID.getInt() - 256, blockBasaltSlab, blockBasaltDoubleSlab, true);
		Item.itemsList[blockMarbleSlabID.getInt()] = new ItemBlockSlab(blockMarbleSlabID.getInt() - 256, blockMarbleSlab, blockMarbleDoubleSlab, false);
		Item.itemsList[blockMarbleDoubleSlabID.getInt()] = new ItemBlockSlab(blockMarbleDoubleSlabID.getInt() - 256, blockMarbleSlab, blockMarbleDoubleSlab, true);
		
		GameRegistry.registerBlock(blockFrame, ItemBlockFrame.class, blockFrame.getUnlocalizedName());
		GameRegistry.registerBlock(blockFlora, ItemBlockFlora.class, blockFlora.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasalt, ItemBlockBasalt.class, blockBasalt.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarble, ItemBlockMarble.class, blockMarble.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasaltBrickStairs, blockBasaltBrickStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarbleBrickStairs, blockMarbleBrickStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockBasaltCobbleStairs, blockBasaltCobbleStairs.getUnlocalizedName());
		GameRegistry.registerBlock(blockMarbleCobbleStairs, blockMarbleCobbleStairs.getUnlocalizedName());
		
		for (int i=0; i<tiers.length; i++)
		{
			LanguageRegistry.addName(new ItemStack(blockFrame, 1, i), tiers[i] + " Frame");
		}
		
		for (int i=0; i<flora.length; i++)
		{
			LanguageRegistry.addName(new ItemStack(blockFlora, 1, i), flora[i]);
		}
		
		for (int i=0; i<rocks.length; i++)
		{
			LanguageRegistry.addName(new ItemStack(blockBasalt, 1, i), "Basalt " + rocks[i]);
			LanguageRegistry.addName(new ItemStack(blockMarble, 1, i), "Marble " + rocks[i]);
		}
		
		LanguageRegistry.addName(new ItemStack(blockBasalt, 1, 5), rocks[5] + " Basalt");
		LanguageRegistry.addName(new ItemStack(blockMarble, 1, 5), rocks[5] + " Marble");
		
		LanguageRegistry.addName(new ItemStack(blockBasaltBrickStairs, 1, 0), "Basalt Brick Stairs");
		LanguageRegistry.addName(new ItemStack(blockMarbleBrickStairs, 1, 0), "Marble Brick Stairs");
		LanguageRegistry.addName(new ItemStack(blockBasaltCobbleStairs, 1, 0), "Basalt Cobblestone Stairs");
		LanguageRegistry.addName(new ItemStack(blockMarbleCobbleStairs, 1, 0), "Marble Cobblestone Stairs");
		
		LanguageRegistry.addName(new ItemStack(blockBasaltSlab, 1, 0), "Basalt Brick Slab");
		LanguageRegistry.addName(new ItemStack(blockBasaltSlab, 1, 1), "Basalt Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(blockBasaltSlab, 1, 2), "Basalt Paver Slab");
		LanguageRegistry.addName(new ItemStack(blockBasaltSlab, 1, 3), "Basalt Antipaver Slab");
		
		LanguageRegistry.addName(new ItemStack(blockMarbleSlab, 1, 0), "Marble Brick Slab");
		LanguageRegistry.addName(new ItemStack(blockMarbleSlab, 1, 1), "Marble Cobblestone Slab");
		LanguageRegistry.addName(new ItemStack(blockMarbleSlab, 1, 2), "Marble Paver Slab");
		LanguageRegistry.addName(new ItemStack(blockMarbleSlab, 1, 3), "Marble Antipaver Slab");
		
		if (ArtificeCore.floraBoneMeal.getBoolean(true))
		{
			MinecraftForge.addGrassPlant(ArtificeCore.blockFlora, 0, 10);
			MinecraftForge.addGrassPlant(ArtificeCore.blockFlora, 1, 10);
			MinecraftForge.addGrassPlant(ArtificeCore.blockFlora, 2, 10);
			MinecraftForge.addGrassPlant(ArtificeCore.blockFlora, 3, 10);
		}
		
		ArtificeRecipes.registerRecipes();
		GameRegistry.registerWorldGenerator(new ArtificeWorldGen());
		ClientProxy.init();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent evt)
	{
		
	}
}
