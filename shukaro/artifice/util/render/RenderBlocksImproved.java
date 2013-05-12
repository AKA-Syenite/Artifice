package shukaro.artifice.util.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import shukaro.artifice.block.frame.BlockFrame;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.TransparentConnectedTexture;
import shukaro.artifice.util.BlockCoord;

public class RenderBlocksImproved
{
    boolean connectBottom;
    boolean connectTop;
    boolean connectEast;
    boolean connectWest;
    boolean connectNorth;
    boolean connectSouth;
    
    /**
     * Renders a block
     * 
     * @param renderer
     * @param block
     * @param x
     * @param y
     * @param z
     * @param meta
     *            Meta to use to choose texture
     * @param inverted
     *            True if rendering backfaces
     * @return
     */
    public boolean renderStandardBlock(RenderBlocks renderer, Block block,
            int x, int y, int z, int meta, boolean inverted)
    {
        int l = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f = (l >> 16 & 255) / 255.0F;
        float f1 = (l >> 8 & 255) / 255.0F;
        float f2 = (l & 255) / 255.0F;
        
        Tessellator tessellator = Tessellator.instance;
        
        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        
        return Minecraft.isAmbientOcclusionEnabled()
                && Block.lightValue[block.blockID] == 0 ? (renderer.partialRenderBounds ? this
                .func_102027_b(renderer, block, x, y, z, f, f1, f2, meta,
                        inverted) : this
                .renderStandardBlockWithAmbientOcclusion(renderer, block, x, y,
                        z, f, f1, f2, meta, inverted))
                : this.renderStandardBlockWithColorMultiplier(renderer, block,
                        x, y, z, f, f1, f2, meta, inverted);
    }
    
    private boolean func_102027_b(RenderBlocks renderer, Block block, int x,
            int y, int z, float r, float g, float b, int meta, boolean inverted)
    {
        connectBottom = false;
        connectTop = false;
        connectEast = false;
        connectWest = false;
        connectNorth = false;
        connectSouth = false;
        
        BlockFrame frame = null;
        if (block instanceof BlockFrame)
            frame = (BlockFrame) block;
        
        Block innerBlock = null;
        if (frame != null)
        {
            innerBlock = frame.getInnerBlock(renderer.blockAccess
                    .getBlockMetadata(x, y, z));
            
            if (frame.textureRenderer != null)
            {
                ConnectedTextureBase text = new TransparentConnectedTexture(
                        frame.textureRenderer.blockID);
                connectBottom = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 0, 0);
                connectTop = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 1, 0);
                connectNorth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 2, 0);
                connectSouth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 3, 0);
                connectWest = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 4, 0);
                connectEast = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 5, 0);
            }
        }
        
        if (innerBlock == null)
        {
            connectBottom = false;
            connectTop = false;
            connectEast = false;
            connectWest = false;
            connectNorth = false;
            connectSouth = false;
        }
        
        renderer.enableAO = true;
        boolean flag = false;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);
        
        if (renderer.getBlockIcon(block).getIconName().equals("grass_top"))
        {
            flag1 = false;
        }
        else if (renderer.hasOverrideBlockTexture())
        {
            flag1 = false;
        }
        
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        float f7;
        int i1;
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1,
                        z, 0) && !connectBottom)
        {
            if (renderer.renderMinY <= 0.0D)
            {
                --y;
            }
            
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchXYNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchYZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchYZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y - 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z - 1)];
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z - 1);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z - 1);
            }
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z + 1);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z + 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z - 1);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z - 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z + 1);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z + 1);
            }
            
            if (renderer.renderMinY <= 0.0D)
            {
                ++y;
            }
            
            i1 = l;
            
            if (renderer.renderMinY <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y - 1, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y - 1, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y - 1, z);
            f3 = (renderer.aoLightValueScratchXYZNNP
                    + renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
            f6 = (renderer.aoLightValueScratchYZNP + f7
                    + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
            f5 = (f7 + renderer.aoLightValueScratchYZNN
                    + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN,
                    renderer.aoBrightnessYZNP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP,
                    renderer.aoBrightnessXYPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN,
                    renderer.aoBrightnessXYZPNN, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN,
                    renderer.aoBrightnessYZNN, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.5F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 0), 0,
                    inverted, tessellator);
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1,
                        z, 1) && !connectTop)
        {
            if (renderer.renderMaxY >= 1.0D)
            {
                ++y;
            }
            
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchXYPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoLightValueScratchYZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchYZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y + 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z - 1)];
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z - 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z - 1);
            }
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z + 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z + 1);
            }
            
            if (renderer.renderMaxY >= 1.0D)
            {
                --y;
            }
            
            i1 = l;
            
            if (renderer.renderMaxY >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y + 1, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y + 1, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y + 1, z);
            f6 = (renderer.aoLightValueScratchXYZNPP
                    + renderer.aoLightValueScratchXYNP
                    + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (renderer.aoLightValueScratchYZPP + f7
                    + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN
                    + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYNP
                    + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP,
                    renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP,
                    renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP,
                    renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN,
                    renderer.aoBrightnessYZPN, i1);
            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b;
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 1), 1,
                    inverted, tessellator);
            flag = true;
        }
        
        float f8;
        float f9;
        float f10;
        float f11;
        int j1;
        int k1;
        int l1;
        int i2;
        Icon icon;
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z - 1, 2) && !connectNorth)
        {
            if (renderer.renderMinZ <= 0.0D)
            {
                --z;
            }
            
            renderer.aoLightValueScratchXZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchYZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchYZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoLightValueScratchXZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z - 1)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z - 1)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z - 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z - 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y - 1, z);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y + 1, z);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y - 1, z);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y + 1, z);
            }
            
            if (renderer.renderMinZ <= 0.0D)
            {
                ++z;
            }
            
            i1 = l;
            
            if (renderer.renderMinZ <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y, z - 1))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y, z - 1);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y, z - 1);
            f9 = (renderer.aoLightValueScratchXZNN
                    + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            f8 = (f7 + renderer.aoLightValueScratchYZPN
                    + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f11 = (renderer.aoLightValueScratchYZNN + f7
                    + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
            f10 = (renderer.aoLightValueScratchXYZNNN
                    + renderer.aoLightValueScratchXZNN
                    + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
            f3 = (float) (f9 * renderer.renderMaxY
                    * (1.0D - renderer.renderMinX) + f8 * renderer.renderMinY
                    * renderer.renderMinX + f11 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMinX + f10 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMinX));
            f4 = (float) (f9 * renderer.renderMaxY
                    * (1.0D - renderer.renderMaxX) + f8 * renderer.renderMaxY
                    * renderer.renderMaxX + f11 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMaxX + f10 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMaxX));
            f5 = (float) (f9 * renderer.renderMinY
                    * (1.0D - renderer.renderMaxX) + f8 * renderer.renderMinY
                    * renderer.renderMaxX + f11 * (1.0D - renderer.renderMinY)
                    * renderer.renderMaxX + f10 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMaxX));
            f6 = (float) (f9 * renderer.renderMinY
                    * (1.0D - renderer.renderMinX) + f8 * renderer.renderMinY
                    * renderer.renderMinX + f11 * (1.0D - renderer.renderMinY)
                    * renderer.renderMinX + f10 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMinX));
            k1 = renderer.getAoBrightness(renderer.aoBrightnessXZNN,
                    renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
            j1 = renderer.getAoBrightness(renderer.aoBrightnessYZPN,
                    renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
            i2 = renderer.getAoBrightness(renderer.aoBrightnessYZNN,
                    renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
            l1 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN,
                    renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);
            renderer.brightnessTopLeft = renderer
                    .mixAoBrightness(k1, j1, i2, l1, renderer.renderMaxY
                            * (1.0D - renderer.renderMinX), renderer.renderMaxY
                            * renderer.renderMinX, (1.0D - renderer.renderMaxY)
                            * renderer.renderMinX, (1.0D - renderer.renderMaxY)
                            * (1.0D - renderer.renderMinX));
            renderer.brightnessBottomLeft = renderer
                    .mixAoBrightness(k1, j1, i2, l1, renderer.renderMaxY
                            * (1.0D - renderer.renderMaxX), renderer.renderMaxY
                            * renderer.renderMaxX, (1.0D - renderer.renderMaxY)
                            * renderer.renderMaxX, (1.0D - renderer.renderMaxY)
                            * (1.0D - renderer.renderMaxX));
            renderer.brightnessBottomRight = renderer
                    .mixAoBrightness(k1, j1, i2, l1, renderer.renderMinY
                            * (1.0D - renderer.renderMaxX), renderer.renderMinY
                            * renderer.renderMaxX, (1.0D - renderer.renderMinY)
                            * renderer.renderMaxX, (1.0D - renderer.renderMinY)
                            * (1.0D - renderer.renderMaxX));
            renderer.brightnessTopRight = renderer
                    .mixAoBrightness(k1, j1, i2, l1, renderer.renderMinY
                            * (1.0D - renderer.renderMinX), renderer.renderMinY
                            * renderer.renderMinX, (1.0D - renderer.renderMinY)
                            * renderer.renderMinX, (1.0D - renderer.renderMinY)
                            * (1.0D - renderer.renderMinX));
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 2), 2,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z + 1, 3) && !connectSouth)
        {
            if (renderer.renderMaxZ >= 1.0D)
            {
                ++z;
            }
            
            renderer.aoLightValueScratchXZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchXZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoLightValueScratchYZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchYZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z + 1)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z + 1)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z + 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y - 1, z);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y + 1, z);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y - 1, z);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y + 1, z);
            }
            
            if (renderer.renderMaxZ >= 1.0D)
            {
                --z;
            }
            
            i1 = l;
            
            if (renderer.renderMaxZ >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y, z + 1))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y, z + 1);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y, z + 1);
            f9 = (renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
            f8 = (f7 + renderer.aoLightValueScratchYZPP
                    + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            f11 = (renderer.aoLightValueScratchYZNP + f7
                    + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
            f10 = (renderer.aoLightValueScratchXYZNNP
                    + renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
            f3 = (float) (f9 * renderer.renderMaxY
                    * (1.0D - renderer.renderMinX) + f8 * renderer.renderMaxY
                    * renderer.renderMinX + f11 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMinX + f10 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMinX));
            f4 = (float) (f9 * renderer.renderMinY
                    * (1.0D - renderer.renderMinX) + f8 * renderer.renderMinY
                    * renderer.renderMinX + f11 * (1.0D - renderer.renderMinY)
                    * renderer.renderMinX + f10 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMinX));
            f5 = (float) (f9 * renderer.renderMinY
                    * (1.0D - renderer.renderMaxX) + f8 * renderer.renderMinY
                    * renderer.renderMaxX + f11 * (1.0D - renderer.renderMinY)
                    * renderer.renderMaxX + f10 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMaxX));
            f6 = (float) (f9 * renderer.renderMaxY
                    * (1.0D - renderer.renderMaxX) + f8 * renderer.renderMaxY
                    * renderer.renderMaxX + f11 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMaxX + f10 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMaxX));
            k1 = renderer.getAoBrightness(renderer.aoBrightnessXZNP,
                    renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
            j1 = renderer.getAoBrightness(renderer.aoBrightnessYZPP,
                    renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
            i2 = renderer.getAoBrightness(renderer.aoBrightnessYZNP,
                    renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
            l1 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP,
                    renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);
            renderer.brightnessTopLeft = renderer
                    .mixAoBrightness(k1, l1, i2, j1, renderer.renderMaxY
                            * (1.0D - renderer.renderMinX),
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMinX),
                            (1.0D - renderer.renderMaxY) * renderer.renderMinX,
                            renderer.renderMaxY * renderer.renderMinX);
            renderer.brightnessBottomLeft = renderer
                    .mixAoBrightness(k1, l1, i2, j1, renderer.renderMinY
                            * (1.0D - renderer.renderMinX),
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMinX),
                            (1.0D - renderer.renderMinY) * renderer.renderMinX,
                            renderer.renderMinY * renderer.renderMinX);
            renderer.brightnessBottomRight = renderer
                    .mixAoBrightness(k1, l1, i2, j1, renderer.renderMinY
                            * (1.0D - renderer.renderMaxX),
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMaxX),
                            (1.0D - renderer.renderMinY) * renderer.renderMaxX,
                            renderer.renderMinY * renderer.renderMaxX);
            renderer.brightnessTopRight = renderer
                    .mixAoBrightness(k1, l1, i2, j1, renderer.renderMaxY
                            * (1.0D - renderer.renderMaxX),
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMaxX),
                            (1.0D - renderer.renderMaxY) * renderer.renderMaxX,
                            renderer.renderMaxY * renderer.renderMaxX);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 3), 3,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y,
                        z, 4) && !connectWest)
        {
            if (renderer.renderMinX <= 0.0D)
            {
                --x;
            }
            
            renderer.aoLightValueScratchXYNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchXZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchXZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z - 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z + 1)];
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z - 1);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z - 1);
            }
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z + 1);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z + 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z - 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z + 1);
            }
            
            if (renderer.renderMinX <= 0.0D)
            {
                ++x;
            }
            
            i1 = l;
            
            if (renderer.renderMinX <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x - 1, y, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
                        x - 1, y, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess,
                    x - 1, y, z);
            f9 = (renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
            f8 = (f7 + renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
            f11 = (renderer.aoLightValueScratchXZNN + f7
                    + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
            f10 = (renderer.aoLightValueScratchXYZNNN
                    + renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
            f3 = (float) (f8 * renderer.renderMaxY * renderer.renderMaxZ + f11
                    * renderer.renderMaxY * (1.0D - renderer.renderMaxZ) + f10
                    * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMaxZ) + f9
                    * (1.0D - renderer.renderMaxY) * renderer.renderMaxZ);
            f4 = (float) (f8 * renderer.renderMaxY * renderer.renderMinZ + f11
                    * renderer.renderMaxY * (1.0D - renderer.renderMinZ) + f10
                    * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMinZ) + f9
                    * (1.0D - renderer.renderMaxY) * renderer.renderMinZ);
            f5 = (float) (f8 * renderer.renderMinY * renderer.renderMinZ + f11
                    * renderer.renderMinY * (1.0D - renderer.renderMinZ) + f10
                    * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMinZ) + f9
                    * (1.0D - renderer.renderMinY) * renderer.renderMinZ);
            f6 = (float) (f8 * renderer.renderMinY * renderer.renderMaxZ + f11
                    * renderer.renderMinY * (1.0D - renderer.renderMaxZ) + f10
                    * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMaxZ) + f9
                    * (1.0D - renderer.renderMinY) * renderer.renderMaxZ);
            k1 = renderer.getAoBrightness(renderer.aoBrightnessXYNN,
                    renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
            j1 = renderer.getAoBrightness(renderer.aoBrightnessXZNP,
                    renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
            i2 = renderer.getAoBrightness(renderer.aoBrightnessXZNN,
                    renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
            l1 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN,
                    renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);
            renderer.brightnessTopLeft = renderer
                    .mixAoBrightness(j1, i2, l1, k1, renderer.renderMaxY
                            * renderer.renderMaxZ, renderer.renderMaxY
                            * (1.0D - renderer.renderMaxZ),
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMaxZ),
                            (1.0D - renderer.renderMaxY) * renderer.renderMaxZ);
            renderer.brightnessBottomLeft = renderer
                    .mixAoBrightness(j1, i2, l1, k1, renderer.renderMaxY
                            * renderer.renderMinZ, renderer.renderMaxY
                            * (1.0D - renderer.renderMinZ),
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMinZ),
                            (1.0D - renderer.renderMaxY) * renderer.renderMinZ);
            renderer.brightnessBottomRight = renderer
                    .mixAoBrightness(j1, i2, l1, k1, renderer.renderMinY
                            * renderer.renderMinZ, renderer.renderMinY
                            * (1.0D - renderer.renderMinZ),
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMinZ),
                            (1.0D - renderer.renderMinY) * renderer.renderMinZ);
            renderer.brightnessTopRight = renderer
                    .mixAoBrightness(j1, i2, l1, k1, renderer.renderMinY
                            * renderer.renderMaxZ, renderer.renderMinY
                            * (1.0D - renderer.renderMaxZ),
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMaxZ),
                            (1.0D - renderer.renderMinY) * renderer.renderMaxZ);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 4), 4,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y,
                        z, 5) && !connectEast)
        {
            if (renderer.renderMaxX >= 1.0D)
            {
                ++x;
            }
            
            renderer.aoLightValueScratchXYPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchXZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchXZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z - 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z - 1);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z - 1);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z + 1);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z + 1);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z - 1);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z + 1);
            }
            
            if (renderer.renderMaxX >= 1.0D)
            {
                --x;
            }
            
            i1 = l;
            
            if (renderer.renderMaxX >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x + 1, y, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
                        x + 1, y, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess,
                    x + 1, y, z);
            f9 = (renderer.aoLightValueScratchXYPN
                    + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
            f8 = (renderer.aoLightValueScratchXYZPNN
                    + renderer.aoLightValueScratchXYPN
                    + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
            f11 = (renderer.aoLightValueScratchXZPN + f7
                    + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
            f10 = (f7 + renderer.aoLightValueScratchXZPP
                    + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            f3 = (float) (f9 * (1.0D - renderer.renderMinY)
                    * renderer.renderMaxZ + f8 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMaxZ) + f11 * renderer.renderMinY
                    * (1.0D - renderer.renderMaxZ) + f10 * renderer.renderMinY
                    * renderer.renderMaxZ);
            f4 = (float) (f9 * (1.0D - renderer.renderMinY)
                    * renderer.renderMinZ + f8 * (1.0D - renderer.renderMinY)
                    * (1.0D - renderer.renderMinZ) + f11 * renderer.renderMinY
                    * (1.0D - renderer.renderMinZ) + f10 * renderer.renderMinY
                    * renderer.renderMinZ);
            f5 = (float) (f9 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMinZ + f8 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMinZ) + f11 * renderer.renderMaxY
                    * (1.0D - renderer.renderMinZ) + f10 * renderer.renderMaxY
                    * renderer.renderMinZ);
            f6 = (float) (f9 * (1.0D - renderer.renderMaxY)
                    * renderer.renderMaxZ + f8 * (1.0D - renderer.renderMaxY)
                    * (1.0D - renderer.renderMaxZ) + f11 * renderer.renderMaxY
                    * (1.0D - renderer.renderMaxZ) + f10 * renderer.renderMaxY
                    * renderer.renderMaxZ);
            k1 = renderer.getAoBrightness(renderer.aoBrightnessXYPN,
                    renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
            j1 = renderer.getAoBrightness(renderer.aoBrightnessXZPP,
                    renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
            i2 = renderer.getAoBrightness(renderer.aoBrightnessXZPN,
                    renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
            l1 = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN,
                    renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);
            renderer.brightnessTopLeft = renderer
                    .mixAoBrightness(k1, l1, i2, j1,
                            (1.0D - renderer.renderMinY) * renderer.renderMaxZ,
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMaxZ),
                            renderer.renderMinY * (1.0D - renderer.renderMaxZ),
                            renderer.renderMinY * renderer.renderMaxZ);
            renderer.brightnessBottomLeft = renderer
                    .mixAoBrightness(k1, l1, i2, j1,
                            (1.0D - renderer.renderMinY) * renderer.renderMinZ,
                            (1.0D - renderer.renderMinY)
                                    * (1.0D - renderer.renderMinZ),
                            renderer.renderMinY * (1.0D - renderer.renderMinZ),
                            renderer.renderMinY * renderer.renderMinZ);
            renderer.brightnessBottomRight = renderer
                    .mixAoBrightness(k1, l1, i2, j1,
                            (1.0D - renderer.renderMaxY) * renderer.renderMinZ,
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMinZ),
                            renderer.renderMaxY * (1.0D - renderer.renderMinZ),
                            renderer.renderMaxY * renderer.renderMinZ);
            renderer.brightnessTopRight = renderer
                    .mixAoBrightness(k1, l1, i2, j1,
                            (1.0D - renderer.renderMaxY) * renderer.renderMaxZ,
                            (1.0D - renderer.renderMaxY)
                                    * (1.0D - renderer.renderMaxZ),
                            renderer.renderMaxY * (1.0D - renderer.renderMaxZ),
                            renderer.renderMaxY * renderer.renderMaxZ);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 5), 5,
                    inverted, tessellator);
            
            flag = true;
        }
        
        renderer.enableAO = false;
        return flag;
    }
    
    private boolean renderStandardBlockWithAmbientOcclusion(
            RenderBlocks renderer, Block block, int x, int y, int z, float r,
            float g, float b, int meta, boolean inverted)
    {
        connectBottom = false;
        connectTop = false;
        connectEast = false;
        connectWest = false;
        connectNorth = false;
        connectSouth = false;
        
        BlockFrame frame = null;
        if (block instanceof BlockFrame)
            frame = (BlockFrame) block;
        
        Block innerBlock = null;
        if (frame != null)
        {
            innerBlock = frame.getInnerBlock(renderer.blockAccess
                    .getBlockMetadata(x, y, z));
            
            if (frame.textureRenderer != null)
            {
                ConnectedTextureBase text = new TransparentConnectedTexture(
                        frame.textureRenderer.blockID);
                connectBottom = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 0, 0);
                connectTop = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 1, 0);
                connectNorth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 2, 0);
                connectSouth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 3, 0);
                connectWest = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 4, 0);
                connectEast = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 5, 0);
            }
        }
        
        if (innerBlock == null)
        {
            connectBottom = false;
            connectTop = false;
            connectEast = false;
            connectWest = false;
            connectNorth = false;
            connectSouth = false;
        }
        
        renderer.enableAO = true;
        boolean flag = false;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);
        
        if (renderer.getBlockIcon(block).getIconName().equals("grass_top"))
        {
            flag1 = false;
        }
        else if (renderer.hasOverrideBlockTexture())
        {
            flag1 = false;
        }
        
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        float f7;
        int i1;
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1,
                        z, 0) && !connectBottom)
        {
            if (renderer.renderMinY <= 0.0D)
            {
                --y;
            }
            
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchXYNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchYZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchYZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y - 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z - 1)];
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z - 1);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z - 1);
            }
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z + 1);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z + 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z - 1);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z - 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z + 1);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z + 1);
            }
            
            if (renderer.renderMinY <= 0.0D)
            {
                ++y;
            }
            
            i1 = l;
            
            if (renderer.renderMinY <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y - 1, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y - 1, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y - 1, z);
            f3 = (renderer.aoLightValueScratchXYZNNP
                    + renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
            f6 = (renderer.aoLightValueScratchYZNP + f7
                    + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
            f5 = (f7 + renderer.aoLightValueScratchYZNN
                    + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN,
                    renderer.aoBrightnessYZNP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP,
                    renderer.aoBrightnessXYPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN,
                    renderer.aoBrightnessXYZPNN, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN,
                    renderer.aoBrightnessYZNN, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.5F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 0), 0,
                    inverted, tessellator);
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1,
                        z, 1) && !connectTop)
        {
            if (renderer.renderMaxY >= 1.0D)
            {
                ++y;
            }
            
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchXYPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoLightValueScratchYZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchYZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y + 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z - 1)];
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z - 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z - 1);
            }
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y, z + 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y, z + 1);
            }
            
            if (renderer.renderMaxY >= 1.0D)
            {
                --y;
            }
            
            i1 = l;
            
            if (renderer.renderMaxY >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y + 1, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y + 1, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y + 1, z);
            f6 = (renderer.aoLightValueScratchXYZNPP
                    + renderer.aoLightValueScratchXYNP
                    + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (renderer.aoLightValueScratchYZPP + f7
                    + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN
                    + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYNP
                    + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP,
                    renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP,
                    renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP,
                    renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN,
                    renderer.aoBrightnessYZPN, i1);
            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b;
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 1), 1,
                    inverted, tessellator);
            flag = true;
        }
        
        Icon icon;
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z - 1, 2) && !connectNorth)
        {
            if (renderer.renderMinZ <= 0.0D)
            {
                --z;
            }
            
            renderer.aoLightValueScratchXZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchYZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchYZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoLightValueScratchXZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z - 1)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z - 1)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z - 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z - 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y - 1, z);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y + 1, z);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y - 1, z);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y + 1, z);
            }
            
            if (renderer.renderMinZ <= 0.0D)
            {
                ++z;
            }
            
            i1 = l;
            
            if (renderer.renderMinZ <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y, z - 1))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y, z - 1);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y, z - 1);
            f3 = (renderer.aoLightValueScratchXZNN
                    + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN
                    + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchYZNN + f7
                    + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
            f6 = (renderer.aoLightValueScratchXYZNNN
                    + renderer.aoLightValueScratchXZNN
                    + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN,
                    renderer.aoBrightnessYZPN, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN,
                    renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN,
                    renderer.aoBrightnessXZPN, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN,
                    renderer.aoBrightnessYZNN, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 2), 2,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z + 1, 3) && !connectSouth)
        {
            if (renderer.renderMaxZ >= 1.0D)
            {
                ++z;
            }
            
            renderer.aoLightValueScratchXZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x - 1,
                            y, z);
            renderer.aoLightValueScratchXZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x + 1,
                            y, z);
            renderer.aoLightValueScratchYZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchYZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z + 1)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z + 1)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y + 1, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x,
                    y - 1, z + 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y - 1, z);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y - 1, z);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x - 1, y + 1, z);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x - 1, y + 1, z);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y - 1, z);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y - 1, z);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess,
                                x + 1, y + 1, z);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x + 1, y + 1, z);
            }
            
            if (renderer.renderMaxZ >= 1.0D)
            {
                --z;
            }
            
            i1 = l;
            
            if (renderer.renderMaxZ >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x, y, z + 1))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x,
                        y, z + 1);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess, x,
                    y, z + 1);
            f3 = (renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
            f6 = (f7 + renderer.aoLightValueScratchYZPP
                    + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            f5 = (renderer.aoLightValueScratchYZNP + f7
                    + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYZNNP
                    + renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP,
                    renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP,
                    renderer.aoBrightnessXYZPPP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP,
                    renderer.aoBrightnessXZPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP,
                    renderer.aoBrightnessYZNP, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 3), 3,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y,
                        z, 4) && !connectWest)
        {
            if (renderer.renderMinX <= 0.0D)
            {
                --x;
            }
            
            renderer.aoLightValueScratchXYNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchXZNN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchXZNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYNP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z - 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x - 1,
                    y, z + 1)];
            
            if (!flag5 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z - 1);
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z - 1);
            }
            
            if (!flag4 && !flag2)
            {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z + 1);
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z + 1);
            }
            
            if (!flag5 && !flag3)
            {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z - 1);
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z - 1);
            }
            
            if (!flag4 && !flag3)
            {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            }
            else
            {
                renderer.aoLightValueScratchXYZNPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z + 1);
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z + 1);
            }
            
            if (renderer.renderMinX <= 0.0D)
            {
                ++x;
            }
            
            i1 = l;
            
            if (renderer.renderMinX <= 0.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x - 1, y, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
                        x - 1, y, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess,
                    x - 1, y, z);
            f6 = (renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
            f3 = (f7 + renderer.aoLightValueScratchXZNP
                    + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXZNN + f7
                    + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYZNNN
                    + renderer.aoLightValueScratchXYNN
                    + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP,
                    renderer.aoBrightnessXZNP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP,
                    renderer.aoBrightnessXYZNPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN,
                    renderer.aoBrightnessXYNP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN,
                    renderer.aoBrightnessXZNN, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 4), 4,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y,
                        z, 5) && !connectEast)
        {
            if (renderer.renderMaxX >= 1.0D)
            {
                ++x;
            }
            
            renderer.aoLightValueScratchXYPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y - 1, z);
            renderer.aoLightValueScratchXZPN = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z - 1);
            renderer.aoLightValueScratchXZPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x, y,
                            z + 1);
            renderer.aoLightValueScratchXYPP = block
                    .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                            y + 1, z);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(
                    renderer.blockAccess, x, y + 1, z);
            flag3 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y + 1, z)];
            flag2 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y - 1, z)];
            flag5 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z + 1)];
            flag4 = Block.canBlockGrass[renderer.blockAccess.getBlockId(x + 1,
                    y, z - 1)];
            
            if (!flag2 && !flag4)
            {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z - 1);
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z - 1);
            }
            
            if (!flag2 && !flag5)
            {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPNP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y - 1, z + 1);
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y - 1, z + 1);
            }
            
            if (!flag3 && !flag4)
            {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPN = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z - 1);
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z - 1);
            }
            
            if (!flag3 && !flag5)
            {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            }
            else
            {
                renderer.aoLightValueScratchXYZPPP = block
                        .getAmbientOcclusionLightValue(renderer.blockAccess, x,
                                y + 1, z + 1);
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(
                        renderer.blockAccess, x, y + 1, z + 1);
            }
            
            if (renderer.renderMaxX >= 1.0D)
            {
                --x;
            }
            
            i1 = l;
            
            if (renderer.renderMaxX >= 1.0D
                    || !renderer.blockAccess.isBlockOpaqueCube(x + 1, y, z))
            {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess,
                        x + 1, y, z);
            }
            
            f7 = block.getAmbientOcclusionLightValue(renderer.blockAccess,
                    x + 1, y, z);
            f3 = (renderer.aoLightValueScratchXYPN
                    + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYZPNN
                    + renderer.aoLightValueScratchXYPN
                    + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
            f5 = (renderer.aoLightValueScratchXZPN + f7
                    + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
            f6 = (f7 + renderer.aoLightValueScratchXZPP
                    + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP,
                    renderer.aoBrightnessXZPP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP,
                    renderer.aoBrightnessXYZPPP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(
                    renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN,
                    renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(
                    renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN,
                    renderer.aoBrightnessXZPN, i1);
            
            if (flag1)
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;
            }
            else
            {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }
            
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 5), 5,
                    inverted, tessellator);
            
            flag = true;
        }
        
        renderer.enableAO = false;
        return flag;
    }
    
    private boolean renderStandardBlockWithColorMultiplier(
            RenderBlocks renderer, Block block, int x, int y, int z, float r,
            float g, float b, int meta, boolean inverted)
    {
        connectBottom = false;
        connectTop = false;
        connectEast = false;
        connectWest = false;
        connectNorth = false;
        connectSouth = false;
        
        BlockFrame frame = null;
        if (block instanceof BlockFrame)
            frame = (BlockFrame) block;
        
        Block innerBlock = null;
        if (frame != null)
        {
            innerBlock = frame.getInnerBlock(renderer.blockAccess
                    .getBlockMetadata(x, y, z));
            
            if (frame.textureRenderer != null)
            {
                ConnectedTextureBase text = new TransparentConnectedTexture(
                        frame.textureRenderer.blockID);
                connectBottom = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 0, 0);
                connectTop = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 1, 0);
                connectNorth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 2, 0);
                connectSouth = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 3, 0);
                connectWest = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 4, 0);
                connectEast = text.canConnectOnSide(renderer.blockAccess,
                        new BlockCoord(x, y, z), 5, 0);
            }
        }
        
        if (innerBlock == null)
        {
            connectBottom = false;
            connectTop = false;
            connectEast = false;
            connectWest = false;
            connectNorth = false;
            connectSouth = false;
        }
        
        renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * r;
        float f8 = f4 * g;
        float f9 = f4 * b;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;
        
        if (block != Block.grass)
        {
            f10 = f3 * r;
            f11 = f5 * r;
            f12 = f6 * r;
            f13 = f3 * g;
            f14 = f5 * g;
            f15 = f6 * g;
            f16 = f3 * b;
            f17 = f5 * b;
            f18 = f6 * b;
        }
        
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1,
                        z, 0) && !connectBottom)
        {
            tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1,
                            z));
            tessellator.setColorOpaque_F(f10, f13, f16);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 0), 0,
                    inverted, tessellator);
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1,
                        z, 1) && !connectTop)
        {
            tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1,
                            z));
            tessellator.setColorOpaque_F(f7, f8, f9);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 1), 1,
                    inverted, tessellator);
            flag = true;
        }
        
        Icon icon;
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z - 1, 2) && !connectNorth)
        {
            tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y,
                            z - 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 2), 2,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y,
                        z + 1, 3) && !connectSouth)
        {
            tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y,
                            z + 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 3), 3,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y,
                        z, 4) && !connectWest)
        {
            tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y,
                            z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 4), 4,
                    inverted, tessellator);
            
            flag = true;
        }
        
        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y,
                        z, 5) && !connectEast)
        {
            tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y,
                            z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            RenderFacing.renderFace(renderer, block, x, y, z,
                    block.getBlockTexture(renderer.blockAccess, x, y, z, 5), 5,
                    inverted, tessellator);
            
            flag = true;
        }
        
        return flag;
    }
    
}