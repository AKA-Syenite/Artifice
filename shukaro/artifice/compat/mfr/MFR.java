package shukaro.artifice.compat.mfr;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import shukaro.artifice.ArtificeBlocks;
import shukaro.artifice.ArtificeCore;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ArtificeCompat|MFR", name = "Artifice Compat: MFR", version = ArtificeCore.modVersion, dependencies = "after:Artifice;after:MineFactoryReloaded")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class MFR
{
	@Init
	public static void load(FMLInitializationEvent e0)
	{
		if (!Loader.isModLoaded("MineFactoryReloaded"))
		{
			ArtificeCore.logger.warning("MFR missing, not loading compat");
			return;
		}
		try
		{
			Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
			if (registry != null)
			{
				Method reg = registry.getMethod("registerHarvestable", IFactoryHarvestable.class);
				reg.invoke(registry, ArtificeBlocks.blockFlora);
			}
			
			ArtificeCore.logger.log(Level.INFO, "MFR Compat Initialized");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}