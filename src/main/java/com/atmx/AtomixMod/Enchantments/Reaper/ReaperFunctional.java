package com.atmx.AtomixMod.Enchantments.Reaper;

import com.atmx.AtomixMod.entity.MinionEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class ReaperFunctional {
    public static void SummonEntity(PlayerEntity user, LivingEntity target, World world) {
        EntityType targetType = target.getType();
        MinionEntity entity = new MinionEntity(targetType, target.getWorld());
        entity.setOwner(user);

        entity.setHealth(target.getMaxHealth());
        entity.setHeadYaw(target.headYaw);
        entity.setPitch(target.getPitch());
        entity.setPos(target.getX(), target.getY(), target.getZ());
        entity.setCustomName(Text.of("Undead Minion"));
        world.spawnEntity(entity);
    }
}
