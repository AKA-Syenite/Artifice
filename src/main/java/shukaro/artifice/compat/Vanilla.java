package shukaro.artifice.compat;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;

import java.util.ArrayList;

public class Vanilla implements ICompat
{
    public String getModID()
    {
        return null;
    }

    public void load()
    {
        ArtificeRegistry.registerDimensionBlacklist(1);
        ArtificeRegistry.registerDimensionBlacklist(-1);

        ArtificeRegistry.registerStoneType(Blocks.stone, 0);

        ArtificeRegistry.registerWorldTypeBlacklist("flat");

        loadSledges();
    }

    private void loadSledges()
    {
        if (ArtificeConfig.convenienceRecipes)
        {
            ArrayList<ItemStack> stoneBricks = new ArrayList<ItemStack>();
            stoneBricks.add(new ItemStack(Blocks.stonebrick, 1, 2));
            ArtificeRegistry.registerSledgeBlock(Blocks.stonebrick, 0, stoneBricks);
        }

        ArrayList<ItemStack> workbench = new ArrayList<ItemStack>();
        workbench.add(new ItemStack(Blocks.planks, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.crafting_table, 0, workbench);

        ArrayList<ItemStack> furnace = new ArrayList<ItemStack>();
        furnace.add(new ItemStack(Blocks.cobblestone, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.furnace, furnace);
        ArtificeRegistry.registerWildSledgeBlock(Blocks.lit_furnace, furnace);

        ArrayList<ItemStack> chest = new ArrayList<ItemStack>();
        chest.add(new ItemStack(Blocks.planks, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.chest, chest);

        ArrayList<ItemStack> wool = new ArrayList<ItemStack>();
        wool.add(new ItemStack(Items.string, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.wool, wool);

        ArrayList<ItemStack> tnt = new ArrayList<ItemStack>();
        tnt.add(new ItemStack(Blocks.sand, 4, 0));
        tnt.add(new ItemStack(Items.gunpowder, 5, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.tnt, 0, tnt);

        ArrayList<ItemStack> brick = new ArrayList<ItemStack>();
        brick.add(new ItemStack(Items.brick, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.brick_block, 0, brick);

        ArrayList<ItemStack> netherBrick = new ArrayList<ItemStack>();
        netherBrick.add(new ItemStack(Items.netherbrick, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.nether_brick, 0, netherBrick);

        ArrayList<ItemStack> bookShelf = new ArrayList<ItemStack>();
        bookShelf.add(new ItemStack(Blocks.planks, 6, 0));
        bookShelf.add(new ItemStack(Items.book, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.bookshelf, 0, bookShelf);

        ArrayList<ItemStack> sand = new ArrayList<ItemStack>();
        sand.add(new ItemStack(Blocks.sand, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.sandstone, 0, sand);

        ArrayList<ItemStack> quartz = new ArrayList<ItemStack>();
        quartz.add(new ItemStack(Items.quartz, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.quartz_block, 0, quartz);

        ArrayList<ItemStack> jack = new ArrayList<ItemStack>();
        jack.add(new ItemStack(Blocks.pumpkin, 1, 0));
        jack.add(new ItemStack(Blocks.torch, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.lit_pumpkin, jack);

        ArrayList<ItemStack> anvil = new ArrayList<ItemStack>();
        anvil.add(new ItemStack(Blocks.iron_block, 1, 0));
        anvil.add(new ItemStack(Blocks.iron_block, 1, 0));
        anvil.add(new ItemStack(Blocks.iron_block, 1, 0));
        anvil.add(new ItemStack(Items.iron_ingot, 1, 0));
        anvil.add(new ItemStack(Items.iron_ingot, 1, 0));
        anvil.add(new ItemStack(Items.iron_ingot, 1, 0));
        anvil.add(new ItemStack(Items.iron_ingot, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.anvil, anvil);

        ArrayList<ItemStack> beacon = new ArrayList<ItemStack>();
        beacon.add(new ItemStack(Items.nether_star, 1, 0));
        beacon.add(new ItemStack(Blocks.obsidian, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.beacon, beacon);

        ArrayList<ItemStack> note = new ArrayList<ItemStack>();
        note.add(new ItemStack(Blocks.planks, 8, 0));
        note.add(new ItemStack(Items.redstone, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.noteblock, note);

        ArrayList<ItemStack> juke = new ArrayList<ItemStack>();
        juke.add(new ItemStack(Blocks.planks, 8, 0));
        juke.add(new ItemStack(Items.diamond, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.jukebox, juke);

        ArrayList<ItemStack> dispenser = new ArrayList<ItemStack>();
        dispenser.add(new ItemStack(Blocks.cobblestone, 7, 0));
        dispenser.add(new ItemStack(Items.bow, 1, 0));
        dispenser.add(new ItemStack(Items.redstone, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.dispenser, dispenser);

        ArrayList<ItemStack> dropper = new ArrayList<ItemStack>();
        dropper.add(new ItemStack(Blocks.cobblestone, 7, 0));
        dropper.add(new ItemStack(Items.redstone, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.dropper, dropper);

        ArrayList<ItemStack> piston = new ArrayList<ItemStack>();
        piston.add(new ItemStack(Blocks.planks, 3, 0));
        piston.add(new ItemStack(Blocks.cobblestone, 4, 0));
        piston.add(new ItemStack(Items.iron_ingot, 1, 0));
        piston.add(new ItemStack(Items.redstone, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.piston, piston);

        ArrayList<ItemStack> sticky = new ArrayList<ItemStack>();
        sticky.add(new ItemStack(Blocks.piston, 1, 0));
        sticky.add(new ItemStack(Items.slime_ball, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.sticky_piston, sticky);

        ArrayList<ItemStack> lamp = new ArrayList<ItemStack>();
        lamp.add(new ItemStack(Items.redstone, 4, 0));
        lamp.add(new ItemStack(Items.glowstone_dust, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.redstone_lamp, 0, lamp);
        ArtificeRegistry.registerSledgeBlock(Blocks.lit_redstone_lamp, 0, lamp);

        ArrayList<ItemStack> sensor = new ArrayList<ItemStack>();
        sensor.add(new ItemStack(Items.quartz, 3, 0));
        sensor.add(new ItemStack(Blocks.wooden_slab, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Blocks.daylight_detector, 0, sensor);

        ArrayList<ItemStack> hopper = new ArrayList<ItemStack>();
        hopper.add(new ItemStack(Blocks.planks, 8, 0));
        hopper.add(new ItemStack(Items.iron_ingot, 5, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.hopper, hopper);

        ArrayList<ItemStack> trapped = new ArrayList<ItemStack>();
        trapped.add(new ItemStack(Blocks.planks, 8, 0));
        trapped.add(new ItemStack(Blocks.tripwire_hook, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.trapped_chest, trapped);

        ArrayList<ItemStack> woodDoor = new ArrayList<ItemStack>();
        woodDoor.add(new ItemStack(Blocks.planks, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.wooden_door, woodDoor);

        ArrayList<ItemStack> ironDoor = new ArrayList<ItemStack>();
        ironDoor.add(new ItemStack(Items.iron_ingot, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.iron_door, ironDoor);

        ArrayList<ItemStack> repeater = new ArrayList<ItemStack>();
        repeater.add(new ItemStack(Blocks.stone, 3, 0));
        repeater.add(new ItemStack(Blocks.redstone_torch, 2, 0));
        repeater.add(new ItemStack(Items.redstone, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.unpowered_repeater, repeater);
        ArtificeRegistry.registerWildSledgeBlock(Blocks.powered_repeater, repeater);

        ArrayList<ItemStack> comparator = new ArrayList<ItemStack>();
        comparator.add(new ItemStack(Blocks.redstone_torch, 3, 0));
        comparator.add(new ItemStack(Blocks.stone, 3, 0));
        comparator.add(new ItemStack(Items.quartz, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.unpowered_comparator, comparator);
        ArtificeRegistry.registerWildSledgeBlock(Blocks.powered_comparator, comparator);

        ArrayList<ItemStack> gate = new ArrayList<ItemStack>();
        gate.add(new ItemStack(Blocks.planks, 2, 0));
        gate.add(new ItemStack(Items.stick, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.fence_gate, gate);

        ArrayList<ItemStack> bed = new ArrayList<ItemStack>();
        bed.add(new ItemStack(Blocks.wool, 3, 0));
        bed.add(new ItemStack(Blocks.planks, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.bed, bed);

        ArrayList<ItemStack> table = new ArrayList<ItemStack>();
        table.add(new ItemStack(Items.book, 1, 0));
        table.add(new ItemStack(Items.diamond, 2, 0));
        table.add(new ItemStack(Blocks.obsidian, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.enchanting_table, table);

        ArrayList<ItemStack> cauldron = new ArrayList<ItemStack>();
        cauldron.add(new ItemStack(Items.iron_ingot, 7, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.cauldron, cauldron);

        ArrayList<ItemStack> stand = new ArrayList<ItemStack>();
        stand.add(new ItemStack(Items.blaze_rod, 1, 0));
        stand.add(new ItemStack(Blocks.cobblestone, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Blocks.brewing_stand, stand);
    }
}
