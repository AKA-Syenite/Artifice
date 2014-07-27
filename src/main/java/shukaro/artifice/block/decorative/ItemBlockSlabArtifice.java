package shukaro.artifice.block.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeRegistry;
import shukaro.artifice.ArtificeTooltips;
import shukaro.artifice.util.NameMetaPair;

import java.util.List;

public class ItemBlockSlabArtifice extends ItemSlab
{
    public ItemBlockSlabArtifice(Block block)
    {
        super(block, getHalfSlab(block), getDoubleSlab(block), block.isOpaqueCube());
    }

    private static BlockSlab getHalfSlab(Block block)
    {
        if (block instanceof BlockBasaltSlab) return ArtificeBlocks.blockBasaltSlab;
        else return ArtificeBlocks.blockMarbleSlab;
    }

    private static BlockSlab getDoubleSlab(Block block)
    {
        if (block instanceof BlockBasaltSlab) return ArtificeBlocks.blockBasaltDoubleSlab;
        else return ArtificeBlocks.blockMarbleDoubleSlab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips.getBoolean(true))
            return;
        NameMetaPair pair = new NameMetaPair(stack.getItem(), stack.getItemDamage());
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
