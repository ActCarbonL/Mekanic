package com.Act_Carbon_L.mekanic.Modifiers;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class vectorchange extends NoLevelsModifier {

    public vectorchange() {
    }


    public static ResourceLocation IFCANFLY =new ResourceLocation("ifcanfly");

    @Override
    public void onEquip(IToolStackView tool, int level, EquipmentChangeContext context) {
        LivingEntity entity = context.getEntity();
        if (entity instanceof Player player) {
            tool.getPersistentData().putBoolean(IFCANFLY,player.getAbilities().mayfly);
            player.getAbilities().mayfly=true;
        }
    }

    @Override
    public void onUnequip(IToolStackView tool, int level, EquipmentChangeContext context) {
        LivingEntity entity = context.getEntity();
        if (entity instanceof Player player) {
            player.getAbilities().mayfly=tool.getPersistentData().getBoolean(IFCANFLY);
        }
    }

    @Override
    public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        Player player= (Player) holder;
        ItemStack stackmain=player.getMainHandItem();
        ItemStack stackoff=player.getOffhandItem();
        if(!tool.getPersistentData().getBoolean(IFCANFLY)){
            player.getAbilities().flying=player.getAbilities().flying&&(Math.max(ModifierUtil.getModifierLevel(stackmain,this.getId()),ModifierUtil.getModifierLevel(stackoff,this.getId()))>0);
        }
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        // the speed is reduced when not on the ground, cancel out
        if (!event.getEntity().isOnGround()) {
            event.setNewSpeed(event.getNewSpeed() * 5);
        }
    }

    { MinecraftForge.EVENT_BUS.addListener(this::LivingAttackEvent);}
    public void LivingAttackEvent(LivingAttackEvent event){
        if(event.getSource().isProjectile() &&event.getEntity() instanceof Player player){
            ItemStack stackmain=player.getMainHandItem();
            ItemStack stackoff=player.getOffhandItem();
            int ModifierLevel= Math.max(ModifierUtil.getModifierLevel(stackmain,this.getId()),ModifierUtil.getModifierLevel(stackoff,this.getId()));
            if(ModifierLevel>0){
                event.getEntityLiving().invulnerableTime=60;
                event.setCanceled(true);
            }
        }
    }
}
