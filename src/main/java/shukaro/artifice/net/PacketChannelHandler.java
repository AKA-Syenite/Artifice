package shukaro.artifice.net;

import shukaro.artifice.ArtificeCore;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketChannelHandler extends FMLIndexedMessageToMessageCodec<Packet> {
	private final MessageHandlerBase handlerClient, handlerServer;
	
    public PacketChannelHandler(MessageHandlerBase client, MessageHandlerBase server) {
    	this.handlerClient = client;
    	this.handlerServer = server;
    	addDiscriminator(0, Packet.class);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, Packet packet, ByteBuf data) throws Exception {
        packet.toBytes(data);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data, Packet packet) {
        packet.fromBytes(data);
        INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        ArtificeCore.proxy.handlePacket(handlerClient, handlerServer, packet, netHandler);
    }
}
