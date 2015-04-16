package shukaro.artifice.compat;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
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
            ArtificeCore.logger.info("MineFactoryReloaded Compat Initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't initialize MineFactoryReloaded compat");
            ex.printStackTrace();
        }
    }
}
