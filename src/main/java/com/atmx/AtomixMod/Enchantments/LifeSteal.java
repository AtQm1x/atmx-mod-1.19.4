package com.atmx.AtomixMod.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;

public class LifeSteal extends Enchantment {

    public LifeSteal() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        World world = user.getWorld();
        if (!world.isClient()) {
            if (target instanceof LivingEntity) {
                if (Math.random() <= 0.1 + 0.05f * level) {
                    float userMaxHealth = user.getMaxHealth();
                    user.heal(userMaxHealth * 0.1f);
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 2, 0));
                }
            }
        }
        super.onTargetDamaged(user, target, level);
    }
}