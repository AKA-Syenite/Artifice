package shukaro.artifice;

import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import shukaro.artifice.event.EventHandler;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.net.ClientProxy;
import shukaro.artifice.net.CommonProxy;
import shukaro.artifice.world.ArtificeWorldGen;
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

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion)
public class ArtificeCore
{
    @SidedProxy(clientSide = "shukaro.artifice.net.ClientProxy", serverSide = "shukaro.artifice.net.CommonProxy")
    public static CommonProxy proxy;
    
    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modVersion = "1.5.2R1.0";
    
    public static ArtificeWorldGen worldGen;
    public static Logger logger;
    public static EventHandler eventHandler;
    
    public static int frameRenderID;
    public static int lotusRenderID;
    
    public static String[] tiers = { "Basic", "Reinforced", "Industrial", "Advanced" };
    public static String[] flora = { "Bluebell", "Orchid", "Iris", "Lotus", "LotusClosed"};
    public static String[] rocks = { "", "Cobblestone", "Brick", "Paver", "Antipaver", "Chiseled" };
    
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
        
        ArtificeConfig.extractLang(new String[] { "en_US" });
        ArtificeConfig.loadLang();
        
        logger = evt.getModLog();
        ArtificeCore.eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(ArtificeCore.eventHandler);
        ClientProxy.init();
        CommonProxy.init();
    }
    
    @Init
    public void init(FMLInitializationEvent evt)
    {
        ArtificeBlocks.initBlocks();
        ArtificeItems.initItems();
        
        ArtificeTooltips.initTooltips();
        
        GameRegistry.registerWorldGenerator(ArtificeCore.worldGen = new ArtificeWorldGen());
        
        if (ArtificeConfig.floraBoneMeal.getBoolean(true))
        {
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 0, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 1, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 2, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 3, 10);
        }
        
        ArtificeRecipes.registerRecipes();
        
        GameRegistry.registerTileEntity(TileEntityMultiblock.class, "ArtificeMultiblock");
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent evt)
    {
        
    }
}
