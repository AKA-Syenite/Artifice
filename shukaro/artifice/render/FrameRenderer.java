package shukaro.artifice.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.frame.BlockFrame;
import shukaro.artifice.render.connectedtexture.ILayeredRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FrameRenderer implements ISimpleBlockRenderingHandler
{
    private final RenderBlocksImproved renderImproved = new RenderBlocksImproved();
    
    @Override
    public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        
        if (block instanceof ILayeredRender)
        {
            ILayeredRender inner = (ILayeredRender) block;
            
            renderer.setRenderBounds(0.0005F, 0.0005F, 0.0005F, 0.9995F, 0.9995F, 0.9995F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            
            if (inner.getRenderIcon(0, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(0, meta));
                tessellator.draw();
            }
            
            if (inner.getRenderIcon(1, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(1, meta));
                tessellator.draw();
            }
            
            if (inner.getRenderIcon(2, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(2, meta));
                tessellator.draw();
            }
            
            if (inner.getRenderIcon(3, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(3, meta));
                tessellator.draw();
            }
            
            if (inner.getRenderIcon(4, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(4, meta));
                tessellator.draw();
            }
            
            if (inner.getRenderIcon(5, meta) != null)
            {
                tessellator.startDrawingQuads();
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, inner.getRenderIcon(5, meta));
                tessellator.draw();
            }
            
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
        
        renderer.setRenderBounds(0.00005F, 0.00005F, 0.00005F, 0.99995F, 0.99995F, 0.99995F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
        tessellator.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {    
        renderer.setRenderBounds(0.0002F, 0.0002F, 0.0002F, 0.9998F, 0.9998F, 0.9998F);
        
        if (block instanceof ILayeredRender)
        {
            if (renderImproved.renderStandardBlock(renderer, block, x, y, z, world.getBlockMetadata(x, y, z), false, true))
            {
                renderImproved.renderStandardBlock(renderer, block, x, y, z, world.getBlockMetadata(x, y, z), true, true);
            }
        }
        
        renderer.setRenderBounds(0.0001F, 0.0001F, 0.0001F, 0.9999F, 0.9999F, 0.9999F);
        
        if (renderImproved.renderStandardBlock(renderer, block, x, y, z, world.getBlockMetadata(x, y, z), false, false))
        {
            renderImproved.renderStandardBlock(renderer, block, x, y, z, world.getBlockMetadata(x, y, z), true, false);
        }
        
        return true;
    }
    
    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }
    
    @Override
    public int getRenderId()
    {
        return ArtificeCore.frameRenderID;
    }
}
