package net.anemoia.virtua.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.anemoia.virtua.core.Virtua;
import net.anemoia.virtua.core.registry.builtin.*;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, ModOverworldBiomeSlices::bootstrap);

    public ModDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider, BUILDER, Set.of(Virtua.MOD_ID, "minecraft"));
    }
}
