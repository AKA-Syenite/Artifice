package shukaro.artifice.block.world;

import cofh.util.ItemHelper;
import cofh.core.util.oredict.OreDictionaryArbiter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ClientProxy;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.util.NameMetaPair;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BlockOre extends BlockArtifice
{
    private IIcon icon;
    private String name;

    public BlockOre(String name)
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setBlockName("artifice." + name.toLowerCase(Locale.ENGLISH));
        setCreativeTab(ArtificeCore.worldTab);
        this.name = name;

        setHarvestLevel("pickaxe", 2);
        if (name.equals("oreCoal") || name.equals("oreSulfur"))
            setHarvestLevel("pickaxe", 0);
        else if (name.equals("oreIron") || name.equals("oreLapis") || name.equals("oreCopper") || name.equals("oreTin"))
            setHarvestLevel("pickaxe", 1);
        else if (name.equals("oreGold") || name.equals("oreDiamond") || name.equals("oreRedstone") || name.equals("oreEmerald"))
            setHarvestLevel("pickaxe", 2);
    }

    @Override
    public int quantityDropped(Random rand)
    {
        if (name.equals("oreLapis"))
            return Blocks.lapis_ore.quantityDropped(rand);
        else if (name.equals("oreRedstone"))
            return Blocks.redstone_ore.quantityDropped(rand);
        else if (name.equals("oreSulfur"))
            return 4 + rand.nextInt(2);
        return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random rand)
    {
        if (name.equals("oreLapis"))
            return Blocks.lapis_ore.quantityDropped(rand) + rand.nextInt(fortune + 1);
        else if (name.equals("oreRedstone"))
            return Blocks.redstone_ore.quantityDropped(rand) + rand.nextInt(fortune + 1);
        else if (name.equals("oreSulfur"))
            return this.quantityDropped(rand) + rand.nextInt(fortune + 1);
        return this.quantityDropped(rand);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        if (name.equals("oreCoal"))
            return Blocks.coal_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreIron"))
            return Blocks.iron_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreLapis"))
            return Blocks.lapis_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreGold"))
            return Blocks.gold_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreDiamond"))
            return Blocks.diamond_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreRedstone"))
            return Blocks.redstone_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreEmerald"))
            return Blocks.redstone_ore.getItemDropped(0, rand, fortune);
        else if (name.equals("oreSulfur"))
            return ArtificeBlocks.blockSulfur.getItemDropped(0, rand, fortune);
        else
            return ItemHelper.getOre(name).getItem();
    }

    @Override
    public int damageDropped(int meta)
    {
        if (name.equals("oreLapis"))
            return Blocks.lapis_ore.damageDropped(meta);
        else
            return 0;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        icon = IconHandler.registerSingle(reg, name, "ores");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icon;
    }

    @Override
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        return icon;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        for (int i=0; i<ArtificeBlocks.rockBlocks.length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public boolean canRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }

    @Override
    public int getRenderBlockPass() { return 1; }

    @Override
    public boolean isOpaqueCube() { return true; }

    @Override
    public int getRenderType() { return ArtificeConfig.oreRenderID; }

    public static NameMetaPair getOre(NameMetaPair ore, Block rock)
    {
        String oreName = ItemHelper.getOreName(ore.getStack());
        if (!oreName.equals(OreDictionaryArbiter.UNKNOWN))
        {
            for (int i=0; i<ArtificeBlocks.rockBlocks.length; i++)
            {
                if (ArtificeBlocks.rockBlocks[i].equals(rock))
                    return new NameMetaPair(ArtificeBlocks.oreMappings.get(oreName), i);
            }
        }
        return null;
    }

    public static class BlockOreDummy extends Block
    {
        public BlockOreDummy()
        {
            super(Material.rock);
        }

        @Override
        public IIcon getIcon(int side, int meta)
        {
            return ArtificeBlocks.rockBlocks[meta].getIcon(side, 0);
        }

        @Override
        public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
        {
            int meta = block.getBlockMetadata(x, y, z);
            return ArtificeBlocks.rockBlocks[meta].getIcon(side, 0);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getRenderColor(int meta)
        {
            return ArtificeBlocks.rockBlocks[meta].getRenderColor(meta);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int colorMultiplier(IBlockAccess block, int x, int y, int z)
        {
            int meta = block.getBlockMetadata(x, y, z);
            return ArtificeBlocks.rockBlocks[meta].getRenderColor(meta);
        }
    }
}
