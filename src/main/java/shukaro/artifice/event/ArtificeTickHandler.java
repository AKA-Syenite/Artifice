package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import net.minecraft.world.World;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.world.ISuspendableGen;

import java.util.ArrayDeque;

public class ArtificeTickHandler
{
    public static TMap<Integer, ArrayDeque<ISuspendableGen>> toGen = new THashMap<Integer, ArrayDeque<ISuspendableGen>>();

    @SubscribeEvent
    public void worldTickEnd(TickEvent.WorldTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER)
            return;

        World world = event.world;
        int dim = world.provider.dimensionId;
        ArrayDeque<ISuspendableGen> generators = toGen.get(Integer.valueOf(dim));

        if (generators != null && generators.size() > 0)
        {
            for (ISuspendableGen gen : generators)
            {
                for (ChunkCoord c : gen.getChunksToGen())
                {
                    if (world.blockExists(c.chunkX << 4, 0, c.chunkZ << 4))
                    {
                        gen.doGeneration(c.chunkX, c.chunkZ);
                        if (gen.getChunksToGen().size() == 0)
                            toGen.get(Integer.valueOf(dim)).removeFirst();
                        return;
                    }
                }
            }
        }
    }
}
