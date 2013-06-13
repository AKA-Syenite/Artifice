package shukaro.artifice.event;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.ChunkDataEvent;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

public class EventHandler
{
    private List<Integer> dimBlacklist;
    private int dim;
    private ChunkCoordIntPair c;
    private ArrayList chunks;
    
    @ForgeSubscribe
    public void chunkSave(ChunkDataEvent.Save e)
    {
        e.getData().setString("Artifice", ArtificeConfig.regenKey.getString());
    }
    
    @ForgeSubscribe
    public void chunkLoad(ChunkDataEvent.Load e)
    {
        dim = e.world.provider.dimensionId;
        c = e.getChunk().getChunkCoordIntPair();
        
        if (dimBlacklist == null)
            dimBlacklist = ArtificeRegistry.getDimensionBlacklist();
        
        if (dimBlacklist.contains(dim))
            return;
        
        if ((!e.getData().getString("Artifice").equals(ArtificeConfig.regenKey.getString())) && (ArtificeConfig.regenFlora.getBoolean(false) || ArtificeConfig.regenBasalt.getBoolean(false) || ArtificeConfig.regenMarble.getBoolean(false)))
        {
            ArtificeCore.logger.log(Level.WARNING, "World gen was never run for chunk at " + e.getChunk().getChunkCoordIntPair() + ". Adding to queue for regeneration.");
            chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            if (chunks == null)
            {
                WorldTicker.chunksToGen.put(Integer.valueOf(dim), new ArrayList());
                chunks = (ArrayList) WorldTicker.chunksToGen.get(Integer.valueOf(dim));
            }
            if (chunks != null)
            {
                chunks.add(new ChunkCoord(c.chunkXPos, c.chunkZPos));
                WorldTicker.chunksToGen.put(Integer.valueOf(dim), chunks);
            }
        }
    }
    
    //@ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent e)
    {
    	EntityPlayer player = e.entityPlayer;
    	World world = player.worldObj;
    	
    	if (e.action != Action.RIGHT_CLICK_BLOCK || world.isRemote)
    		return;
    	
    	int x = e.x;
    	int y = e.y;
    	int z = e.z;
    	ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];
    	int blockID = ArtificeBlocks.blockScaffold.blockID;
    	Block block = Block.blocksList[blockID];
    	
    	if (held == null || held.itemID != blockID || held.getItemDamage() != world.getBlockMetadata(x, y, z))
    		return;
    	
		if (player.isSneaking())
		{
			if (world.getBlockId(x, y, z) == blockID)
			{
				for (int i = y; i < 256; i++)
				{
					if (world.isAirBlock(x, i, z))
					{
						world.setBlock(x, i, z, blockID, held.getItemDamage(), 3);
						if (!player.capabilities.isCreativeMode)
						{
							held.stackSize--;
							if(held.stackSize == 0)
							{
								held = null;
							}
						}
						break;
					}
				}
			}
		}
		else
		{
			BlockCoord face = new BlockCoord(x, y, z).offset(e.face);
			if (block.canPlaceBlockAt(world, face.x, face.y, face.z))
			{
				world.setBlock(face.x, face.y, face.z, blockID, held.getItemDamage(), 3);
				if (!player.capabilities.isCreativeMode)
				{
					held.stackSize--;
					if(held.stackSize == 0)
					{
						held = null;
					}
				}
			}
		}
    }
}
