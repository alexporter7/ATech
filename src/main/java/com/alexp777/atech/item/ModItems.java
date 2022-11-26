package com.alexp777.atech.item;

import com.alexp777.atech.ATech;
import com.alexp777.atech.item.custom.*;
import com.alexp777.atech.util.Alloy;
import com.alexp777.atech.util.FormFactor;
import com.alexp777.atech.util.Material;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Alex Porter
 * Used to register and handle all mod items
 */
public class ModItems {

	//Deferred registry
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ATech.MODID);

	//Items
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", CustomIngot::new);
	//public static final RegistryObject<Item> TUNGSTEN_STEEL_INGOT = ITEMS.register("tungsten_steel_ingot", CustomIngot::new);
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new ATechMaterial(Material.COPPER, FormFactor.INGOT));
	public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
			() -> new ATechMaterial(Material.COPPER, FormFactor.DUST));
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
			() -> new ATechMaterial(Material.TIN, FormFactor.INGOT));
	public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
			() -> new ATechAlloy(Alloy.BRONZE, FormFactor.INGOT));

	//Connecting Rods
	public static final RegistryObject<Item> CONNECTING_ROD =
			ITEMS.register("connecting_rod", ConnectingRodItem::new);

	//Pistons
	public static final RegistryObject<Item> PISTON = ITEMS.register("piston", PistonItem::new);

	//Engine Blocks
	public static final RegistryObject<Item> ENGINE_BLOCK = ITEMS.register("engine_block", EngineBlockItem::new);

	//Engines
	public static final RegistryObject<Item> ENGINE = ITEMS.register("engine", EngineItem::new);

	/**
	 * Used to register items in deferred registry
	 * @param eventBus Passed in from main mod class
	 */
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
