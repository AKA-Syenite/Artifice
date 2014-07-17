package shukaro.artifice;

import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import shukaro.artifice.net.MessageHandlerBase;
import shukaro.artifice.net.Packet;

public class CommonProxy
{
    public void init()
    {
    }

    public void handlePacket(MessageHandlerBase client, MessageHandlerBase server, Packet packet, INetHandler handler)
    {
        if (server != null)
            server.onMessage(packet, handler, ((NetHandlerPlayServer) handler).playerEntity);
    }

}
