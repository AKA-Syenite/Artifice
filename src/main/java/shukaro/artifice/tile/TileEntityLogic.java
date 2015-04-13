package shukaro.artifice.tile;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLogic extends TileEntity
{
    public SideState[] sides = new SideState[6];
    public int power = 0;

    public enum SideState { DISABLED, OUTPUT, POSITIVE, NEGATIVE }

    public TileEntityLogic()
    {
        for (int i=0; i<6; i++)
            sides[i] = SideState.DISABLED;
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        boolean changed;
        int power = 0;
        if (meta == 0) // digital
        {
            boolean hasInput = false;
            for (int i=0; i<6; i++)
            {
                if (sides[i] == SideState.POSITIVE || sides[i] == SideState.NEGATIVE)
                {
                    hasInput = true;
                    break;
                }
            }
            boolean powered = hasInput;
            for (int i=0; i< 6; i++)
            {
                if (sides[i] == SideState.POSITIVE && getIndirectPowerFrom(i) == 0)
                    powered = false;
                else if (sides[i] == SideState.NEGATIVE && getIndirectPowerFrom(i) > 0)
                    powered = false;
            }
            power = powered ? 15 : 0;
        }
        else //analog
        {
            for (int i = 0; i < 6; i++)
            {
                if (sides[i] == SideState.POSITIVE)
                    power += getIndirectPowerFrom(i);
                if (sides[i] == SideState.NEGATIVE)
                    power -= getIndirectPowerFrom(i);
            }
            power = MathHelper.clampI(power, 0, 15);
        }
        changed = this.power != power;
        if (changed)
        {
            this.power = power;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
        }
    }

    private int getIndirectPowerFrom(int side)
    {
        ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[side];
        return worldObj.getIndirectPowerLevelTo(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.ordinal());
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        int[] sidesInt = tag.getIntArray("sides");
        for (int i=0; i<6; i++)
            this.sides[i] = SideState.values()[sidesInt[i]];
        this.power = tag.getInteger("power");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        int[] sidesInt = new int[6];
        for (int i=0; i<6; i++)
            sidesInt[i] = this.sides[i].ordinal();
        tag.setIntArray("sides", sidesInt);
        tag.setInteger("power", this.power);
    }
}
