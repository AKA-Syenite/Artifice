package shukaro.artifice.net;

import shukaro.artifice.event.WorldTicker;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy
{
	public WorldTicker worldTicker = new WorldTicker();
	
	public void registerTickers()
	{
		TickRegistry.registerTickHandler(this.worldTicker, Side.SERVER);
	}
}
