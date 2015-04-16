package shukaro.artifice.compat;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeFluids;

public class Buildcraft implements ICompat
{
    public String getModID()
    {
        return "BuildCraft|Core";
    }

    public void load()
    {
        try
        {
            for (int i = 0; i < ArtificeConfig.tiers.length; i++)
                FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockReinforced) + "@" + i);
            for (int i = 0; i < ArtificeConfig.rocks.length; i++)
            {
                FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockBasalt) + "@" + i);
                FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockMarble) + "@" + i);
            }
            for (int i = 0; i < ArtificeBlocks.blockLimestones.length; i++)
            {
                for (int j = 0; j < ArtificeConfig.rocks.length; j++)
                    FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockLimestones[i]) + "@" + j);
            }
            for (int i = 0; i < ArtificeBlocks.blockLamps.length; i++)
            {
                FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockLamps[i]) + "@" + 0);
                FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockLampsInverted[i]) + "@" + 0);
            }
            FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockSteel) + "@0");
            FMLInterModComms.sendMessage(getModID(), "add-facade", Block.blockRegistry.getNameForObject(ArtificeBlocks.blockUranium) + "@1");

            FluidStack oil = new FluidStack(ArtificeFluids.fluidOil, 1);
            FluidStack fuel = new FluidStack(ArtificeFluids.fluidFuel, 1);

            NBTTagCompound recipe = new NBTTagCompound();
            NBTTagCompound input = new NBTTagCompound();
            NBTTagCompound output = new NBTTagCompound();

            oil.writeToNBT(input);
            fuel.writeToNBT(output);

            recipe.setTag("input", input);
            recipe.setTag("output", output);
            recipe.setInteger("energy", 12);
            recipe.setInteger("delay", 1);

            FMLInterModComms.sendMessage(getModID(), "add-refinery-recipe", recipe);

            ArtificeCore.logger.info("BuildCraft Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize BuildCraft compat");
            ex.printStackTrace();
        }
    }
}
