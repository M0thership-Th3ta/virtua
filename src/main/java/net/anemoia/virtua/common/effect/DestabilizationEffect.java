package net.anemoia.virtua.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DestabilizationEffect extends MobEffect {
    public DestabilizationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x66F2F2); // Example color, adjust as needed
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
