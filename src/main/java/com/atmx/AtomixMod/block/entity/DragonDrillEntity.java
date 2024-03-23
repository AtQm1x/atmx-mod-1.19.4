package com.atmx.AtomixMod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DragonDrillEntity extends BlockEntity {
    private static int ctick = 0;
    private static int delay = 10;

    public DragonDrillEntity(BlockPos pos, BlockState state) {
        super(modBlockEntities.DEMO_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, DragonDrillEntity be) {
        ctick++;
        if (ctick > delay) {

            BlockPos bp = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
            BlockState bs = world.getBlockState(bp);
            if (bs.getBlock() instanceof FluidBlock
                    || bs.getFluidState().getFluid() instanceof WaterFluid
                    || bs.getFluidState().getFluid() instanceof LavaFluid) {
                // Remove fluids by setting the block state to air
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            }
            if (bs.getBlock().getBlastResistance() < 100 && !bs.isAir()) {

                world.removeBlock(bp, true);
                ctick = 0;
            }
        }
    }

    // Serialize the BlockEntity
    @Override
    public void writeNbt(NbtCompound nbt) {
        // Save the current value of the number to the nbt
        nbt.putInt("delay", delay);

        super.writeNbt(nbt);
    }

    // Deserialize the BlockEntity
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        delay = nbt.getInt("delay");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
