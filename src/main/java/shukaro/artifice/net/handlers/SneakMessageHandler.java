package shukaro.artifice.net.handlers;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import shukaro.artifice.net.PlayerTracking;
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
            if (PlayerTracking.sneaks.contains(msg.playerID))
                PlayerTracking.sneaks.remove(Integer.valueOf(msg.playerID));
            else
                PlayerTracking.sneaks.add(msg.playerID);
        }
    }
}
