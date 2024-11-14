package com.Act_Carbon_L.mekanic.MekanicRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.Act_Carbon_L.mekanic.Mekanic.MOD_ID;

public class MekanicBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);


    //后面你就复制粘贴这个abcd改名顺着往下写就行了
    /*public static final RegistryObject<Block> ABCD_BLOCK = BLOCKS.register("abcd_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK)));*/
}
