package shukaro.artifice.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import shukaro.artifice.multiblock.erogenousbeef.MultiblockControllerBase;

public class MultiBlockController extends MultiblockControllerBase
{

    protected MultiBlockController(World world)
    {
        super(world);
    }

    @Override
    protected void onBlockAdded(IMultiblockPart newPart)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void onBlockRemoved(IMultiblockPart oldPart)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected int getMinimumNumberOfBlocksForAssembledMachine()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected void onMachineMerge(MultiblockControllerBase otherMachine)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToNBT(NBTTagCompound data)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void formatDescriptionPacket(NBTTagCompound data)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void decodeDescriptionPacket(NBTTagCompound data)
    {
        // TODO Auto-generated method stub
        
    }
    
}
