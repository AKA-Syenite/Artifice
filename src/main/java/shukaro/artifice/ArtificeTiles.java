package shukaro.artifice;

import cpw.mods.fml.common.registry.GameRegistry;
import shukaro.artifice.tile.TileEntityAttuned;

public class ArtificeTiles
{
    public static void initTiles()
    {
        GameRegistry.registerTileEntity(TileEntityAttuned.class, "tileArtificeAttuned");
    }
}
