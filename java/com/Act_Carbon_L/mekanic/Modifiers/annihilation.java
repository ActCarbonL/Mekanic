package com.Act_Carbon_L.mekanic.Modifiers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class annihilation extends Modifier {
    public annihilation() {
    }

    {MinecraftForge.EVENT_BUS.addListener(this::LivingHurtEvent);}

    private void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            LivingEntity target=event.getEntityLiving();//伤害作用到的生物命名为target方便后面引用
            ItemStack stack=player.getMainHandItem();//将玩家主手物品转化为stack
            int ModifierLevel= ModifierUtil.getModifierLevel(stack,this.getId());//计算玩家主手物品该词条等级
            IToolStackView tool= ToolStack.from(stack);//从stack这个工具获取IToolstackView
            //ModDataNBT ToolData=iToolStackView.getPersistentData();//从IToolstackView中获取nbt
            //ToolData.getInt(ITERATION);//如果你需要获取之前定义的key的int值
            if((!tool.isBroken())&&ModifierLevel>0){
                //event.setAmount(event.getAmount() * (1+ToolData.getInt(ITERATION)*0.01F));//如果修改原伤害就改括号内
                if(tool.getCurrentDurability()*100>ModifierLevel*tool.getStats().getInt(ToolStats.DURABILITY)){
                    target.hurt(DamageSource.MAGIC,50*tool.getStats().getInt(ToolStats.DURABILITY)*ModifierLevel);//如果额外伤害就
                    ToolDamageUtil.directDamage(tool, ModifierLevel*tool.getStats().getInt(ToolStats.DURABILITY)/100, player,player.getMainHandItem());
                }
                else{
                    target.hurt(DamageSource.MAGIC,5000*tool.getCurrentDurability());
                    ToolDamageUtil.directDamage(tool,tool.getCurrentDurability() , player,player.getMainHandItem());
                }
            }
        }
    }
    //tool.getStats().getInt(ToolStats.DURABILITY)
    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        Player player=context.getPlayer();
        ToolDamageUtil.directDamage(tool, level*tool.getStats().getInt(ToolStats.DURABILITY)/100, player,player.getMainHandItem());
    }
    @Override
    public void addToolStats(ToolRebuildContext context, int level, ModifierStatsBuilder builder) {
        ToolStats.MINING_SPEED.add(builder, level * 20); //箭矢初速度
    }
}
