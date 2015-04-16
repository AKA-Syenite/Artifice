package shukaro.artifice.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.util.MiscUtils;

public class TileEntityNuclearBattery extends TileEntity implements IEnergyProvider
{
    private EnergyStorage charge = new EnergyStorage(ArtificeConfig.nuclearBatteryCapacity);
    private int tickGen = 0;

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        this.tickGen = 0;
        int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        int power = charge.getEnergyStored();
        if (power == 0 && meta != 3)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 3);
        else if (power > 0 && power <= (ArtificeConfig.nuclearBatteryCapacity * 0.4) && meta != 2)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 3);
        else if (power > (ArtificeConfig.nuclearBatteryCapacity * 0.4) && power <= (ArtificeConfig.nuclearBatteryCapacity * 0.7) && meta != 1)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
        else if (power > (ArtificeConfig.nuclearBatteryCapacity * 0.7) && meta != 0)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
        charge.receiveEnergy(MiscUtils.provideEnergyToNeighbors(charge.extractEnergy(ArtificeConfig.nuclearBatteryRate, false), worldObj, xCoord, yCoord, zCoord), false);
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
        this.charge.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        this.charge.writeToNBT(tag);
    }

    public void setEnergy(int amount)
    {
        this.charge.setEnergyStored(amount);
    }

    public int getMaxRate()
    {
        float ratio = charge.getEnergyStored() / (float)charge.getMaxEnergyStored();
        return (int)Math.ceil(ArtificeConfig.nuclearBatteryRate * ratio);
    }

    @Override
    public int extractEnergy(ForgeDirection dir, int amount, boolean simulate)
    {
        int maxRate = this.getMaxRate();
        if (tickGen >= maxRate)
            return 0;
        if (amount > maxRate)
            amount = maxRate;
        int x = charge.extractEnergy(amount, simulate);
        if (!simulate)
            tickGen += x;
        return x;
    }

    @Override
    public int getEnergyStored(ForgeDirection dir)
    {
        return charge.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection dir)
    {
        return charge.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection dir)
    {
        return true;
    }
}
