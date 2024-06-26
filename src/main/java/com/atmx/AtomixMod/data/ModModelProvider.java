package com.atmx.AtomixMod.data;

import com.atmx.AtomixMod.block.ModBlocks;
import com.atmx.AtomixMod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DRAGON_GEM_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DRAGON_GEM_ORE);
        blockStateModelGenerator.registerItemModel(ModBlocks.DRAGON_DRILL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.DRAGON_GEM_ITEM, Models.GENERATED);
    }
}
