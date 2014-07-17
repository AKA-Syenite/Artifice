package shukaro.artifice;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockSlab;
import net.minecraftforge.oredict.OreDictionary;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.block.decorative.*;
import shukaro.artifice.block.frame.*;

public class ArtificeBlocks
{
    public static BlockFrame blockFrame;
    public static BlockFlora blockFlora;
    public static BlockLotus blockLotus;
    public static BlockBasalt blockBasalt;
    public static BlockMarble blockMarble;
    public static BlockStairsArtifice blockBasaltBrickStairs;
    public static BlockStairsArtifice blockMarbleBrickStairs;
    public static BlockStairsArtifice blockBasaltCobbleStairs;
    public static BlockStairsArtifice blockMarbleCobbleStairs;
    public static BlockSlab blockBasaltSlab;
    public static BlockSlab blockBasaltDoubleSlab;
    public static BlockSlab blockMarbleSlab;
    public static BlockSlab blockMarbleDoubleSlab;
    public static BlockFrame blockDetector;
    public static BlockSteel blockSteel;
    public static BlockFrameBlastWall blockReinforced;
    public static BlockFrameGlassWall blockGlassWall;
    public static BlockFrameScaffold blockScaffold;

    public static void initBlocks()
    {
        if (ArtificeConfig.enableWorldGen.getBoolean(true))
        {
            blockFlora = new BlockFlora();
            blockLotus = new BlockLotus();
            blockBasalt = new BlockBasalt();
            blockMarble = new BlockMarble();
            blockBasaltBrickStairs = new BlockStairsArtifice(blockBasalt, 2);
            blockMarbleBrickStairs = new BlockStairsArtifice(blockMarble, 2);
            blockBasaltCobbleStairs = new BlockStairsArtifice(blockBasalt, 1);
            blockMarbleCobbleStairs = new BlockStairsArtifice(blockMarble, 1);
            blockBasaltSlab = new BlockBasaltSlab(false);
            blockBasaltDoubleSlab = new BlockBasaltSlab(true);
            blockMarbleSlab = new BlockMarbleSlab(false);
            blockMarbleDoubleSlab = new BlockMarbleSlab(true);
            GameRegistry.registerBlock(blockBasaltSlab, ItemBlockSlabArtifice.class, blockBasalt.getUnlocalizedName() + "_slab");
            GameRegistry.registerBlock(blockBasaltDoubleSlab, ItemBlockSlabArtifice.class, blockBasalt.getUnlocalizedName() + "_double_slab");
            GameRegistry.registerBlock(blockMarbleSlab, ItemBlockSlabArtifice.class, blockMarble.getUnlocalizedName() + "_slab");
            GameRegistry.registerBlock(blockMarbleDoubleSlab, ItemBlockSlabArtifice.class, blockMarble.getUnlocalizedName() + "_dobule_slab");

            GameRegistry.registerBlock(blockFlora, ItemBlockFlora.class, blockFlora.getUnlocalizedName());
            GameRegistry.registerBlock(blockLotus, ItemBlockLotus.class, blockLotus.getUnlocalizedName());
            GameRegistry.registerBlock(blockBasalt, ItemBlockBasalt.class, blockBasalt.getUnlocalizedName());
            GameRegistry.registerBlock(blockMarble, ItemBlockMarble.class, blockMarble.getUnlocalizedName());
            GameRegistry.registerBlock(blockBasaltBrickStairs, ItemBlockArtifice.class, blockBasaltBrickStairs.getUnlocalizedName());
            GameRegistry.registerBlock(blockMarbleBrickStairs, ItemBlockArtifice.class, blockMarbleBrickStairs.getUnlocalizedName());
            GameRegistry.registerBlock(blockBasaltCobbleStairs, ItemBlockArtifice.class, blockBasaltCobbleStairs.getUnlocalizedName());
            GameRegistry.registerBlock(blockMarbleCobbleStairs, ItemBlockArtifice.class, blockMarbleCobbleStairs.getUnlocalizedName());
            ArtificeRegistry.registerBasaltType(ArtificeBlocks.blockBasalt, 0);
            ArtificeRegistry.registerMarbleType(ArtificeBlocks.blockMarble, 0);
            //OreDictionary.registerOre("stone", new ItemStack(blockBasalt, 1, 0));
            //OreDictionary.registerOre("stone", new ItemStack(blockMarble, 1, 0));
            //OreDictionary.registerOre("cobblestone", new ItemStack(blockBasalt, 1, 1));
            //OreDictionary.registerOre("cobblestone", new ItemStack(blockMarble, 1, 1));
        }

        if (ArtificeConfig.enableFrames.getBoolean(true))
        {
            blockFrame = new BlockFrameBase();
            blockDetector = new BlockFrameDetector();
            blockReinforced = new BlockFrameBlastWall();
            blockGlassWall = new BlockFrameGlassWall();
            blockScaffold = new BlockFrameScaffold();
            GameRegistry.registerBlock(blockFrame, ItemBlockFrame.class, blockFrame.getUnlocalizedName());
            GameRegistry.registerBlock(blockDetector, ItemBlockArtifice.class, blockDetector.getUnlocalizedName());
            GameRegistry.registerBlock(blockReinforced, ItemBlockFrame.class, blockReinforced.getUnlocalizedName());
            GameRegistry.registerBlock(blockGlassWall, ItemBlockFrame.class, blockGlassWall.getUnlocalizedName());
            GameRegistry.registerBlock(blockScaffold, ItemBlockFrame.class, blockScaffold.getUnlocalizedName());
        }

        if (ArtificeConfig.enableSteel.getBoolean(true))
        {
            blockSteel = new BlockSteel();
            GameRegistry.registerBlock(blockSteel, ItemBlockArtifice.class, blockSteel.getUnlocalizedName());
            OreDictionary.registerOre("blockSteel", blockSteel);
        }
    }
}
