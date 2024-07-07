package com.github.imashinomisaki.betterlandgaming.mixin;

import com.github.imashinomisaki.betterlandgaming.Land;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.text.DecimalFormat;
@Mixin(TntEntityRenderer.class)
public abstract class TntEntityRendererMixin extends EntityRenderer<TntEntity> {

    @Unique
    private final DecimalFormat betterLandGaming$timeFormatter = new DecimalFormat("0.00");

    public TntEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Inject(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    public void betterLandGaming$render(TntEntity entity, float yaw, float delta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
        if (Land.TNT_TIME) {
            super.renderLabelIfPresent(entity, betterLandGaming$getFuseTime(entity.getFuse()), matrixStack,
                    vertexConsumerProvider, light);
        }
    }

    @Unique
    private Text betterLandGaming$getFuseTime(int time) {
        float secs = time / 20F;
        return Text.of(String.valueOf(betterLandGaming$timeFormatter.format(secs))).copy()
                .setStyle(Style.EMPTY.withColor(betterLandGaming$getCurrentColor(secs)));
    }

    @Unique
    private Formatting betterLandGaming$getCurrentColor(float seconds) {
        if (seconds > 7d) {
            return Formatting.DARK_AQUA;
        } else if (seconds > 6d) {
            return Formatting.AQUA;
        } else if (seconds > 4d) {
            return Formatting.DARK_GREEN;
        } else if (seconds > 3d) {
            return Formatting.GREEN;
        } else if (seconds > 2d) {
            return Formatting.GOLD;
        } else if (seconds > 1d) {
            return Formatting.RED;
        } else if (seconds > 0d) {
            return Formatting.DARK_RED;
        } else {
            return Formatting.WHITE;
        }
    }
}
