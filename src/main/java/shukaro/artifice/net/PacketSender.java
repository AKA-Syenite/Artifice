package shukaro.artifice.net;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pl.asie.lib.network.Packet;
import shukaro.artifice.ArtificeCore;
import net.minecraft.world.World;

public class PacketSender {
	public static void sendTextureUpdatePacket(World world, int x, int y, int z) {
		try {
			Packet packet = ArtificeCore.packet.create(Packets.TEXTUREUPDATE)
					.writeInt(x).writeInt(y).writeInt(z);
			ArtificeCore.packet.sendToAllAround(packet, new TargetPoint(world.provider.dimensionId, x, y, z, 192));
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	@SideOnly(Side.CLIENT)
	public static void sendSneakEvent(int playerID, boolean doAdd) {
		try {
			Packet packet = ArtificeCore.packet.create(Packets.SNEAKEVENT)
					.writeInt(playerID).writeByte((byte)(doAdd ? 1 : 0));
			ArtificeCore.packet.sendToServer(packet);
		} catch(Exception e) { e.printStackTrace(); }
	}
}
