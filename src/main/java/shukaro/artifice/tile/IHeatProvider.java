package shukaro.artifice.tile;

public interface IHeatProvider
{
    /**
     * @param amount heat to extract
     * @return heat that was extracted
     */
    public int extractHeat(int amount);

    public int getHeat();

    public int getMaxHeat();
}
