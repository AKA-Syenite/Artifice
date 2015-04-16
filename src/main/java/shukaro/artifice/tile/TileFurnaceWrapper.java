package shukaro.artifice.tile;

import net.minecraft.block.BlockFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import shukaro.artifice.ArtificeConfig;

public class TileFurnaceWrapper implements IHeatReceiver
{
    private TileEntityFurnace furnace;

    public TileFurnaceWrapper(TileEntityFurnace furnace)
    {
        this.furnace = furnace;
    }

    public boolean canSmelt()
    {
        if (furnace.getStackInSlot(0) == null)
            return false;
        ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(furnace.getStackInSlot(0));
        if (output == null)
            return false;
        if (furnace.getStackInSlot(2) == null)
            return true;
        if (!furnace.getStackInSlot(2).isItemEqual(output))
            return false;
        int result = furnace.getStackInSlot(2).stackSize + output.stackSize;
        return (result <= furnace.getInventoryStackLimit()) && (result <= output.getMaxStackSize());
    }

    @Override
    public int receiveHeat(int amount)
    {
        if (!canSmelt())
            return 0;
        int received = Math.min(this.getMaxHeat()-this.getHeat(), amount);
        this.furnace.furnaceBurnTime += received / ArtificeConfig.RFperFurnaceHeat;
        this.furnace.furnaceCookTime = Math.min(this.furnace.furnaceCookTime, 199);
        BlockFurnace.updateFurnaceBlockState(furnace.furnaceCookTime > 0, furnace.getWorldObj(), furnace.xCoord, furnace.yCoord, furnace.zCoord);
        return received;
    }

    @Override
    public int getHeat()
    {
        return furnace.furnaceBurnTime * ArtificeConfig.RFperFurnaceHeat;
    }

    @Override
    public int getMaxHeat()
    {
        return 1600;
    }
}
