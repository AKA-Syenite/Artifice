package shukaro.artifice.compat.vanilla;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import shukaro.artifice.ArtificeCore;
import shukaro.artifice.compat.ArtificeRegistry;

@Mod(modid = "ArtificeCompat|Vanilla", name = "Artifice Compat: Vanilla", version = ArtificeCore.modVersion, dependencies = "after:Artifice")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Vanilla
{
    @Init
    public void load(FMLInitializationEvent e)
    {
        ArtificeRegistry.registerDimensionBlacklist(1);
        ArtificeRegistry.registerDimensionBlacklist(-1);
        ArtificeRegistry.registerStoneType(Block.stone.blockID);
    }
}
