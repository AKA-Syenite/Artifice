package shukaro.artifice.net.handlers;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import shukaro.artifice.event.Tracking;
import shukaro.artifice.net.packets.ArtificePacketSneak;

@ChannelHandler.Sharable
public class SneakMessageHandler extends SimpleChannelInboundHandler<ArtificePacketSneak>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ArtificePacketSneak msg) throws Exception
    {
        INetHandler handler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();

        if (handler instanceof NetHandlerPlayServer)
        {
            if (Tracking.sneaks.contains(msg.playerID))
                Tracking.sneaks.remove(Integer.valueOf(msg.playerID));
            else
                Tracking.sneaks.add(msg.playerID);
        }
    }
}
