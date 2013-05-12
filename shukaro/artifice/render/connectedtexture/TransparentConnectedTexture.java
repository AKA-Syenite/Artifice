package shukaro.artifice.render.connectedtexture;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.util.BlockCoord;

public class TransparentConnectedTexture extends ConnectedTextureBase
{
    private final int[] textureIndexMap = new int[256];
    
    public TransparentConnectedTexture(int blockID)
    {
        super(blockID);
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
    
    private int getBlockID(IBlockAccess block, BlockCoord coord)
    {
        Block b = Block.blocksList[block.getBlockId(coord.x, coord.y, coord.z)];
        return b == null ? 0 : b.blockID;
    }
    
    private int getBlockMeta(IBlockAccess block, BlockCoord coord)
    {
        Block b = Block.blocksList[block.getBlockId(coord.x, coord.y, coord.z)];
        if (b == null)
            return 0;
        return block.getBlockMetadata(coord.x, coord.y, coord.z);
    }
    
    @Override
    public int getTextureFromMap(int map)
    {
        return this.textureIndexMap[map];
    }
    
    @Override
    public boolean canConnectOnSide(IBlockAccess blockAccess, BlockCoord coord,
            int side, int face)
    {
        BlockCoord copy = coord.copy();
        
        int block = getBlockID(blockAccess, coord.offset(side));
        
        int thisMeta = getBlockMeta(blockAccess, copy);
        int blockMeta = getBlockMeta(blockAccess, copy.offset(side));
        
        return (block == this.blockID) && (blockMeta == thisMeta);
    }
    
}
