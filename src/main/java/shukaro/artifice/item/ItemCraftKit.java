package shukaro.artifice.item;

import cofh.lib.util.helpers.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import shukaro.artifice.ArtificeConfig;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.gui.GuiCraftKit;
import shukaro.artifice.gui.GuiHandlerArtifice;
import shukaro.artifice.render.TextureHandler;

public class ItemCraftKit extends ItemArtifice
{
    private IIcon icon;

    public ItemCraftKit()
    {
        super();
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
        this.setUnlocalizedName("artifice.craftkit");
        GameRegistry.registerItem(this, this.getUnlocalizedName());
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        return this.icon;
    }

    @Override
    public void registerIcons(IIconRegister reg)
    {
        this.icon = TextureHandler.registerIcon(reg, "craftkit", "misc");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        int x = MathHelper.floor(player.posX);
        int y = MathHelper.floor(player.posY);
        int z = MathHelper.floor(player.posZ);
        player.openGui(ArtificeCore.instance, GuiHandlerArtifice.craftKitID, world, x, y, z);
        return stack;
    }
}
