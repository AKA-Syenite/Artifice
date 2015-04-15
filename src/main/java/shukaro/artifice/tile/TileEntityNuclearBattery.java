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

public class TileEntityNuclearBattery extends TileEntity implements IEnergyProvider
{
    private EnergyStorage charge = new EnergyStorage(ArtificeConfig.nuclearBatteryCapacity, 0, ArtificeConfig.nuclearBatteryRate);
    private int genned = 0;

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        this.genned = 0;
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

    @Override
    public int extractEnergy(ForgeDirection dir, int amount, boolean simulate)
    {
        if (genned >= charge.getMaxExtract())
            return 0;
        int x = charge.extractEnergy(amount, simulate);
        if (!simulate)
            genned += x;
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