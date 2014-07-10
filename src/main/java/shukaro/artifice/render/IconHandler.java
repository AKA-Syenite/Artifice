package shukaro.artifice.render;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;

import java.util.Locale;

public class IconHandler
{
    public static void registerConnectedTexture(IIconRegister reg, ConnectedTextures texture, String folder)
    {
        for (int i = 0; i < texture.textureList.length; i++)
        {
            texture.textureList[i] = reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + texture.name + "_" + (i > 9 ? i : "0" + i));
        }
    }

    public static IIcon[] registerSingleDirectional(IIconRegister reg, String name, String folder)
    {
        IIcon[] icons = new IIcon[6];
        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS)
        {
            icons[d.ordinal()] = reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + name + "_" + d.toString().toLowerCase(Locale.ENGLISH));
        }
        return icons;
    }

    public static IIcon[][] registerMetaDirectional(IIconRegister reg, String[] names, String folder)
    {
        IIcon[][] icons = new IIcon[names.length][6];
        for (int i = 0; i < names.length; i++)
        {
            for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS)
            {
                icons[i][d.ordinal()] = reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + names[i] + "_" + d.toString().toLowerCase(Locale.ENGLISH));
            }
        }
        return icons;
    }

    public static IIcon registerSingle(IIconRegister reg, String name, String folder)
    {
        return reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + name);
    }

    public static IIcon[] registerMeta(IIconRegister reg, String names[], String folder)
    {
        IIcon[] icons = new IIcon[names.length];
        for (int i = 0; i < names.length; i++)
        {
            icons[i] = reg.registerIcon(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + folder + "/" + names[i]);
        }
        return icons;
    }
}
