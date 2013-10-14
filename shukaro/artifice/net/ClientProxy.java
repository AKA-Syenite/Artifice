package shukaro.artifice.net;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.event.ArtificeKeyHandler;
import shukaro.artifice.event.ScaffoldTicker;
import shukaro.artifice.render.FrameRenderer;
import shukaro.artifice.render.LotusRenderer;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
    public static void init()
    {
        ArtificeCore.frameRenderID = RenderingRegistry.getNextAvailableRenderId();
        ArtificeCore.lotusRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(ArtificeCore.frameRenderID, new FrameRenderer());
        RenderingRegistry.registerBlockHandler(ArtificeCore.lotusRenderID, new LotusRenderer());
        
        if (ArtificeConfig.enableBoxes.getBoolean(true))
        {
	        KeyBinding[] sneak = {new KeyBinding("Sneak", Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)};
	        boolean[] repeats = {false};
	        KeyBindingRegistry.registerKeyBinding(new ArtificeKeyHandler(sneak, repeats));
        }
        
        if (ArtificeConfig.enableFrames.getBoolean(true))
            TickRegistry.registerTickHandler(new ScaffoldTicker(), Side.CLIENT);
    }
}
