package shukaro.artifice.block;

import net.minecraft.item.ItemBlock;

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
}
