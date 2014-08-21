package shukaro.artifice.compat;

import net.minecraft.block.Block;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeRegistry;

public class Chisel implements ICompat
{
    @Override
    public String getModID()
    {
        return "chisel";
    }

    @Override
    public void load()
    {
        try
        {
            Class<?> blockClass = Class.forName("info.jbcs.minecraft.chisel.ChiselBlocks");
            Block block = (Block) blockClass.getField("blockMarble").get(null);
            ArtificeRegistry.registerMarbleType(block, 0);
            ArtificeCore.logger.info("Chisel compat initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't load Chisel compat");
            ex.printStackTrace();
        }
    }
}
