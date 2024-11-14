package com.Act_Carbon_L.mekanic.MekanicRegister;

import com.Act_Carbon_L.mekanic.Mekanic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

import static com.Act_Carbon_L.mekanic.Mekanic.MOD_ID;

public class MekanicFluid {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MOD_ID);
    private static FluidObject<ForgeFlowingFluid> register(String name, int temp) {
        String still = String.format("%s:block/fluid/%s/still", Mekanic.MOD_ID, name);
        String flow = String.format("%s:block/fluid/%s/flowing", Mekanic.MOD_ID, name);
        return FLUIDS.register(name, FluidAttributes.builder(new ResourceLocation(still), new ResourceLocation(flow)).density(2000).viscosity(10000).temperature(temp).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA), Material.LAVA, 15);
    }
    //后面你就复制粘贴这个abcd改名顺着往下写就行了
    //public static FluidObject<ForgeFlowingFluid> MOLTEN_ABCD = register("molten_abcd", 666);  //流体注册

}
