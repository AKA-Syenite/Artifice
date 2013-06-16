package shukaro.artifice.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.render.IconHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockSteel extends Block
{
	public Icon icon;
	
	public BlockSteel(int id)
	{
		super(id, Material.iron);
		this.setCreativeTab(ArtificeCreativeTab.tab);
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
