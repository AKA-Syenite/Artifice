package shukaro.artifice.render;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;

public class IconHandler
{
    public static void registerConnectedTexture(IconRegister reg, ConnectedTexture texture, String folder)
    {
        for (int i=0; i<texture.textureList.length; i++)
        {
            texture.textureList[i] = reg.registerIcon(ArtificeCore.modID.toLowerCase() + ":" + folder + "/" + texture.name + "_" + (i > 9 ? i : "0" + i));
        }
        for (ConnectedTexture t : ConnectedTexture.values())
        {
            if (t.name == texture.name)
            {
                t.textureList = texture.textureList;
            }
        }
    }
    
    public static Icon[] registerSingleDirectional(IconRegister reg, String name, String folder)
    {
        Icon[] icons = new Icon[6];
        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS)
        {
            icons[d.ordinal()] = reg.registerIcon(ArtificeCore.modID.toLowerCase() + ":" + folder + "/" + name + "_" + d.toString().toLowerCase());
        }
        return icons;
    }
    
    public static Icon[][] registerMetaDirectional(IconRegister reg, String[] names, String folder)
    {
        Icon[][] icons = new Icon[names.length][6];
        for (int i=0; i<names.length; i++)
        {
            for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS)
            {
                icons[i][d.ordinal()] = reg.registerIcon(ArtificeCore.modID.toLowerCase() + ":" + folder + "/" + names[i] + "_" + d.toString().toLowerCase());
            }
        }
        return icons;
    }
    
    public static Icon registerSingle(IconRegister reg, String name, String folder)
    {
        Icon icon = reg.registerIcon(ArtificeCore.modID.toLowerCase() + ":" + folder + "/" + name);
        return icon;
    }
    
    public static Icon[] registerMeta(IconRegister reg, String names[], String folder)
    {
        Icon[] icons = new Icon[names.length];
        for (int i=0; i<names.length; i++)
        {
            icons[i] = reg.registerIcon(ArtificeCore.modID.toLowerCase() + ":" + folder + "/" + names[i]);
        }
        return icons;
    }
}
