package shukaro.artifice.util;

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
}
