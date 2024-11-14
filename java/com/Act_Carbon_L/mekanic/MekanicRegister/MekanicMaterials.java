package com.Act_Carbon_L.mekanic.MekanicRegister;

import com.Act_Carbon_L.mekanic.Mekanic;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;


public class MekanicMaterials {
    public static final MaterialId pellets_antimatter = createMaterial("pellets_antimatter");
    public static final MaterialId basic_control_circuit = createMaterial("basic_control_circuit");
    public static final MaterialId advanced_control_circuit = createMaterial("advanced_control_circuit");
    public static final MaterialId elite_control_circuit = createMaterial("elite_control_circuit");
    public static final MaterialId ultimate_control_circuit = createMaterial("ultimate_control_circuit");
    public static final MaterialId absolute_control_circuit = createMaterial("absolute_control_circuit");
    public static final MaterialId supreme_control_circuit = createMaterial("supreme_control_circuit");
    public static final MaterialId cosmic_control_circuit = createMaterial("cosmic_control_circuit");
    public static final MaterialId infinite_control_circuit = createMaterial("infinite_control_circuit");
    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(Mekanic.MOD_ID, name)); //*是你的模组主类名
    }
}
