package com.atmx.AtomixMod.entity;

import com.atmx.AtomixMod.atmxMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MinionEntity> MINION = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(atmxMod.MOD_ID, "minion"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MinionEntity::new).dimensions(EntityDimensions.fixed(0.75f, 1.75f)).build()
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(MINION, MinionEntity.createMobAttributes());
    }
}
