package shukaro.artifice.block.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.ArtificeItems;
import shukaro.artifice.render.TextureHandler;

import java.util.List;
import java.util.Random;

public class BlockEnderOre extends Block
{
    public IIcon icon;
    public boolean glowing;

    public BlockEnderOre(boolean glowing)
    {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setCreativeTab(ArtificeCore.worldTab);
        if (glowing)
            setBlockName("artifice.enderore.glowing");
        else
            setBlockName("artifice.enderore");
        setHarvestLevel("pickaxe", 2);
        this.glowing = glowing;

        if (this.glowing)
        {
            this.setLightLevel(0.625F);
            this.setTickRandomly(true);
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int fortune)
    {
        super.harvestBlock(world, entityplayer, x, y, z, fortune);
        if (!world.isRemote && world.rand.nextInt(100) < 20 && ArtificeConfig.spawnEndermen.getBoolean(true) && !EnchantmentHelper.getSilkTouchModifier(entityplayer))
        {
            int tries = world.rand.nextInt(20);
            for (int i=0; i<tries; i++)
            {
                int spawnX = x + world.rand.nextInt(3) - world.rand.nextInt(3);
                int spawnY = y + world.rand.nextInt(3) - world.rand.nextInt(3);
                int spawnZ = z + world.rand.nextInt(3) - world.rand.nextInt(3);
                if (canSpawnEnder(world, spawnX, spawnY, spawnZ))
                {
                    EntityEnderman ender = new EntityEnderman(world);
                    ender.setLocationAndAngles((double)spawnX + world.rand.nextDouble(), (double)spawnY + world.rand.nextDouble(), (double)spawnZ + world.rand.nextDouble(), world.rand.nextFloat(), world.rand.nextFloat());
                    world.spawnEntityInWorld(ender);
                    ender.spawnExplosionParticle();
                    ender.playSound("mob.endermen.portal", 1.0F, 1.0F);
                    break;
                }
            }
        }
    }

    private boolean canSpawnEnder(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z) == null || world.isAirBlock(x, y, z))
        {
            if (world.getBlock(x, y+1, z) == null || world.isAirBlock(x, y+1, z))
            {
                if (world.getBlock(x, y+2, z) == null || world.isAirBlock(x, y+2, z))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int tickRate(World p_149738_1_)
    {
        return 30;
    }

    private void activate(World world, int x, int y, int z)
    {
        this.sparkle(world, x, y, z);
        if (!this.glowing)
            world.setBlock(x, y, z, ArtificeBlocks.blockEnderOreGlowing);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (this.glowing)
            world.setBlock(x, y, z, ArtificeBlocks.blockEnderOre);
    }

    private void sparkle(World world, int x, int y, int z)
    {
        for (int i=0; i<4; i++)
        {
            double dx = (double)x + 0.5D + world.rand.nextDouble() - world.rand.nextDouble();
            double dy = (double)y + 0.5D + world.rand.nextDouble() - world.rand.nextDouble();
            double dz = (double)z + 0.5D + world.rand.nextDouble() - world.rand.nextDouble();
            world.spawnParticle("portal", dx, dy, dz, world.rand.nextDouble() - world.rand.nextDouble(), world.rand.nextDouble() - world.rand.nextDouble(), world.rand.nextDouble() - world.rand.nextDouble());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (this.glowing)
            this.sparkle(world, x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        this.activate(world, x, y, z);
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        this.activate(world, x, y, z);
        super.onEntityWalking(world, x, y, z, entity);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        this.activate(world, x, y, z);
        super.onBlockClicked(world, x, y, z, player);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random rand)
    {
        return 2 + rand.nextInt(3) + rand.nextInt(fortune + 1);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
        return ArtificeItems.itemResource;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) { return world.getBlockMetadata(x, y, z); }

    @Override
    public int damageDropped(int meta)
    {
        return 2;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.icon = TextureHandler.registerIcon(reg, "oreEnder", "ores/ender");
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.icon;
    }

    @Override
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side)
    {
        return this.icon;
    }

    @Override
    public void getSubBlocks(Item i, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(i, 1, 0));
    }
}
