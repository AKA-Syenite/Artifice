package shukaro.artifice.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.tile.TileEntityHeatingCoil;
import shukaro.artifice.util.FormatCodes;
import shukaro.artifice.util.MiscUtils;

public class BlockHeatingCoil extends Block implements ITileEntityProvider
{
    private IIcon powered;
    private IIcon unpowered;

    public BlockHeatingCoil()
    {
        super(Material.iron);
        this.setCreativeTab(ArtificeCore.mainTab);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setBlockName("artifice.heatingcoil");
        GameRegistry.registerBlock(this, ItemBlockArtifice.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        TextureHandler.registerConnectedTexture(reg, this, 0, "unpowered", "coil");
        TextureHandler.registerConnectedTexture(reg, this, 1, "powered", "coil");
        this.unpowered = TextureHandler.getConnectedTexture(this, 0, 0).icon;
        this.powered = TextureHandler.getConnectedTexture(this, 1, 0).icon;
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return meta == 0 ? this.unpowered : this.powered;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType() { return ArtificeConfig.ctmRenderID; }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 0)
            return 0;
        return 7;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) { return true; }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityHeatingCoil();
    }

    @Override
    public boolean hasTileEntity(int metadata) { return true; }
}
