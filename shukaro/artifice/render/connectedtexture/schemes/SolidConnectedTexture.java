package shukaro.artifice.render.connectedtexture.schemes;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
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
        BlockCoord self = coord.copy();
        Block neighbor = coord.offset(side).getBlock(blockAccess);
        BlockCoord other = coord.copy();
        Block cover = coord.offset(face).getBlock(blockAccess);
        ConnectedTexture neighborT = null;
        
        if (neighbor instanceof IConnectedTexture)
        {
            neighborT = ((IConnectedTexture) neighbor).getTextureType(face, meta);
            
            if (((IConnectedTexture) neighbor).getTextureRenderer(side, meta) instanceof SlabConnectedTexture)
                return false;
        }
        
        if (neighborT != null && cover != null)
            return !cover.isOpaqueCube() && this.texture.name == neighborT.name && self.blockEquals(blockAccess, other);
        else if (neighborT != null)
            return this.texture.name == neighborT.name && self.blockEquals(blockAccess, other);
        return false;
    }
}
