package shukaro.artifice.render.connectedtexture;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.util.BlockCoord;

public class TransparentConnectedTexture extends ConnectedTextureBase
{
    public TransparentConnectedTexture(ConnectedTexture texture)
    {
        super(texture);
    }
    
    @Override
    public int getTextureFromMap(int map)
    {
        return this.textureIndexMap[map];
    }
    
    @Override
    public boolean canConnectOnSide(IBlockAccess blockAccess, BlockCoord coord, int side, int face)
    {
    	ConnectedTexture neighbor = null;
    	int meta = coord.getMeta(blockAccess);
    	BlockCoord self = coord.copy();
    	
        if (coord.offset(side).getBlock(blockAccess) instanceof IConnectedTexture)
        {
        	neighbor = ((IConnectedTexture) coord.getBlock(blockAccess)).getTextureType(face, meta);
        }
        
        BlockCoord other = coord.copy();
        
        return neighbor != null && neighbor.name == this.texture.name && self.blockEquals(blockAccess, other);
    }
    
}
