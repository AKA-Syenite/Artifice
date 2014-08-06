package shukaro.artifice;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import shukaro.artifice.net.ArtificeMessageToMessageCodec;
import shukaro.artifice.net.handlers.SneakMessageHandler;
import shukaro.artifice.net.handlers.TextureMessageHandler;

import java.util.EnumMap;

public class CommonProxy
{
    public static EnumMap<Side, FMLEmbeddedChannel> artificeChannel;

    public void init()
    {
        artificeChannel = NetworkRegistry.INSTANCE.newChannel(ArtificeCore.modChannel, new ArtificeMessageToMessageCodec(),
                new SneakMessageHandler(), new TextureMessageHandler());
    }
}
