package com.atmx.AtomixMod.Enchantments.Reaper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ReaperFunctional {
    public static void SummonEntity(PlayerEntity user, LivingEntity target){
        World world = user.getWorld();
        EntityType targetType = target.getType();
        LivingEntity entity = new WolfEntity(targetType, world);

        entity.setHealth(target.getMaxHealth());
        entity.setHeadYaw(target.headYaw);
        entity.setPitch(target.getPitch());
        entity.setPos(target.getX(), target.getY(), target.getZ());
        entity.setCustomName(Text.of("Undead Minion"));
        world.spawnEntity(entity);
    }
}
