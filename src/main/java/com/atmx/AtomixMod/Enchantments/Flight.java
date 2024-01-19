package com.atmx.AtomixMod.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Flight extends Enchantment {

    protected Flight(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[] {EquipmentSlot.CHEST});
    }

    @Override
    public int getMinPower(int level){
        return 1;
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }
}
