package shukaro.artifice;

import cofh.util.oredict.OreDictionaryArbiter;
import cofh.world.WorldHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import shukaro.artifice.compat.*;
import shukaro.artifice.event.ArtificeEventHandler;
import shukaro.artifice.event.ArtificeTickHandler;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.recipe.ArtificeRecipes;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.NameMetaPair;
import shukaro.artifice.world.*;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion,
        dependencies = "required-after:CoFHCore;after:BuildCraft|Core;after:EE3;after:Forestry;after:MineFactoryReloaded;after:Thaumcraft")
public class ArtificeCore
{
    @SidedProxy(clientSide = "shukaro.artifice.ClientProxy", serverSide = "shukaro.artifice.CommonProxy")
    public static CommonProxy proxy;

    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modChannel = "Artifice";
    public static final String modVersion = "1.7.10R1.1.4";

    public static Logger logger;
    public static ArtificeEventHandler eventHandler;
    public static ArtificeTickHandler tickHandler;

    public static final String[] tiers = {"Basic", "Reinforced", "Industrial", "Advanced"};
    public static final String[] flora = {"Bluebell", "Orchid", "Iris", "Lotus", "LotusClosed"};
    public static final String[] rocks = {"", "Cobblestone", "Brick", "Paver", "Antipaver", "Chiseled"};

    public static final ArtificeCreativeTab mainTab = new ArtificeCreativeTab("Artifice");
    public static final ArtificeCreativeTab worldTab = new ArtificeCreativeTab("Artifice Worldgen");

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

        MinecraftForge.EVENT_BUS.register(eventHandler = new ArtificeEventHandler());
        FMLCommonHandler.instance().bus().register(tickHandler = new ArtificeTickHandler());

        ArtificeConfig.initClient(evt);
        ArtificeConfig.initCommon(evt);

        ArtificeBlocks.initBlocks();
        ArtificeItems.initItems();

        for (int i=0; i<ArtificeConfig.rockNames.length; i++)
        {
            if (ArtificeConfig.rockLayersGen[i].getBoolean())
                WorldHandler.addFeature(new WorldGenLayer(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockLayersMinHeight[i].getInt(), ArtificeConfig.rockLayersMaxHeight[i].getInt()));
        }
        for (int i=0; i<3; i++)
        {
            if (ArtificeConfig.rockClustersGen[i].getBoolean())
                WorldHandler.addFeature(new WorldGenCluster(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockClustersMinHeight[i].getInt(), ArtificeConfig.rockClustersMaxHeight[i].getInt(), ArtificeConfig.rockClustersSize[i].getInt(), ArtificeConfig.rockClustersFrequency[i].getInt()));
            if (ArtificeConfig.rockCavesGen[i].getBoolean())
                WorldHandler.addFeature(new WorldGenCave(ArtificeBlocks.rockBlocks[i], 0, ArtificeConfig.rockCavesMinHeight[i].getInt(), ArtificeConfig.rockCavesMaxHeight[i].getInt(), ArtificeConfig.rockCavesSize[i].getInt(), ArtificeConfig.rockCavesFrequency[i].getInt()));
        }
        if (ArtificeConfig.floraWorldGen.getBoolean())
            WorldHandler.addFeature(new WorldGenFlowers());
        if (ArtificeConfig.lotusWorldGen.getBoolean())
            WorldHandler.addFeature(new WorldGenLily());
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
        ArtificeBlocks.oreMappings = new THashMap<String, Block>();
        for (int j=0; j<ArtificeBlocks.oreNames.length; j++)
            ArtificeBlocks.oreMappings.put(ArtificeBlocks.oreNames[j], ArtificeBlocks.blockOres[j]);

        ArtificeBlocks.oreSet = new THashSet<NameMetaPair>();
        for (String s : ArtificeBlocks.oreNames)
        {
            for (ItemStack stack : OreDictionaryArbiter.getOres(s))
                ArtificeBlocks.oreSet.add(new NameMetaPair(stack.getItem(), stack.getItemDamage()));
        }

        if (ArtificeConfig.floraBoneMeal.getBoolean(true) && ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
            {
                if (biome != null) for (int i = 0; i < 4; i++)
                    biome.addFlower(ArtificeBlocks.blockFlora, i, 10);
            }
        }
    }

    @EventHandler
    public void processIMCRequests(FMLInterModComms.IMCEvent event)
    {
        IMC.processIMC(event);
    }
}
