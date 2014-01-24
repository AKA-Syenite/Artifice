package shukaro.artifice.net;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.event.KeyTicker;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import shukaro.artifice.util.BlockCoord;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
    public static void init()
    {
        ArtificeConfig.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.lotusRenderID, new LotusRenderer());
        ArtificeConfig.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.frameRenderID, new FrameRenderer());
        
        if (ArtificeConfig.enableBoxes.getBoolean(true))
	        TickRegistry.registerTickHandler(new KeyTicker(), Side.CLIENT);
        
        ArtificeCore.textureCache = new HashMap<BlockCoord, int[]>();
    }
}
