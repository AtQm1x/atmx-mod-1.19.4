package com.atmx.AtomixMod;

import com.atmx.AtomixMod.Enchantments.ModEnchants;
import com.atmx.AtomixMod.block.ModBlocks;
import com.atmx.AtomixMod.block.entity.modBlockEntities;
import com.atmx.AtomixMod.entity.ModEntities;
import com.atmx.AtomixMod.gamerules.ModGamerules;
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
        modBlockEntities.register();
        ModBlocks.registerModBlocks();
        ModEntities.register();
        ModWorldGeneration.generateModWordGen();
        ModEnchants.registerModEnchants();
        ModGamerules.registerGamerules();

    }
}