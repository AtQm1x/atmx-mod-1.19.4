package com.atmx.AtomixMod.item;

import com.atmx.AtomixMod.atmxMod;
import com.atmx.AtomixMod.item.OrbitalStrike.OrbitalStrikeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DRAGON_GEM = registerItem("dragon_gem",
            new Item(new FabricItemSettings()));
    public static final Item ORBITAL_STRIKE_ITEM = registerItem("orbital_strike_item",
            new OrbitalStrikeItem(new FabricItemSettings()));
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM,new Identifier(atmxMod.MOD_ID,name),item);
    }
    public static void addItemsToItemGroup(){
        addToItemGroup(ModItemGroup.moai,DRAGON_GEM);
        addToItemGroup(ModItemGroup.moai,ORBITAL_STRIKE_ITEM);

    }
    public static final void addToItemGroup(ItemGroup group,Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems(){
        //this runs on initialize so almost anything must be here
        atmxMod.LOGGER.info("Registering Mod Items for " + atmxMod.MOD_ID);

        addItemsToItemGroup();
    }
}
