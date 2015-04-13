package shukaro.artifice.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Random;

public class MiscUtils
{
    /**
     * Shuffle an array of type T
     *
     * @param <T> The type contained in the array
     * @param array The array to be shuffled
     */
    public static <T> void shuffleArray(T[] array)
    {
        for (int i = array.length; i > 1; i--)
        {
            T temp = array[i - 1];
            int randIx = (int) (Math.random() * i);
            array[i - 1] = array[randIx];
            array[randIx] = temp;
        }
    }

    public static int randWithin50(Random rand, int input)
    {
        if (input > 1)
            return input + rand.nextInt(input/2) - rand.nextInt(input/2);
        return 1;
    }

    public static void dropStack(World world, int x, int y, int z, ItemStack itemstack)
    {
        do
        {
            if (itemstack.stackSize <= 0)
                break;

            float xOffset = world.rand.nextFloat() * 0.8F + 0.1F;
            float yOffset = world.rand.nextFloat() * 0.8F + 0.1F;
            float zOffset = world.rand.nextFloat() * 0.8F + 0.1F;

            int amountToDrop = Math.min(world.rand.nextInt(21) + 10, itemstack.stackSize);

            EntityItem entityitem = new EntityItem(world, x + xOffset, y + yOffset, z + zOffset, itemstack.splitStack(amountToDrop));

            float motionMultiplier = 0.05F;
            entityitem.motionX = (float)world.rand.nextGaussian() * motionMultiplier;
            entityitem.motionY = (float)world.rand.nextGaussian() * motionMultiplier + 0.2F;
            entityitem.motionZ = (float)world.rand.nextGaussian() * motionMultiplier;

            world.spawnEntityInWorld(entityitem);
        } while(true);
    }

    public static void addChatMessage(EntityPlayer player, String message)
    {
        player.addChatMessage(new ChatComponentText(message));
    }
}
