package shukaro.artifice.item;

import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.IdMetaPair;

public class DispenserBehaviorSledge extends BehaviorDefaultDispenseItem
{
	private final BehaviorDefaultDispenseItem defaultDispense = new BehaviorDefaultDispenseItem();
	
	@Override
	public ItemStack dispenseStack(IBlockSource dispenser, ItemStack stack)
	{
		World world = dispenser.getWorld();
		Random rand = world.rand;
		IPosition pos = BlockDispenser.getIPositionFromBlockSource(dispenser);
		EnumFacing face = BlockDispenser.getFacing(dispenser.getBlockMetadata());
		BlockCoord target = new BlockCoord(dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt());
		target.offset(face.ordinal());
		IdMetaPair pair = new IdMetaPair(target.getBlockID(world), target.getMeta(world));
		if (ArtificeRegistry.getSledgeBlocks().containsKey(pair))
		{
			world.setBlockToAir(target.x, target.y, target.z);
			stack.setItemDamage(stack.getItemDamage() + 1);
			for (ItemStack drop : ArtificeRegistry.getSledgeBlocks().get(pair))
			{
				EntityItem item = new EntityItem(world, target.x + 0.5, target.y + 0.5, target.z + 0.5, drop.copy());
				double xVel = -0.1 + (0.1 - -0.1) * rand.nextDouble();
				double zVel = -0.1 + (0.1 - -0.1) * rand.nextDouble();
				item.setVelocity(xVel, 0.1, zVel);
				world.spawnEntityInWorld(item);
			}
		}
		else if (ArtificeRegistry.getWildSledgeBlocks().containsKey(pair))
		{
			world.setBlockToAir(target.x, target.y, target.z);
			stack.setItemDamage(stack.getItemDamage() + 1);
			for (ItemStack drop : ArtificeRegistry.getWildSledgeBlocks().get(pair))
			{
				EntityItem item = new EntityItem(world, target.x + 0.5, target.y + 0.5, target.z + 0.5, drop.copy());
				double xVel = -0.1 + (0.1 - -0.1) * rand.nextDouble();
				double zVel = -0.1 + (0.1 - -0.1) * rand.nextDouble();
				item.setVelocity(xVel, 0.1, zVel);
				world.spawnEntityInWorld(item);
			}
		}
		return stack;
	}
	
	@Override
	protected void playDispenseSound(IBlockSource dispenser)
	{
		dispenser.getWorld().playAuxSFX(1002, dispenser.getXInt(), dispenser.getYInt(), dispenser.getZInt(), 0);
	}
}
