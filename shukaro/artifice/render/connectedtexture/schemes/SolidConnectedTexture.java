package shukaro.artifice.render.connectedtexture.schemes;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.util.BlockCoord;

public class SolidConnectedTexture extends ConnectedTextureBase
{
    public SolidConnectedTexture(ConnectedTextures texture)
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
        Block cover = coord.copy().offset(side).offset(face).getBlock(blockAccess);
        
        if (self != null && neighbor != null && cover != null)
            return !cover.isOpaqueCube() && self.getIcon(face, meta).getIconName() == neighbor.getIcon(face, neighborMeta).getIconName() && (self.blockID == neighbor.blockID && meta == neighborMeta);
        else if (self != null && neighbor != null)
            return self.getIcon(face, meta).getIconName() == neighbor.getIcon(face, neighborMeta).getIconName() && (self.blockID == neighbor.blockID && meta == neighborMeta);
        return false;
    }
}
