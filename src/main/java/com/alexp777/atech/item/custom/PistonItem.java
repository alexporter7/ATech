package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.world.item.Item;

public class PistonItem extends Item {

	public PistonItem() {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.stacksTo(64));
	}

}
