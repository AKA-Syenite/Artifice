package shukaro.artifice.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeConfig;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class LotusRenderer implements ISimpleBlockRenderingHandler
{

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // Don't do anything
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        renderer.renderBlockLilyPad(Block.waterlily, x, y, z);
        renderer.renderCrossedSquares(block, x, y, z);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    @Override
    public int getRenderId()
    {
        return ArtificeConfig.lotusRenderID;
    }
    
}
