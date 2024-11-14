package com.Act_Carbon_L.mekanic.MekanicRegister;

import com.Act_Carbon_L.mekanic.Modifiers.*;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.Act_Carbon_L.mekanic.Mekanic.MOD_ID;

public class MekanicModifier {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);
    //后面你就复制粘贴这个annihilation改名顺着往下写就行了
    public static final StaticModifier<annihilation> annihilation_STATIC_MODIFIER = MODIFIERS.register("annihilation", annihilation::new);  //annihilation
    public static final StaticModifier<selfannihilation> selfannihilation_STATIC_MODIFIER = MODIFIERS.register("selfannihilation", selfannihilation::new);
    public static final StaticModifier<advancingtechnology> advancingtechnology_STATIC_MODIFIER = MODIFIERS.register("advancingtechnology", advancingtechnology::new);
    public static final StaticModifier<getthetop> getthetop_STATIC_MODIFIER = MODIFIERS.register("getthetop", getthetop::new);
    public static final StaticModifier<vectorchange> vectorchange_STATIC_MODIFIER = MODIFIERS.register("vectorchange", vectorchange::new);
    public static final StaticModifier<condense> condense_STATIC_MODIFIER = MODIFIERS.register("condense", condense::new);
}
