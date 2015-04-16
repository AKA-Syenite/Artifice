package shukaro.artifice;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import shukaro.artifice.command.CommandCalc;
import shukaro.artifice.compat.*;
import shukaro.artifice.event.ArtificeEventHandler;
import shukaro.artifice.event.ArtificeTickHandler;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.net.CommonProxy;
import shukaro.artifice.recipe.ArtificeRecipes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = ArtificeCore.modID, name = ArtificeCore.modName, version = ArtificeCore.modVersion,
        dependencies = "required-after:CoFHCore;after:BuildCraft|Core;after:EE3;after:Forestry;after:MineFactoryReloaded;after:Thaumcraft;after:Railcraft")
public class ArtificeCore
{
    @SidedProxy(clientSide = "shukaro.artifice.net.ClientProxy", serverSide = "shukaro.artifice.net.CommonProxy")
    public static CommonProxy proxy;

    public static final String modID = "Artifice";
    public static final String modName = "Artifice";
    public static final String modChannel = "Artifice";
    public static final String modVersion = "1.7.10R1.1.4";

    public static Logger logger;
    public static ArtificeEventHandler eventHandler;
    public static ArtificeTickHandler tickHandler;

    public static final ArtificeCreativeTab mainTab = new ArtificeCreativeTab("Artifice");
    public static final ArtificeCreativeTab worldTab = new ArtificeCreativeTab("Artifice Worldgen");

    @Instance(modID)
    public static ArtificeCore instance;

    @EventHandler
    public void serverStarting(FMLServerStartingEvent evt)
    {
        evt.registerServerCommand(new CommandCalc());
    }

    private ArrayList<ICompat> compats = new ArrayList<ICompat>();

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        compats.add(new Buildcraft());
        compats.add(new EE3());
        compats.add(new FMP());
        compats.add(new Forestry());
        compats.add(new CarpentersBlocks());
        compats.add(new MFR());
        compats.add(new AE2());
        compats.add(new Vanilla());

        logger = evt.getModLog();

        MinecraftForge.EVENT_BUS.register(eventHandler = new ArtificeEventHandler());
        FMLCommonHandler.instance().bus().register(tickHandler = new ArtificeTickHandler());

        ArtificeConfig.initClient(evt);
        ArtificeConfig.initCommon(evt);

        ArtificeFluids.initFluids();
        ArtificeBlocks.initBlocks();
        ArtificeTiles.initTiles();
        ArtificeItems.initItems();
        ArtificeEnchants.initEnchants();
        ArtificeWorld.initWorldGen();
    }

    @EventHandler
    public void init(FMLInitializationEvent evt)
    {
        ArtificeTooltips.initTooltips();
        ArtificeRecipes.registerRecipes();

        proxy.init();

        for (ICompat c : compats)
        {
            if (c.getModID() == null || Loader.isModLoaded(c.getModID()))
            {
                logger.debug("Loading compat " + c.getClass().getName());
                c.load();
            }
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
        ArtificeBlocks.initOreMappings();
        ArtificeBlocks.initOreSet();
        ArtificeBlocks.registerOreVariants();
        ArtificeBlocks.registerBasaltsAndMarbles();

        if (ArtificeConfig.floraBoneMeal)
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

    @EventHandler
    public void missingMappings(FMLMissingMappingsEvent event)
    {
        List<FMLMissingMappingsEvent.MissingMapping> missing = event.get();
        for (FMLMissingMappingsEvent.MissingMapping m : missing)
        {
            if (m.name.contains("dobule"))
            {
                if (m.type == GameRegistry.Type.BLOCK)
                    m.remap((Block)Block.blockRegistry.getObject(m.name.replace("dobule", "double")));
                else if (m.type == GameRegistry.Type.ITEM)
                    m.remap((Item)Item.itemRegistry.getObject(m.name.replace("dobule", "double")));
            }
        }
    }
}
