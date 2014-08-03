package shukaro.artifice.net;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;

import java.io.IOException;

public class ClientPacketHandler extends MessageHandlerBase
{
    @Override
    public void onMessage(Packet packet, INetHandler handler, EntityPlayer player, int command) throws IOException
    {
        switch (command)
        {
            case Packets.TEXTUREUPDATE:
            {
                BlockCoord coord = new BlockCoord(packet.readInt(), packet.readInt(), packet.readInt());
                TextureHandler.updateTexture(coord);
                for (BlockCoord n : coord.getNearby())
                    TextureHandler.updateTexture(n);
            }
            return;
        }
    }
}
