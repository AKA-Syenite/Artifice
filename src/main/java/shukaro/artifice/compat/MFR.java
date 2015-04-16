package shukaro.artifice.compat;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;

public class MFR implements ICompat
{
    @Override
    public String getModID()
    {
        return "MineFactoryReloaded";
    }

    @Override
    public void load()
    {
        try
        {
            FMLInterModComms.sendMessage(getModID(), "registerHarvestable_Standard", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockFlora));
            if (ArtificeConfig.uraniumMFRLaser)
            {
                ItemStack uranium = new ItemStack(ArtificeBlocks.blockUranium, 1, 0);
                NBTTagCompound uraniumTag = uranium.writeToNBT(new NBTTagCompound());
                uraniumTag.setInteger("value", 60);
                FMLInterModComms.sendMessage(getModID(), "registerLaserOre", uraniumTag);
            }
            if (ArtificeConfig.enderOreMFRLaser)
            {
                ItemStack ender = new ItemStack(ArtificeBlocks.blockEnderOre, 1, 0);
                NBTTagCompound enderTag = ender.writeToNBT(new NBTTagCompound());
                enderTag.setInteger("value", 80);
                FMLInterModComms.sendMessage(getModID(), "registerLaserOre", enderTag);
            }
            if (ArtificeConfig.sulfurMFRLaser)
            {
                ItemStack sulfur = new ItemStack(ArtificeBlocks.blockSulfur, 1, 0);
                NBTTagCompound sulfurTag = sulfur.writeToNBT(new NBTTagCompound());
                sulfurTag.setInteger("value", 90);
                FMLInterModComms.sendMessage(getModID(), "registerLaserOre", sulfurTag);
            }
            if (ArtificeConfig.niterMFRLaser)
            {
                ItemStack niter = new ItemStack(ArtificeBlocks.blockNiter, 1, 0);
                NBTTagCompound niterTag = niter.writeToNBT(new NBTTagCompound());
                niterTag.setInteger("value", 90);
                FMLInterModComms.sendMessage(getModID(), "registerLaserOre", niterTag);
            }
            ArtificeCore.logger.info("MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
