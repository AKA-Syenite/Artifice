package shukaro.artifice.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

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

            BlockCoord coord = new BlockCoord((Integer)packetReadout[0], (Integer)packetReadout[1], (Integer)packetReadout[2]);

            TextureHandler.updateTexture(coord);
            for (BlockCoord n : coord.getNearby())
                TextureHandler.updateTexture(n);
        }
	}
}
