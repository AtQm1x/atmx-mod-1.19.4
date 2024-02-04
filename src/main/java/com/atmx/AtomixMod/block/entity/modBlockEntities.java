package com.atmx.AtomixMod.block.entity;

import com.atmx.AtomixMod.atmxMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.atmx.AtomixMod.block.ModBlocks.DEMO_BLOCK;

public class modBlockEntities {

    public static BlockEntityType<DragonDrill> DEMO_BLOCK_ENTITY;
    public static void register() {
        DEMO_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(atmxMod.MOD_ID, "demo_block_entity"),
                FabricBlockEntityTypeBuilder.create(DragonDrill::new, DEMO_BLOCK).build()
        );
    }
}
