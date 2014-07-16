package shukaro.artifice;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
	
public class CommonProxy {
	public void init() {}
	
	public void handlePacket(MessageHandlerBase client, MessageHandlerBase server, Packet packet, INetHandler handler) {
	    if(server != null)
	    	server.onMessage(packet, handler, ((NetHandlerPlayServer) handler).playerEntity);
	}

}
