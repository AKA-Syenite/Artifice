package shukaro.artifice.compat;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.block.decorative.BlockBasaltSlab;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.logging.Level;

public class Thaumcraft implements ICompat
{
    private static LinkedHashMap<String, ? extends Object> aspects = null;
    private static Method registerItem = null;
    private static Method registerEntity = null;
    private static Class<?> AspectList = null;
    private static Constructor<?> newAspectList = null;
    private static Method addAspect = null;

    public String getModID() { return "Thaumcraft"; }
    public void load()
    {
        try
        {
            Class<?> Aspect = Class.forName("thaumcraft.api.aspects.Aspect");
            aspects = (LinkedHashMap<String, ? extends Object>)Aspect.getDeclaredField("aspects").get(null);
            Class<?> ThaumcraftApi = Class.forName("thaumcraft.api.ThaumcraftApi");
            AspectList = Class.forName("thaumcraft.api.aspects.AspectList");
            registerItem = ThaumcraftApi.getDeclaredMethod("registerObjectTag", int.class, int.class, AspectList);
            registerEntity = ThaumcraftApi.getDeclaredMethod("registerEntityTag", String.class, AspectList, NBTBase[].class);
            addAspect = AspectList.getDeclaredMethod("add", Aspect, int.class);
            newAspectList = AspectList.getDeclaredConstructor(int.class, int.class);

            doAspects();

            ArtificeCore.logger.info("Thaumcraft Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize Thaumcraft compat");
            ex.printStackTrace();
        }
    }

    private static void parseAspects(ItemStack item, String toadd, boolean craftedAspects) throws Throwable
    {
        String[] list = toadd.split(",");
        Object aspectList;
        if (craftedAspects)
            aspectList = newAspectList.newInstance(item.getItem(), item.getItemDamage());
        else
            aspectList = AspectList.newInstance();
        for (int i = list.length; i --> 0; )
        {
            String[] temp = list[i].trim().split(" ");
            temp[1] = temp[1].toLowerCase();
            if (aspects.containsKey(temp[1]))
                addAspect.invoke(aspectList, aspects.get(temp[1]), Integer.parseInt(temp[0], 10));
            else
                FMLLog.severe("%s aspect missing.", temp[1]);
        }
        registerItem.invoke(null, item.getItem(), item.getItemDamage(), aspectList);
    }

    private static void parseAspects(String entity, String toadd) throws Throwable
    {
        String[] list = toadd.split(",");
        Object aspectList = AspectList.newInstance();
        for (int i = list.length; i --> 0; )
        {
            String[] temp = list[i].trim().split(" ");
            temp[1] = temp[1].toLowerCase();
            if (aspects.containsKey(temp[1]))
                addAspect.invoke(aspectList, aspects.get(temp[1]), Integer.parseInt(temp[0], 10));
        }
        registerEntity.invoke(null, entity, aspectList, null);
    }

    private static void parseAspects(Item item, String toadd) throws Throwable
    {
        parseAspects(new ItemStack(item, 1, 0), toadd, false);
    }

    private static void parseAspects(Item item, int meta, String toadd) throws Throwable
    {
        parseAspects(new ItemStack(item, 1, meta), toadd, false);
    }

    private static void parseAspects(Block item, int meta, String toadd) throws Throwable
    {
        parseAspects(new ItemStack(item, 1, meta), toadd, false);
    }

    private static void parseAspects(Block item, String toadd) throws Throwable
    {
        parseAspects(item, 0, toadd);
    }

    private static void parseAspects(Block item, int meta, String toadd, boolean craftedAspects) throws Throwable
    {
        parseAspects(new ItemStack(item, 1, meta), toadd, craftedAspects);
    }

    private static void parseAspects(Block item, String toadd, boolean craftedAspects) throws Throwable
    {
        parseAspects(item, 0, toadd, craftedAspects);
    }

    private static void doAspects() throws Throwable
    {
        parseAspects(ArtificeBlocks.blockFrame, 0, "1 Arbor, 1 Ordo");
        parseAspects(ArtificeBlocks.blockFrame, 1, "1 Arbor, 1 Metallum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockFrame, 2, "2 Metallum, 2 Ordo");
        parseAspects(ArtificeBlocks.blockFrame, 3, "2 Metallum, 2 Ordo");

        parseAspects(ArtificeBlocks.blockFlora, 0, "1 Sensus, 1 Herba");
        parseAspects(ArtificeBlocks.blockFlora, 1, "1 Sensus, 1 Herba");
        parseAspects(ArtificeBlocks.blockFlora, 2, "1 Sensus, 1 Herba");
        parseAspects(ArtificeBlocks.blockFlora, 3, "1 Sensus, 1 Herba");

        parseAspects(ArtificeBlocks.blockLotus, "1 Sensus, 1 Herba, 1 Aqua");

        parseAspects(ArtificeBlocks.blockBasalt, 0, "1 Saxum");
        parseAspects(ArtificeBlocks.blockBasalt, 1, "1 Saxum, 1 Perditio");
        parseAspects(ArtificeBlocks.blockBasalt, 2, "1 Saxum");
        parseAspects(ArtificeBlocks.blockBasalt, 3, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockBasalt, 4, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockBasalt, 5, "1 Saxum, 1 Ordo");

        parseAspects(ArtificeBlocks.blockMarble, 0, "1 Saxum");
        parseAspects(ArtificeBlocks.blockMarble, 1, "1 Saxum, 1 Perditio");
        parseAspects(ArtificeBlocks.blockMarble, 2, "1 Saxum");
        parseAspects(ArtificeBlocks.blockMarble, 3, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockMarble, 4, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockMarble, 5, "1 Saxum, 1 Ordo");

        parseAspects(ArtificeBlocks.blockBasaltBrickStairs, "1 Saxum");
        parseAspects(ArtificeBlocks.blockBasaltCobbleStairs, "1 Saxum, 1 Perditio");

        parseAspects(ArtificeBlocks.blockMarbleBrickStairs, "1 Saxum");
        parseAspects(ArtificeBlocks.blockMarbleCobbleStairs, "1 Saxum, 1 Perditio");

        parseAspects(ArtificeBlocks.blockBasaltSlab, 0, "1 Saxum");
        parseAspects(ArtificeBlocks.blockBasaltSlab, 1, "1 Saxum, 1 Perditio");
        parseAspects(ArtificeBlocks.blockBasaltSlab, 2, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockBasaltSlab, 3, "1 Saxum, 1 Ordo");

        parseAspects(ArtificeBlocks.blockMarbleSlab, 0, "1 Saxum");
        parseAspects(ArtificeBlocks.blockMarbleSlab, 1, "1 Saxum, 1 Perditio");
        parseAspects(ArtificeBlocks.blockMarbleSlab, 2, "1 Saxum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockMarbleSlab, 3, "1 Saxum, 1 Ordo");

        parseAspects(ArtificeBlocks.blockDetector, "2 Sensus, 2 Machina, 2 Metallum, 2 Arbor");

        parseAspects(ArtificeItems.itemSteelIngot, 0, "6 Metallum");
        parseAspects(ArtificeItems.itemSteelIngot, 1, "3 Metallum, 3 Ignis");
        parseAspects(ArtificeBlocks.blockSteel, "18 Metallum");

        parseAspects(ArtificeBlocks.blockReinforced, 0, "4 Saxum, 1 Tutamen");
        parseAspects(ArtificeBlocks.blockReinforced, 1, "4 Saxum, 2 Ordo, 1 Tutamen");
        parseAspects(ArtificeBlocks.blockReinforced, 2, "4 Saxum, 2 Ignis, 2 Tutamen");
        parseAspects(ArtificeBlocks.blockReinforced, 3, "4 Metallum, 2 Ordo, 2 Tutamen");

        parseAspects(ArtificeBlocks.blockGlassWall, 0, "3 Saxum, 1 Vitreus, 1 Tutamen");
        parseAspects(ArtificeBlocks.blockGlassWall, 1, "3 Saxum, 1 Vitreus, 2 Ordo, 1 Tutamen");
        parseAspects(ArtificeBlocks.blockGlassWall, 2, "3 Saxum, 1 Vitreus, 2 Ignis, 2 Tutamen");
        parseAspects(ArtificeBlocks.blockGlassWall, 3, "4 Metallum, 1 Vitreus, 2 Ordo, 2 Tutamen");

        parseAspects(ArtificeBlocks.blockScaffold, 0, "2 Motus, 1 Arbor, 1 Ordo");
        parseAspects(ArtificeBlocks.blockScaffold, 1, "2 Motus, 1 Arbor, 1 Metallum, 1 Ordo");
        parseAspects(ArtificeBlocks.blockScaffold, 2, "2 Motus, 2 Metallum, 2 Ordo");
        parseAspects(ArtificeBlocks.blockScaffold, 3, "2 Motus, 2 Metallum, 2 Ordo");

        parseAspects(ArtificeItems.itemSledgeWood, "4 Instrumentum, 2 Perditio, 2 Arbor");
        parseAspects(ArtificeItems.itemSledgeStone, "4 Instrumentum, 2 Perditio, 2 Saxum");
        parseAspects(ArtificeItems.itemSledgeIron, "4 Instrumentum, 2 Perditio, 2 Metallum");
        parseAspects(ArtificeItems.itemSledgeGold, "4 Instrumentum, 2 Perditio, 2 Lucrum");
        parseAspects(ArtificeItems.itemSledgeDiamond, "4 Instrumentum, 2 Perditio, 2 Vitreus");

        parseAspects(ArtificeItems.itemBox, "3 Arbor, 2 Vacuos");

        parseAspects(ArtificeItems.itemDye, 0, "1 Sensus, 1 Permutatio");
        parseAspects(ArtificeItems.itemDye, 1, "1 Sensus, 1 Permutatio");
        parseAspects(ArtificeItems.itemDye, 2, "1 Sensus, 1 Permutatio");
        parseAspects(ArtificeItems.itemDye, 3, "1 Sensus, 1 Permutatio");

        parseAspects(ArtificeItems.itemSickleWood, "4 Instrumentum, 2 Meto, 2 Arbor");
        parseAspects(ArtificeItems.itemSickleStone, "4 Instrumentum, 2 Meto, 2 Saxum");
        parseAspects(ArtificeItems.itemSickleIron, "4 Instrumentum, 2 Meto, 2 Metallum");
        parseAspects(ArtificeItems.itemSickleGold, "4 Instrumentum, 2 Meto, 2 Lucrum");
        parseAspects(ArtificeItems.itemSickleDiamond, "4 Instrumentum, 2 Meto, 2 Vitreus");

        parseAspects(ArtificeItems.itemNugget, 0, "1 Metallum, 1 Victus");
        parseAspects(ArtificeItems.itemNugget, 0, "1 Metallum, 1 Sensus");
        parseAspects(ArtificeItems.itemNugget, 0, "1 Metallum, 1 Vitreus");

        parseAspects(ArtificeItems.itemCoin, 0, "1 Lucrum, 1 Victus");
        parseAspects(ArtificeItems.itemCoin, 1, "1 Lucrum, 1 Sensus");
        parseAspects(ArtificeItems.itemCoin, 2, "2 Lucrum");
        parseAspects(ArtificeItems.itemCoin, 3, "1 Lucrum, 1 Vitreus");

        parseAspects(ArtificeItems.itemUpgrade, 0, "1 Fabrico, 1 Ignis, 1 Telum");
        parseAspects(ArtificeItems.itemUpgrade, 1, "1 Fabrico, 2 Ordo, 1 Cognitio");
        parseAspects(ArtificeItems.itemUpgrade, 2, "1 Fabrico, 1 Potentia, 1 Arbor");
        parseAspects(ArtificeItems.itemUpgrade, 3, "1 Fabrico, 2 Ordo, 1 Pannus");
        parseAspects(ArtificeItems.itemUpgrade, 4, "1 Fabrico, 4 Saxum");
        parseAspects(ArtificeItems.itemUpgrade, 5, "1 Fabrico, 1 Metallum, 1 Telum");
        parseAspects(ArtificeItems.itemUpgrade, 6, "1 Fabrico, 1 Bestia, 1 Tutamen");
        parseAspects(ArtificeItems.itemUpgrade, 7, "1 Fabrico, 1 Pannus, 1 Tutamen");
        parseAspects(ArtificeItems.itemUpgrade, 8, "1 Fabrico, 2 Limus");
        parseAspects(ArtificeItems.itemUpgrade, 9, "1 Fabrico, 1 Limus, 2 Aqua");
        parseAspects(ArtificeItems.itemUpgrade, 10, "1 Fabrico, 1 Limus, 1 Tutamen");
        parseAspects(ArtificeItems.itemUpgrade, 11, "1 Fabrico, 2 Aqua, 2 Aer");
        parseAspects(ArtificeItems.itemUpgrade, 12, "1 Fabrico, 2 Aqua, 1 Motus");
    }
}