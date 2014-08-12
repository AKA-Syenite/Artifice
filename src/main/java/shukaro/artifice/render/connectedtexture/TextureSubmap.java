package shukaro.artifice.render.connectedtexture;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

public class TextureSubmap
{
    public int width, height;
    public IIcon icon;
    public IIcon icons[];

    public TextureSubmap(IIcon i, int w, int h)
    {
        icon = i;
        width = w;
        height = h;
        icons = new IIcon[width * height];

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void TexturesStitched(TextureStitchEvent.Post event)
    {

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                icons[y * width + x] = new TextureVirtual(icon, width, height, x, y);
            }
        }
    }

}