package shukaro.artifice;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.block.decorative.BlockBasalt;
import shukaro.artifice.block.decorative.BlockBasaltSlab;
import shukaro.artifice.block.decorative.BlockFlora;
import shukaro.artifice.block.decorative.BlockMarble;
import shukaro.artifice.block.decorative.BlockMarbleSlab;
import shukaro.artifice.block.decorative.BlockStairsArtifice;
import shukaro.artifice.block.decorative.ItemBlockBasalt;
import shukaro.artifice.block.decorative.ItemBlockFlora;
import shukaro.artifice.block.decorative.ItemBlockMarble;
import shukaro.artifice.block.frame.BlockFrame;
import shukaro.artifice.block.frame.BlockFrameBase;
import shukaro.artifice.block.frame.BlockFrameRefractory;
import shukaro.artifice.block.frame.ItemBlockFrame;
import shukaro.artifice.event.EventHandler;
import shukaro.artifice.event.WorldTicker;
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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion)
public class ArtificeCore
{
	@SidedProxy(clientSide = "shukaro.artifice.net.ClientProxy", serverSide = "shukaro.artifice.net.CommonProxy")
	public static CommonProxy proxy;
	
	public static final String modID = "artifice";
	public static final String modName = "Artifice";
	public static final String modVersion = "1.5.2R1.0";
	
	public static ArtificeWorldGen worldGen;
	public static Logger logger;
	public static EventHandler eventHandler;
	
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
		ArtificeConfig.setConfigFolderBase(evt.getModConfigurationDirectory());
		
		ArtificeConfig.initCommon(evt);
		ArtificeConfig.initClient(evt);
		
		ArtificeConfig.extractLang(new String[] {"en_US"});
		ArtificeConfig.loadLang();
		
		logger = evt.getModLog();
		this.eventHandler = new EventHandler();
		MinecraftForge.EVENT_BUS.register(this.eventHandler);
		ClientProxy.init();
		CommonProxy.init();
	}
	
	@Init
	public void init(FMLInitializationEvent evt)
	{
		ArtificeBlocks.initBlocks();
		
		GameRegistry.registerWorldGenerator(this.worldGen = new ArtificeWorldGen());
		
		if (ArtificeConfig.floraBoneMeal.getBoolean(true))
		{
			MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 0, 10);
			MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 1, 10);
			MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 2, 10);
			MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 3, 10);
		}
		
		ArtificeRecipes.registerRecipes();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent evt)
	{
		
	}
}
