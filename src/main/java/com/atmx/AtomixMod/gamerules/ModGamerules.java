package com.atmx.AtomixMod.gamerules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGamerules {
    public static GameRules.Key<GameRules.IntRule> ORBITAL_STRIKE_RANGE;

    public static void registerGamerules() {
        ORBITAL_STRIKE_RANGE =
                GameRuleRegistry.register("orbitalStrikeRange", GameRules.Category.MISC, GameRuleFactory.createIntRule(50));
    }
}
