package shukaro.artifice.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
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
        if (MinecraftServer.getServer() == null || MinecraftServer.getServer().worldServers == null)
            return i;
        for (World world : MinecraftServer.getServer().worldServers)
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
        if (MinecraftServer.getServer() == null || MinecraftServer.getServer().worldServers == null)
            return;
        for (World world : MinecraftServer.getServer().worldServers)
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
