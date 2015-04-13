package shukaro.artifice.net;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.handlers.SneakMessageHandler;

import java.util.EnumMap;

public class CommonProxy
{
    public static EnumMap<Side, FMLEmbeddedChannel> artificeChannel;

    public void init()
    {
        artificeChannel = NetworkRegistry.INSTANCE.newChannel(ArtificeCore.modChannel, new ArtificeMessageToMessageCodec(),
                new SneakMessageHandler());
    }
}
