package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.world.item.Item;

public class EngineBlockItem extends Item {

	public EngineBlockItem() {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.stacksTo(1)); //It's heavy obviously
	}

}
