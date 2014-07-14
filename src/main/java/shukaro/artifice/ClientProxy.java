package shukaro.artifice;

import java.util.concurrent.ConcurrentHashMap;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.MinecraftForge;
import shukaro.artifice.event.ArtificeBoxEventHandler;
import shukaro.artifice.event.ArtificeClientEventHandler;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

public class ClientProxy extends CommonProxy {
	public void init() {
    	MinecraftForge.EVENT_BUS.register(new ArtificeClientEventHandler());
    	if(ArtificeConfig.enableBoxes.getBoolean(true))
    		MinecraftForge.EVENT_BUS.register(new ArtificeBoxEventHandler());
    	
        ArtificeConfig.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.lotusRenderID, new LotusRenderer());
        ArtificeConfig.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.frameRenderID, new FrameRenderer());
        
    	ArtificeCore.textureCache = new ConcurrentHashMap<ChunkCoord, ConcurrentHashMap<BlockCoord, int[]>>();
	}
}
