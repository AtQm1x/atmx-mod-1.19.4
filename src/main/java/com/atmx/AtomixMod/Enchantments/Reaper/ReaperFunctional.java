package com.atmx.AtomixMod.Enchantments.Reaper;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ReaperFunctional {
    public static void SummonEntity(PlayerEntity user, LivingEntity target) {
        World world = user.getWorld();
        EntityType targetType = target.getType();
        TameableEntity entity = new WolfEntity(targetType, world);

        entity.setOwner(user);
        entity.setHealth(target.getMaxHealth());
        entity.setHeadYaw(target.headYaw);
        entity.setPitch(target.getPitch());
        entity.setPos(target.getX(), target.getY(), target.getZ());
        entity.setCustomName(Text.of("Undead Minion"));
        world.spawnEntity(entity);
    }
}
