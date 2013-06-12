package shukaro.artifice.block;

import java.util.List;

import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.compat.ArtificeRegistry;
import shukaro.artifice.util.IdMetaPair;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockArtifice extends ItemBlock
{
    public ItemBlockArtifice(int id)
    {
        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
    	if (!ArtificeConfig.tooltips.getBoolean(true))
    		return;
    	IdMetaPair pair = new IdMetaPair(stack.itemID, stack.getItemDamage());
    	if (ArtificeRegistry.getTooltipMap().get(pair) != null)
		{
    		for (String s : ArtificeRegistry.getTooltipMap().get(pair))
    		{
    			if (!ArtificeConfig.flavorText.getBoolean(true) && s.startsWith(ArtificeTooltips.commentCode))
    				continue;
    			infoList.add(s);
    		}
		}
    }
}
