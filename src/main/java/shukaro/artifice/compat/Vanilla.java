package shukaro.artifice.compat;

import net.minecraft.init.Blocks;
import shukaro.artifice.ArtificeRegistry;

public class Vanilla implements ICompat
{
    public String getModID()
    {
        return null;
    }

    public void load()
    {
        ArtificeRegistry.registerDimensionBlacklist(1);
        ArtificeRegistry.registerDimensionBlacklist(-1);

        ArtificeRegistry.registerStoneType(Blocks.stone, 0);

        ArtificeRegistry.registerWorldTypeBlacklist("flat");
    }
}
