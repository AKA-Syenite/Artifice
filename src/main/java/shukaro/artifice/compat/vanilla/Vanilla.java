package shukaro.artifice.compat.vanilla;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;

import java.util.ArrayList;

@Mod(modid = "ArtificeCompat|Vanilla", name = "Artifice Compat: Vanilla", version = ArtificeCore.modVersion, dependencies = "after:Artifice")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Vanilla
{
    @EventHandler
    public void load(FMLInitializationEvent e)
    {
        ArtificeRegistry.registerDimensionBlacklist(1);
        ArtificeRegistry.registerDimensionBlacklist(-1);

        ArtificeRegistry.registerStoneType(Block.stone);

        ArtificeRegistry.registerWorldTypeBlacklist("flat");

        loadSledges();
    }

    private void loadSledges()
    {
        if (ArtificeConfig.convenienceRecipes.getBoolean(true))
        {
            ArrayList<ItemStack> stoneBricks = new ArrayList<ItemStack>();
            stoneBricks.add(new ItemStack(Block.stoneBrick, 1, 2));
            ArtificeRegistry.registerSledgeBlock(Block.stoneBrick, 0, stoneBricks);
        }

        ArrayList<ItemStack> workbench = new ArrayList<ItemStack>();
        workbench.add(new ItemStack(Block.planks, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.workbench, 0, workbench);

        ArrayList<ItemStack> furnace = new ArrayList<ItemStack>();
        furnace.add(new ItemStack(Block.cobblestone, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.furnaceIdle, furnace);
        ArtificeRegistry.registerWildSledgeBlock(Block.furnaceBurning, furnace);

        ArrayList<ItemStack> chest = new ArrayList<ItemStack>();
        chest.add(new ItemStack(Block.planks, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.chest, chest);

        ArrayList<ItemStack> wool = new ArrayList<ItemStack>();
        wool.add(new ItemStack(Item.silk.itemID, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.cloth, wool);

        ArrayList<ItemStack> tnt = new ArrayList<ItemStack>();
        tnt.add(new ItemStack(Block.sand, 4, 0));
        tnt.add(new ItemStack(Item.gunpowder.itemID, 5, 0));
        ArtificeRegistry.registerSledgeBlock(Block.tnt, 0, tnt);

        ArrayList<ItemStack> brick = new ArrayList<ItemStack>();
        brick.add(new ItemStack(Item.brick.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.brick, 0, brick);

        ArrayList<ItemStack> netherBrick = new ArrayList<ItemStack>();
        netherBrick.add(new ItemStack(Item.netherrackBrick.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.netherBrick, 0, netherBrick);

        ArrayList<ItemStack> bookShelf = new ArrayList<ItemStack>();
        bookShelf.add(new ItemStack(Block.planks, 6, 0));
        bookShelf.add(new ItemStack(Item.book.itemID, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Block.bookShelf, 0, bookShelf);

        ArrayList<ItemStack> sand = new ArrayList<ItemStack>();
        sand.add(new ItemStack(Block.sand, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.sandStone, 0, sand);

        ArrayList<ItemStack> quartz = new ArrayList<ItemStack>();
        quartz.add(new ItemStack(Item.netherQuartz.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.blockNetherQuartz, 0, quartz);

        ArrayList<ItemStack> jack = new ArrayList<ItemStack>();
        jack.add(new ItemStack(Block.pumpkin, 1, 0));
        jack.add(new ItemStack(Block.torchWood, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pumpkinLantern, jack);

        ArrayList<ItemStack> anvil = new ArrayList<ItemStack>();
        anvil.add(new ItemStack(Block.blockIron, 1, 0));
        anvil.add(new ItemStack(Block.blockIron, 1, 0));
        anvil.add(new ItemStack(Block.blockIron, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.anvil, anvil);

        ArrayList<ItemStack> beacon = new ArrayList<ItemStack>();
        beacon.add(new ItemStack(Item.netherStar.itemID, 1, 0));
        beacon.add(new ItemStack(Block.obsidian, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.beacon, beacon);

        ArrayList<ItemStack> note = new ArrayList<ItemStack>();
        note.add(new ItemStack(Block.planks, 8, 0));
        note.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.music, note);

        ArrayList<ItemStack> juke = new ArrayList<ItemStack>();
        juke.add(new ItemStack(Block.planks, 8, 0));
        juke.add(new ItemStack(Item.diamond.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.jukebox, juke);

        ArrayList<ItemStack> dispenser = new ArrayList<ItemStack>();
        dispenser.add(new ItemStack(Block.cobblestone, 7, 0));
        dispenser.add(new ItemStack(Item.bow.itemID, 1, 0));
        dispenser.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.dispenser, dispenser);

        ArrayList<ItemStack> dropper = new ArrayList<ItemStack>();
        dropper.add(new ItemStack(Block.cobblestone, 7, 0));
        dropper.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.dropper, dropper);

        ArrayList<ItemStack> piston = new ArrayList<ItemStack>();
        piston.add(new ItemStack(Block.planks, 3, 0));
        piston.add(new ItemStack(Block.cobblestone, 4, 0));
        piston.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        piston.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pistonBase, piston);

        ArrayList<ItemStack> sticky = new ArrayList<ItemStack>();
        sticky.add(new ItemStack(Block.pistonBase, 1, 0));
        sticky.add(new ItemStack(Item.slimeBall.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pistonStickyBase, sticky);

        ArrayList<ItemStack> lamp = new ArrayList<ItemStack>();
        lamp.add(new ItemStack(Item.redstone.itemID, 4, 0));
        lamp.add(new ItemStack(Item.glowstone.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.redstoneLampIdle, 0, lamp);
        ArtificeRegistry.registerSledgeBlock(Block.redstoneLampActive, 0, lamp);

        ArrayList<ItemStack> sensor = new ArrayList<ItemStack>();
        sensor.add(new ItemStack(Item.netherQuartz.itemID, 3, 0));
        sensor.add(new ItemStack(Block.woodSingleSlab, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Block.daylightSensor, 0, sensor);

        ArrayList<ItemStack> hopper = new ArrayList<ItemStack>();
        hopper.add(new ItemStack(Block.planks, 8, 0));
        hopper.add(new ItemStack(Item.ingotIron.itemID, 5, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.hopperBlock, hopper);

        ArrayList<ItemStack> trapped = new ArrayList<ItemStack>();
        trapped.add(new ItemStack(Block.planks, 8, 0));
        trapped.add(new ItemStack(Block.tripWireSource, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.chestTrapped, trapped);

        ArrayList<ItemStack> woodDoor = new ArrayList<ItemStack>();
        woodDoor.add(new ItemStack(Block.planks, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.doorWood, woodDoor);

        ArrayList<ItemStack> ironDoor = new ArrayList<ItemStack>();
        ironDoor.add(new ItemStack(Item.ingotIron.itemID, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.doorIron, ironDoor);

        ArrayList<ItemStack> repeater = new ArrayList<ItemStack>();
        repeater.add(new ItemStack(Block.stone, 3, 0));
        repeater.add(new ItemStack(Block.torchRedstoneActive, 2, 0));
        repeater.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneRepeaterIdle, repeater);
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneRepeaterActive, repeater);

        ArrayList<ItemStack> comparator = new ArrayList<ItemStack>();
        comparator.add(new ItemStack(Block.torchRedstoneActive, 3, 0));
        comparator.add(new ItemStack(Block.stone, 3, 0));
        comparator.add(new ItemStack(Item.netherQuartz.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneComparatorIdle, comparator);
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneComparatorActive, comparator);

        ArrayList<ItemStack> gate = new ArrayList<ItemStack>();
        gate.add(new ItemStack(Block.planks, 2, 0));
        gate.add(new ItemStack(Item.stick.itemID, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.fenceGate, gate);

        ArrayList<ItemStack> bed = new ArrayList<ItemStack>();
        bed.add(new ItemStack(Block.cloth, 3, 0));
        bed.add(new ItemStack(Block.planks, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.bed, bed);

        ArrayList<ItemStack> table = new ArrayList<ItemStack>();
        table.add(new ItemStack(Item.book.itemID, 1, 0));
        table.add(new ItemStack(Item.diamond.itemID, 2, 0));
        table.add(new ItemStack(Block.obsidian, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.enchantmentTable, table);

        ArrayList<ItemStack> cauldron = new ArrayList<ItemStack>();
        cauldron.add(new ItemStack(Item.ingotIron.itemID, 7, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.cauldron, cauldron);

        ArrayList<ItemStack> stand = new ArrayList<ItemStack>();
        stand.add(new ItemStack(Item.blazeRod.itemID, 1, 0));
        stand.add(new ItemStack(Block.cobblestone, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.brewingStand, stand);
    }
}
