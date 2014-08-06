package shukaro.artifice.net.packets;

import io.netty.buffer.ByteBuf;

public interface ArtificePacket
{
    public void readBytes(ByteBuf bytes);

    public void writeBytes(ByteBuf bytes);
}
