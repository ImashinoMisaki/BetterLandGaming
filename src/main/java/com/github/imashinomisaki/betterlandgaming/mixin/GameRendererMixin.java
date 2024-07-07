package com.github.imashinomisaki.betterlandgaming.mixin;

import com.github.imashinomisaki.betterlandgaming.Land;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public final class GameRendererMixin {

    /**
     *
     * @reason 去除受伤视角抖动
     *
     */
    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void onHurtViewTilt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (Land.NO_HURT_SHAKE) ci.cancel();
    }
}
