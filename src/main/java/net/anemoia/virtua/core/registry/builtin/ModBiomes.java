package net.anemoia.virtua.core.registry.builtin;

import net.anemoia.virtua.core.Virtua;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Virtua.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ModBiomes {
    public static final ResourceKey<Biome> SANDS_OF_TIME = createKey("sands_of_time");

    public static void bootstrap(BootstapContext<Biome> context) {
        HolderGetter<PlacedFeature> feature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(SANDS_OF_TIME, sandsOfTime(feature, carvers));
    }

    public static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Virtua.MOD_ID, name));
    }

    public static Biome sandsOfTime(HolderGetter<PlacedFeature> features, HolderGetter<ConfiguredWorldCarver<?>> carvers) {
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .skyColor(2421480)
                                .fogColor(0)
                                .waterColor(2421480)
                                .waterFogColor(2421480)
                                .build()
                )
                .mobSpawnSettings(
                        new MobSpawnSettings.Builder()
                                .build()
                )
                .generationSettings(
                        new BiomeGenerationSettings.Builder(features, carvers)
                                .build()
                ).build();
    }
}
