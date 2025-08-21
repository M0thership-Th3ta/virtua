package net.anemoia.virtua.client.shader;

import net.anemoia.virtua.core.Virtua;
import net.anemoia.virtua.core.registry.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Virtua.MOD_ID, value = Dist.CLIENT)
public class ClientDestabilizationShaderHandler {

    private static final ResourceLocation[] AMPLIFIER_SHADERS = {
            new ResourceLocation("minecraft:shaders/post/flip.json"),
            new ResourceLocation("minecraft:shaders/post/invert.json"),
            new ResourceLocation("minecraft:shaders/post/bumpy.json"),
            new ResourceLocation("minecraft:shaders/post/blobs2.json"),
            new ResourceLocation("minecraft:shaders/post/scan_pincushion.json"),
            new ResourceLocation("minecraft:shaders/post/wobble.json"),
            new ResourceLocation("minecraft:shaders/post/phosphor.json"),
            new ResourceLocation("minecraft:shaders/post/pencil.json"),
            new ResourceLocation("minecraft:shaders/post/desaturate.json"),
            new ResourceLocation("minecraft:shaders/post/deconverge.json")
    };

    private static boolean applied = false;
    private static ResourceLocation currentShader = null;
    private static int lastAmplifier = -1;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        var effectInstance = mc.player.getEffect(ModEffects.DESTABILIZATION.get());
        if (effectInstance != null) {
            int amp = effectInstance.getAmplifier();
            int idx = Math.min(amp, AMPLIFIER_SHADERS.length - 1);
            ResourceLocation desired = AMPLIFIER_SHADERS[idx];

            if (!applied || lastAmplifier != amp || currentShader != desired) {
                applyShader(mc, desired);
                lastAmplifier = amp;
            }
        } else if (applied) {
            unloadShader(mc);
        }
    }

    private static void applyShader(Minecraft mc, ResourceLocation shader) {
        unloadShader(mc);
        try {
            mc.gameRenderer.loadEffect(shader);
            currentShader = shader;
            applied = true;
        } catch (Exception e) {
            applied = false;
            currentShader = null;
        }
    }

    private static void unloadShader(Minecraft mc) {
        if (!applied) return;
        try {
            mc.gameRenderer.shutdownEffect();
        } catch (Exception ignored) {}
        applied = false;
        currentShader = null;
        lastAmplifier = -1;
    }
}
