package shukaro.artifice.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import pl.asie.lib.network.MessageHandlerBase;
import pl.asie.lib.network.Packet;

public class ServerPacketHandler extends MessageHandlerBase
{
	@Override
	public void onMessage(Packet packet, INetHandler handler, EntityPlayer player,
			int command) throws IOException {
		switch(command) {
		case Packets.SNEAKEVENT: {
			int entityId = packet.readInt();
			boolean doAdd = packet.readByte() != 0;
			if(doAdd) {
				PlayerTracking.sneaks.add(entityId);
			} else {
				PlayerTracking.sneaks.remove(entityId);
			}
		} return;
		}
	}
}
