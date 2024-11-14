package com.Act_Carbon_L.mekanic;

import com.Act_Carbon_L.mekanic.MekanicRegister.MekanicBlock;
import com.Act_Carbon_L.mekanic.MekanicRegister.MekanicFluid;
import com.Act_Carbon_L.mekanic.MekanicRegister.MekanicItem;
import com.Act_Carbon_L.mekanic.MekanicRegister.MekanicModifier;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Mekanic.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Mekanic {
    public static final String MOD_ID = "mekanic";
    public Mekanic() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MekanicItem.ITEMS.register(eventBus);
        MekanicModifier.MODIFIERS.register(eventBus);
        MekanicBlock.BLOCKS.register(eventBus);
        MekanicFluid.FLUIDS.register(eventBus);
    }
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {
        }
        if (event.includeServer()) {
        }
    }
}