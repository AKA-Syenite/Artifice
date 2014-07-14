package shukaro.artifice;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;
import shukaro.artifice.event.ArtificeEventHandler;
import shukaro.artifice.net.ClientPacketHandler;
import shukaro.artifice.net.ServerPacketHandler;
import shukaro.artifice.recipe.ArtificeRecipes;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.world.ArtificeWorldGen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.Logger;

import pl.asie.lib.network.PacketHandler;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion)
public class ArtificeCore
{
    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modChannel = "Artifice";
    public static final String modVersion = "1.6.4R1.1.3";

    public static ArtificeWorldGen worldGen;
    public static Logger logger;
    public static ArtificeEventHandler eventHandler;
    public static PacketHandler packet;
    
    public static final String[] tiers = {"Basic", "Reinforced", "Industrial", "Advanced"};
    public static final String[] flora = {"Bluebell", "Orchid", "Iris", "Lotus", "LotusClosed"};
    public static final String[] rocks = {"", "Cobblestone", "Brick", "Paver", "Antipaver", "Chiseled"};

    @SideOnly(Side.CLIENT)
    public static ConcurrentHashMap<ChunkCoord, ConcurrentHashMap<BlockCoord, int[]>> textureCache;

    @Instance(modID)
    public static ArtificeCore instance;

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        logger = evt.getModLog();

        ArtificeConfig.initClient(evt);
        ArtificeConfig.initCommon(evt);

        packet = new PacketHandler(modID, new ClientPacketHandler(), new ServerPacketHandler());
        ArtificeCore.eventHandler = new ArtificeEventHandler();
        MinecraftForge.EVENT_BUS.register(ArtificeCore.eventHandler);

        ArtificeBlocks.initBlocks();
        ArtificeItems.initItems();

        if (ArtificeConfig.enableWorldGen.getBoolean(true))
            GameRegistry.registerWorldGenerator(ArtificeCore.worldGen = new ArtificeWorldGen(), 100);

        if (ArtificeConfig.floraBoneMeal.getBoolean(true) && ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 0, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 1, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 2, 10);
            MinecraftForge.addGrassPlant(ArtificeBlocks.blockFlora, 3, 10);
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        ArtificeTooltips.initTooltips();
        ArtificeRecipes.registerRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
    }
}
