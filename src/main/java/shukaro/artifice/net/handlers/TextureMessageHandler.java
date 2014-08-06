package shukaro.artifice.net.handlers;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import shukaro.artifice.net.packets.ArtificePacketTexture;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;

@ChannelHandler.Sharable
public class TextureMessageHandler extends SimpleChannelInboundHandler<ArtificePacketTexture>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ArtificePacketTexture msg) throws Exception
    {
        INetHandler handler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();

        if (handler instanceof NetHandlerPlayClient)
        {
            BlockCoord coord = new BlockCoord(msg.x, msg.y, msg.z);
            TextureHandler.updateTexture(coord);
            for (BlockCoord n : coord.getNearby())
                TextureHandler.updateTexture(n);
        }
    }
}
