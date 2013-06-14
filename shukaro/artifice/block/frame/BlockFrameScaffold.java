package shukaro.artifice.block.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFrameScaffold extends BlockFrame
{
	private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN };
	
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
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];
		if (held != null && held.itemID == this.blockID && held.getItemDamage() == world.getBlockMetadata(x, y, z))
		{
			while (world.getBlockId(x, y, z) == this.blockID)
				y++;
			if (checkStay(world, x, y, z, held.getItemDamage()))
			{
				world.setBlock(x, y, z, this.blockID, held.getItemDamage(), 3);
				if (!player.capabilities.isCreativeMode)
				{
					held.stackSize--;
					if (held.stackSize <= 0)
						held = null;
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
	
	public boolean checkStay(World world, int x, int y, int z, int meta)
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
	
	public boolean isRooted(World world, int x, int y, int z, int meta)
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
		return null;
	}
}
