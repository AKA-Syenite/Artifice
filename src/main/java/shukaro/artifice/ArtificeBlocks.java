package shukaro.artifice;

import cpw.mods.fml.common.registry.GameRegistry;
import gnu.trove.map.TMap;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.block.decorative.*;
import shukaro.artifice.block.fluid.BlockFluidBitumen;
import shukaro.artifice.block.fluid.BlockFluidCreosote;
import shukaro.artifice.block.fluid.BlockFluidFuel;
import shukaro.artifice.block.fluid.BlockFluidOil;
import shukaro.artifice.block.frame.*;
import shukaro.artifice.block.world.*;
import shukaro.artifice.util.MinecraftColors;
import shukaro.artifice.util.NameMetaPair;

import java.util.Set;

public class ArtificeBlocks
{
    public static BlockFrame blockFrame;
    public static BlockFlora blockFlora;
    public static BlockLotus blockLotus;
    public static BlockRock blockBasalt;
    public static BlockRock blockMarble;
    public static BlockStairsArtifice blockBasaltBrickStairs;
    public static BlockStairsArtifice blockMarbleBrickStairs;
    public static BlockStairsArtifice blockBasaltCobbleStairs;
    public static BlockStairsArtifice blockMarbleCobbleStairs;
    public static BlockRockSlab blockBasaltSlab;
    public static BlockRockSlab blockBasaltDoubleSlab;
    public static BlockRockSlab blockMarbleSlab;
    public static BlockRockSlab blockMarbleDoubleSlab;
    public static BlockFrame blockDetector;
    public static BlockSteel blockSteel;
    public static BlockFrameBlastWall blockReinforced;
    public static BlockFrameGlassWall blockGlassWall;
    public static BlockFrameGlassWall blockGlassWallDark;
    public static BlockFrameScaffold blockScaffold;
    public static BlockLamp[] blockLamps = new BlockLamp[16];
    public static BlockLamp[] blockLampsInverted = new BlockLamp[16];
    public static BlockRock[] blockLimestones = new BlockRock[7];
    public static BlockRockSlab[] blockLimestoneSlabs = new BlockRockSlab[7];
    public static BlockRockSlab[] blockLimestoneDoubleSlabs = new BlockRockSlab[7];
    public static BlockStairsArtifice[] blockLimestoneBrickStairs = new BlockStairsArtifice[7];
    public static BlockStairsArtifice[] blockLimestoneCobbleStairs = new BlockStairsArtifice[7];
    public static String[] oreNames = { "oreCoal", "oreIron", "oreLapis", "oreGold", "oreDiamond", "oreRedstone", "oreEmerald", "oreCopper", "oreTin", "oreSilver", "oreLead", "oreNickel", "oreSulfur", "oreEnder" };
    public static BlockOre[] blockOres = new BlockOre[oreNames.length];
    public static BlockOre[] blockOresGlowing = new BlockOre[oreNames.length];
    public static BlockSulfur blockSulfur;
    public static BlockEnderOre blockEnderOre;
    public static BlockEnderOre blockEnderOreGlowing;
    public static BlockNiter blockNiter;
    public static Block blockOil;
    public static Block blockFuel;
    public static Block blockBitumen;
    public static Block blockCreosote;
    public static BlockGlowSand blockGlowSand;
    public static BlockColoredTorch[] blockColoredTorches = new BlockColoredTorch[16];

    public static String[] rockColorNames = { "gray",   "lightgray", "brown",  "tan",    "reddish", "bluish", "greenish" };
    public static int[] rockColors =        { 11579568, 16777215,    12362119, 15853509, 11706528,  10526898, 10531488 };
    public static Block[] rockBlocks = new Block[9];
    public static String[] rockBlockNames = { "basalt", "marble", "limestone.gray", "limestone.lightgray", "limestone.brown", "limestone.tan", "limestone.reddish", "limestone.bluish", "limestone.greenish" };
    public static Block blockDummy;
    public static TMap<String, Block> oreMappings;
    public static Set<NameMetaPair> oreSet;

    public static void initBlocks()
    {
        blockFlora = new BlockFlora();
        blockLotus = new BlockLotus();
        blockBasalt = new BlockRock("basalt");
        rockBlocks[0] = blockBasalt;
        blockMarble = new BlockRock("marble");
        rockBlocks[1] = blockMarble;
        blockBasaltBrickStairs = new BlockStairsArtifice(blockBasalt, 2);
        blockMarbleBrickStairs = new BlockStairsArtifice(blockMarble, 2);
        blockBasaltCobbleStairs = new BlockStairsArtifice(blockBasalt, 1);
        blockMarbleCobbleStairs = new BlockStairsArtifice(blockMarble, 1);
        blockBasaltSlab = new BlockRockSlab("basalt", false);
        blockBasaltDoubleSlab = new BlockRockSlab("basalt", true);
        blockMarbleSlab = new BlockRockSlab("marble", false);
        blockMarbleDoubleSlab = new BlockRockSlab("marble", true);
        for (int i=0; i<blockLimestones.length; i++)
        {
            blockLimestones[i] = new BlockRock("limestone." + rockColorNames[i], rockColors[i]);
            rockBlocks[i+2] = blockLimestones[i];
            blockLimestoneSlabs[i] = new BlockRockSlab("limestone." + rockColorNames[i], rockColors[i], false);
            blockLimestoneDoubleSlabs[i] = new BlockRockSlab("limestone." + rockColorNames[i], rockColors[i], true);
            blockLimestoneBrickStairs[i] = new BlockStairsArtifice(blockLimestones[i], 2, rockColors[i]);
            blockLimestoneCobbleStairs[i] = new BlockStairsArtifice(blockLimestones[i], 1, rockColors[i]);
        }
        for (int i=0; i<oreNames.length; i++)
        {
            if (oreNames[i].equals("oreRedstone") || oreNames[i].equals("oreEnder"))
            {
                blockOres[i] = new BlockOre(oreNames[i], false, 0, i);
                blockOresGlowing[i] = new BlockOre(oreNames[i], true, 0, i);
            }
            else
                blockOres[i] = new BlockOre(oreNames[i], i);
        }
        blockDummy = new BlockOre.BlockOreDummy();
        blockSulfur = new BlockSulfur();
        blockEnderOre = new BlockEnderOre(false);
        blockEnderOreGlowing = new BlockEnderOre(true);
        blockNiter = new BlockNiter();
        if (FluidRegistry.getFluid("oil").getBlock() == null)
        {
            blockOil = new BlockFluidOil();
            GameRegistry.registerBlock(blockOil, ItemBlockArtifice.class, blockOil.getUnlocalizedName());
        }
        else
            blockOil = FluidRegistry.getFluid("oil").getBlock();
        if (FluidRegistry.getFluid("fuel").getBlock() == null)
        {
            blockFuel = new BlockFluidFuel();
            GameRegistry.registerBlock(blockFuel, ItemBlockArtifice.class, blockFuel.getUnlocalizedName());
        }
        else
            blockFuel = FluidRegistry.getFluid("fuel").getBlock();
        if (FluidRegistry.getFluid("bitumen").getBlock() == null)
        {
            blockBitumen = new BlockFluidBitumen();
            GameRegistry.registerBlock(blockBitumen, ItemBlockArtifice.class, blockBitumen.getUnlocalizedName());
        }
        else
            blockBitumen = FluidRegistry.getFluid("bitumen").getBlock();
        if (FluidRegistry.getFluid("creosote").getBlock() == null)
        {
            blockCreosote = new BlockFluidCreosote();
            GameRegistry.registerBlock(blockCreosote, ItemBlockArtifice.class, blockCreosote.getUnlocalizedName());
        }
        else
            blockCreosote = FluidRegistry.getFluid("creosote").getBlock();
        blockGlowSand = new BlockGlowSand();
        for (int i=0; i<blockColoredTorches.length; i++)
            blockColoredTorches[i] = new BlockColoredTorch(i);

        GameRegistry.registerBlock(blockBasaltSlab, ItemBlockSlabArtifice.class, blockBasalt.getUnlocalizedName() + ".slab");
        GameRegistry.registerBlock(blockBasaltDoubleSlab, ItemBlockSlabArtifice.class, blockBasalt.getUnlocalizedName() + ".doubleslab");
        GameRegistry.registerBlock(blockMarbleSlab, ItemBlockSlabArtifice.class, blockMarble.getUnlocalizedName() + ".slab");
        GameRegistry.registerBlock(blockMarbleDoubleSlab, ItemBlockSlabArtifice.class, blockMarble.getUnlocalizedName() + ".dobuleslab");
        GameRegistry.registerBlock(blockFlora, ItemBlockFlora.class, blockFlora.getUnlocalizedName());
        GameRegistry.registerBlock(blockLotus, ItemBlockLotus.class, blockLotus.getUnlocalizedName());
        GameRegistry.registerBlock(blockBasalt, ItemBlockRock.class, blockBasalt.getUnlocalizedName());
        GameRegistry.registerBlock(blockMarble, ItemBlockRock.class, blockMarble.getUnlocalizedName());
        GameRegistry.registerBlock(blockBasaltBrickStairs, ItemBlockArtifice.class, blockBasaltBrickStairs.getUnlocalizedName());
        GameRegistry.registerBlock(blockMarbleBrickStairs, ItemBlockArtifice.class, blockMarbleBrickStairs.getUnlocalizedName());
        GameRegistry.registerBlock(blockBasaltCobbleStairs, ItemBlockArtifice.class, blockBasaltCobbleStairs.getUnlocalizedName());
        GameRegistry.registerBlock(blockMarbleCobbleStairs, ItemBlockArtifice.class, blockMarbleCobbleStairs.getUnlocalizedName());
        for (int i=0; i<blockLimestones.length; i++)
        {
            GameRegistry.registerBlock(blockLimestones[i], ItemBlockRock.class, blockLimestones[i].getUnlocalizedName());
            GameRegistry.registerBlock(blockLimestoneSlabs[i], ItemBlockSlabArtifice.class, blockLimestones[i].getUnlocalizedName() + ".slab");
            GameRegistry.registerBlock(blockLimestoneDoubleSlabs[i], ItemBlockSlabArtifice.class, blockLimestones[i].getUnlocalizedName() + ".doubleslab");
            GameRegistry.registerBlock(blockLimestoneBrickStairs[i], ItemBlockArtifice.class, blockLimestoneBrickStairs[i].getUnlocalizedName());
            GameRegistry.registerBlock(blockLimestoneCobbleStairs[i], ItemBlockArtifice.class, blockLimestoneCobbleStairs[i].getUnlocalizedName());
        }
        for (int i=0; i<oreNames.length; i++)
        {
            GameRegistry.registerBlock(blockOres[i], ItemBlockOre.class, blockOres[i].getUnlocalizedName());
            if (blockOresGlowing[i] != null)
                GameRegistry.registerBlock(blockOresGlowing[i], ItemBlockOre.class, blockOresGlowing[i].getUnlocalizedName());
        }
        GameRegistry.registerBlock(blockSulfur, ItemBlockSulfur.class, blockSulfur.getUnlocalizedName());
        GameRegistry.registerBlock(blockEnderOre, ItemBlockArtifice.class, blockEnderOre.getUnlocalizedName());
        GameRegistry.registerBlock(blockEnderOreGlowing, ItemBlockArtifice.class, blockEnderOreGlowing.getUnlocalizedName());
        GameRegistry.registerBlock(blockNiter, ItemBlockNiter.class, blockNiter.getUnlocalizedName());
        GameRegistry.registerBlock(blockGlowSand, ItemBlockArtifice.class, blockGlowSand.getUnlocalizedName());
        for (int i=0; i<blockColoredTorches.length; i++)
            GameRegistry.registerBlock(blockColoredTorches[i], ItemBlockArtifice.class, blockColoredTorches[i].getUnlocalizedName());

        ArtificeRegistry.registerBasaltType(ArtificeBlocks.blockBasalt, 0);
        ArtificeRegistry.registerMarbleType(ArtificeBlocks.blockMarble, 0);

        OreDictionary.registerOre("blockMarble", new ItemStack(blockMarble));
        OreDictionary.registerOre("blockBasalt", new ItemStack(blockBasalt));
        for (int i=0; i<blockLimestones.length; i++)
            OreDictionary.registerOre("blockLimestone", new ItemStack(blockLimestones[i]));
        OreDictionary.registerOre("oreSulfur", new ItemStack(blockSulfur, 1, 0));
        OreDictionary.registerOre("oreSaltpeter", new ItemStack(blockNiter, 1, 1));
        OreDictionary.registerOre("oreEnder", new ItemStack(blockEnderOre, 1, 0));

        blockFrame = new BlockFrameBase();
        blockDetector = new BlockFrameDetector();
        blockReinforced = new BlockFrameBlastWall();
        blockGlassWall = new BlockFrameGlassWall(false);
        blockGlassWallDark = new BlockFrameGlassWall(true);
        blockScaffold = new BlockFrameScaffold();

        GameRegistry.registerBlock(blockFrame, ItemBlockFrame.class, blockFrame.getUnlocalizedName());
        GameRegistry.registerBlock(blockDetector, ItemBlockArtifice.class, blockDetector.getUnlocalizedName());
        GameRegistry.registerBlock(blockReinforced, ItemBlockFrame.class, blockReinforced.getUnlocalizedName());
        GameRegistry.registerBlock(blockGlassWall, ItemBlockFrame.class, blockGlassWall.getUnlocalizedName());
        GameRegistry.registerBlock(blockGlassWallDark, ItemBlockFrame.class, blockGlassWallDark.getUnlocalizedName());
        GameRegistry.registerBlock(blockScaffold, ItemBlockFrame.class, blockScaffold.getUnlocalizedName());

        for (int i=0; i<ArtificeCore.tiers.length; i++)
        {
            OreDictionary.registerOre("blockGlass", new ItemStack(blockGlassWall, 1, i));
            OreDictionary.registerOre("blockGlass", new ItemStack(blockGlassWallDark, 1, i));
        }

        blockSteel = new BlockSteel();

        GameRegistry.registerBlock(blockSteel, ItemBlockArtifice.class, blockSteel.getUnlocalizedName());

        OreDictionary.registerOre("blockSteel", new ItemStack(blockSteel));

        for (int i=0; i<16; i++)
        {
            blockLamps[i] = new BlockLamp(MinecraftColors.values()[i], false);
            blockLampsInverted[i] = new BlockLamp(MinecraftColors.values()[i], true);

            GameRegistry.registerBlock(blockLamps[i], ItemBlockArtifice.class, blockLamps[i].getUnlocalizedName());
            GameRegistry.registerBlock(blockLampsInverted[i], ItemBlockArtifice.class, blockLampsInverted[i].getUnlocalizedName());
        }
    }
}
