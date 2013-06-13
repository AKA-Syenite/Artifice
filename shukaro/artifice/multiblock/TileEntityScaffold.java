package shukaro.artifice.multiblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import shukaro.artifice.util.BlockCoord;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityScaffold extends TileEntity
{
	private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN };
	
	public boolean isRoot = false;
	public float northLoad = 0.0F;
	public float southLoad = 0.0F;
	public float eastLoad = 0.0F;
	public float westLoad = 0.0F;
	public Map<BlockCoord, Integer> roots = new HashMap<BlockCoord, Integer>();

	/**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setBoolean("isRoot", this.isRoot);
        tag.setFloat("northLoad", this.northLoad);
        tag.setFloat("southLoad", this.southLoad);
        tag.setFloat("eastLoad", this.eastLoad);
        tag.setFloat("westLoad", this.westLoad);
        
        List<BlockCoord> keyList = new ArrayList<BlockCoord>(roots.keySet());
        int[] rootList = new int[roots.size() * 3];
        for (BlockCoord c : keyList)
        {
        	int j = 0;
        	for (int i : c.intArray())
        	{
        		rootList[keyList.indexOf(c) * j] = i;
        		j++;
        	}
        }
        tag.setIntArray("roots", rootList);
        
        List<Integer> valueList = new ArrayList<Integer>(roots.values());
        int[] sideList = new int[roots.size()];
        for (int i : valueList)
        {
        	sideList[valueList.indexOf(i)] = i;
        }
        tag.setIntArray("sides", sideList);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.isRoot = tag.getBoolean("isRoot");
        this.northLoad = tag.getFloat("northLoad");
        this.southLoad = tag.getFloat("southLoad");
        this.eastLoad = tag.getFloat("eastLoad");
        this.westLoad = tag.getFloat("westLoad");
        
        int[] rootList = tag.getIntArray("roots");
        int[] sideList = tag.getIntArray("sides");
        for (int i=0; i<sideList.length; i++)
        {
        	BlockCoord c = new BlockCoord(rootList[i*3], rootList[i*3+1], rootList[i*3+2]);
        	roots.put(c, sideList[i]);
        }
    }
    
    public float getLoad(int side)
    {
    	switch (side)
    	{
    	case 2:
    		return this.northLoad;
    	case 3:
    		return this.southLoad;
    	case 4:
    		return this.westLoad;
    	case 5:
    		return this.eastLoad;
		default:
			return 0.0F;
    	}
    }
    
    public void setLoad(int side, float load)
    {
    	switch (side)
    	{
    	case 2:
    		this.northLoad = load;
    	case 3:
    		this.southLoad = load;
    	case 4:
    		this.westLoad = load;
    	case 5:
    		this.eastLoad = load;
    	}
    }
    
	@SuppressWarnings("unused")
	public void getRoots()
    {
    	BlockCoord c = new BlockCoord(this.xCoord, this.yCoord, this.zCoord);
		
		if (this.isRoot)
			return;
		
		for (int i=c.y-1; i>1; i--)
		{
			BlockCoord t = new BlockCoord(c.x, i, c.z);
			if (this.worldObj.isBlockSolidOnSide(t.x, t.y, t.z, ForgeDirection.UP, false))
			{
				if (t.getBlockID(this.worldObj) == c.getBlockID(this.worldObj))
				{
					if (t.getTileEntity(this.worldObj) != null && ((TileEntityScaffold)t.getTileEntity(this.worldObj)).isRoot)
					{
						this.isRoot = true;
						return;
					}
					else
						break;
				}
				else
				{
					this.isRoot = true;
					return;
				}
			}
			else
				break;
		}
		
		for (ForgeDirection d : sides)
		{
			BlockCoord temp = c.copy().offset(d.ordinal());
			if (temp.getBlock(this.worldObj) != null && (temp.getBlockID(this.worldObj) == c.getBlockID(this.worldObj) && temp.getMeta(this.worldObj) == c.getMeta(this.worldObj)))
			{
				TileEntityScaffold other = (TileEntityScaffold) temp.getTileEntity(this.worldObj);
				if (!other.isRoot)
				{
					if (other.roots.equals(this.roots))
						break;
					this.roots.putAll(other.roots);
					other.getRoots();
				}
				else
				{
					int side = c.getDirectionFromSourceCoords(temp.x, temp.y, temp.z).ordinal();
					this.roots.put(temp.copy(), side);
				}
			}
		}
    }
}
