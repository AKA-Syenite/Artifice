package shukaro.artifice.render;

import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.util.NameMetaPair;

import java.util.Locale;

public class TextureHandler
{
    private static TMap<NameMetaPair, ConnectedTexture[]> connectedTextures = new THashMap<NameMetaPair, ConnectedTexture[]>();

    public static IIcon registerIcon(IIconRegister reg, String name, String folder)
    {
        return reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + name);
    }

    public static void registerConnectedTexture(IIconRegister reg, Block block, int meta, String name, String folder)
    {
        ConnectedTexture[] textures = new ConnectedTexture[6];
        for (int i=0; i<6; i++)
            textures[i] = new ConnectedTexture(reg, name, folder);
        connectedTextures.put(new NameMetaPair(block, meta), textures);
    }

    public static void registerConnectedTexture(IIconRegister reg, Block block, int meta, int side, String name, String folder)
    {
        NameMetaPair pair = new NameMetaPair(block, meta);
        ConnectedTexture[] textures;
        if (connectedTextures.get(pair) == null)
            textures = new ConnectedTexture[6];
        else
            textures = connectedTextures.get(pair);
        textures[side] = new ConnectedTexture(reg, name, folder);
        connectedTextures.put(pair, textures);
    }

    public static ConnectedTexture getConnectedTexture(Block block, int meta, int side)
    {
        NameMetaPair pair = new NameMetaPair(block, meta);
        if (connectedTextures.get(pair) != null && connectedTextures.get(pair)[side] != null)
            return connectedTextures.get(pair)[side];
        else
            return null;
    }
}
