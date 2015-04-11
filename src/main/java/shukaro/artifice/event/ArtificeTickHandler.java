package shukaro.artifice.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import net.minecraft.world.World;
import shukaro.artifice.util.ChunkCoord;
import shukaro.artifice.world.IDelayedGen;
import shukaro.artifice.world.ISuspendableGen;

import java.util.ArrayDeque;

public class ArtificeTickHandler
{
    public static TMap<Integer, ArrayDeque<ISuspendableGen>> suspendedGenerators = new THashMap<Integer, ArrayDeque<ISuspendableGen>>();
    public static TMap<Integer, ArrayDeque<IDelayedGen>> delayedGenerators = new THashMap<Integer, ArrayDeque<IDelayedGen>>();

    public static void queueGeneration(int dimID, ISuspendableGen gen)
    {
        if (gen.getChunksToGen().size() == 0)
            return;
        if (!suspendedGenerators.containsKey(Integer.valueOf(dimID)))
            suspendedGenerators.put(dimID, new ArrayDeque<ISuspendableGen>());
        suspendedGenerators.get(Integer.valueOf(dimID)).add(gen);
    }

    public static void queueGeneration(int dimID, IDelayedGen gen)
    {
        if (!delayedGenerators.containsKey(Integer.valueOf(dimID)))
            delayedGenerators.put(dimID, new ArrayDeque<IDelayedGen>());
        delayedGenerators.get(Integer.valueOf(dimID)).add(gen);
    }

    @SubscribeEvent
    public void worldTickEnd(TickEvent.WorldTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END || event.side != Side.SERVER)
            return;

        World world = event.world;
        int dim = world.provider.dimensionId;
        ArrayDeque<ISuspendableGen> suspendedGens = suspendedGenerators.get(Integer.valueOf(dim));

        if (world.getTotalWorldTime() % 5 == 0 && suspendedGens != null && suspendedGens.size() > 0)
        {
            boolean done = false;
            for (ISuspendableGen gen : suspendedGens)
            {
                for (ChunkCoord c : gen.getChunksToGen())
                {
                    gen.doGeneration(c.chunkX, c.chunkZ);
                    if (gen.getChunksToGen().size() == 0)
                        suspendedGenerators.get(Integer.valueOf(dim)).remove(gen);
                    done = true;
                    break;
                }
                if (done)
                    break;
            }
        }

        ArrayDeque<IDelayedGen> delayedGens = delayedGenerators.get(Integer.valueOf(dim));

        if (world.getTotalWorldTime() % 10 == 0 && delayedGens != null && delayedGens.size() > 0)
        {
            for (IDelayedGen gen : delayedGens)
            {
                gen.doGeneration();
                delayedGenerators.get(Integer.valueOf(dim)).remove(gen);
                break;
            }
        }
    }
}
