package shukaro.artifice.block.frame;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.IconHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrameBase extends BlockFrame
{
    private Icon[] icons = new Icon[ArtificeCore.tiers.length];
    
    public BlockFrameBase(int id)
    {
        super(id);
        setUnlocalizedName("artifice.frame");
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        for (int i=0; i<ArtificeCore.tiers.length; i++)
            icons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase(), "frame");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        return icons[block.getBlockMetadata(x, y, z)];
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }

	@Override
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
	}
}
