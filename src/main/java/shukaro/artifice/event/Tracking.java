package shukaro.artifice.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.tile.TileEntityAttuned;

import java.util.ArrayList;
import java.util.List;

public class Tracking
{
    public static List<Integer> sneaks = new ArrayList<Integer>();

    public static int getHighestFrequencyPower(String frequency)
    {
        int i = 0;
        if (DimensionManager.getWorlds() == null)
            return i;
        for (World world : DimensionManager.getWorlds())
        {
            if (world == null)
                continue;
            for (Object o : world.loadedTileEntityList)
            {
                if (o instanceof TileEntityAttuned)
                {
                    TileEntityAttuned tea = (TileEntityAttuned)o;
                    if (tea.frequency.equals(frequency) && tea.getWorldObj().getBlock(tea.xCoord, tea.yCoord, tea.zCoord) == ArtificeBlocks.blockAttunedRedstoneReceiver)
                        i = Math.max(tea.power, i);
                }
            }
        }
        return i;
    }

    public static void updateFrequency(String frequency)
    {
        if (DimensionManager.getWorlds() == null)
            return;
        for (World world : DimensionManager.getWorlds())
        {
            if (world == null)
                continue;
            for (Object o : world.loadedTileEntityList)
            {
                if (o instanceof TileEntityAttuned)
                {
                    TileEntityAttuned tea = (TileEntityAttuned)o;
                    if (tea.frequency.equals(frequency))
                        tea.updateEntity();
                }
            }
        }
    }
}
