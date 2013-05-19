package shukaro.artifice.block.decorative;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.ItemBlockArtifice;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlora extends ItemBlockArtifice
{
    public ItemBlockFlora(int id)
    {
        super(id);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return BlockFlora.textureList[meta];
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.flora[stack.getItemDamage()].toLowerCase();
    }
}
