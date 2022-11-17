package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.world.item.Item;

public class EngineItem extends Item {

	public EngineItem() {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.stacksTo(1)
				.fireResistant());
	}


}
