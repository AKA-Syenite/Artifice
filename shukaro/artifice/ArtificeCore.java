package shukaro.artifice;

import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import shukaro.artifice.event.ArtificeEventHandler;
import shukaro.artifice.net.ClientProxy;
import shukaro.artifice.net.CommonProxy;
import shukaro.artifice.recipe.ArtificeRecipes;
import shukaro.artifice.world.ArtificeWorldGen;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion)
public class ArtificeCore
{
    @SidedProxy(clientSide = "shukaro.artifice.net.ClientProxy", serverSide = "shukaro.artifice.net.CommonProxy")
    public static CommonProxy proxy;
    
    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modVersion = "1.6.4R1.1.3";
    
    public static ArtificeWorldGen worldGen;
    public static Logger logger;
    public static ArtificeEventHandler eventHandler;
    
    public static int frameRenderID;
    public static int lotusRenderID;
    
    public static String[] tiers = { "Basic", "Reinforced", "Industrial", "Advanced" };
    public static String[] flora = { "Bluebell", "Orchid", "Iris", "Lotus", "LotusClosed"};
    public static String[] rocks = { "", "Cobblestone", "Brick", "Paver", "Antipaver", "Chiseled" };
    
    @Instance(modID)
    public static ArtificeCore instance;
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        ArtificeConfig.setConfigFolderBase(evt.getModConfigurationDirectory());
        
        ArtificeConfig.initCommon(evt);
        ArtificeConfig.initClient(evt);
        
        logger = evt.getModLog();
        ArtificeCore.eventHandler = new ArtificeEventHandler();
        MinecraftForge.EVENT_BUS.register(ArtificeCore.eventHandler);
        ClientProxy.init();
        CommonProxy.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        ArtificeBlocks.initBlocks();
        ArtificeItems.initItems();
        
        ArtificeTooltips.initTooltips();
        
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
            GameRegistry.registerWorldGenerator(ArtificeCore.worldGen = new ArtificeWorldGen());
        
        if (ArtificeConfig.floraBoneMeal.getBoolean(true) && ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 0, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 1, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 2, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 3, 10);
        }
        
        ArtificeRecipes.registerRecipes();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
        
    }
}
