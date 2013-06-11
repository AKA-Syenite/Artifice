package shukaro.artifice.block.decorative;

import java.util.List;

import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.IdMetaPair;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockSlabArtifice extends ItemSlab
{
	public ItemBlockSlabArtifice(int par1, BlockHalfSlab par2BlockHalfSlab, BlockHalfSlab par3BlockHalfSlab, boolean par4)
	{
		super(par1, par2BlockHalfSlab, par3BlockHalfSlab, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
    	IdMetaPair pair = new IdMetaPair(stack.itemID, stack.getItemDamage());
    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
		{
    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
    		{
    			infoList.add(s);
    		}
		}
    }
}
