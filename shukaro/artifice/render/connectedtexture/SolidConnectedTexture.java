package shukaro.artifice.render.connectedtexture;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.util.BlockCoord;

public class SolidConnectedTexture extends ConnectedTextureBase
{
    public SolidConnectedTexture(ConnectedTexture texture)
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
    	int meta = coord.getMeta(blockAccess);
    	Block neighbor = coord.offset(side).getBlock(blockAccess);
    	Block cover = coord.offset(face).getBlock(blockAccess);
    	ConnectedTexture neighborT = null;
    	
    	if (neighbor instanceof IConnectedTexture)
    	{
    		neighborT = ((IConnectedTexture) neighbor).getTextureType(face, meta);
    		
    		if (((IConnectedTexture) neighbor).getTextureRenderer(side, meta) instanceof SlabConnectedTexture)
    			return false;
    	}
    	
    	if (neighborT != null && cover != null)
    		return !cover.isOpaqueCube() && this.texture.name == neighborT.name;
    	else if (neighborT != null)
    		return this.texture.name == neighborT.name;
    	return false;
    }
}
