package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;

public class BlockFrameRefractory extends BlockFrame
{
    public BlockFrameRefractory(int id)
    {
        super(id);
        setUnlocalizedName("artifice.refractory");
        this.textureRenderer = new SolidConnectedTexture(this.blockID);
    }
    
    @Override
    public Block getInnerBlock(int meta)
    {
        switch (meta)
        {
            case 0:
                return Block.stoneBrick;
            case 1:
                return Block.brick;
            case 2:
                return Block.obsidian;
            case 3:
                return Block.netherBrick;
            default:
                return null;
        }
    }
    
    @Override
    public int getInnerMeta(int meta)
    {
        return 0;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }
}
