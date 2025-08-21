package net.anemoia.virtua.core.registry;

import net.anemoia.virtua.core.Virtua;
import net.anemoia.virtua.common.item.TimeBottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Virtua.MOD_ID);

    public static final RegistryObject<Item> DISTILLED_TIME = ITEMS.register("distilled_time",
            () -> new Item(new Item.Properties().rarity(Rarity.valueOf("RARE"))));
    public static final RegistryObject<Item> TIME_BOTTLE = ITEMS.register("time_bottle",
            () -> new TimeBottleItem(new Item.Properties().rarity(Rarity.valueOf("RARE")).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);}
}
