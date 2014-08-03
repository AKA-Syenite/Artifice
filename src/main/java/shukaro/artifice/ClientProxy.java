package shukaro.artifice;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraftforge.common.MinecraftForge;
import shukaro.artifice.event.ArtificeClientEventHandler;
import shukaro.artifice.event.ArtificeClientTickHandler;
import shukaro.artifice.net.MessageHandlerBase;
import shukaro.artifice.net.Packet;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import shukaro.artifice.render.RockRenderer;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;

import java.util.concurrent.ConcurrentHashMap;

public class ClientProxy extends CommonProxy
{
    public void init()
    {
        MinecraftForge.EVENT_BUS.register(new ArtificeClientEventHandler());
        if (ArtificeConfig.enableBoxes.getBoolean(true) || ArtificeConfig.enableFrames.getBoolean(true))
            FMLCommonHandler.instance().bus().register(new ArtificeClientTickHandler());

        ArtificeConfig.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.lotusRenderID, new LotusRenderer());
        ArtificeConfig.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.frameRenderID, new FrameRenderer());
        ArtificeConfig.rockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeConfig.rockRenderID, new RockRenderer());

        ArtificeCore.textureCache = new ConcurrentHashMap<ChunkCoord, ConcurrentHashMap<BlockCoord, int[]>>();
    }

    @Override
    public void handlePacket(MessageHandlerBase client, MessageHandlerBase server, Packet packet, INetHandler handler)
    {
        switch (FMLCommonHandler.instance().getEffectiveSide())
        {
            case CLIENT:
                if (client != null)
                    client.onMessage(packet, handler, (EntityPlayer) Minecraft.getMinecraft().thePlayer);
                break;
            case SERVER:
                super.handlePacket(client, server, packet, handler);
                break;
        }
    }
}
