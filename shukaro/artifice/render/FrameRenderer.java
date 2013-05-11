package shukaro.artifice.render;

import org.lwjgl.opengl.GL11;

import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.frame.BlockFrame;
import shukaro.artifice.net.ClientProxy;
import shukaro.artifice.util.render.RenderBlocksFrame;
import shukaro.artifice.util.render.RenderBlocksInverted;
import shukaro.artifice.util.render.RenderBlocksMeta;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FrameRenderer implements ISimpleBlockRenderingHandler
{
	private RenderBlocksInverted invertedRenderer = new RenderBlocksInverted();
	private RenderBlocksMeta metaRenderer = new RenderBlocksMeta();
	private RenderBlocksFrame frameRenderer = new RenderBlocksFrame();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		
		BlockFrame frame = null;
		
		if (block instanceof BlockFrame)
			frame = (BlockFrame)block;
		
		Block innerBlock = frame.getInnerBlock(metadata);
		int innerMeta = frame.getInnerMeta(metadata);
        
		if (innerBlock != null)
		{
	        renderer.setRenderBounds(0.0005F, 0.0005F, 0.0005F, 0.9995F, 0.9995F, 0.9995F);
	        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
	        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	        
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, -1.0F, 0.0F);
	        renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 0, innerMeta));
	        tessellator.draw();
	
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 1.0F, 0.0F);
	        renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 1, innerMeta));
	        tessellator.draw();
	
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 0.0F, -1.0F);
	        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 2, innerMeta));
	        tessellator.draw();
	        
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(0.0F, 0.0F, 1.0F);
	        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 3, innerMeta));
	        tessellator.draw();
	        
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 4, innerMeta));
	        tessellator.draw();
	        
	        tessellator.startDrawingQuads();
	        tessellator.setNormal(1.0F, 0.0F, 0.0F);
	        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(innerBlock, 5, innerMeta));
	        tessellator.draw();
	        
	        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
        
        renderer.setRenderBounds(0.00005F, 0.00005F, 0.00005F, 0.99995F, 0.99995F, 0.99995F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        
        
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		BlockFrame frame = null;
		
		if (block instanceof BlockFrame)
			frame = (BlockFrame)block;
		
		Block innerBlock = frame.getInnerBlock(world.getBlockMetadata(x, y, z));
		int innerMeta = frame.getInnerMeta(world.getBlockMetadata(x, y, z));
				
		renderer.setRenderBounds(0.0002F, 0.0002F, 0.0002F, 0.9998F, 0.9998F, 0.9998F);
		
		if (frame != null && innerBlock != null)
		{
			metaRenderer.renderStandardBlock(renderer, innerBlock, innerMeta, x, y, z);
		}
		
		renderer.setRenderBounds(0.0001F, 0.0001F, 0.0001F, 0.9999F, 0.9999F, 0.9999F);
		
		if (frameRenderer.renderStandardBlock(renderer, block, x, y, z))
		{
			invertedRenderer.renderStandardBlock(renderer, block, x, y, z);
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
