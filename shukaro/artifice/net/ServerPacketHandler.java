package shukaro.artifice.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import shukaro.artifice.util.PacketWrapper;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		int packetType = PacketWrapper.readPacketID(data);
		
		if (packetType == Packets.SNEAKEVENT)
		{
			Class[] decodeAs = {Integer.class, Boolean.class};
			Object[] packetReadout = PacketWrapper.readPacketData(data, decodeAs);
			
			if ((Boolean)packetReadout[1])
				PlayerTracking.sneaks.add((Integer)packetReadout[0]);
			else
				PlayerTracking.sneaks.remove((Integer)packetReadout[0]);
		}
	}
}
