package shukaro.artifice.block.decorative;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.ArtificeCreativeTab;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLotus extends BlockLilyPad
{
    public static Icon lotus;
    public static Icon lotusClosed;
    
    public BlockLotus(int par1)
    {
        super(par1);
        this.setCreativeTab(ArtificeCreativeTab.tab);
        this.setUnlocalizedName("artifice.flora.lily");
    }
    
    @Override
    public int getRenderType()
    {
        return ArtificeCore.lotusRenderID;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        int meta = world.getBlockMetadata(x, y, z);
        
        if (meta == 0 && (world.getWorldTime() > 12000))
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        if (meta == 1 && (world.getWorldTime() <= 12000))
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return meta == 0 ? lotus : lotusClosed;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        lotus = reg.registerIcon("artifice:flora_waterlotus");
        lotusClosed = reg.registerIcon("artifice:flora_waterlotusclosed");
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta == 1 ? 0 : meta;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 1 ? 0 : world.getBlockMetadata(x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 16777215;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 16777215;
    }
}
