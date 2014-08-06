package shukaro.artifice.net.packets;

import io.netty.buffer.ByteBuf;

public class ArtificePacketSneak implements ArtificePacket
{
    public int playerID;

    public ArtificePacketSneak() {}

    public ArtificePacketSneak(int playerID)
    {
        this.playerID = playerID;
    }

    @Override
    public void readBytes(ByteBuf bytes)
    {
        this.playerID = bytes.readInt();
    }

    @Override
    public void writeBytes(ByteBuf bytes)
    {
        bytes.writeInt(this.playerID);
    }
}
