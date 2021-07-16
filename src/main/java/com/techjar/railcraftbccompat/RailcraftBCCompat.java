package com.techjar.railcraftbccompat;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;

@Mod(modid = "railcraftbccompat", name = "Railcraft BC Compat", version = "@VERSION@", acceptedMinecraftVersions = "1.12.2", dependencies = "required-after:railcraft;required-after:buildcraftenergy;")
public class RailcraftBCCompat {
	public static final String MOD_ID = "railcraftbccompat";
	public static final String MOD_NAME = "Railcraft BC Compat";
	public static final String MOD_VERSION = "@VERSION@";

	@Mod.Instance("railcraftbccompat")
	public static RailcraftBCCompat instance;

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Registering BuildCraft fluids for boiler...");
		registerFluidFuel("oil_distilled", 48000);
		registerFluidFuel("fuel_dense", 192000);
		registerFluidFuel("fuel_mixed_heavy", 76800);
		registerFluidFuel("fuel_light", 96000);
		registerFluidFuel("fuel_mixed_light", 38400);
		registerFluidFuel("fuel_gaseous", 24000);
	}

	private void registerFluidFuel(String name, int value) {
		Fluid fluid = FluidRegistry.getFluid(name);
		if (fluid != null) {
			System.out.println(name + ": " + value);
			FluidStack fluidStack = new FluidStack(fluid, 1000);
			NBTTagCompound tagCompound = new NBTTagCompound();
			fluidStack.writeToNBT(tagCompound);
			tagCompound.setInteger("Fuel", value);
			FMLInterModComms.sendMessage("railcraft", "fluid-fuel", tagCompound);
		} else {
			System.out.println("Fluid not found: " + name);
		}
	}
}
