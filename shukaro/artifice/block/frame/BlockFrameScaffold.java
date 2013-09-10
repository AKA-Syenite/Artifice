package shukaro.artifice.block.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.multiblock.TileEntityMultiblock;
import shukaro.artifice.multiblock.erogenousbeef.IMultiblockPart;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTexture;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.IConnectedTexture;
import shukaro.artifice.render.connectedtexture.ILayeredRender;
import shukaro.artifice.render.connectedtexture.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFrameScaffold extends BlockFrame implements IConnectedTexture, ILayeredRender
{
    private static final ForgeDirection[] sides = new ForgeDirection[] { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.DOWN };
    
    private Icon[] sideIcons = new Icon[ArtificeCore.tiers.length];
    private Icon[] vertIcons = new Icon[ArtificeCore.tiers.length];
    private ConnectedTextureBase basic = new SolidConnectedTexture(ConnectedTexture.BasicFrame);
    private ConnectedTextureBase reinforced = new SolidConnectedTexture(ConnectedTexture.ReinforcedFrame);
    private ConnectedTextureBase industrial = new SolidConnectedTexture(ConnectedTexture.IndustrialFrame);
    private ConnectedTextureBase advanced = new SolidConnectedTexture(ConnectedTexture.AdvancedFrame);
    
    public BlockFrameScaffold(int id)
    {
        super(id);
        setUnlocalizedName("artifice.scaffold");
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if(entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)entity).playerNetServerHandler.ticksForFloatKick = 0;
            entity.fallDistance = 0;
        }
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        ItemStack held = player.inventory.mainInventory[player.inventory.currentItem];
        if (held != null && held.itemID == this.blockID && held.getItemDamage() == world.getBlockMetadata(x, y, z))
        {
            while (world.getBlockId(x, y, z) == this.blockID)
                y++;
            if (checkStay(world, x, y, z, held.getItemDamage()) && (Block.blocksList[world.getBlockId(x, y, z)] == null || Block.blocksList[world.getBlockId(x, y, z)].isAirBlock(world, x, y, z)))
            {
                world.setBlock(x, y, z, this.blockID, held.getItemDamage(), 3);
                if (!player.capabilities.isCreativeMode)
                {
                    held.stackSize--;
                    if (held.stackSize <= 0)
                        held = null;
                }
            }
        }
    }
    
    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack stack)
    {
        return this.checkStay(world, x, y, z, stack.getItemDamage());
    }
    
    public int getOverhang(int meta)
    {
        switch (meta)
        {
        case 0:
            return 4;
        case 1:
            return 8;
        case 2:
            return 12;
        case 3:
            return 16;
        default:
            return 0;
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return this.checkStay(world, x, y, z, world.getBlockMetadata(x, y, z));
    }
    
    public boolean checkStay(World world, int x, int y, int z, int meta)
    {
        BlockCoord c = new BlockCoord(x, y, z);
        
        if (isRooted(world, x, y, z, meta))
            return true;
        
        for (ForgeDirection d : sides)
        {
            BlockCoord t = c.copy().offset(d.ordinal());
            if (t.getBlock(world) instanceof BlockFrameScaffold)
            {
                if (t.blockEquals(world, this.blockID, meta))
                {
                    if (isRooted(world, t.x, t.y, t.z, meta))
                        return true;
                    for (BlockCoord match : c.getRadiusMatches(world, getOverhang(meta), this.blockID, meta))
                    {
                        if (isRooted(world, match.x, match.y, match.z, meta) && c.isConnected(world, match, this.blockID, meta) && c.getDistance(match) <= getOverhang(meta))
                            return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    public boolean isRooted(World world, int x, int y, int z, int meta)
    {
        for (int i=y-1; i>0; i--)
        {
            if (world.isBlockSolidOnSide(x, i, z, ForgeDirection.UP))
            {
                if (world.getBlockId(x, i, z) == this.blockID)
                {
                    if (world.getBlockMetadata(x, i, z) == meta)
                        continue;
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int side)
    {
        if(!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return (side == ForgeDirection.UP || side == ForgeDirection.DOWN) ? true : false;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }

    @Override
    public Icon getRenderIcon(int side, int meta)
    {
        if (side == 0 || side == 1)
            return vertIcons[meta];
        return null;
    }

    @Override
    public ConnectedTexture getTextureType(int side, int meta)
    {
        if (side == 0 || side == 1)
        {
            switch (meta)
            {
            case 0:
                return ConnectedTexture.BasicFrame;
            case 1:
                return ConnectedTexture.ReinforcedFrame;
            case 2:
                return ConnectedTexture.IndustrialFrame;
            case 3:
                return ConnectedTexture.AdvancedFrame;
            default:
                return null;
            }
        }
        return null;
    }

    @Override
    public ConnectedTextureBase getTextureRenderer(int side, int meta)
    {
        if (side == 0 || side == 1)
        {
            switch (meta)
            {
            case 0:
                return basic;
            case 1:
                return reinforced;
            case 2:
                return industrial;
            case 3:
                return advanced;
            default:
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        for (int i=0; i<ArtificeCore.tiers.length; i++)
        {
            sideIcons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase() + "_side", "scaffold");
            vertIcons[i] = IconHandler.registerSingle(reg, ArtificeCore.tiers[i].toLowerCase() + "_vert", "scaffold");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (side == 0 || side == 1)
            return this.getTextureType(side, meta).textureList[0];
        return this.sideIcons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        if (side == 0 || side == 1)
            return this.getTextureType(side, block.getBlockMetadata(x, y, z)).textureList[this.getTextureRenderer(side, block.getBlockMetadata(x, y, z)).getTextureIndex(block, x, y, z, side)];
        return this.sideIcons[block.getBlockMetadata(x, y, z)];
    }
}
