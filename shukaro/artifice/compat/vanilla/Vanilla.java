package shukaro.artifice.compat.vanilla;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;

@Mod(modid = "ArtificeCompat|Vanilla", name = "Artifice Compat: Vanilla", version = ArtificeCore.modVersion, dependencies = "after:Artifice")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Vanilla
{
    @Init
    public void load(FMLInitializationEvent e)
    {
        ArtificeRegistry.registerDimensionBlacklist(1);
        ArtificeRegistry.registerDimensionBlacklist(-1);
        
        ArtificeRegistry.registerStoneType(Block.stone.blockID);
        
        ArtificeRegistry.registerWorldTypeBlacklist("flat");
        
        ArtificeRegistry.registerFloraBlacklist("Swampland");
        ArtificeRegistry.registerFloraBlacklist("MushroomIsland");
        ArtificeRegistry.registerFloraBlacklist("MushroomIslandShore");
        
        ArtificeRegistry.registerLotusWhitelist("Swampland");
        
        loadSledges();
    }
    
    private void loadSledges()
    {
    	ArrayList<ItemStack> workbench = new ArrayList<ItemStack>();
        workbench.add(new ItemStack(Block.planks.blockID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.workbench.blockID, 0, workbench);
        
        ArrayList<ItemStack> furnace = new ArrayList<ItemStack>();
        furnace.add(new ItemStack(Block.cobblestone.blockID, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.furnaceIdle.blockID, furnace);
        ArtificeRegistry.registerWildSledgeBlock(Block.furnaceBurning.blockID, furnace);
        
        ArrayList<ItemStack> chest = new ArrayList<ItemStack>();
        chest.add(new ItemStack(Block.planks.blockID, 8, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.chest.blockID, chest);
        
        ArrayList<ItemStack> wool = new ArrayList<ItemStack>();
        wool.add(new ItemStack(Item.silk.itemID, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.cloth.blockID, wool);
        
        ArrayList<ItemStack> tnt = new ArrayList<ItemStack>();
        tnt.add(new ItemStack(Block.sand.blockID, 4, 0));
        tnt.add(new ItemStack(Item.gunpowder.itemID, 5, 0));
        ArtificeRegistry.registerSledgeBlock(Block.tnt.blockID, 0, tnt);
        
        ArrayList<ItemStack> brick = new ArrayList<ItemStack>();
        brick.add(new ItemStack(Item.brick.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.brick.blockID, 0, brick);
        
        ArrayList<ItemStack> netherBrick = new ArrayList<ItemStack>();
        netherBrick.add(new ItemStack(Item.netherrackBrick.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.netherBrick.blockID, 0, netherBrick);
        
        ArrayList<ItemStack> bookShelf = new ArrayList<ItemStack>();
        bookShelf.add(new ItemStack(Block.planks.blockID, 6, 0));
        bookShelf.add(new ItemStack(Item.book.itemID, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Block.bookShelf.blockID, 0, bookShelf);
        
        ArrayList<ItemStack> sand = new ArrayList<ItemStack>();
        sand.add(new ItemStack(Block.sand.blockID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.sandStone.blockID, 0, sand);
        
        ArrayList<ItemStack> quartz = new ArrayList<ItemStack>();
        quartz.add(new ItemStack(Item.netherQuartz.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.blockNetherQuartz.blockID, 0, quartz);
        
        ArrayList<ItemStack> jack = new ArrayList<ItemStack>();
        jack.add(new ItemStack(Block.pumpkin.blockID, 1, 0));
        jack.add(new ItemStack(Block.torchWood.blockID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pumpkinLantern.blockID, jack);
        
        ArrayList<ItemStack> anvil = new ArrayList<ItemStack>();
        anvil.add(new ItemStack(Block.blockIron.blockID, 1, 0));
        anvil.add(new ItemStack(Block.blockIron.blockID, 1, 0));
        anvil.add(new ItemStack(Block.blockIron.blockID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        anvil.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.anvil.blockID, anvil);
        
        ArrayList<ItemStack> beacon = new ArrayList<ItemStack>();
        beacon.add(new ItemStack(Item.netherStar.itemID, 1, 0));
        beacon.add(new ItemStack(Block.obsidian.blockID, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.beacon.blockID, beacon);
        
        ArrayList<ItemStack> note = new ArrayList<ItemStack>();
        note.add(new ItemStack(Block.planks.blockID, 8, 0));
        note.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.music.blockID, note);
        
        ArrayList<ItemStack> juke = new ArrayList<ItemStack>();
        juke.add(new ItemStack(Block.planks.blockID, 8, 0));
        juke.add(new ItemStack(Item.diamond.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.jukebox.blockID, juke);
        
        ArrayList<ItemStack> dispenser = new ArrayList<ItemStack>();
        dispenser.add(new ItemStack(Block.cobblestone.blockID, 7, 0));
        dispenser.add(new ItemStack(Item.bow.itemID, 1, 0));
        dispenser.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.dispenser.blockID, dispenser);
        
        ArrayList<ItemStack> dropper = new ArrayList<ItemStack>();
        dropper.add(new ItemStack(Block.cobblestone.blockID, 7, 0));
        dropper.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.dropper.blockID, dropper);
        
        ArrayList<ItemStack> piston = new ArrayList<ItemStack>();
        piston.add(new ItemStack(Block.planks.blockID, 3, 0));
        piston.add(new ItemStack(Block.cobblestone.blockID, 4, 0));
        piston.add(new ItemStack(Item.ingotIron.itemID, 1, 0));
        piston.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pistonBase.blockID, piston);
        
        ArrayList<ItemStack> sticky = new ArrayList<ItemStack>();
        sticky.add(new ItemStack(Block.pistonBase.blockID, 1, 0));
        sticky.add(new ItemStack(Item.slimeBall.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.pistonStickyBase.blockID, sticky);
        
        ArrayList<ItemStack> lamp = new ArrayList<ItemStack>();
        lamp.add(new ItemStack(Item.redstone.itemID, 4, 0));
        lamp.add(new ItemStack(Item.lightStoneDust.itemID, 4, 0));
        ArtificeRegistry.registerSledgeBlock(Block.redstoneLampIdle.blockID, 0, lamp);
        ArtificeRegistry.registerSledgeBlock(Block.redstoneLampActive.blockID, 0, lamp);
        
        ArrayList<ItemStack> sensor = new ArrayList<ItemStack>();
        sensor.add(new ItemStack(Item.netherQuartz.itemID, 3, 0));
        sensor.add(new ItemStack(Block.woodSingleSlab.blockID, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Block.daylightSensor.blockID, 0, sensor);
        
        ArrayList<ItemStack> hopper = new ArrayList<ItemStack>();
        hopper.add(new ItemStack(Block.planks.blockID, 8, 0));
        hopper.add(new ItemStack(Item.ingotIron.itemID, 5, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.hopperBlock.blockID, hopper);
        
        ArrayList<ItemStack> trapped = new ArrayList<ItemStack>();
        trapped.add(new ItemStack(Block.planks.blockID, 8, 0));
        trapped.add(new ItemStack(Block.tripWireSource.blockID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.chestTrapped.blockID, trapped);
        
        ArrayList<ItemStack> woodDoor = new ArrayList<ItemStack>();
        woodDoor.add(new ItemStack(Block.planks.blockID, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.doorWood.blockID, woodDoor);
        
        ArrayList<ItemStack> ironDoor = new ArrayList<ItemStack>();
        ironDoor.add(new ItemStack(Item.ingotIron.itemID, 6, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.doorIron.blockID, ironDoor);
        
        ArrayList<ItemStack> repeater = new ArrayList<ItemStack>();
        repeater.add(new ItemStack(Block.stone.blockID, 3, 0));
        repeater.add(new ItemStack(Block.torchRedstoneActive.blockID, 2, 0));
        repeater.add(new ItemStack(Item.redstone.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneRepeaterIdle.blockID, repeater);
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneRepeaterActive.blockID, repeater);
        
        ArrayList<ItemStack> comparator = new ArrayList<ItemStack>();
        comparator.add(new ItemStack(Block.torchRedstoneActive.blockID, 3, 0));
        comparator.add(new ItemStack(Block.stone.blockID, 3, 0));
        comparator.add(new ItemStack(Item.netherQuartz.itemID, 1, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneComparatorIdle.blockID, comparator);
        ArtificeRegistry.registerWildSledgeBlock(Block.redstoneComparatorActive.blockID, comparator);
        
        ArrayList<ItemStack> gate = new ArrayList<ItemStack>();
        gate.add(new ItemStack(Block.planks.blockID, 2, 0));
        gate.add(new ItemStack(Item.stick.itemID, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.fenceGate.blockID, gate);
        
        ArrayList<ItemStack> bed = new ArrayList<ItemStack>();
        bed.add(new ItemStack(Block.cloth.blockID, 3, 0));
        bed.add(new ItemStack(Block.planks.blockID, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.bed.blockID, bed);
        
        ArrayList<ItemStack> table = new ArrayList<ItemStack>();
        table.add(new ItemStack(Item.book.itemID, 1, 0));
        table.add(new ItemStack(Item.diamond.itemID, 2, 0));
        table.add(new ItemStack(Block.obsidian.blockID, 4, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.enchantmentTable.blockID, table);
        
        ArrayList<ItemStack> cauldron = new ArrayList<ItemStack>();
        cauldron.add(new ItemStack(Item.ingotIron.itemID, 7, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.cauldron.blockID, cauldron);
        
        ArrayList<ItemStack> stand = new ArrayList<ItemStack>();
        stand.add(new ItemStack(Item.blazeRod.itemID, 1, 0));
        stand.add(new ItemStack(Block.cobblestone.blockID, 3, 0));
        ArtificeRegistry.registerWildSledgeBlock(Block.brewingStand.blockID, stand);
    }
}
