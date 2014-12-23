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
        if (!FluidRegistry.isFluidRegistered("oil"))
        {
            fluidOil = new Fluid("oil").setDensity(850).setViscosity(2000);
            FluidRegistry.registerFluid(fluidOil);
        }
        else
            fluidOil = FluidRegistry.getFluid("oil");
        if (!FluidRegistry.isFluidRegistered("fuel"))
        {
            fluidFuel = new Fluid("fuel").setDensity(750);
            FluidRegistry.registerFluid(fluidFuel);
        }
        else
            fluidFuel = FluidRegistry.getFluid("fuel");
        if (!FluidRegistry.isFluidRegistered("creosote"))
        {
            fluidCreosote = new Fluid("creosote").setDensity(1100).setViscosity(1100);
            FluidRegistry.registerFluid(fluidCreosote);
        }
        else
            fluidCreosote = FluidRegistry.getFluid("creosote");
        if (!FluidRegistry.isFluidRegistered("bitumen"))
        {
            fluidBitumen = new Fluid("bitumen").setDensity(1100).setViscosity(10000);
            FluidRegistry.registerFluid(fluidBitumen);
        }
        else
            fluidBitumen = FluidRegistry.getFluid("bitumen");
    }
}
