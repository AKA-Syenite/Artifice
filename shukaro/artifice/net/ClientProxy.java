package shukaro.artifice.net;

import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.event.ScaffoldTicker;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
    public static void init()
    {
        ArtificeCore.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        ArtificeCore.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeCore.frameRenderID, new FrameRenderer());
        RenderingRegistry.registerBlockHandler(ArtificeCore.lotusRenderID, new LotusRenderer());
        
        if (ArtificeConfig.enableFrames.getBoolean(true))
        	TickRegistry.registerTickHandler(new ScaffoldTicker(), Side.CLIENT);
    }
}
