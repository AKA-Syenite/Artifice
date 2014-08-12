package shukaro.artifice.render.connectedtexture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shukaro.artifice.render.TextureHandler;

public class ConnectedTexture
{
    public String name;
    public String folder;
    public IIcon icon;
    public TextureSubmap submap;
    public TextureSubmap submapSmall;

    public ConnectedTexture(IIconRegister reg, String name, String folder)
    {
        this.name = name;
        this.folder = folder;
        this.icon = TextureHandler.registerIcon(reg, name, folder);
        this.submap = new TextureSubmap(TextureHandler.registerIcon(reg, name + "-ctm", folder), 4, 4);
        this.submapSmall = new TextureSubmap(this.icon, 2, 2);
    }
}
