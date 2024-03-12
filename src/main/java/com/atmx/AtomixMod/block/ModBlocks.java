package com.atmx.AtomixMod.block;

import com.atmx.AtomixMod.atmxMod;
import com.atmx.AtomixMod.block.custom.DragonDrill;
import com.atmx.AtomixMod.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block DRAGON_GEM_BLOCK = registerBlock("dragon_gem_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(10.0f, 1200f).requiresTool())
            , ModItemGroup.TMX_ITEM_GROUP);

    public static final Block DRAGON_GEM_ORE = registerBlock("dragon_gem_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(10.0f, 1200f).requiresTool()
                    , UniformIntProvider.create(2, 5)), ModItemGroup.TMX_ITEM_GROUP);

    public static final Block DRAGON_DRILL = registerBlock("dragon_drill",
            new DragonDrill(FabricBlockSettings.of(Material.METAL).strength(10f)), ModItemGroup.TMX_ITEM_GROUP);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(atmxMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        Item item = Registry.register(Registries.ITEM, new Identifier(atmxMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        atmxMod.LOGGER.info("Registering Mod Blocks for " + atmxMod.MOD_ID);
    }
}
