package shukaro.artifice.block.decorative;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.block.BlockArtifice;
import shukaro.artifice.gui.ArtificeCreativeTab;
import shukaro.artifice.net.Packets;
import shukaro.artifice.render.IconHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMarble extends BlockArtifice
{
    private Icon[] icons = new Icon[ArtificeCore.rocks.length];
    private ConnectedTextureBase paver = new SolidConnectedTexture(ConnectedTextures.MarblePaver);
    private ConnectedTextureBase antipaver = new SolidConnectedTexture(ConnectedTextures.MarbleAntipaver);
    
    public BlockMarble(int id)
    {
        super(id, Material.rock);
        setCreativeTab(ArtificeCreativeTab.main);
        setHardness(1.5F);
        setResistance(10.0F);
        setUnlocalizedName("artifice.marble");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int i, CreativeTabs tabs, List list)
    {
        for (int j = 0; j < ArtificeCore.rocks.length; j++)
        {
            list.add(new ItemStack(i, 1, j));
        }
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return meta == 0 ? 1 : meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg)
    {
    	ArtificeConfig.registerConnectedTextures(reg);
        icons[0] = IconHandler.registerSingle(reg, "marble", "marble");
        icons[1] = IconHandler.registerSingle(reg, "cobblestone", "marble");
        icons[2] = IconHandler.registerSingle(reg, "bricks", "marble");
        icons[5] = IconHandler.registerSingle(reg, "chiseled", "marble");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
        	return this.getTextureRenderer(side, meta).texture.textureList[0];
        else
            return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess block, int x, int y, int z, int side)
    {
        int meta = block.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
        {
        	Integer worldID = Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId;
        	BlockCoord coord = new BlockCoord(x, y, z);
        	ChunkCoord chunk = new ChunkCoord(coord);
        	
        	if (!ArtificeCore.textureCache.contains(worldID, chunk, coord))
        	{
        		int[] indices = new int[6];
        		for (int i=0; i<indices.length; i++)
        			indices[i] = this.getTextureRenderer(i, meta).getTextureIndex(block, x, y, z, i);
        		ArtificeCore.textureCache.add(worldID, chunk, coord, indices);
        	}
        	
        	if (ArtificeCore.textureCache.get(worldID, chunk, coord) == null)
        		return this.getIcon(side, meta);
        	return this.getTextureRenderer(side, meta).texture.textureList[ArtificeCore.textureCache.get(worldID, chunk, coord)[side]];
        }
        else
            return icons[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	if (meta == 3 || meta == 4)
    	{
	    	Integer worldID = world.provider.dimensionId;
	    	BlockCoord coord = new BlockCoord(x, y, z);
	    	ChunkCoord chunk = new ChunkCoord(coord);
	    	
	    	int[] old = ArtificeCore.textureCache.get(worldID, chunk, coord);
	    	int[] indices = new int[6];
			for (int i=0; i<indices.length; i++)
				indices[i] = this.getTextureRenderer(i, meta).getTextureIndex(world, x, y, z, i);
			ArtificeCore.textureCache.add(worldID, chunk, coord, indices);
				
			if (old == null || !old.equals(indices))
				PacketDispatcher.sendPacketToAllInDimension(PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.INDEXDATA, new Object[] {x, y, z, indices[0], indices[1], indices[2], indices[3], indices[4], indices[5]}), worldID);
    	}
    }

    public ConnectedTextureBase getTextureRenderer(int side, int meta)
    {
        if (meta == 3)
            return this.paver;
        if (meta == 4)
            return this.antipaver;
        return null;
    }
}
