package shukaro.artifice.net;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public abstract class MessageHandlerBase {
	public abstract void onMessage(Packet packet, INetHandler handler, EntityPlayer player, int command) throws IOException;
	
	public Packet onMessage(Packet packet, INetHandler handler, EntityPlayer player) {
		try {
			onMessage(packet, handler, player, packet.readUnsignedShort());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return packet;
	}

}
