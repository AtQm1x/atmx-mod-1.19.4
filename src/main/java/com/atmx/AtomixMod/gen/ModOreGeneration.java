package com.atmx.AtomixMod.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import com.atmx.AtomixMod.world.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {
        // includeByKey(BiomeKeys.END_BARRENS,BiomeKeys.END_HIGHLANDS,BiomeKeys.END_MIDLANDS,BiomeKeys.SMALL_END_ISLANDS
        // foundInTheEnd()

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.END_BARRENS,BiomeKeys.END_HIGHLANDS,BiomeKeys.END_MIDLANDS, BiomeKeys.SMALL_END_ISLANDS),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.DRAGON_GEM_ORE_PLACED_KEY);
    }
}
