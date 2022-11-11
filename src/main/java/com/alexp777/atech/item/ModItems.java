package com.alexp777.atech.item;

import com.alexp777.atech.ATech;
import com.alexp777.atech.item.custom.FlyWheelItem;
import com.alexp777.atech.util.Material;
import com.alexp777.atech.util.ModValue;
import net.minecraft.world.item.CreativeModeTab;
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

	//Flywheels
	public static final RegistryObject<Item> IRON_FLY_WHEEL =
			ITEMS.register("iron_fly_wheel", () -> new FlyWheelItem(Material.IRON, ModValue.IRON_FLY_WHEEL_DURABILITY));
	public static final RegistryObject<Item> STEEL_FLY_WHEEL =
			ITEMS.register("steel_fly_wheel", () -> new FlyWheelItem(Material.STEEL, ModValue.STEEL_FLY_WHEEL_DURABILITY));
	public static final RegistryObject<Item> TUNGSTEN_STEEL_FLY_WHEEL =
			ITEMS.register("tungsten_steel_fly_wheel", () -> new FlyWheelItem(Material.TUNGSTEN_STEEL, ModValue.TUNGSTEN_STEEL_FLY_WHEEL_DURABILITY));

	/**
	 * Used to register items in deferred registry
	 * @param eventBus Passed in from main mod class
	 */
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
