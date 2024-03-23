package com.atmx.AtomixMod.item.OrbitalStrike;

import com.atmx.AtomixMod.atmxMod;
import com.atmx.AtomixMod.gamerules.ModGamerules;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class OrbitalStrikeItem extends Item {
    private final double cd = 0;
    int cdticks = 0;
    private int exRadius = 1;

    public OrbitalStrikeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) {
            return TypedActionResult.fail(player.getStackInHand(hand));
        }
        double reachDistance = 10000.0; // Adjust this value as needed
        Vec3d rotation = player.getRotationVec(1.0F);
        Vec3d start = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d end = start.add(rotation.x * reachDistance, rotation.y * reachDistance, rotation.z * reachDistance);

        BlockHitResult hitResult = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            player.getItemCooldownManager().set(this, 20);
            BlockPos blockPos = hitResult.getBlockPos();
            // Run your function at the intersection point (blockPos)
            yourCustomFunction(world, blockPos, player);
            return TypedActionResult.fail(player.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(player.getStackInHand(hand));
        }
    }

    private void yourCustomFunction(World world, BlockPos blockPos, PlayerEntity player) {
        // Your custom logic goes here
        if (!world.isClient()) {
            //atmxMod.LOGGER.info("ran function at: " + blockPos.toString() + " in: " + world);
            // Inform the player or log the explosion
            player.sendMessage(Text.literal("<" + player.getEntityName() + "> Now I am become Death, the destroyer of worlds."), false);
            boom((ServerWorld) world, blockPos, exRadius);
        }
    }

    public void boom(ServerWorld world, BlockPos blockPos, int radius) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();

        int oldp = blockPos.getX();

        Iterable<BlockPos> offsetBox = BlockPos.iterate(new BlockPos(0, 0, 0), new BlockPos(radius, radius, radius));
        for (BlockPos offsetPos : offsetBox) {

            double distanceSquared = (offsetPos.getSquaredDistance(x, y, z));

            if (distanceSquared <= radius * radius) {

                BlockPos bp = new BlockPos(offsetPos.getX(), offsetPos.getY(), offsetPos.getZ());
                BlockState blockState = world.getBlockState(bp);

                explosionBreakBlock(blockState, world, blockPos.add(offsetPos.getX(),-offsetPos.getY(),offsetPos.getZ()));
                explosionBreakBlock(blockState, world, blockPos.add(-offsetPos.getX(),-offsetPos.getY(),offsetPos.getZ()));
                explosionBreakBlock(blockState, world, blockPos.add(offsetPos.getX(),-offsetPos.getY(),-offsetPos.getZ()));
                explosionBreakBlock(blockState, world, blockPos.add(-offsetPos.getX(),-offsetPos.getY(),-offsetPos.getZ()));

            }
            if (oldp != offsetPos.getZ()) {
                atmxMod.LOGGER.info((double) Math.round((float) ((offsetPos.getZ() - z) + radius) / (2 * radius) * 1000) / 10 + "%");
                oldp = offsetPos.getZ();
                //test
            }
        }
        // Notify neighbors after processing all block changes
        world.updateNeighbors(blockPos, world.getBlockState(blockPos).getBlock());
        atmxMod.LOGGER.info("boom complete");
    }

    private void explosionBreakBlock(BlockState b, ServerWorld world, BlockPos pos) {
        if (b.getBlock() instanceof FluidBlock || b.getFluidState().getFluid() instanceof WaterFluid || b.getFluidState().getFluid() instanceof LavaFluid) {
            // Remove fluids by setting the block state to air
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
        }
        if (b.getBlock().getBlastResistance() < 100 && !b.isAir()) {
            // Queue block changes for later processing
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);

            //Random r = new Random();
            // Simulate block drops (modify this based on your custom logic)
            //double checkCriteria = r.nextDouble() * b.getBlock().getBlastResistance() / radius * 20;
            // dropReq = (double) (radius * radius) / ((double) (100 * radius) / 5 / 3 * 4) + (radius * 1.5 / 100);
//
            //if (!world.isClient() && checkCriteria >= dropReq * 100000) {
            //    BlockPos finalBlockPos = new BlockPos(blockPos.getX(), offsetPos.getY(), blockPos.getZ());
            //    Block.getDroppedStacks(blockState, world, offsetPos, world.getBlockEntity(offsetPos))
            //            .forEach(itemStack -> Block.dropStack(world, finalBlockPos, itemStack));
            //}
        }
    }


    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        // formatted red text
        tooltip.add(Text.literal("Explosion radius: " + exRadius));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            exRadius = Objects.requireNonNull(world.getServer()).getGameRules()
                    .getInt(ModGamerules.ORBITAL_STRIKE_RANGE);
        }
        if (!world.isClient && entity instanceof PlayerEntity) {

        }
    }
}