package shukaro.artifice.tile;

public interface IHeatReceiver
{
    /**
     * @param amount heat to receive
     * @return heat that was received
     */
    public int receiveHeat(int amount);

    public int getHeat();

    public int getMaxHeat();
}
