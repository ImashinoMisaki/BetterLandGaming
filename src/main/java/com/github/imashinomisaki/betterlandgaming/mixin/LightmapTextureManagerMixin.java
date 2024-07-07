package com.github.imashinomisaki.betterlandgaming.mixin;

import com.github.imashinomisaki.betterlandgaming.Land;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightmapTextureManager.class)
public final class LightmapTextureManagerMixin {

    /**
     *
     * @reason 夜视
     *
     */
    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;getGamma()Lnet/minecraft/client/option/SimpleOption;"))
    private SimpleOption<Double> fullBright(GameOptions instance) {
        if (Land.FULL_BRIGHT) {
            return new SimpleOption<>("options.gamma", SimpleOption.emptyTooltip(), (optionText, value) -> optionText,
                    SimpleOption.DoubleSliderCallbacks.INSTANCE, 15D, value -> {
            });
        } else {
            return instance.getGamma();
        }
    }
}
