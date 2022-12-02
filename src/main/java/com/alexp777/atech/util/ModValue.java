package com.alexp777.atech.util;

public class ModValue {

	/**
	 * Colors (i don't care if this isn't best practice i just need to write it down)
	 * Gear outline - 3f3e3e
	 * Steel - 656363
	 * Tungstensteel - 45435b
	 */

	/**
	 * (dark_red) 	§4 	\u00A74 	11141120 	AA0000
	 * (red) 	§c 	\u00A7c 	16733525 	FF5555
	 * (gold) 	§6 	\u00A76 	16755200 	FFAA00
	 * (yellow) 	§e 	\u00A7e 	16777045 	FFFF55
	 * (dark_green) 	§2 	\u00A72 	43520 	00AA00
	 * (green) 	§a 	\u00A7a 	5635925 	55FF55
	 * (aqua) 	§b 	\u00A7b 	5636095 	55FFFF
	 * (dark_aqua) 	§3 	\u00A73 	43690 	00AAAA
	 * (dark_blue) 	§1 	\u00A71 	170 	0000AA
	 * (blue) 	§9 	\u00A79 	5592575 	5555FF
	 * (light_purple) 	§d 	\u00A7d 	16733695 	FF55FF
	 * (dark_purple) 	§5 	\u00A75 	11141290 	AA00AA
	 * (white) 	§f 	\u00A7f 	16777215 	FFFFFF
	 * (gray) 	§7 	\u00A77 	11184810 	AAAAAA
	 * (dark_gray) 	§8 	\u00A78 	5592405 	555555
	 * (black) 	§0 	\u00A70 	0 	000000
	 * Obfuscated	§k	\u00A7k
	 * Bold	§l	\u00A7l
	 * Strikethrough	§m	\u00A7m
	 * Underline	§n	\u00A7n
	 * Italic	§o	\u00A7o
	 * Reset the default color	§r	\u00A7r
	 */

	public static final String RESET_FORMATTER = "§r";
	public static final String BLUE_FORMATTER = "§9";
	public static final String DARK_GRAY_FORMATTER = "§8";

	public static final String CREATIVE_MODE_TAB_LABEL = "atech_tab";

	//Flywheels
	public static final int IRON_FLY_WHEEL_DURABILITY = 1000;
	public static final int STEEL_FLY_WHEEL_DURABILITY = 2500;
	public static final int TUNGSTEN_STEEL_FLY_WHEEL_DURABILITY = 5000;

	public static final String FLY_WHEEL_TEMPERATURE_TAG = "atech.fly_wheel_temperature";
	public static final String FLY_WHEEL_DURABILITY_TAG = "atech.fly_wheel_durability";
	public static final String FLY_WHEEL_MODIFIER_TAG = "atech.fly_wheel_modifier";

	//Gears
	public static final int IRON_GEAR_DURABILITY = 500;
	public static final int STEEL_GEAR_DURABILITY = 1500;
	public static final int TUNGSTEN_STEEL_GEAR_DURABILITY = 3000;

	public static final String GEAR_TEMPERATURE_TAG = "atech.fly_wheel_temperature";
	public static final String GEAR_DURABILITY_TAG = "atech.fly_wheel_durability";
	public static final String GEAR_MODIFIER_TAG = "atech.fly_wheel_modifier";

	//Connecting Rods
	public static final String CONNECTING_ROD_STRENGTH_TAG = "atech.connecting_rod_strength";
	public static final String CONNECTING_ROD_DURABILITY_TAG = "atech.connecting_rod_durability";
	public static final String CONNECTING_ROD_MODIFIER_TAG = "atech.connecting_rod_modifier";
	public static final String CONNECTING_ROD_MAX_RPM = "atech.connecting_rod_max_rpm";

	//Pistons
	public static final String PISTON_MAX_POWER_FACTOR_TAG = "atech.piston_max_power_factor";
	public static final String PISTON_DURABILITY_TAG = "atech.piston_durability"; //probably should use built in durability
	public static final String PISTON_MODIFIER_TAG = "atech.piston_modifier";

	//Block Entities
	public static final String BLOCK_ENTITY_INVENTORY_KEY = "inventory";

	/*
	======= Steel Forge =======
	 */

	//Recipe Related
	public static final String STEEL_FORGE_RECIPE_TYPE_ID = "steel_forging";

	//NBT Tags
	public static final String STEEL_FORGE_TEMPERATURE_TAG = "atech.forge_temperature";
	public static final String STEEL_FORGE_HEAT_TICK_TAG = "atech.forge_heat_ticks";
	public static final String STEEL_FORGE_COOLDOWN_FACTOR = "atech.forge_cooldown_factor";
	public static final String STEEL_FORGE_PROGRESS_TAG = "atech.forge_progress";
	public static final String STEEL_FORGE_MAX_PROGRESS_TAG = "atech.forge_max_progress";
	public static final int STEEL_FORGE_MAX_TEMP = 3500;

	//Fancy IIntArray Stuff
	public static final int STEEL_FORGE_CASE_TEMPERATURE_DATA = 0;
	public static final int STEEL_FORGE_CASE_MAX_TEMP_DATA = 1;
	public static final int STEEL_FORGE_COOLDOWN_FACTOR_DATA = 2;
	public static final int STEEL_FORGE_HEAT_TICK_DATA = 3;
	public static final int STEEL_FORGE_PROGRESS_DATA = 4;
	public static final int STEEL_FORGE_MAX_PROGRESS_DATA = 5;
	public static final int STEEL_FORGE_CONTAINER_COUNT = 6;

	//Inventory Values
	public static final int STEEL_FORGE_SLOTS = 3;

	public static final int STEEL_FORGE_IRON_SLOT = 0;
	public static final int STEEL_FORGE_CARBON_SLOT = 1;
	public static final int STEEL_FORGE_OUTPUT_SLOT = 2;

	/*
	======= Crusher =======
	The Crusher has one Item Input slot that takes in Ore

	There are two (2) component Slots
		1. Piston Slot - Piston Component determines machine speed
		2. Plate Slot - Hardness/Integrity determines maximum ore tier
	 */

	//=== Recipe ===
	public static final String CRUSHING_RECIPE_KEY = "crushing";

	//=== Slot Info ===
	public static final int CRUSHER_SLOTS = 5;

	public static final int CRUSHER_INPUT_SLOT = 0;
	public static final int CRUSHER_ENERGY_SLOT = 1;
	public static final int CRUSHER_PISTON_SLOT = 2;
	public static final int CRUSHER_PRESS_PLATE_SLOT = 3;
	public static final int CRUSHER_OUTPUT_SLOT = 4;

	//=== NBT DATA ===
	public static final String CRUSHER_MODIFIER_KEY = "crusher_modifier";
	public static final String CRUSHER_PROGRESS_KEY = "crusher_progress";
	public static final String CRUSHER_MAX_PROGRESS_KEY = "crusher_max_progress";

	//=== Fancy IIntArray Stuff ===

	public static final int CRUSHER_DATA_SIZE = 5;

	public static final int CRUSHER_MODIFIER_DATA = 0;
	public static final int CRUSHER_PROGRESS_DATA = 1;
	public static final int CRUSHER_MAX_PROGRESS_DATA = 2;
	public static final int CRUSHER_HAS_RECIPE_DATA = 3;
	public static final int CRUSHER_RECIPE_TIER = 4;

	/*
	======= Project Table =======
	 */
	public static final int PROJECT_TABLE_SLOTS = 11;
	public static final String PROJECT_TABLE_INVENTORY_KEY = "inventory";

	public static final int PISTON_SLOT_1 = 0;
	public static final int PISTON_SLOT_2 = 1;
	public static final int PISTON_SLOT_3 = 2;
	public static final int PISTON_SLOT_4 = 3;

	public static final int CONNECTING_ROD_SLOT_1 = 4;
	public static final int CONNECTING_ROD_SLOT_2 = 5;
	public static final int CONNECTING_ROD_SLOT_3 = 6;
	public static final int CONNECTING_ROD_SLOT_4 = 7;

	public static final int ENGINE_BLOCK_SLOT = 8;
	public static final int HAMMER_SLOT = 9;
	public static final int OUTPUT_SLOT = 10;
}
