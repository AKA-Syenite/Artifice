package shukaro.artifice.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.util.MiscUtils;

import java.util.List;

public class TileEntityHeatingCoil extends TileEntity implements IEnergyReceiver, IHeatProvider
{
    private EnergyStorage energyStorage = new EnergyStorage(1600);

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        if (energyStorage.getEnergyStored() > energyStorage.getMaxEnergyStored()/2)
            energyStorage.receiveEnergy(MiscUtils.provideEnergyToNeighbors(energyStorage.extractEnergy(ArtificeConfig.heatingCoilRate, false), worldObj, xCoord, yCoord, zCoord), false);
        IHeatReceiver target = MiscUtils.getHeatReceiverNeighbor(worldObj, xCoord, yCoord, zCoord);
        if (target != null && energyStorage.getEnergyStored() > 0 && target.getHeat() < target.getMaxHeat())
        {
            int sent = target.receiveHeat(energyStorage.extractEnergy(ArtificeConfig.heatingCoilRate, false));
            energyStorage.receiveEnergy(ArtificeConfig.heatingCoilRate - sent, false);
        }
        if (energyStorage.getEnergyStored() > 0 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) != 1)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
        else if (energyStorage.getEnergyStored() == 0 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) != 0)
            worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
        energyStorage.extractEnergy(ArtificeConfig.coilLoss, false);
        if (energyStorage.getEnergyStored() > 0)
            doFire();
    }

    private void doFire()
    {
        AxisAlignedBB around = worldObj.getBlock(xCoord, yCoord, zCoord).getCollisionBoundingBoxFromPool(worldObj, xCoord, yCoord, zCoord).expand(0.1D, 0.1D, 0.1D);
        List<EntityLivingBase> entities = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, around);
        for (EntityLivingBase entity : entities)
            entity.setFire(1);
    }

    @Override
    public int receiveEnergy(ForgeDirection forgeDirection, int i, boolean b)
    {
        return energyStorage.receiveEnergy(i, b);
    }

    @Override
    public int getEnergyStored(ForgeDirection forgeDirection)
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection forgeDirection)
    {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection)
    {
        return true;
    }

    @Override
    public int extractHeat(int amount)
    {
        return energyStorage.extractEnergy(amount, false);
    }

    @Override
    public int getHeat()
    {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxHeat()
    {
        return energyStorage.getMaxEnergyStored();
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
        this.energyStorage.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        this.energyStorage.writeToNBT(tag);
    }
}
