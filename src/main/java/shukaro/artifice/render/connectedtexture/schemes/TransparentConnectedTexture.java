package shukaro.artifice.render.connectedtexture.schemes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;

public class TransparentConnectedTexture extends ConnectedTextureBase
{
    public TransparentConnectedTexture(ConnectedTextures texture)
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
        int neighborMeta = coord.copy().offset(side).getMeta(blockAccess);
        Block self = coord.getBlock(blockAccess);
        Block neighbor = coord.copy().offset(side).getBlock(blockAccess);

        // Temporary
        if (self instanceof BlockSlab != neighbor instanceof BlockSlab)
            return false;
        if (self.getUnlocalizedName().contains("double") != neighbor.getUnlocalizedName().contains("double"))
            return false;
        if(self.colorMultiplier(blockAccess, coord.x, coord.y, coord.z) != neighbor.colorMultiplier(blockAccess, coord.x, coord.y, coord.z))
            return false;

        if (self != null && neighbor != null)
            return self.getIcon(face, meta) != null && neighbor.getIcon(face, neighborMeta) != null && self.getIcon(face, meta).getIconName().equals(neighbor.getIcon(face, neighborMeta).getIconName());
        return false;
    }

}
