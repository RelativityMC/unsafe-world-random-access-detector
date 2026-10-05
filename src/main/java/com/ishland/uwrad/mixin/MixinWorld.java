package com.ishland.uwrad.mixin;

import com.ishland.uwrad.common.CheckedThreadLocalRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSeed;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(World.class)
public class MixinWorld {

    @Shadow @Final private Thread thread;

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;create()Lnet/minecraft/util/math/random/Random;", remap = true), remap = false)
    private Random redirectWorldRandomInit() {
        Class<?> clazz = this.getClass();
        while (clazz != null) {
            if (clazz.getName().equals("com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld")) { // stop-gap solution
                return Random.create();
            }
            clazz = clazz.getSuperclass();
        }
        return new CheckedThreadLocalRandom(RandomSeed.getSeed(), () -> this.thread);
    }

}
