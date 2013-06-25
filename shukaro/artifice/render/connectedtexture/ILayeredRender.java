package shukaro.artifice.render.connectedtexture;

import net.minecraft.util.Icon;

public interface ILayeredRender
{
    /*
     * Returns the icon to render underneath for the given side and meta
     */
    Icon getRenderIcon(int side, int meta);
}