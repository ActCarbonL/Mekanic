package com.Act_Carbon_L.mekanic.Modifiers;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.TooltipKey;

import javax.annotation.Nullable;
import java.util.List;

public class advancingtechnology extends Modifier {
    public static ResourceLocation ITERATION =new ResourceLocation("iteration");
    public advancingtechnology() {
    }
    @Override
    public void onRemoved(IToolStackView tool){
        tool.getPersistentData().remove(ITERATION);
    }
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder){
        double factor=level*0.1+1;
        ToolStats.DURABILITY.multiply(builder,factor);
        ToolStats.MINING_SPEED.multiply(builder,factor);
        ToolStats.ATTACK_SPEED.multiply(builder,factor);
        ToolStats.ATTACK_DAMAGE.multiply(builder,factor);
        ToolStats.ACCURACY.multiply(builder,factor);
        ToolStats.DRAW_SPEED.multiply(builder,factor);
    }
    @Override
    public void onInventoryTick(IToolStackView tool, int level, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack){
        if (level > 1) {
            if(holder!=null&&(!tool.isBroken())){
                holder.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,20,Math.min(level/2-1,2),false,true));
                holder.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,20,Math.min(level/2-1,2),false,true));
            }
        }
    }
    @Override
    public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
        if(level>3){
            volatileData.addSlots(SlotType.UPGRADE,(level>>2)<<1);
            volatileData.addSlots(SlotType.ABILITY,level>>2);
        }
        if(level<8){
            volatileData.putInt(ITERATION,0);
        }
        else if(volatileData.get(ITERATION)==null){
            volatileData.putInt(ITERATION,0);
        }
    }

    {MinecraftForge.EVENT_BUS.addListener(this::LivingHurtEvent);}

    private void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            //LivingEntity target=event.getEntityLiving();//伤害作用到的生物命名为target方便后面引用
            ItemStack stack=player.getMainHandItem();//将玩家主手物品转化为stack
            int ModifierLevel= ModifierUtil.getModifierLevel(stack,this.getId());//计算玩家主手物品该词条等级
            IToolStackView iToolStackView= ToolStack.from(stack);//从stack这个工具获取IToolstackView
            ModDataNBT ToolData=iToolStackView.getPersistentData();//从IToolstackView中获取nbt
            //ToolData.getInt(ITERATION);//如果你需要获取之前定义的key的int值
            if((!iToolStackView.isBroken())&&ModifierLevel>7){
                event.setAmount(event.getAmount() * (1+ToolData.getInt(ITERATION)*0.01F));//如果修改原伤害就改括号内
                //target.hurt(DamageSource.MAGIC,1145);//如果额外伤害就
                ToolData.putInt(ITERATION,ToolData.getInt(ITERATION)+1);
            }
        }
    }
    @Override
    public void onBreakSpeed(IToolStackView tool, int level, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier){
        if(level>7){
            ModDataNBT ToolNBT=tool.getPersistentData();
            event.setNewSpeed(event.getNewSpeed()+ToolNBT.getFloat(ITERATION)/100);
        }
    }
    @Override
    public void finishBreakingBlocks(IToolStackView tool, int level, ToolHarvestContext context){
        if(level>7){
            ModDataNBT ToolNBT=tool.getPersistentData();
            ToolNBT.putInt(ITERATION,ToolNBT.getInt(ITERATION)+1);
        }
    }
    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player != null&&level>7) {
            ModDataNBT toolData = tool.getPersistentData();
            if(toolData.get(ITERATION)==null){
                toolData.putInt(ITERATION,0);
            }
            tooltip.add(this.applyStyle((new TranslatableComponent("已迭代"+toolData.get(ITERATION).getAsString()+"次"))));
        }
    }
}
