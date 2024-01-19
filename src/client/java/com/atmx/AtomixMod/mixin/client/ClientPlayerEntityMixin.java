package com.atmx.AtomixMod.mixin.client;

import com.atmx.AtomixMod.Enchantments.ModEnchants;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Unique
	private int jumpCount = 0;
	@Unique
	private boolean jumpedLastTick = false;

	@Inject(method = "tickMovement", at = @At("HEAD"))
	private void tickMovement(CallbackInfo info) {
		ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
		int jumpLevel = EnchantmentHelper.getEquipmentLevel(ModEnchants.DUALJUMP, player);

		// Dual Jump
		if (player.isOnGround() || player.isClimbing()) {
			jumpCount = jumpLevel;
		} else if (!jumpedLastTick && jumpCount > 0 && player.getVelocity().y < 0) {
			if (player.input.jumping && canJump(player)) {
				float yaw = player.getYaw();
				jumpCount--;

				double x = -MathHelper.sin(yaw * 0.017453292F);
				double y = 0.25 + 0.125 * jumpLevel;
				double z = MathHelper.cos(yaw * 0.017453292F);

				Vec3d motion = new Vec3d(x/4, y, z/4);

				// Apply velocity based on sprinting or not
				double speedMultiplier = player.isSprinting() ? 1.25 * jumpLevel : 1.0;
				player.setVelocity(motion.x * speedMultiplier, motion.y, motion.z * speedMultiplier);
			}
		}
		int cloudLevel = EnchantmentHelper.getEquipmentLevel(ModEnchants.CLOUDFALL, player);

		if ((!player.isOnGround() || !player.isClimbing()) && cloudLevel > 0) {
			player.stopFallFlying();
		}

		jumpedLastTick = player.input.jumping;
	}

	@Unique
	private boolean canJump(ClientPlayerEntity player) {
		ItemStack chestItemStack = player.getEquippedStack(EquipmentSlot.CHEST);
		boolean wearingUsableElytra = chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isUsable(chestItemStack);

		return !wearingUsableElytra && !player.isFallFlying() && !player.hasVehicle()
				&& !player.isTouchingWater() && !player.hasStatusEffect(StatusEffects.LEVITATION)
				&& !player.getAbilities().creativeMode && !player.getAbilities().flying;
	}
}