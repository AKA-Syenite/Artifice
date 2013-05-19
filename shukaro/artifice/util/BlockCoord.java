package shukaro.artifice.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class BlockCoord implements Comparable
{
    public int x;
    public int y;
    public int z;
    public static final BlockCoord[] sideOffsets =
        { new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0),
                new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1),
                new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0) };
    
    public static final BlockCoord[] nearbyOffsets =
        { new BlockCoord(-1, 1, 1), new BlockCoord(0, 1, 1),
                new BlockCoord(1, 1, 1), new BlockCoord(-1, 0, 1),
                new BlockCoord(0, 0, 1), new BlockCoord(1, 0, 1),
                new BlockCoord(-1, -1, 1), new BlockCoord(0, -1, 1),
                new BlockCoord(1, -1, 1),
                
                new BlockCoord(-1, 1, 0), new BlockCoord(0, 1, 0),
                new BlockCoord(1, 1, 0), new BlockCoord(-1, 0, 0),
                new BlockCoord(1, 0, 0), new BlockCoord(-1, -1, 0),
                new BlockCoord(0, -1, 0), new BlockCoord(1, -1, 0),
                
                new BlockCoord(-1, 1, -1), new BlockCoord(0, 1, -1),
                new BlockCoord(1, 1, -1), new BlockCoord(-1, 0, -1),
                new BlockCoord(0, 0, -1), new BlockCoord(1, 0, -1),
                new BlockCoord(-1, -1, -1), new BlockCoord(0, -1, -1),
                new BlockCoord(1, -1, -1) };
    
    public BlockCoord(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public BlockCoord(TileEntity tile)
    {
        this(tile.xCoord, tile.yCoord, tile.zCoord);
    }
    
    public BlockCoord(int[] array)
    {
        this(array[0], array[1], array[2]);
    }
    
    public BlockCoord()
    {
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (!(o instanceof BlockCoord))
            return false;
        BlockCoord t = (BlockCoord) o;
        return (this.x == t.x) && (this.y == t.y) && (this.z == t.z);
    }
    
    @Override
    public int hashCode()
    {
        return (this.x ^ this.z) * 31 + this.y;
    }
    
    @Override
    public int compareTo(Object o)
    {
        if (o instanceof BlockCoord)
        {
            BlockCoord t = (BlockCoord) o;
            if (this.x < t.x)
            {
                return -1;
            }
            else if (this.x > t.x)
            {
                return 1;
            }
            else if (this.y < t.y)
            {
                return -1;
            }
            else if (this.y > t.y)
            {
                return 1;
            }
            else if (this.z < t.z)
            {
                return -1;
            }
            else if (this.z > t.z)
            {
                return 1;
            }
        }
        return 0;
    }
    
    public ForgeDirection getDirectionFromSourceCoords(int x, int y, int z)
    {
        if (this.x < x)
        {
            return ForgeDirection.WEST;
        }
        else if (this.x > x)
        {
            return ForgeDirection.EAST;
        }
        else if (this.y < y)
        {
            return ForgeDirection.DOWN;
        }
        else if (this.y > y)
        {
            return ForgeDirection.UP;
        }
        else if (this.z < z)
        {
            return ForgeDirection.SOUTH;
        }
        else if (this.z > z)
        {
            return ForgeDirection.NORTH;
        }
        else
        {
            return ForgeDirection.UNKNOWN;
        }
    }
    
    public ForgeDirection getOppositeDirectionFromSourceCoords(int x, int y, int z)
    {
        if (this.x < x)
        {
            return ForgeDirection.EAST;
        }
        else if (this.x > x)
        {
            return ForgeDirection.WEST;
        }
        else if (this.y < y)
        {
            return ForgeDirection.UP;
        }
        else if (this.y > y)
        {
            return ForgeDirection.DOWN;
        }
        else if (this.z < z)
        {
            return ForgeDirection.NORTH;
        }
        else if (this.z > z)
        {
            return ForgeDirection.SOUTH;
        }
        else
        {
            return ForgeDirection.UNKNOWN;
        }
    }
    
    public BlockCoord multiply(int i)
    {
        this.x *= i;
        this.y *= i;
        this.z *= i;
        return this;
    }
    
    public boolean isZero()
    {
        return (this.x == 0) && (this.y == 0) && (this.z == 0);
    }
    
    public BlockCoord add(BlockCoord t)
    {
        this.x += t.x;
        this.y += t.y;
        this.z += t.z;
        return this;
    }
    
    public BlockCoord add(int i, int j, int k)
    {
        this.x += i;
        this.y += j;
        this.z += k;
        return this;
    }
    
    public BlockCoord sub(BlockCoord t)
    {
        this.x -= t.x;
        this.y -= t.y;
        this.z -= t.z;
        return this;
    }
    
    public BlockCoord sub(int i, int j, int k)
    {
        this.x -= i;
        this.y -= j;
        this.z -= k;
        return this;
    }
    
    public BlockCoord offset(int side)
    {
        return offset(side, 1);
    }
    
    public BlockCoord offset(int side, int amount)
    {
        BlockCoord offset = sideOffsets[side];
        this.x += offset.x * amount;
        this.y += offset.y * amount;
        this.z += offset.z * amount;
        return this;
    }
    
    public BlockCoord inset(int side)
    {
        return inset(side, 1);
    }
    
    public BlockCoord inset(int side, int amount)
    {
        return offset(side, -amount);
    }
    
    public BlockCoord[] getAdjacent()
    {
        BlockCoord[] adjacent = new BlockCoord[6];
        int i = 0;
        
        for (BlockCoord c : sideOffsets)
        {
            adjacent[i] = this.copy().add(c);
            i++;
        }
        
        return adjacent;
    }
    
    public List<BlockCoord> getNearby()
    {
        List<BlockCoord> nearby = new ArrayList<BlockCoord>();
        
        for (BlockCoord c : nearbyOffsets)
        {
            nearby.add(this.copy().add(c));
        }
        
        return nearby;
    }
    
    public int[] intArray()
    {
        return new int[] { this.x, this.y, this.z };
    }
    
    public BlockCoord copy()
    {
        return new BlockCoord(this.x, this.y, this.z);
    }
    
    public BlockCoord set(int i, int j, int k)
    {
        this.x = i;
        this.y = j;
        this.z = k;
        return this;
    }
    
    public BlockCoord set(BlockCoord t)
    {
        return set(t.x, t.y, t.z);
    }
    
    @Override
    public String toString()
    {
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}
