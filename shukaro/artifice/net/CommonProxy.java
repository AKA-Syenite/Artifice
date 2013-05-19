package shukaro.artifice.net;

import shukaro.artifice.event.WorldTicker;
import shukaro.artifice.multiblock.erogenousbeef.MultiblockServerTickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy
{
    public static void init()
    {
        TickRegistry.registerTickHandler(new WorldTicker(), Side.SERVER);
        TickRegistry.registerTickHandler(new MultiblockServerTickHandler(), Side.SERVER);
    }
}
