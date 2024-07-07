package com.github.imashinomisaki.betterlandgaming.mixin;

import com.github.imashinomisaki.betterlandgaming.Land;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public final class ParticleManagerMixin {

    /**
     *
     * @reason 去除挖掘方块粒子
     *
     */
    @Inject(method = "addBlockBreakParticles", at = @At("HEAD"), cancellable = true)
    private void cancelBreakParticles(BlockPos pos, BlockState state, CallbackInfo ci) {
        if (Land.DISABLE_BLOCK_BREAK_PARTICLES) ci.cancel();

    }
}
