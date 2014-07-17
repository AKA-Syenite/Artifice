package shukaro.artifice;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
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
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import shukaro.artifice.compat.*;
import shukaro.artifice.event.ArtificeEventHandler;
import shukaro.artifice.event.ArtificeTickHandler;
import shukaro.artifice.net.ClientPacketHandler;
import shukaro.artifice.net.PacketHandler;
import shukaro.artifice.net.ServerPacketHandler;
import shukaro.artifice.recipe.ArtificeRecipes;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.world.ArtificeWorldGen;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion,
        dependencies = "after:BuildCraft|Core;after:EE3;after:Forestry;after:MineFactoryReloaded;after:Thaumcraft")
public class ArtificeCore
{
    @SidedProxy(clientSide = "shukaro.artifice.ClientProxy", serverSide = "shukaro.artifice.CommonProxy")
    public static CommonProxy proxy;

    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modChannel = "Artifice";
    public static final String modVersion = "1.7.10R1.1.4";

    public static ArtificeWorldGen worldGen;
    public static Logger logger;
    public static ArtificeEventHandler eventHandler;
    public static ArtificeTickHandler tickHandler;
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

    private ArrayList<ICompat> compats = new ArrayList<ICompat>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        // init reflection
        try
        {
            NetHandlerPlayServer.class.getDeclaredField("floatingTickCount").setAccessible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        compats.add(new Buildcraft());
        compats.add(new EE3());
        compats.add(new FMP());
        compats.add(new Forestry());
        compats.add(new MFR());
        compats.add(new Thaumcraft());
        compats.add(new Vanilla());

        logger = evt.getModLog();

        ArtificeConfig.initClient(evt);
        ArtificeConfig.initCommon(evt);

        packet = new PacketHandler(modID, new ClientPacketHandler(), new ServerPacketHandler());
        MinecraftForge.EVENT_BUS.register(eventHandler = new ArtificeEventHandler());
        FMLCommonHandler.instance().bus().register(tickHandler = new ArtificeTickHandler());


        ArtificeBlocks.initBlocks();
        ArtificeItems.initItems();

        if (ArtificeConfig.enableWorldGen.getBoolean(true))
            GameRegistry.registerWorldGenerator(ArtificeCore.worldGen = new ArtificeWorldGen(), 100);
    }

    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        for (ICompat c : compats)
        {
            if (c.getModID() == null || Loader.isModLoaded(c.getModID()))
            {
                logger.debug("Loading compat " + c.getClass().getName());
                c.load();
            }
        }

        ArtificeTooltips.initTooltips();
        ArtificeRecipes.registerRecipes();

        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
        if (ArtificeConfig.floraBoneMeal.getBoolean(true) && ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
            {
                if (biome != null) for (int i = 0; i < 4; i++)
                    biome.addFlower(ArtificeBlocks.blockFlora, i, 10);
            }
        }
    }
}
