package shukaro.artifice.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockCoord implements Comparable
{
    public int x;
    public int y;
    public int z;
    private static final BlockCoord[] sideOffsets =
            {
                    new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0),
                    new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1),
                    new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0)};

    private static final BlockCoord[] nearbyOffsets =
            {
                    new BlockCoord(-1, 1, 1), new BlockCoord(0, 1, 1),
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
                    new BlockCoord(1, -1, -1)};

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
        if (!(o instanceof BlockCoord))
            return false;
        BlockCoord t = (BlockCoord) o;
        return (this.x == t.x) && (this.y == t.y) && (this.z == t.z);
    }

    @Override
    public int hashCode()
    {
        return (this.x * this.z) + this.y;
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

    public BlockCoord[] getSides()
    {
        BlockCoord[] sides = new BlockCoord[4];
        int j = 0;

        for (int i = 2; i <= 5; i++)
        {
            sides[j] = this.copy().offset(i);
            j++;
        }

        return sides;
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
        return new int[]{this.x, this.y, this.z};
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

    public List<BlockCoord> getRadiusMatches(World world, int radius, Block block, int meta)
    {
        List<BlockCoord> matches = new ArrayList<BlockCoord>();
        BlockCoord c = this.copy();

        int minX = c.x - radius;
        int maxX = c.x + radius + 1;
        int minY = c.y - radius;
        int maxY = c.y + radius + 1;
        int minZ = c.z - radius;
        int maxZ = c.z + radius + 1;

        for (int x = minX; x < maxX; x++)
        {
            for (int y = minY; y < maxY; y++)
            {
                for (int z = minZ; z < maxZ; z++)
                {
                    BlockCoord t = new BlockCoord(x, y, z);
                    if (t.blockEquals(world, block, meta))
                        matches.add(t);
                }
            }
        }

        return matches;
    }

    public List<BlockCoord> getRadiusBlocks(int radius)
    {
        List<BlockCoord> matches = new ArrayList<BlockCoord>();
        BlockCoord c = this.copy();

        int minX = c.x - radius;
        int maxX = c.x + radius + 1;
        int minY = c.y - radius;
        int maxY = c.y + radius + 1;
        int minZ = c.z - radius;
        int maxZ = c.z + radius + 1;

        for (int x = minX; x < maxX; x++)
        {
            for (int y = minY; y < maxY; y++)
            {
                for (int z = minZ; z < maxZ; z++)
                {
                    BlockCoord t = new BlockCoord(x, y, z);
                    matches.add(t);
                }
            }
        }

        return matches;
    }

    public boolean blockEquals(World world, BlockCoord c)
    {
        return c.getBlock(world) == null && this.getBlock(world) == null || !(c.getBlock(world) == null ^ this.getBlock(world) == null) && (c.getBlock(world).equals(this.getBlock(world)) && c.getMeta(world) == this.getMeta(world));
    }

    public boolean blockEquals(World world, Block block, int meta)
    {
        return this.getBlock(world) != null && (this.getBlock(world).equals(block) && this.getMeta(world) == meta);
    }

    public boolean blockEquals(IBlockAccess world, BlockCoord c)
    {
        return c.getBlock(world) == null && this.getBlock(world) == null || !(c.getBlock(world) == null ^ this.getBlock(world) == null) && (c.getBlock(world).equals(this.getBlock(world)) && c.getMeta(world) == this.getMeta(world));
    }

    public boolean blockEquals(IBlockAccess world, Block block, int meta)
    {
        return this.getBlock(world) != null && (this.getBlock(world).equals(block) && this.getMeta(world) == meta);
    }

    public boolean isConnected(World world, BlockCoord c)
    {
        return this.isConnected(world, c, this.getBlock(world), this.getMeta(world));
    }

    public boolean isConnected(World world, BlockCoord c, Block block, int meta)
    {
        List<BlockCoord> traversed = new LinkedList<BlockCoord>();
        List<BlockCoord> toTraverse = new LinkedList<BlockCoord>();

        toTraverse.add(this);

        while (!toTraverse.isEmpty())
        {
            BlockCoord t = toTraverse.get(0);
            traversed.add(toTraverse.remove(0));

            for (BlockCoord j : t.getAdjacent())
            {
                if (j.blockEquals(world, block, meta))
                {
                    if (j.equals(c))
                        return true;
                    if (!traversed.contains(j) && !toTraverse.contains(j))
                        toTraverse.add(j);
                }
            }
        }

        return false;
    }

    public float getDistance(BlockCoord c)
    {
        return this.getDistance(c.x, c.y, c.z);
    }

    public float getDistance(int x, int y, int z)
    {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

    public int getMeta(World world)
    {
        return world.getBlockMetadata(this.x, this.y, this.z);
    }

    public int getMeta(IBlockAccess access)
    {
        return access.getBlockMetadata(this.x, this.y, this.z);
    }

    public Block getBlock(World world)
    {
        return world.getBlock(this.x, this.y, this.z);
    }

    public Block getBlock(IBlockAccess access)
    {
        return access.getBlock(this.x, this.y, this.z);
    }

    public TileEntity getTileEntity(World world)
    {
        return world.getTileEntity(this.x, this.y, this.z);
    }

    public boolean isAir(IBlockAccess access) { return access.isAirBlock(this.x, this.y, this.z); }

    public ItemStack getStack(IBlockAccess access) { return new ItemStack(access.getBlock(this.x, this.y, this.z), 1, access.getBlockMetadata(this.x, this.y, this.z)); }

    @Override
    public String toString()
    {
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}
