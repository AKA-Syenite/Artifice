package shukaro.artifice;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ArtificeFluids
{
    public static Fluid fluidOil;
    public static Fluid fluidFuel;
    public static Fluid fluidCreosote;
    public static Fluid fluidBitumen;

    public static void initFluids()
    {
        fluidOil = new Fluid("oil").setDensity(850).setViscosity(2000);
        fluidFuel = new Fluid("fuel").setDensity(750);
        fluidCreosote = new Fluid("creosote").setDensity(1100).setViscosity(1100);
        fluidBitumen = new Fluid("bitumen").setDensity(1100).setViscosity(10000);

        FluidRegistry.registerFluid(fluidOil);
        FluidRegistry.registerFluid(fluidFuel);
        FluidRegistry.registerFluid(fluidCreosote);
        FluidRegistry.registerFluid(fluidBitumen);
    }
}
