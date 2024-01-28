package com.atmx.AtomixMod;

import com.atmx.AtomixMod.Enchantments.ModEnchants;
import com.atmx.AtomixMod.block.ModBlocks;
import com.atmx.AtomixMod.gen.ModWorldGeneration;
import com.atmx.AtomixMod.item.ModItemGroup;
import com.atmx.AtomixMod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class atmxMod implements ModInitializer {
    public static final String MOD_ID = "atmx-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger("atmx-mod");

    @Override
    public void onInitialize() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(OnEntiyKilled::onAfterKilledOtherEntity);

        ModItemGroup.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.generateModWordGen();
        ModEnchants.registerModEnchants();


    }
}