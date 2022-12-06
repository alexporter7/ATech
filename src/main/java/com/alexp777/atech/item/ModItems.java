package com.alexp777.atech.item;

import com.alexp777.atech.ATech;
import com.alexp777.atech.item.custom.*;
import com.alexp777.atech.util.ATechComponent;
import com.alexp777.atech.util.FormFactor;
import com.alexp777.atech.util.ATechMaterial;
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

	/*
	======= Custom Parts =======
	 */

	public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod",
			() -> new ATechMaterialItem(ATechMaterial.IRON, FormFactor.ROD));
	public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
			() -> new ATechMaterialItem(ATechMaterial.IRON, FormFactor.PLATE));
	public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
			() -> new ATechMaterialItem(ATechMaterial.IRON, FormFactor.DUST));

	/*
	======= Custom Materials =======
	 */
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new ATechMaterialItem(ATechMaterial.COPPER, FormFactor.INGOT));
	public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust",
			() -> new ATechMaterialItem(ATechMaterial.COPPER, FormFactor.DUST));
	public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
			() -> new ATechMaterialItem(ATechMaterial.TIN, FormFactor.INGOT));
	public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust",
			() -> new ATechMaterialItem(ATechMaterial.TIN, FormFactor.DUST));

	public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
			() -> new ATechMaterialItem(ATechMaterial.NICKEL, FormFactor.INGOT));
	public static final RegistryObject<Item> NICKEL_DUST = ITEMS.register("nickel_dust",
			() -> new ATechMaterialItem(ATechMaterial.NICKEL, FormFactor.DUST));

	public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
			() -> new ATechMaterialItem(ATechMaterial.LEAD, FormFactor.INGOT));
	public static final RegistryObject<Item> LEAD_DUST = ITEMS.register("lead_dust",
			() -> new ATechMaterialItem(ATechMaterial.LEAD, FormFactor.DUST));

	/*
	======= Alloys =======
	 */



	/*
	======= Components =======
	 */

	//Motors
	public static final RegistryObject<Item> BASIC_MOTOR = ITEMS.register("basic_motor",
			() -> new ATechComponentItem(ATechComponent.BASIC_MOTOR));


	//Press Plates
	public static final RegistryObject<Item> IRON_PRESS_PLATE = ITEMS.register("iron_press_plate",
			() -> new ATechComponentItem(ATechComponent.IRON_PRESS_PLATE));

	//Blades

	//Pistons
	public static final RegistryObject<Item> BASIC_PISTON = ITEMS.register("basic_piston",
			() -> new ATechComponentItem(ATechComponent.BASIC_PISTON));
	public static final RegistryObject<Item> MAGIC_PISTON = ITEMS.register("magic_piston",
			() -> new ATechComponentItem(ATechComponent.MAGIC_PISTON));

	/*
	======= Engine Parts =======
	 */
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

	public static void registerFullMaterial(String baseName, ATechMaterial material) {
		ITEMS.register(baseName + "_ingot", () -> new ATechMaterialItem(material, FormFactor.INGOT));
		ITEMS.register(baseName + "_dust", () -> new ATechMaterialItem(material, FormFactor.DUST));
	}

}
