package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.Packets;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameScaffold extends BlockFrame
{
    private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN };
    
    private Icon[] sideIcons = new Icon[ArtificeCore.tiers.length];
    private ConnectedTextureBase basic = new SolidConnectedTexture(ConnectedTextures.BasicScaffold);
    private ConnectedTextureBase reinforced = new SolidConnectedTexture(ConnectedTextures.ReinforcedScaffold);
    private ConnectedTextureBase industrial = new SolidConnectedTexture(ConnectedTextures.IndustrialScaffold);
    private ConnectedTextureBase advanced = new SolidConnectedTexture(ConnectedTextures.AdvancedScaffold);
    
    public BlockFrameScaffold(int id)
    {
        super(id);
        setUnlocalizedName("artifice.scaffold");
        setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if(entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)entity).playerNetServerHandler.ticksForFloatKick = 0;
            entity.fallDistance = 0;
        }
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];
        if (held != null && held.itemID == this.blockID && held.getItemDamage() == world.getBlockMetadata(x, y, z))
        {
            while (world.getBlockId(x, y, z) == this.blockID)
                y++;
            if (checkStay(world, x, y, z, held.getItemDamage()) && (Block.blocksList[world.getBlockId(x, y, z)] == null || Block.blocksList[world.getBlockId(x, y, z)].isAirBlock(world, x, y, z)))
            {
                world.setBlock(x, y, z, this.blockID, held.getItemDamage(), 3);
                if (!player.capabilities.isCreativeMode)
                {
                    held.stackSize--;
                    if (held.stackSize <= 0)
                    	player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
            }
        }
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack stack)
    {
        return this.checkStay(world, x, y, z, stack.getItemDamage());
    }
    
    public int getOverhang(int meta)
    {
        switch (meta)
        {
        case 0:
            return 4;
        case 1:
            return 8;
        case 2:
            return 12;
        case 3:
            return 16;
        default:
            return 0;
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return this.checkStay(world, x, y, z, world.getBlockMetadata(x, y, z));
    }
    
    private boolean checkStay(World world, int x, int y, int z, int meta)
    {
        BlockCoord c = new BlockCoord(x, y, z);
        
        if (isRooted(world, x, y, z, meta))
            return true;
        
        for (ForgeDirection d : sides)
        {
            BlockCoord t = c.copy().offset(d.ordinal());
            if (t.getBlock(world) instanceof BlockFrameScaffold)
            {
                if (t.blockEquals(world, this.blockID, meta))
                {
                    if (isRooted(world, t.x, t.y, t.z, meta))
                        return true;
                    for (BlockCoord match : c.getRadiusMatches(world, getOverhang(meta), this.blockID, meta))
                    {
                        if (isRooted(world, match.x, match.y, match.z, meta) && c.isConnected(world, match, this.blockID, meta) && c.getDistance(match) <= getOverhang(meta))
                            return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    private boolean isRooted(World world, int x, int y, int z, int meta)
    {
        for (int i=y-1; i>0; i--)
        {
            if (world.isBlockSolidOnSide(x, i, z, ForgeDirection.UP))
            {
                if (world.getBlockId(x, i, z) == this.blockID)
                {
                    if (world.getBlockMetadata(x, i, z) == meta)
                        continue;
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
    	if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    	
    	if (!world.isRemote)
    	{
	    	Integer worldID = world.provider.dimensionId;
	    	BlockCoord coord = new BlockCoord(x, y, z);
	    	ChunkCoord chunk = new ChunkCoord(coord);
	    	int meta = coord.getMeta(world);
	    	
	    	int[] old = ArtificeCore.textureCache.get(worldID, chunk, coord);
	    	int[] indices = new int[6];
			for (int i=0; i<indices.length; i++)
				indices[i] = this.getTextureRenderer(i, meta).getTextureIndex(world, x, y, z, i);
			ArtificeCore.textureCache.add(worldID, chunk, coord, indices);
    	}
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return (side == ForgeDirection.UP || side == ForgeDirection.DOWN) ? true : false;
    }

    public ConnectedTextureBase getTextureRenderer(int side, int meta)
    {
        if (side == 0 || side == 1)
        {
            switch (meta)
            {
            case 0:
                return basic;
            case 1:
                return reinforced;
            case 2:
                return industrial;
            case 3:
                return advanced;
            default:
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        for (int i=0; i<ArtificeCore.tiers.length; i++)
            sideIcons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(), "scaffold/sides");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (side == 0 || side == 1)
        	return this.getTextureRenderer(side, meta).texture.textureList[0];
        return this.sideIcons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        if (side == 0 || side == 1)
        {
        	Integer worldID = Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId;
        	BlockCoord coord = new BlockCoord(x, y, z);
        	ChunkCoord chunk = new ChunkCoord(coord);
        	int meta = coord.getMeta(block);
        	
        	if (!ArtificeCore.textureCache.contains(worldID, chunk, coord))
        	{
        		int[] indices = new int[6];
        		for (int i=0; i<indices.length; i++)
        			indices[i] = this.getTextureRenderer(i, meta).getTextureIndex(block, x, y, z, i);
        		ArtificeCore.textureCache.add(worldID, chunk, coord, indices);
        	}
        	
        	if (ArtificeCore.textureCache.get(worldID, chunk, coord) == null)
        		return this.getIcon(side, meta);
        	return this.getTextureRenderer(side, meta).texture.textureList[ArtificeCore.textureCache.get(worldID, chunk, coord)[side]];
        }
        return this.sideIcons[block.getBlockMetadata(x, y, z)];
    }
    
    @Override
	public int getRenderType()
	{
		return ArtificeConfig.frameRenderID;
	}
}
