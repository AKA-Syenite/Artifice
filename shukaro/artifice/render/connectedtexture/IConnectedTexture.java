package shukaro.artifice.render.connectedtexture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeCore;

public interface IConnectedTexture
{
    /*
     * Returns the textureset to use for this side and meta
     */
    ConnectedTexture getTextureType(int side, int meta);
    
    /*
     * Returns the connected texture renderer used by this block
     */
    ConnectedTextureBase getTextureRenderer(int side, int meta);
}