package shukaro.artifice.event;

import java.util.EnumSet;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.util.BlockCoord;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class ScaffoldTicker implements IScheduledTickHandler
{
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        if(!(tickData[0] instanceof EntityPlayerSP))
        {
            return;
        }
        EntityPlayerSP player = (EntityPlayerSP)tickData[0];
        int frontX = MathHelper.floor_double(player.posX + player.getLookVec().xCoord);
        int frontY = MathHelper.floor_double(player.boundingBox.minY);
        int frontZ = MathHelper.floor_double(player.posZ + player.getLookVec().zCoord);

        int blockId = player.worldObj.getBlockId(frontX, frontY, frontZ);
        if(blockId == ArtificeBlocks.blockScaffold.blockID)
        {
            if(player.movementInput.moveForward > 0)
            {
                player.motionY = 0.10;
            }
            else if(player.isSneaking())
            {
                player.motionY = 0.0D;
            }
            else
            {
                player.motionY = -0.15D;
            }
        }
    }

    private int getLookDirection(EntityPlayerSP player)
    {
        int yaw = (int) player.rotationYaw;
        
        if (yaw < 0)
            yaw += 360;
        
        yaw += 22;
        yaw %= 360;
        
        int facing = yaw/45;
        
        return facing;
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel()
    {
        return "Artifice.Scaffold";
    }

    @Override
    public int nextTickSpacing()
    {
        return 0;
    }
}
