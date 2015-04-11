package shukaro.artifice.block.world;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.TextureHandler;

import java.util.List;

public class BlockTephra extends BlockFalling
{
    private IIcon blackSand;
    private IIcon blackGravel;

    public BlockTephra()
    {
        super();
        this.setBlockName("artifice.tephra");
        this.setCreativeTab(ArtificeCore.worldTab);
        this.setStepSound(Block.soundTypeGravel);
        this.setHardness(0.5f);
        GameRegistry.registerBlock(this, ItemBlockTephra.class, this.getUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        blackSand = TextureHandler.registerIcon(reg, "blackSand", "misc");
        blackGravel = TextureHandler.registerIcon(reg, "blackGravel", "misc");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return meta > 0 ? blackGravel : blackSand;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }
}
