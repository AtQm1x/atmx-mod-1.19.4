package com.atmx.AtomixMod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import com.atmx.AtomixMod.block.*;
import com.atmx.AtomixMod.item.*;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.DRAGON_GEM_BLOCK);
        addDrop(ModBlocks.DEMO_BLOCK);

        addDrop(ModBlocks.DRAGON_GEM_ORE, oreDrops(ModBlocks.DRAGON_GEM_ORE, ModItems.DRAGON_GEM));
    }
}
