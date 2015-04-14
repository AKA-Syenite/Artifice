package shukaro.artifice.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandlerArtifice implements IGuiHandler
{
    public static final int craftKitID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == craftKitID)
            return new ContainerCraftkit(player.inventory, world, x, y, z);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == craftKitID)
            return new GuiCraftKit(player.inventory, world, x, y, z);
        return null;
    }
}
