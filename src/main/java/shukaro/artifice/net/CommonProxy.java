package shukaro.artifice.net;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import shukaro.artifice.event.WorldTicker;

public class CommonProxy
{
    public static void init()
    {
        TickRegistry.registerTickHandler(new WorldTicker(), Side.SERVER);
    }
}
