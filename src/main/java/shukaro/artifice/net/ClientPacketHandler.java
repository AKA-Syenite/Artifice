package shukaro.artifice.net;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.PacketWrapper;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ClientPacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
        int packetType = PacketWrapper.readPacketID(data);

        if (packetType == Packets.TEXTUREUPDATE)
        {
            Class[] decodeAs = {Integer.class, Integer.class, Integer.class};
            Object[] packetReadout = PacketWrapper.readPacketData(data, decodeAs);

            BlockCoord coord = new BlockCoord((Integer) packetReadout[0], (Integer) packetReadout[1], (Integer) packetReadout[2]);

            TextureHandler.updateTexture(coord);
            for (BlockCoord n : coord.getNearby())
                TextureHandler.updateTexture(n);
        }
    }
}
