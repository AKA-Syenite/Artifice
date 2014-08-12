package shukaro.artifice.net;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ClientProxy;
import shukaro.artifice.net.packets.ArtificePacketSneak;

public class PacketDispatcher
{
    @SideOnly(Side.CLIENT)
    public static void sendSneakEvent(int playerID)
    {
        try
        {
            ClientProxy.artificeChannel.get(Side.CLIENT).writeOutbound(new ArtificePacketSneak(playerID));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
