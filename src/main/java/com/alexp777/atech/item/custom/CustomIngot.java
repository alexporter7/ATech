package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.world.item.Item;

public class CustomIngot extends Item {

	public CustomIngot() {
		super(new Item.Properties()
						.tab(ModCreativeModeTab.ATECH_TAB)
						.fireResistant()
						.stacksTo(64));
	}
}
