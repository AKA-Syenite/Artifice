package shukaro.artifice.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.event.Tracking;

public class TileEntityAttuned extends TileEntity
{
    public String frequency = "";
    public int power = 0;

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        if (frequency.length() > 0)
        {
            int newPower = this.getPower();
            if (this.power != newPower)
            {
                this.power = newPower;
                if (worldObj.getBlock(xCoord, yCoord, zCoord) == ArtificeBlocks.blockAttunedRedstoneTransmitter)
                {
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
                }
            }
        }
    }

    public int getPower()
    {
        if (worldObj.getBlock(xCoord, yCoord, zCoord) == ArtificeBlocks.blockAttunedRedstoneReceiver)
            return worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
        else
            return Tracking.getHighestFrequencyPower(frequency);
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
        this.frequency = tag.getString("frequency");
        this.power = tag.getInteger("power");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setString("frequency", this.frequency);
        tag.setInteger("power", this.power);
    }
}
