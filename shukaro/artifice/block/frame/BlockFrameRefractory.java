package shukaro.artifice.block.frame;

import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockFrameRefractory extends BlockFrame
{
	public BlockFrameRefractory(int id)
	{
		super(id);
		setUnlocalizedName(super.getUnlocalizedName() + ".refractory");
		this.textureRenderer = new SolidConnectedTexture(this.blockID);
	}
	
	@Override
	public Block getInnerBlock(int meta)
	{
		return Block.brick;
	}
}
