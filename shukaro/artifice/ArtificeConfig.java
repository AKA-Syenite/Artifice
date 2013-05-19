package shukaro.artifice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import shukaro.artifice.compat.ArtificeRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ArtificeConfig
{
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
    public static Property blockRefractoryID;
    
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
    public static Property regenRock;
    public static Property regenFlora;
    public static Property regenKey;
    
    public static Property dimensionBlacklist;
    
    public static File configFolder;
    
    public static void initCommon(FMLPreInitializationEvent evt)
    {
        Configuration c = new Configuration(evt.getSuggestedConfigurationFile());
        try
        {
            c.load();
            idStart = c.get(Configuration.CATEGORY_BLOCK, "Block.IDStart", 3000);
            idStart.comment = "The block ID to use as the starting point for assignment, delete other IDs to reassign";
            
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
            blockRefractoryID = c.getBlock("blockRefractory", s++);
            
            floraWorldGen = c.get("World Generation", "Generate Flora", true);
            floraWorldGen.comment = "Whether or not to generate flora during map generation";
            basaltWorldGen = c.get("World Generation", "Generate Basalt", true);
            basaltWorldGen.comment = "Whether or not to generate basalt during map generation";
            basaltSize = c.get("World Generation", "Basalt Size", 20000);
            basaltSize.comment = "Absolute maximum size of basalt deposits in the world";
            basaltHeight = c.get("World Generation", "Basalt Height", 64);
            basaltHeight.comment = "Max height to begin basalt generation";
            marbleWorldGen = c.get("World Generation", "Generate Marble", true);
            marbleWorldGen.comment = "Whether or not to generate marble during map generation";
            marbleSize = c.get("World Generation", "Marble Size", 20000);
            marbleSize.comment = "Absolute maximum size of marble deposits in the world";
            marbleHeight = c.get("World Generation", "Marble Height", 64);
            marbleHeight.comment = "Max height to begin marble generation";
            dimensionBlacklist = c.get("World Generation", "Dimension Blacklist", "");
            dimensionBlacklist.comment = "A comma-separated list of dimension IDs to disable worldgen in.";
            regenRock = c.get("World Generation", "Regenerate Rock", false);
            regenRock.comment = "Set to true to regenerate basalt and marble";
            regenFlora = c.get("World Generation", "Regenerate Flora", false);
            regenFlora.comment = "Set to true to regenerate flowers";
            regenKey = c.get("World Generation", "Regen Key", "DEFAULT");
            regenKey.comment = "This key is used to keep track of which chunk have been generated/regenerated. Changing it will cause the regeneration code to run again, so only change it if you want it to happen. Useful to regen only one world feature at a time.";
            
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
            ArtificeCore.logger.log(Level.SEVERE, "Artifice couldn't load the config file");
            e.printStackTrace();
        }
        finally
        {
            c.save();
        }
        
        setDimBlacklist();
    }
    
    public static void initClient(FMLPreInitializationEvent evt)
    {
        
    }
    
    private static void setDimBlacklist()
    {
        String blacklist = dimensionBlacklist.getString().trim();
        
        for (String dim : blacklist.split(","))
        {
            try
            {
                Integer dimID = Integer.parseInt(dim);
                ArtificeRegistry.registerDimensionBlacklist(dimID);
            }
            catch (Exception e)
            {
            }
        }
    }
    
    public static void setConfigFolderBase(File folder)
    {
        configFolder = new File(folder.getAbsolutePath() + "/shukaro/" + ArtificeCore.modID + "/");
    }
    
    public static void extractLang(String[] languages)
    {
        String langResourceBase = "/shukaro/" + ArtificeCore.modID + "/lang/";
        for (String lang : languages)
        {
            InputStream is = ArtificeCore.instance.getClass().getResourceAsStream(langResourceBase + lang + ".lang");
            try
            {
                File f = new File(configFolder.getAbsolutePath() + "/lang/" + lang + ".lang");
                if (!f.exists())
                    f.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int read = 0;
                while ((read = is.read(buffer)) != -1)
                {
                    os.write(buffer, 0, read);
                }
                is.close();
                os.flush();
                os.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static void loadLang()
    {
        File f = new File(configFolder.getAbsolutePath() + "/lang/");
        for (File langFile : f.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".lang");
            }
        }))
        {
            try
            {
                Properties langPack = new Properties();
                langPack.load(new FileInputStream(langFile));
                String lang = langFile.getName().replace(".lang", "");
                LanguageRegistry.instance().addStringLocalization(langPack, lang);
            }
            catch (FileNotFoundException x)
            {
                x.printStackTrace();
            }
            catch (IOException x)
            {
                x.printStackTrace();
            }
        }
    }
}
