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

	//Steel Forge
	public static final String STEEL_FORGE_TEMPERATURE_TAG = "atech.forge_temperature";
	public static final int STEEL_FORGE_MAX_TEMP = 200;

	public static final int STEEL_FORGE_CASE_TEMPERATURE = 0;
	public static final int STEEL_FORGE_CASE_MAX_TEMP = 1;
	public static final int STEEL_FORGE_CONTAINER_COUNT = 2;
	public static final int STEEL_FORGE_SLOTS = 3;

	public static final int STEEL_FORGE_IRON_SLOT = 0;
	public static final int STEEL_FORGE_CARBON_SLOT = 1;
	public static final int STEEL_FORGE_OUTPUT_SLOT = 2;

	//Project Table
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
