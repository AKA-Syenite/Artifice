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
    public int x, y, z;

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

    public BlockCoord() {}

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof BlockCoord))
            return false;
        BlockCoord t = (BlockCoord) o;
        return (this.x == t.x) && (this.y == t.y) && (this.z == t.z);
    }

    public boolean equals(int x, int y, int z) { return this.x == x && this.y == y && this.z == z; }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        hash = 71 * hash + this.z;
        return hash;
    }

    public BlockCoord copy()
    {
        return new BlockCoord(this.x, this.y, this.z);
    }

    public void copy(BlockCoord other)
    {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    @Override
    public int compareTo(Object o)
    {
        if(o instanceof BlockCoord)
        {
            BlockCoord other = (BlockCoord)o;
            if(this.x < other.x) { return -1; }
            else if(this.x > other.x) { return 1; }
            else if(this.y < other.y) { return -1; }
            else if(this.y > other.y) { return 1; }
            else if(this.z < other.z) { return -1; }
            else if(this.z > other.z) { return 1; }
            else { return 0; }
        }
        return 0;
    }

    public int compareTo(int xCoord, int yCoord, int zCoord)
    {
        if(this.x < xCoord) { return -1; }
        else if(this.x > xCoord) { return 1; }
        else if(this.y < yCoord) { return -1; }
        else if(this.y > yCoord) { return 1; }
        else if(this.z < zCoord) { return -1; }
        else if(this.z > zCoord) { return 1; }
        else { return 0; }
    }

    public int getChunkX() { return x >> 4; }
    public int getChunkZ() { return z >> 4; }

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
        return new BlockCoord[]
        {
            new BlockCoord(x + 1, y, z),
            new BlockCoord(x - 1, y, z),
            new BlockCoord(x, y + 1, z),
            new BlockCoord(x, y - 1, z),
            new BlockCoord(x, y, z + 1),
            new BlockCoord(x, y, z - 1)
        };
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

    public int getMeta(IBlockAccess access)
    {
        return access.getBlockMetadata(this.x, this.y, this.z);
    }

    public Block getBlock(IBlockAccess access) { return access.getBlock(this.x, this.y, this.z); }

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
