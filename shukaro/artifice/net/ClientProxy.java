package shukaro.artifice.net;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.FrameRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public static void init()
    {
        ArtificeCore.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeCore.frameRenderID, new FrameRenderer());
    }
}
