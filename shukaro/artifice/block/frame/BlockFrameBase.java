package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import shukaro.artifice.ArtificeCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameBase extends BlockFrame
{
    public BlockFrameBase(int id)
    {
        super(id);
        this.textureName = "frame";
        setUnlocalizedName("artifice.frame");
        this.single = true;
    }
    
    @Override
    public Block getInnerBlock(int meta)
    {
        return null;
    }
    
    @Override
    public int getInnerMeta(int meta)
    {
        return 0;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public Icon getRenderIcon(int meta)
    {
    	return null;
    }
}
