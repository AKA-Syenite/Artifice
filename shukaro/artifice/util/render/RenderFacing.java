package shukaro.artifice.util.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

public class RenderFacing
{
	/**
	 * Calls the function to render the appropriate side
	 */
	public static boolean renderFace(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, int side, boolean inverted, Tessellator tessellator)
	{
		switch (side)
		{
		case 0:
			renderFaceYNeg(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		case 1:
			renderFaceYPos(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		case 2:
			renderFaceZNeg(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		case 3:
			renderFaceZPos(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		case 4:
			renderFaceXNeg(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		case 5:
			renderFaceXPos(renderer, block, x, y, z, icon, inverted, tessellator);
			return true;
		default:
			return false;
		}
	}
	
    /**
     * Renders the given texture to the bottom face of the block. Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceYNeg(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
        double d6 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        double d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateBottom == 2)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateBottom == 1)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateBottom == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMinY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;
        
        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	        }
	        else
	        {
	        	tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	        	tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	        	tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	        }
        }
    }

    /**
     * Renders the given texture to the top face of the block. Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceYPos(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
        double d6 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        double d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateTop == 1)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateTop == 2)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateTop == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMaxY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;

        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	        }
        }
    }

    /**
     * Renders the given texture to the north (z-negative) face of the block.  Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceZNeg(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateEast == 2)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateEast == 1)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateEast == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMinY;
        double d14 = y + renderer.renderMaxY;
        double d15 = z + renderer.renderMinZ;

        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d12, d14, d15, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d14, d15, d7, d9);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d13, d15, d4, d6);
	            tessellator.addVertexWithUV(d12, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d12, d14, d15, d3, d5);
	            tessellator.addVertexWithUV(d11, d14, d15, d7, d9);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d14, d15, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d12, d14, d15, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d4, d6);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d14, d15, d7, d9);
	            tessellator.addVertexWithUV(d12, d14, d15, d3, d5);
	            tessellator.addVertexWithUV(d12, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d11, d13, d15, d4, d6);
	        }
        }
    }

    /**
     * Renders the given texture to the south (z-positive) face of the block.  Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceZPos(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateWest == 1)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateWest == 2)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateWest == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMinY;
        double d14 = y + renderer.renderMaxY;
        double d15 = z + renderer.renderMaxZ;

        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d14, d15, d3, d5);
	            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
	            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
	            tessellator.addVertexWithUV(d12, d14, d15, d7, d9);
	        }
        }
    }

    /**
     * Renders the given texture to the west (x-negative) face of the block.  Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceXNeg(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateNorth == 1)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateNorth == 2)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateNorth == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMinX;
        double d12 = y + renderer.renderMinY;
        double d13 = y + renderer.renderMaxY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;

        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
	            tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d13, d15, d7, d9);
	            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
	            tessellator.addVertexWithUV(d11, d12, d14, d8, d10);
	            tessellator.addVertexWithUV(d11, d12, d15, d4, d6);
	        }
        }
    }

    /**
     * Renders the given texture to the east (x-positive) face of the block.  Args: block, x, y, z, texture
     * @param inverted 
     */
    private static void renderFaceXPos(RenderBlocks renderer, Block block, double x, double y, double z, Icon icon, boolean inverted, Tessellator tessellator)
    {
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = (double)icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
        double d4 = (double)icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
        double d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxY * 16.0D);
        double d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinY * 16.0D);
        double d7;

        if (renderer.flipTexture)
        {
            d7 = d3;
            d3 = d4;
            d4 = d7;
        }

        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D)
        {
            d3 = (double)icon.getMinU();
            d4 = (double)icon.getMaxU();
        }

        if (renderer.renderMinY < 0.0D || renderer.renderMaxY > 1.0D)
        {
            d5 = (double)icon.getMinV();
            d6 = (double)icon.getMaxV();
        }

        d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;

        if (renderer.uvRotateSouth == 2)
        {
            d3 = (double)icon.getInterpolatedU(renderer.renderMinY * 16.0D);
            d5 = (double)icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        }
        else if (renderer.uvRotateSouth == 1)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxY * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        }
        else if (renderer.uvRotateSouth == 3)
        {
            d3 = (double)icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d4 = (double)icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double)icon.getInterpolatedV(renderer.renderMaxY * 16.0D);
            d6 = (double)icon.getInterpolatedV(renderer.renderMinY * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }

        double d11 = x + renderer.renderMaxX;
        double d12 = y + renderer.renderMinY;
        double d13 = y + renderer.renderMaxY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;

        if (inverted)
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
	            tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
	            tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
	            tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
	        }
        }
        else
        {
	        if (renderer.enableAO)
	        {
	            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
	            tessellator.setBrightness(renderer.brightnessTopLeft);
	            tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
	            tessellator.setBrightness(renderer.brightnessBottomLeft);
	            tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
	            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
	            tessellator.setBrightness(renderer.brightnessBottomRight);
	            tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
	            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
	            tessellator.setBrightness(renderer.brightnessTopRight);
	            tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
	        }
	        else
	        {
	            tessellator.addVertexWithUV(d11, d12, d15, d8, d10);
	            tessellator.addVertexWithUV(d11, d12, d14, d4, d6);
	            tessellator.addVertexWithUV(d11, d13, d14, d7, d9);
	            tessellator.addVertexWithUV(d11, d13, d15, d3, d5);
	        }
        }
    }
}
