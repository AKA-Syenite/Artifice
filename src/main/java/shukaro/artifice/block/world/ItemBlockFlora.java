package shukaro.artifice.block.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.block.ItemBlockArtifice;

import java.util.Locale;

public class ItemBlockFlora extends ItemBlockArtifice
{
    public ItemBlockFlora(Block block)
    {
        super(block);
    }

    @Override
    public int getMetadata(int meta)
    {
        if (meta > ArtificeConfig.flora.length)
            return 0;
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        if (meta > ArtificeConfig.flora.length)
            return BlockFlora.icons[0];
        return BlockFlora.icons[meta];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() > ArtificeConfig.flora.length)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.flora[0].toLowerCase(Locale.ENGLISH);
        return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeConfig.flora[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}