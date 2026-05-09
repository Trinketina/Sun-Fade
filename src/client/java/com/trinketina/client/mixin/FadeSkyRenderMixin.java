package com.trinketina.client.mixin;

import com.trinketina.client.TimeFadeI;
import com.trinketina.client.config.FadeSkyConfig;
import com.trinketina.client.config.TimeValuesConfigData;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.renderer.LevelRenderer.class)
public abstract class FadeSkyRenderMixin implements TimeFadeI {

    @Shadow
    @Nullable
    private ClientLevel level;

    @ModifyArg(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 2), index = 3)
    private float onRenderSun(float j) {
        if (!FadeSkyConfig.CONFIG.FadeSun) //cancel if fade sun is disabled
            return j;

        assert this.level != null;

        long time = this.level.dayTime();


        j -= 1 - this.setFadeFromTime(time, FadeSkyConfig.CONFIG.SunFadeData.FadeInStart, FadeSkyConfig.CONFIG.SunFadeData.FadeInEnd, FadeSkyConfig.CONFIG.SunFadeData.FadeOutStart, FadeSkyConfig.CONFIG.SunFadeData.FadeOutEnd);
        j = Math.clamp(j, 0.0f, 1.0f);
        return j;
    }

    @Inject(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 1))
    private void onRenderMoon(Matrix4f matrix4f, Matrix4f matrix4f2, float f, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {
        if (!FadeSkyConfig.CONFIG.FadeMoon) //cancel if fade moon is disabled
            return;

        assert this.level != null;

        long time = this.level.dayTime();

        float alpha = 1.0F - this.level.getRainLevel(f);

        alpha -= 1 - this.setFadeFromTime(time, FadeSkyConfig.CONFIG.MoonFadeData.FadeInStart, FadeSkyConfig.CONFIG.MoonFadeData.FadeInEnd, FadeSkyConfig.CONFIG.MoonFadeData.FadeOutStart, FadeSkyConfig.CONFIG.MoonFadeData.FadeOutEnd);
        alpha = Math.clamp(alpha, 0.0f, 1.0f);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
    }
}