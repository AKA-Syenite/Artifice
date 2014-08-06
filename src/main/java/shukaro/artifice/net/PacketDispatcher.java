package shukaro.artifice.net;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import shukaro.artifice.ClientProxy;
import shukaro.artifice.CommonProxy;
import shukaro.artifice.net.packets.ArtificePacketSneak;
import shukaro.artifice.net.packets.ArtificePacketTexture;

public class PacketDispatcher
{
    public static void sendTextureUpdatePacket(World world, int x, int y, int z)
    {
        try
        {
            FMLEmbeddedChannel channel = CommonProxy.artificeChannel.get(Side.SERVER);
            ArtificePacketTexture packet = new ArtificePacketTexture(x, y, z);

            channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
            channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new TargetPoint(world.provider.dimensionId, x, y, z, 192));

           channel.writeOutbound(packet);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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
