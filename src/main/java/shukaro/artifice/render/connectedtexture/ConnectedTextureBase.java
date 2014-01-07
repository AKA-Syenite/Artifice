package shukaro.artifice.render.connectedtexture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.util.BlockCoord;

public abstract class ConnectedTextureBase
{
    public ConnectedTextures texture;
    protected final int[] textureIndexMap = new int[256];
    
    public ConnectedTextureBase(ConnectedTextures texture)
    {
        this.texture = texture;
        loadTextureMap();
    }
    
    private void loadTextureMap()
    {
        this.textureIndexMap[0] = 26;
        this.textureIndexMap[1] = 21;
        this.textureIndexMap[4] = 20;
        this.textureIndexMap[5] = 44;
        this.textureIndexMap[7] = 14;
        this.textureIndexMap[16] = 8;
        this.textureIndexMap[17] = 34;
        this.textureIndexMap[20] = 32;
        this.textureIndexMap[21] = 22;
        this.textureIndexMap[23] = 29;
        this.textureIndexMap[28] = 27;
        this.textureIndexMap[29] = 43;
        this.textureIndexMap[31] = 15;
        this.textureIndexMap[64] = 9;
        this.textureIndexMap[65] = 45;
        this.textureIndexMap[68] = 35;
        this.textureIndexMap[69] = 23;
        this.textureIndexMap[71] = 31;
        this.textureIndexMap[80] = 33;
        this.textureIndexMap[81] = 11;
        this.textureIndexMap[84] = 10;
        this.textureIndexMap[85] = 46;
        this.textureIndexMap[87] = 7;
        this.textureIndexMap[92] = 41;
        this.textureIndexMap[93] = 19;
        this.textureIndexMap[95] = 5;
        this.textureIndexMap[112] = 38;
        this.textureIndexMap[113] = 40;
        this.textureIndexMap[116] = 42;
        this.textureIndexMap[117] = 18;
        this.textureIndexMap[119] = 2;
        this.textureIndexMap[124] = 39;
        this.textureIndexMap[125] = 17;
        this.textureIndexMap[127] = 3;
        this.textureIndexMap[193] = 25;
        this.textureIndexMap[197] = 28;
        this.textureIndexMap[199] = 13;
        this.textureIndexMap[209] = 30;
        this.textureIndexMap[213] = 6;
        this.textureIndexMap[215] = 4;
        this.textureIndexMap[221] = 24;
        this.textureIndexMap[223] = 12;
        this.textureIndexMap[241] = 37;
        this.textureIndexMap[245] = 16;
        this.textureIndexMap[247] = 1;
        this.textureIndexMap[253] = 36;
        this.textureIndexMap[255] = 0;
    }
    
    public int getTextureIndex(IBlockAccess block, int x, int y, int z, int side)
    {
        BlockCoord coord = new BlockCoord();
        int[][] sideSideMap = {
                { 2, 5, 3, 4 },
                { 2, 5, 3, 4 },
                { 1, 4, 0, 5 },
                { 1, 5, 0, 4 },
                { 1, 3, 0, 2 },
                { 1, 2, 0, 3 }};
        
        int map = 0;
        for (int i = 0; i < 4; i++)
        {
            int side0 = sideSideMap[side][((i + 3) % 4)];
            int side1 = sideSideMap[side][i];
            if (!canConnectOnSide(block, coord.set(x, y, z), sideSideMap[side][i], side))
                map |= (7 << i * 2) % 256 | 7 >>> 8 - i * 2;
            else if ((!canConnectOnSide(block, coord.set(x, y, z).offset(side0), side1, side)) || (!canConnectOnSide(block, coord.set(x, y, z).offset(side1), side0, side)))
                map |= 1 << i * 2;
        }
        return getTextureFromMap(map);
    }
    
    protected abstract int getTextureFromMap(int map);
    
    protected abstract boolean canConnectOnSide(IBlockAccess block, BlockCoord coord, int side, int face);
}
