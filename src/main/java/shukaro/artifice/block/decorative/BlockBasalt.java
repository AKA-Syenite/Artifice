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
import shukaro.artifice.render.TextureHandler;
import shukaro.artifice.render.connectedtexture.ConnectedTextureBase;
import shukaro.artifice.render.connectedtexture.ConnectedTextures;
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import shukaro.artifice.util.BlockCoord;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.util.PacketWrapper;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBasalt extends BlockArtifice
{
    private Icon[] icons = new Icon[ArtificeCore.rocks.length];
    
    public BlockBasalt(int id)
    {
        super(id, Material.rock);
        setCreativeTab(ArtificeCreativeTab.main);
        setHardness(1.5F);
        setResistance(10.0F);
        setUnlocalizedName("artifice.basalt");
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
        icons[0] = IconHandler.registerSingle(reg, "basalt", "basalt");
        icons[1] = IconHandler.registerSingle(reg, "cobblestone", "basalt");
        icons[2] = IconHandler.registerSingle(reg, "bricks", "basalt");
        icons[5] = IconHandler.registerSingle(reg, "chiseled", "basalt");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta >= ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3)
        	return ConnectedTextures.BasaltPaver.textureList[0];
        if (meta == 4)
        	return ConnectedTextures.BasaltAntipaver.textureList[0];
        else
            return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side)
    {
        int meta = access.getBlockMetadata(x, y, z);
        if (meta > ArtificeCore.rocks.length)
            meta = 0;
        if (meta == 3 || meta == 4)
        {
        	BlockCoord coord = new BlockCoord(x, y, z);
        	if (!ArtificeCore.textureCache.containsKey(coord))
        		TextureHandler.updateTexture(coord);
        	
        	if (ArtificeCore.textureCache.get(coord) == null)
        		return this.getIcon(side, meta);
        	if (TextureHandler.getConnectedTexture(this.getIcon(side, meta)) != null)
        		return TextureHandler.getConnectedTexture(this.getIcon(side, meta)).textureList[ArtificeCore.textureCache.get(coord)[side]];
        	return this.getIcon(side, meta);
        }
        else
            return icons[meta];
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);
            BlockCoord c = new BlockCoord(x, y, z);
            if (c.getBlock(world) != null && (meta == 3 || meta == 4))
                PacketDispatcher.sendPacketToAllAround(c.x, c.y, c.z, 192, world.provider.dimensionId, PacketWrapper.createPacket(ArtificeCore.modChannel, Packets.TEXTUREUPDATE, new Object[] {c.x, c.y, c.z}));
        }
    }
}
