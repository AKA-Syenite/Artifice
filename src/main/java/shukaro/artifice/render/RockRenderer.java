package shukaro.artifice.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.util.BlockCoord;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RockRenderer implements ISimpleBlockRenderingHandler
{
    // todo - find a formula to calculate this
    private static Map<BlockCoord, Integer> degrees = new HashMap<BlockCoord, Integer>();
    private static Random rand = new Random();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        if (world.getBlockMetadata(x, y, z) != 0)
            return renderer.renderStandardBlock(block, x, y, z);

        int oldBottom = renderer.uvRotateBottom;
        int oldEast = renderer.uvRotateEast;
        int oldNorth = renderer.uvRotateNorth;
        int oldSouth = renderer.uvRotateSouth;
        int oldTop = renderer.uvRotateTop;
        int oldWest = renderer.uvRotateWest;

        BlockCoord c = new BlockCoord(x, y, z);
        int degree;
        if (degrees.containsKey(c))
            degree = degrees.get(c);
        else
        {
            degree = rand.nextInt(4);
            degrees.put(c, degree);
        }
        renderer.uvRotateBottom = degree;
        renderer.uvRotateEast = degree;
        renderer.uvRotateNorth = degree;
        renderer.uvRotateSouth = degree;
        renderer.uvRotateTop = degree;
        renderer.uvRotateWest = degree;
        renderer.renderStandardBlock(block, x, y, z);

        renderer.uvRotateBottom = oldBottom;
        renderer.uvRotateEast = oldEast;
        renderer.uvRotateNorth = oldNorth;
        renderer.uvRotateSouth = oldSouth;
        renderer.uvRotateTop = oldTop;
        renderer.uvRotateWest = oldWest;
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ArtificeConfig.rockRenderID;
    }
}
