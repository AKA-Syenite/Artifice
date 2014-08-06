package shukaro.artifice.net.packets;

import io.netty.buffer.ByteBuf;

public class ArtificePacketTexture implements ArtificePacket
{
    public int x;
    public int y;
    public int z;

    public ArtificePacketTexture() {}

    public ArtificePacketTexture(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void readBytes(ByteBuf bytes)
    {
        this.x = bytes.readInt();
        this.y = bytes.readInt();
        this.z = bytes.readInt();
    }

    @Override
    public void writeBytes(ByteBuf bytes)
    {
        bytes.writeInt(this.x);
        bytes.writeInt(this.y);
        bytes.writeInt(this.z);
    }
}
