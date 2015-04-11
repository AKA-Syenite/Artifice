package shukaro.artifice.compat;

import shukaro.artifice.ArtificeCore;

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
            ArtificeCore.logger.info("Chisel compat initialized");
        }
        catch (Throwable ex)
        {
            ArtificeCore.logger.warn("Couldn't load Chisel compat");
            ex.printStackTrace();
        }
    }
}
