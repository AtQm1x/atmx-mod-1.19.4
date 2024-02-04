package com.atmx.AtomixMod.Enchantments;

import com.atmx.AtomixMod.Enchantments.Reaper.Reaper;
import com.atmx.AtomixMod.atmxMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchants {


    public static Enchantment FROSTBITE = new FrostBite();
    public static Enchantment LIFESTEAL = new LifeSteal();
    public static Enchantment REAPER = new Reaper();
    public static Enchantment DUALJUMP = new DualJump();
    public static Enchantment CLOUDFALL = new CloudFall();

    public static void registerModEnchants() {
        atmxMod.LOGGER.info("Registering Mod Enchantments for " + atmxMod.MOD_ID);

        Registry.register(Registries.ENCHANTMENT, new Identifier(atmxMod.MOD_ID, "frostbite"), FROSTBITE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(atmxMod.MOD_ID, "lifesteal"), LIFESTEAL);
        Registry.register(Registries.ENCHANTMENT, new Identifier(atmxMod.MOD_ID, "reaper"), REAPER);
        Registry.register(Registries.ENCHANTMENT, new Identifier(atmxMod.MOD_ID, "dual_jump"), DUALJUMP);
        Registry.register(Registries.ENCHANTMENT, new Identifier(atmxMod.MOD_ID, "cloud_fall"), CLOUDFALL);
    }
}
