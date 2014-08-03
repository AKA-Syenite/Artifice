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
        if (block instanceof BlockRockSlab && block.getUnlocalizedName().contains("basalt"))
            return ArtificeBlocks.blockBasaltSlab;
        else if (block instanceof BlockRockSlab && block.getUnlocalizedName().contains("marble"))
            return ArtificeBlocks.blockMarbleSlab;
        else
        {
            if (block.getUnlocalizedName().contains("lightgray"))
                return ArtificeBlocks.blockLimestoneSlabs[1];
            if (block.getUnlocalizedName().contains("gray"))
                return ArtificeBlocks.blockLimestoneSlabs[0];
            if (block.getUnlocalizedName().contains("brown"))
                return ArtificeBlocks.blockLimestoneSlabs[2];
            if (block.getUnlocalizedName().contains("tan"))
                return ArtificeBlocks.blockLimestoneSlabs[3];
            if (block.getUnlocalizedName().contains("reddish"))
                return ArtificeBlocks.blockLimestoneSlabs[4];
            if (block.getUnlocalizedName().contains("bluish"))
                return ArtificeBlocks.blockLimestoneSlabs[5];
            else
                return ArtificeBlocks.blockLimestoneSlabs[6];
        }
    }

    private static BlockSlab getDoubleSlab(Block block)
    {
        if (block instanceof BlockRockSlab && block.getUnlocalizedName().contains("basalt"))
            return ArtificeBlocks.blockBasaltDoubleSlab;
        else if (block instanceof BlockRockSlab && block.getUnlocalizedName().contains("marble"))
            return ArtificeBlocks.blockMarbleDoubleSlab;
        else
        {
            if (block.getUnlocalizedName().contains("lightgray"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[1];
            if (block.getUnlocalizedName().contains("gray"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[0];
            if (block.getUnlocalizedName().contains("brown"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[2];
            if (block.getUnlocalizedName().contains("tan"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[3];
            if (block.getUnlocalizedName().contains("reddish"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[4];
            if (block.getUnlocalizedName().contains("bluish"))
                return ArtificeBlocks.blockLimestoneDoubleSlabs[5];
            else
                return ArtificeBlocks.blockLimestoneDoubleSlabs[6];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List infoList, boolean advancedTooltips)
    {
        if (!ArtificeConfig.tooltips.getBoolean(true))
            return;
        NameMetaPair pair = new NameMetaPair(stack.getUnlocalizedName(), stack.getItemDamage());
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
