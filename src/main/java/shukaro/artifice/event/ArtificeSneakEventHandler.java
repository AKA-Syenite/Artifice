package shukaro.artifice.event;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.PacketSender;
import shukaro.artifice.net.PlayerTracking;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ArtificeSneakEventHandler
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void keyTicker(ClientTickEvent cte)
    {
    	if(cte.phase != TickEvent.Phase.START) return;
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null)
            return;
        Integer playerID = mc.thePlayer.getEntityId();
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && !PlayerTracking.sneaks.contains(playerID))
        {
            ArtificeCore.logger.info("sneaking");
            PlayerTracking.sneaks.add(playerID);
            PacketSender.sendSneakEvent(playerID, true);
        }
        else if (!Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && PlayerTracking.sneaks.contains(playerID))
        {
            ArtificeCore.logger.info("not sneaking");
            PlayerTracking.sneaks.remove(Integer.valueOf(playerID));
            PacketSender.sendSneakEvent(playerID, false);
        }
    }
}
