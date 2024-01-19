package com.atmx.AtomixMod.world;

import com.atmx.AtomixMod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

import static com.atmx.AtomixMod.atmxMod.MOD_ID;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> DRAGON_GEM_ORE_KEY = registerKey("dragon_gem_ore");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest endstone = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> End_Ores =
                List.of(OreFeatureConfig.createTarget(endstone, ModBlocks.DRAGON_GEM_ORE.getDefaultState()));

        register(context, DRAGON_GEM_ORE_KEY, Feature.ORE, new OreFeatureConfig(End_Ores, 3));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}