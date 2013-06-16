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
    
    @Override
    public int getMetadata(int meta)
    {
    	if (meta > ArtificeCore.flora.length)
    		return 0;
        return meta;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta)
    {
    	if (meta > ArtificeCore.flora.length)
    		return BlockFlora.icons[0];
        return BlockFlora.icons[meta];
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
    	if (stack.getItemDamage() > ArtificeCore.flora.length)
    		return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.flora[0].toLowerCase();
        return Block.blocksList[stack.itemID].getUnlocalizedName() + "." + ArtificeCore.flora[stack.getItemDamage()].toLowerCase();
    }
}