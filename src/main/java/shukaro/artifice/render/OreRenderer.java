package shukaro.artifice.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ClientProxy;
import shukaro.artifice.block.decorative.BlockOre;

public class OreRenderer implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        boolean color = false;
        if (block instanceof BlockOre)
            this.renderInventoryBlock(ArtificeBlocks.blockDummy, metadata, this.getRenderId(), renderer);
        else if (block instanceof BlockOre.BlockOreDummy)
            color = true;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tessellator.startDrawingQuads();
        tessellator.setNormal(0F,-1F,0F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceYNeg(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,0,metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0F,1F,0F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceYPos(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,1,metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0F,0F,-1F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceZNeg(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,2,metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0F,0F,1F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceZPos(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,3,metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F,0F,0F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceXNeg(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,4,metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1F,0F,0F);
        if (color)
            tessellator.setColorOpaque_I(block.getRenderColor(metadata));
        renderer.renderFaceXPos(block,0D,0D,0D,renderer.getBlockIconFromSideAndMetadata(block,5,metadata));
        tessellator.draw();

        GL11.glTranslatef(0.5F,0.5F,0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        if (ClientProxy.renderPass == 0)
            return renderer.renderStandardBlock(ArtificeBlocks.blockDummy, x, y, z);
        else
            return renderer.renderStandardBlock(block, x, y, z);
    }



    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ArtificeConfig.oreRenderID;
    }
}
