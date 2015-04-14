package shukaro.artifice.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerCraftkit extends ContainerWorkbench
{
    public ContainerCraftkit(InventoryPlayer inventoryPlayer, World world, int x, int y, int z)
    {
        super(inventoryPlayer, world, x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) { return true; }
}
