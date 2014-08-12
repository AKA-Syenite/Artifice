package shukaro.artifice.render.connectedtexture;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.decorative.BlockRockSlab;
import shukaro.artifice.block.world.BlockRock;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.Drawing;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class CTMRenderer implements ISimpleBlockRenderingHandler
{
    RenderBlocksCTM rendererCTM = new RenderBlocksCTM();

    private static Map<BlockCoord, Integer> degrees = new ConcurrentHashMap<BlockCoord, Integer>();
    private static Random rand = new Random();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        Drawing.drawBlock(block, metadata, renderer);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks rendererOld)
    {
        int meta = world.getBlockMetadata(x, y, z);

        boolean ct = false;
        for (int i=0; i<6; i++)
        {
            if (TextureHandler.getConnectedTexture(block, meta, i) != null)
            {
                ct = true;
                break;
            }
        }

        if (ct)
        {
            rendererCTM.blockAccess = world;
            rendererCTM.rendererOld = rendererOld;
            return rendererCTM.renderStandardBlock(block, x, y, z);
        }
        else
        {
            int oldBottom = rendererOld.uvRotateBottom;
            int oldEast = rendererOld.uvRotateEast;
            int oldNorth = rendererOld.uvRotateNorth;
            int oldSouth = rendererOld.uvRotateSouth;
            int oldTop = rendererOld.uvRotateTop;
            int oldWest = rendererOld.uvRotateWest;

            if (block instanceof BlockRock && world.getBlockMetadata(x, y, z) == 0)
            {
                BlockCoord c = new BlockCoord(x, y, z);
                int degree;
                if (degrees.containsKey(c))
                    degree = degrees.get(c);
                else
                {
                    degree = rand.nextInt(4);
                    degrees.put(c, degree);
                }
                rendererOld.uvRotateBottom = degree;
                rendererOld.uvRotateEast = degree;
                rendererOld.uvRotateNorth = degree;
                rendererOld.uvRotateSouth = degree;
                rendererOld.uvRotateTop = degree;
                rendererOld.uvRotateWest = degree;
            }

            boolean res = rendererOld.renderStandardBlock(block, x, y, z);

            if (block instanceof BlockRock && world.getBlockMetadata(x, y, z) == 0)
            {
                rendererOld.uvRotateBottom = oldBottom;
                rendererOld.uvRotateEast = oldEast;
                rendererOld.uvRotateNorth = oldNorth;
                rendererOld.uvRotateSouth = oldSouth;
                rendererOld.uvRotateTop = oldTop;
                rendererOld.uvRotateWest = oldWest;
            }
            return res;
        }
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ArtificeConfig.ctmRenderID;
    }
}
