package shukaro.artifice.render;

import java.util.Locale;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;

@SideOnly(Side.CLIENT)
public class TextureHandler
{
	public static void updateTexture(BlockCoord c)
	{
		World world = Minecraft.getMinecraft().thePlayer.worldObj;
		updateTexture(world, c);
	}
	
	public static void updateTexture(World world, BlockCoord c)
	{
		ArtificeCore.textureCache.remove(c);
		if (c.getBlock(world) != null)
		{
			Block block = c.getBlock(world);
			int meta = c.getMeta(world);
			boolean updated = false;
			int[] indices = new int[6];
			
			for (int i=0; i<6; i++)
			{
				String s = block.getIcon(i, meta).getIconName();
				for (ConnectedTextures t : ConnectedTextures.values())
				{
					if (s.startsWith(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + t.name.replace('_', '/')))
					{
						indices[i] = t.renderer.getTextureIndex(world, c.x, c.y, c.z, i);
						updated = true;
						break;
					}
				}
			}
			
			if (updated)
			{
				ArtificeCore.textureCache.put(c, indices);
				world.markBlockForUpdate(c.x, c.y, c.z);
			}
		}
	}
	
	public static ConnectedTextures getConnectedTexture(Icon icon)
	{
		String s = icon.getIconName();
		for (ConnectedTextures t : ConnectedTextures.values())
		{
			if (s.startsWith(ArtificeCore.modID.toLowerCase(Locale.ENGLISH) + ":" + t.name.replace('_', '/')))
			{
				return t;
			}
		}
		return null;
	}
}
