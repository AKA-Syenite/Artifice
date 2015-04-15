package shukaro.artifice.block.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.render.TextureHandler;

import java.util.List;

public class BlockBasicOre extends Block
{
    private IIcon[] icons = new IIcon[2];
    private String name;

    public BlockBasicOre(String name, int harvestLevel)
    {
        super(Material.rock);
        this.setCreativeTab(ArtificeCore.worldTab);
        this.name = name;
        this.setBlockName("artifice." + name);
        this.setHarvestLevel("pickaxe", harvestLevel);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        GameRegistry.registerBlock(this, ItemBlockBasicOre.class, this.getUnlocalizedName());
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icons[0] = TextureHandler.registerIcon(reg, name + "Ore", "ores/" + name);
        this.icons[1] = TextureHandler.registerIcon(reg, name + "Block", "ores/" + name);
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta == 0)
            return this.icons[0];
        else
            return this.icons[1];
    }

    @Override
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(i, 1, 0));
        list.add(new ItemStack(i, 1, 1));
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
