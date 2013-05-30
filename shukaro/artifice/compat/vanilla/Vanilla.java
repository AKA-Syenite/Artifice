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
        
        ArrayList<ItemStack> bookShelf = new ArrayList<ItemStack>();
        bookShelf.add(new ItemStack(Block.planks.blockID, 6, 0));
        bookShelf.add(new ItemStack(Item.book.itemID, 3, 0));
        ArtificeRegistry.registerSledgeBlock(Block.bookShelf.blockID, 0, bookShelf);
        
        ArrayList<ItemStack> tnt = new ArrayList<ItemStack>();
        tnt.add(new ItemStack(Block.sand.blockID, 4, 0));
        tnt.add(new ItemStack(Item.gunpowder.itemID, 5, 0));
        ArtificeRegistry.registerSledgeBlock(Block.tnt.blockID, 0, tnt);
    }
}
