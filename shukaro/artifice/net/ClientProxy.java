package shukaro.artifice.net;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public static void init()
    {
        ArtificeCore.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        ArtificeCore.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeCore.frameRenderID, new FrameRenderer());
        RenderingRegistry.registerBlockHandler(ArtificeCore.lotusRenderID, new LotusRenderer());
    }
}
