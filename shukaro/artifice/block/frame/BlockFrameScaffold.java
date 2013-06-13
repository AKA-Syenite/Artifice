package shukaro.artifice.block.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.TileEntityScaffold;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import shukaro.artifice.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFrameScaffold extends BlockFrame
{
	private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
	
	public BlockFrameScaffold(int id)
	{
		super(id);
		this.textureName = "scaffold";
		setUnlocalizedName("artifice.scaffold");
		this.normal = true;
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
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canBlockStay(world, x, y, z);
	}
	
	private float getDistance(int meta)
	{
		switch (meta)
		{
		case 0:
			return 4.0F;
		case 1:
			return 8.0F;
		case 2:
			return 16.0F;
		case 3:
			return 32.0F;
		default:
			return 0.0F;
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		BlockCoord c = new BlockCoord(x, y, z);
		
		if (c.getTileEntity(world) == null)
			return;
		
		TileEntityScaffold s = (TileEntityScaffold) c.getTileEntity(world);
		
		if (s.isRoot)
		{
			world.removeBlockTileEntity(x, y, z);
			return;
		}
		
		float totalLoad = -1.0F;
		
		for (BlockCoord root : s.roots.keySet())
		{
			if (root.getTileEntity(world) == null)
			{
				s.roots.remove(root);
				continue;
			}
			int side = s.roots.get(root);
			totalLoad += ((TileEntityScaffold)root.getTileEntity(world)).getLoad(side);
		}
		
		int numRoots = s.roots.size();
		float loadEach = totalLoad / numRoots;
		
		for (BlockCoord root : s.roots.keySet())
		{
			int side = s.roots.get(root);
			((TileEntityScaffold)root.getTileEntity(world)).setLoad(side, loadEach);
		}
		
		world.removeBlockTileEntity(x, y, z);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		BlockCoord c = new BlockCoord(x, y, z);
		
		if (c.getTileEntity(world) == null)
			return;
		
		TileEntityScaffold base = (TileEntityScaffold) c.getTileEntity(world);
		base.getRoots();
		System.out.println(base.isRoot);
		if (!base.isRoot)
		{
			float totalLoad = 1.0F;
			
			for (BlockCoord root : base.roots.keySet())
			{
				if (root.getTileEntity(world) == null)
				{
					base.roots.remove(root);
					continue;
				}
				int side = base.roots.get(root);
				totalLoad += ((TileEntityScaffold)root.getTileEntity(world)).getLoad(side);
			}
			
			int numRoots = base.roots.size();
			float loadEach = totalLoad / numRoots;
			
			for (BlockCoord root : base.roots.keySet())
			{
				int side = base.roots.get(root);
				((TileEntityScaffold)root.getTileEntity(world)).setLoad(side, loadEach);
			}
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		BlockCoord c = new BlockCoord(x, y, z);
		BlockCoord below = c.copy().offset(0);
		int meta = c.getMeta(world);
		
		if (world.isBlockSolidOnSide(below.x, below.y, below.z, ForgeDirection.UP) && below.getBlockID(world) != blockID)
			return true;
		else
		{
			if (below.getTileEntity(world) instanceof TileEntityScaffold)
			{
				TileEntityScaffold s = (TileEntityScaffold) below.getTileEntity(world);
				System.out.println(s.isRoot);
				if (s.isRoot)
					return true;
			}
		}
		
		Map<BlockCoord, Integer> roots = new HashMap<BlockCoord, Integer>();
		for (BlockCoord t : c.getAdjacent())
		{
			TileEntity te = t.getTileEntity(world);
			if (te != null && te instanceof TileEntityScaffold)
			{
				if (((TileEntityScaffold)te).roots.size() > 0)
					roots.putAll(((TileEntityScaffold)te).roots);
				else
					roots.put(t, c.getDirectionFromSourceCoords(t.x, t.y, t.z).ordinal());
			}
		}
		
		for (BlockCoord root : roots.keySet())
		{
			if (root.getTileEntity(world) == null)
				roots.remove(root);
		}
		
		TileEntityScaffold s = (TileEntityScaffold) c.copy().offset(0).getTileEntity(world);
		if ((s == null || !s.isRoot) && (roots == null || roots.size() == 0))
			return false;
		
		for (BlockCoord root : roots.keySet())
		{
			int side = roots.get(root);
			switch (side)
			{
			case 2:
				if (((TileEntityScaffold)root.getTileEntity(world)).northLoad <= getDistance(meta))
					return true;
			case 3:
				if (((TileEntityScaffold)root.getTileEntity(world)).southLoad <= getDistance(meta))
					return true;
			case 4:
				if (((TileEntityScaffold)root.getTileEntity(world)).westLoad <= getDistance(meta))
					return true;
			case 5:
				if (((TileEntityScaffold)root.getTileEntity(world)).eastLoad <= getDistance(meta))
					return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int side)
	{
		if(!canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return (side == ForgeDirection.UP || side == ForgeDirection.DOWN) ? true : false;
	}
	
	@Override
	public Block getInnerBlock(int meta)
	{
		return null;
	}

	@Override
	public int getInnerMeta(int meta)
	{
		return 0;
	}

	@Override
	public Icon getRenderIcon(int meta)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityScaffold();
	}

}
