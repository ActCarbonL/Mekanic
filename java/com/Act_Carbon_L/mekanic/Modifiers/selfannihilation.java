package com.Act_Carbon_L.mekanic.Modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class selfannihilation extends Modifier {
    public selfannihilation() {
    }

    {MinecraftForge.EVENT_BUS.addListener(this::LivingHurtEvent);}

    private void LivingHurtEvent(LivingHurtEvent event) {
        if(event.getSource().getEntity() instanceof Player player){
            ItemStack stack=player.getMainHandItem();//将玩家主手物品转化为stack
            int ModifierLevel= ModifierUtil.getModifierLevel(stack,this.getId());//计算玩家主手物品该词条等级
            IToolStackView tool= ToolStack.from(stack);//从stack这个工具获取IToolstackView
            //ModDataNBT ToolData=iToolStackView.getPersistentData();//从IToolstackView中获取nbt
            //ToolData.getInt(ITERATION);//如果你需要获取之前定义的key的int值
            if((!tool.isBroken())&&ModifierLevel>0){
                ToolDamageUtil.directDamage(tool, ModifierLevel*tool.getStats().getInt(ToolStats.DURABILITY)/100, player,stack);
                player.kill();
            }
        }
    }
    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        Player player=context.getPlayer();
        player.kill();
        ToolDamageUtil.directDamage(tool, level*tool.getStats().getInt(ToolStats.DURABILITY)/100, player,player.getMainHandItem());
    }
}
