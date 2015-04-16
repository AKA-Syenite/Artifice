package shukaro.artifice.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;

public class HeatStorage implements IHeatProvider, IHeatReceiver
{
    private int heat;
    private int maxHeat;

    public HeatStorage(int maxHeat)
    {
        this.maxHeat = maxHeat;
        this.heat = 0;
    }

    public int getHeat() { return this.heat; }

    public int getMaxHeat() { return this.maxHeat; }

    @Override
    public int extractHeat(int amount)
    {
        int toExtract = Math.min(this.heat, amount);
        this.heat -= toExtract;
        return toExtract;
    }

    @Override
    public int receiveHeat(int amount)
    {
        int toReceive = Math.min(this.maxHeat - this.heat, amount);
        this.heat += toReceive;
        return toReceive;
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        this.heat = tag.getInteger("Heat");
        if (this.heat > this.maxHeat)
            this.heat = this.maxHeat;
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        if (this.heat < 0)
            this.heat = 0;
        tag.setInteger("Heat", this.heat);
    }
}
