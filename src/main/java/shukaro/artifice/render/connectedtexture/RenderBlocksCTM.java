package shukaro.artifice.render.connectedtexture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import shukaro.artifice.block.world.BlockRock;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.util.NameMetaPair;

public class RenderBlocksCTM extends RenderBlocks
{
    public RenderBlocksCTM()
    {
        super();
        resetVertices();
    }

    Tessellator tessellator;
    double[] X = new double[26];
    double[] Y = new double[26];
    double[] Z = new double[26];
    double[] U = new double[26];
    double[] V = new double[26];
    int[] L = new int[26];
    float[] R = new float[26];
    float[] G = new float[26];
    float[] B = new float[26];
    TextureSubmap submap;
    TextureSubmap submapSmall;
    RenderBlocks rendererOld;

    int bx, by, bz;

    @Override
    public boolean renderStandardBlock(Block block, int x, int y, int z)
    {
        bx = x;
        by = y;
        bz = z;

        tessellator = Tessellator.instance;
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        tessellator.addTranslation(x, y, z);

        boolean res = super.renderStandardBlock(block, x, y, z);

        tessellator.addTranslation(-x, -y, -z);

        return res;
    }

    void setupSides(int a, int b, int c, int d, int xa, int xb, int xc, int xd, int e)
    {
        L[a] = brightnessBottomLeft;
        L[b] = brightnessBottomRight;
        L[c] = brightnessTopRight;
        L[d] = brightnessTopLeft;
        L[e] = (brightnessBottomLeft + brightnessTopLeft + brightnessTopRight + brightnessBottomRight) / 4;
        L[xa] = (L[a] + L[b]) / 2;
        L[xb] = (L[b] + L[c]) / 2;
        L[xc] = (L[c] + L[d]) / 2;
        L[xd] = (L[d] + L[a]) / 2;

        R[a] = colorRedBottomLeft;
        R[b] = colorRedBottomRight;
        R[c] = colorRedTopRight;
        R[d] = colorRedTopLeft;
        R[e] = (colorRedBottomLeft + colorRedTopLeft + colorRedTopRight + colorRedBottomRight) / 4;
        R[xa] = (R[a] + R[b]) / 2;
        R[xb] = (R[b] + R[c]) / 2;
        R[xc] = (R[c] + R[d]) / 2;
        R[xd] = (R[d] + R[a]) / 2;

        G[a] = colorGreenBottomLeft;
        G[b] = colorGreenBottomRight;
        G[c] = colorGreenTopRight;
        G[d] = colorGreenTopLeft;
        G[e] = (colorGreenBottomLeft + colorGreenTopLeft + colorGreenTopRight + colorGreenBottomRight) / 4;
        G[xa] = (G[a] + G[b]) / 2;
        G[xb] = (G[b] + G[c]) / 2;
        G[xc] = (G[c] + G[d]) / 2;
        G[xd] = (G[d] + G[a]) / 2;

        B[a] = colorBlueBottomLeft;
        B[b] = colorBlueBottomRight;
        B[c] = colorBlueTopRight;
        B[d] = colorBlueTopLeft;
        B[e] = (colorBlueBottomLeft + colorBlueTopLeft + colorBlueTopRight + colorBlueBottomRight) / 4;
        B[xa] = (B[a] + B[b]) / 2;
        B[xb] = (B[b] + B[c]) / 2;
        B[xc] = (B[c] + B[d]) / 2;
        B[xd] = (B[d] + B[a]) / 2;
    }

    void side(int a, int b, int c, int d, int iconIndex, boolean flip)
    {
        IIcon icon = iconIndex >= 16 ? submapSmall.icons[iconIndex - 16] : submap.icons[iconIndex];

        double u0 = icon.getMaxU();
        double u1 = icon.getMinU();
        double v0 = icon.getMaxV();
        double v1 = icon.getMinV();

        U[a] = flip ? u1 : u1;
        U[b] = flip ? u0 : u1;
        U[c] = flip ? u0 : u0;
        U[d] = flip ? u1 : u0;

        V[a] = flip ? v1 : v1;
        V[b] = flip ? v1 : v0;
        V[c] = flip ? v0 : v0;
        V[d] = flip ? v0 : v1;

        vert(a);
        vert(b);
        vert(c);
        vert(d);
    }

    void vert(int index)
    {
        if(enableAO)
        {
            tessellator.setColorOpaque_F(R[index], G[index], B[index]);
            tessellator.setBrightness(L[index]);
        }

        tessellator.addVertexWithUV(X[index], Y[index], Z[index], U[index], V[index]);
    }

    @Override
    public void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 4);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(4, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(0.0, 1.0, 0.0, i.getMinU(), i.getMinV());
            tessellator.addVertexWithUV(0.0, 0.0, 0.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(0.0, 0.0, 1.0, i.getMaxU(), i.getMaxV());
            tessellator.addVertexWithUV(0.0, 1.0, 1.0, i.getMaxU(), i.getMinV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 4);

            setupSides(1, 0, 4, 5, 14, 19, 17, 23, 9);
            side(1, 14, 9, 23, tex[0], false);
            side(23, 9, 17, 5, tex[1], false);
            side(9, 19, 4, 17, tex[3], false);
            side(14, 0, 19, 9, tex[2], false);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }

    @Override
    public void renderFaceXPos(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 5);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(5, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(1.0, 1.0, 1.0, i.getMaxU(), i.getMinV());
            tessellator.addVertexWithUV(1.0, 0.0, 1.0, i.getMaxU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 0.0, 0.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 1.0, 0.0, i.getMinU(), i.getMinV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 5);

            setupSides(3, 2, 6, 7, 15, 25, 16, 21, 11);
            side(11, 21, 3, 15, tex[3], false);
            side(16, 7, 21, 11, tex[2], false);
            side(25, 11, 15, 2, tex[1], false);
            side(6, 16, 11, 25, tex[0], false);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }

    @Override
    public void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 2);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(2, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(1.0, 1.0, 0.0, i.getMaxU(), i.getMinV());
            tessellator.addVertexWithUV(1.0, 0.0, 0.0, i.getMaxU(), i.getMaxV());
            tessellator.addVertexWithUV(0.0, 0.0, 0.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(0.0, 1.0, 0.0, i.getMinU(), i.getMinV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 2);

            setupSides(2, 3, 0, 1, 15, 18, 14, 22, 8);
            side(2, 15, 8, 22, tex[0], false);
            side(15, 3, 18, 8, tex[2], false);
            side(8, 18, 0, 14, tex[3], false);
            side(22, 8, 14, 1, tex[1], false);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }


    @Override
    public void renderFaceZPos(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 3);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(3, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(0.0, 1.0, 1.0, i.getMinU(), i.getMinV());
            tessellator.addVertexWithUV(0.0, 0.0, 1.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 0.0, 1.0, i.getMaxU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 1.0, 1.0, i.getMaxU(), i.getMinV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 3);

            setupSides(4, 7, 6, 5, 20, 16, 24, 17, 10);
            side(17, 4, 20, 10, tex[2], false);
            side(5, 17, 10, 24, tex[0], false);
            side(24, 10, 16, 6, tex[1], false);
            side(10, 20, 7, 16, tex[3], false);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }

    @Override
    public void renderFaceYNeg(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 0);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(0, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(0.0, 0.0, 1.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(0.0, 0.0, 0.0, i.getMinU(), i.getMinV());
            tessellator.addVertexWithUV(1.0, 0.0, 0.0, i.getMaxU(), i.getMinV());
            tessellator.addVertexWithUV(1.0, 0.0, 1.0, i.getMaxU(), i.getMaxV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 0);

            setupSides(0, 3, 7, 4, 18, 21, 20, 19, 13);
            side(13, 21, 7, 20, tex[3], true);
            side(19, 13, 20, 4, tex[2], true);
            side(0, 18, 13, 19, tex[0], true);
            side(18, 3, 21, 13, tex[1], true);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }

    @Override
    public void renderFaceYPos(Block block, double x, double y, double z, IIcon icon)
    {
        int meta = blockAccess.getBlockMetadata(bx, by, bz);
        ConnectedTexture texture = TextureHandler.getConnectedTexture(block, meta, 1);
        if (texture == null)
            rendererOld.setOverrideBlockTexture(block.getIcon(1, meta));
        else
        {
            this.submap = texture.submap;
            this.submapSmall = texture.submapSmall;
        }

        if(rendererOld != null && rendererOld.hasOverrideBlockTexture())
        {
            IIcon i = rendererOld.overrideBlockTexture;

            tessellator.addVertexWithUV(0.0, 1.0, 0.0, i.getMinU(), i.getMinV());
            tessellator.addVertexWithUV(0.0, 1.0, 1.0, i.getMinU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 1.0, 1.0, i.getMaxU(), i.getMaxV());
            tessellator.addVertexWithUV(1.0, 1.0, 0.0, i.getMaxU(), i.getMinV());
        } else
        {
            int tex[] = CTM.getSubmapIndices(blockAccess, bx, by, bz, 1);

            setupSides(2, 1, 5, 6, 22, 23, 24, 25, 12);
            side(12, 24, 6, 25, tex[3], false);
            side(22, 12, 25, 2, tex[1], false);
            side(1, 23, 12, 22, tex[0], false);
            side(23, 5, 24, 12, tex[2], false);
        }
        if (texture == null)
            rendererOld.clearOverrideBlockTexture();
    }

    void resetVertices()
    {
        X[0] = 0;
        Z[0] = 0;
        Y[0] = 0;

        X[1] = 0;
        Z[1] = 0;
        Y[1] = 1;

        X[2] = 1;
        Z[2] = 0;
        Y[2] = 1;

        X[3] = 1;
        Z[3] = 0;
        Y[3] = 0;

        X[4] = 0;
        Z[4] = 1;
        Y[4] = 0;

        X[5] = 0;
        Z[5] = 1;
        Y[5] = 1;

        X[6] = 1;
        Z[6] = 1;
        Y[6] = 1;

        X[7] = 1;
        Z[7] = 1;
        Y[7] = 0;

        X[8] = 0.5;
        Z[8] = 0;
        Y[8] = 0.5;

        X[9] = 0;
        Z[9] = 0.5;
        Y[9] = 0.5;

        X[10] = 0.5;
        Z[10] = 1;
        Y[10] = 0.5;

        X[11] = 1;
        Z[11] = 0.5;
        Y[11] = 0.5;

        X[12] = 0.5;
        Z[12] = 0.5;
        Y[12] = 1;

        X[13] = 0.5;
        Z[13] = 0.5;
        Y[13] = 0;

        X[14] = 0;
        Z[14] = 0;
        Y[14] = 0.5;

        X[15] = 1;
        Z[15] = 0;
        Y[15] = 0.5;

        X[16] = 1;
        Z[16] = 1;
        Y[16] = 0.5;

        X[17] = 0;
        Z[17] = 1;
        Y[17] = 0.5;

        X[18] = 0.5;
        Z[18] = 0;
        Y[18] = 0;

        X[19] = 0;
        Z[19] = 0.5;
        Y[19] = 0;

        X[20] = 0.5;
        Z[20] = 1;
        Y[20] = 0;

        X[21] = 1;
        Z[21] = 0.5;
        Y[21] = 0;

        X[22] = 0.5;
        Z[22] = 0;
        Y[22] = 1;

        X[23] = 0;
        Z[23] = 0.5;
        Y[23] = 1;

        X[24] = 0.5;
        Z[24] = 1;
        Y[24] = 1;

        X[25] = 1;
        Z[25] = 0.5;
        Y[25] = 1;
    }
}