package shukaro.artifice.block.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import shukaro.artifice.block.world.BlockFlora;

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
        if (meta > ArtificeCore.flora.length)
            return 0;
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        if (meta > ArtificeCore.flora.length)
            return BlockFlora.icons[0];
        return BlockFlora.icons[meta];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() > ArtificeCore.flora.length)
            return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeCore.flora[0].toLowerCase(Locale.ENGLISH);
        return Block.getBlockFromItem(stack.getItem()).getUnlocalizedName() + "." + ArtificeCore.flora[stack.getItemDamage()].toLowerCase(Locale.ENGLISH);
    }
}