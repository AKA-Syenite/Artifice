package shukaro.artifice.block.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;

public class BlockSteel extends Block
{
    private Icon icon;

    public BlockSteel(int id)
    {
        super(id, Material.iron);
        this.setCreativeTab(ArtificeCreativeTab.main);
        this.setHardness(3.0F);
        this.setUnlocalizedName("artifice.steel");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
        this.icon = IconHandler.registerSingle(reg, "steel", "misc");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return this.icon;
    }
}
