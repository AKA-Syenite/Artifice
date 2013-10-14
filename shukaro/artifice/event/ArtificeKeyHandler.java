package shukaro.artifice.event;

import java.util.EnumSet;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.net.Packets;
import shukaro.artifice.net.PlayerTracking;
import shukaro.artifice.util.PacketWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ArtificeKeyHandler extends KeyHandler
{
	public ArtificeKeyHandler(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel()
	{
		return "ArtificeKey";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		Integer playerID = Minecraft.getMinecraft().thePlayer.entityId;
		PlayerTracking.sneaks.add(playerID);
		PacketDispatcher.sendPacketToServer(PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.SNEAKEVENT, new Object[] {playerID, true}));
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		Integer playerID = Minecraft.getMinecraft().thePlayer.entityId;
		PlayerTracking.sneaks.remove(playerID);
		PacketDispatcher.sendPacketToServer(PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.SNEAKEVENT, new Object[] {playerID, false}));
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

}
