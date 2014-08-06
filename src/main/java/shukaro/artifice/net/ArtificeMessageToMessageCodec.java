package shukaro.artifice.net;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import shukaro.artifice.net.packets.ArtificePacket;
import shukaro.artifice.net.packets.ArtificePacketSneak;
import shukaro.artifice.net.packets.ArtificePacketTexture;

public class ArtificeMessageToMessageCodec extends FMLIndexedMessageToMessageCodec<ArtificePacket>
{
    public static final int SNEAKEVENT = 1;
    public static final int TEXTUREUPDATE = 2;

    public ArtificeMessageToMessageCodec()
    {
        addDiscriminator(SNEAKEVENT, ArtificePacketSneak.class);
        addDiscriminator(TEXTUREUPDATE, ArtificePacketTexture.class);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ArtificePacket msg, ByteBuf target) throws Exception
    {

    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, ArtificePacket msg)
    {

    }
}
