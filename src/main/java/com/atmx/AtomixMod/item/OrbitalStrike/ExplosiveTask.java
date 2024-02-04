package com.atmx.AtomixMod.item.OrbitalStrike;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

class ExplosiveTask extends Thread {
    public World world;
    public BlockPos blockPos;
    double radius;

    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Thread " + Thread.currentThread().getId()
                            + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}