package com.Act_Carbon_L.mekanic.MekanicRegister;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.Act_Carbon_L.mekanic.Mekanic.MOD_ID;

public class MekanicItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create( ForgeRegistries.ITEMS,MOD_ID );

    //后面你就复制粘贴这个annihilation改名顺着往下写就行了
    //public static final RegistryObject<Item> annihilation = ITEMS.register("annihilation", ( ) -> new Item(new Item.Properties().tab(MekanicTab.MATERIALS)));
}
