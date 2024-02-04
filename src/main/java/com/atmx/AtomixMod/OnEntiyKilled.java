package com.atmx.AtomixMod;

import com.atmx.AtomixMod.Enchantments.Reaper.ReaperFunctional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;

public class OnEntiyKilled {
    public static void onAfterKilledOtherEntity(ServerWorld world, Entity user, LivingEntity killedEntity) {
        if (user instanceof PlayerEntity player) {
            ItemStack MainHandItemStack = player.getMainHandStack();
            NbtList Enchantments = MainHandItemStack.getEnchantments();
            for (NbtElement enc : Enchantments) {
                if (enc instanceof NbtCompound enchantmentTag) {
                    String id = enchantmentTag.getString("id");
                    short level = enchantmentTag.getShort("lvl");
                    //atmxMod.LOGGER.info("id:" + id + ", lvl:" + level);
                    switch (id) {
                        case "atmx-mod:reaper":
                            if (Math.random() <= 0.125 * level) {
                                ReaperFunctional.SummonEntity(player, killedEntity,world);
                            }
                            break;
                    }
                }
            }
        }
    }
}
