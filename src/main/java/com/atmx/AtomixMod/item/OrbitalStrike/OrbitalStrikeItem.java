package com.atmx.AtomixMod.item.OrbitalStrike;

import com.atmx.AtomixMod.atmxMod;
import net.minecraft.block.Block;
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

import java.util.List;
import java.util.Random;


public class OrbitalStrikeItem extends Item {
    public OrbitalStrikeItem(Settings settings) {
        super(settings);
    }
    private final float exRadius = 200;
    private float cooldownTicks = 0;
    private double cd;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        double reachDistance = 10000.0; // Adjust this value as needed
        Vec3d rotation = player.getRotationVec(1.0F);
        Vec3d start = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d end = start.add(rotation.x * reachDistance, rotation.y * reachDistance, rotation.z * reachDistance);

        BlockHitResult hitResult = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            // Run your function at the intersection point (blockPos)
            yourCustomFunction(world, blockPos, player);
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }
    private void yourCustomFunction(World world, BlockPos blockPos, PlayerEntity player) {
        // Your custom logic goes here
        if (!world.isClient() && cooldownTicks <= 0){
            cooldownTicks = 20 * 1;
            atmxMod.LOGGER.info("ran function at: " + blockPos.toString() + " in: " + world.toString());
            // Inform the player or log the explosion
            player.sendMessage(Text.literal(player.getEntityName() + ": Now I am become Death, the destroyer of worlds."), false);
            boom(world, blockPos, exRadius);
            player.sendMessage(Text.literal("Cooldown " + cd + "s"), true);
        }
        else if(!world.isClient()){
            player.sendMessage(Text.literal("Wait " + cd + "s"), true);
        }
    }

    public void boom(World world, BlockPos blockPos, double radius) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        atmxMod.LOGGER.info("boom at:" + x + " " + y + " " + z);
        int intRadius = (int) Math.ceil(radius);
        BlockPos.Mutable currentPos = new BlockPos.Mutable();

        for (int dx = intRadius; dx >= -intRadius; dx--) {
            for (int dz = intRadius; dz >= -intRadius; dz--) {
                for (int dy = intRadius; dy >= -intRadius; dy--) {
                    double distanceSquared = dx * dx + dy * dy + dz * dz;

                    if (distanceSquared <= radius * radius) {
                        BlockState blockState = world.getBlockState(currentPos);
                        //int rx = x + dx;
                        //int ry = y + dy;
                        //int rz = z + dz;

                        currentPos.set(x + dx, y + dy, z + dz);


                        if (blockState.getBlock() instanceof FluidBlock || blockState.getFluidState().getFluid() instanceof WaterFluid || blockState.getFluidState().getFluid() instanceof LavaFluid) {
                            // Remove fluids by setting the block state to air
                            world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 2);
                        } else if (blockState.getBlock().getBlastResistance() < 100
                                //&& blockState != Blocks.AIR.getDefaultState()
                                //&& blockState != Blocks.BEDROCK.getDefaultState()
                                && blockState.getBlock().getBlastResistance() > 0
                        ) {
                            // Queue block changes for later processing
                            world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), 2);
                            //Random r = new Random();
                            //// Simulate block drops (modify this based on your custom logic)
                            //double checkCriteria = r.nextDouble() * blockState.getBlock().getBlastResistance() / intRadius * 20;
                            //double dropReq = (radius * radius) / (100 * radius / 5 / 3 * 4) + (radius * 1.5 / 100);
//
                            //if (!world.isClient() && checkCriteria >= dropReq * 100000) {
                            //    BlockPos finalBlockPos = new BlockPos(blockPos.getX(), currentPos.getY(), blockPos.getZ());
                            //    Block.getDroppedStacks(blockState, (ServerWorld) world, currentPos, world.getBlockEntity(currentPos))
                            //            .forEach(itemStack -> Block.dropStack(world, finalBlockPos, itemStack));
                            //}
                        }
                    }
                }
            }
            atmxMod.LOGGER.info( (double)Math.round( (radius - dx) / (2 * radius) * 1000 ) / 10 + "%" );
        }
        System.gc();
        // Notify neighbors after processing all block changes
        world.updateNeighbors(blockPos, world.getBlockState(blockPos).getBlock());
        atmxMod.LOGGER.info("boom complete");
    }


    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        // default white text
        tooltip.add(Text.literal("Cooldown: " + cd + "s"));
        // formatted red text
        tooltip.add(Text.literal("explosion radius: " + exRadius));
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && entity instanceof PlayerEntity && cooldownTicks >= 0) {
            cd = Math.max((double)Math.round(cooldownTicks / 2) / 10, 0);
            cooldownTicks--;
        }
    }
}