package com.atmx.AtomixMod.data;

import com.atmx.AtomixMod.block.ModBlocks;
import com.atmx.AtomixMod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.DRAGON_GEM_BLOCK);
        addDrop(ModBlocks.DRAGON_DRILL);

        addDrop(ModBlocks.DRAGON_GEM_ORE, oreDrops(ModBlocks.DRAGON_GEM_ORE, ModItems.DRAGON_GEM_ITEM));
    }
}
