package shukaro.artifice.event;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.Packets;
import shukaro.artifice.net.PlayerTracking;
import shukaro.artifice.util.PacketWrapper;

import java.util.EnumSet;

public class KeyTicker implements ITickHandler
{
    @Override
    public String getLabel()
    {
        return "ArtificeKeys";
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null)
            return;
        Integer playerID = mc.thePlayer.entityId;

        if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode) && !PlayerTracking.sneaks.contains(playerID))
        {
            PlayerTracking.sneaks.add(playerID);
            PacketDispatcher.sendPacketToServer(PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.SNEAKEVENT, new Object[]{playerID, true}));
        }
        else if (!Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode) && PlayerTracking.sneaks.contains(playerID))
        {
            PlayerTracking.sneaks.remove(playerID);
            PacketDispatcher.sendPacketToServer(PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.SNEAKEVENT, new Object[]{playerID, false}));
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

}
