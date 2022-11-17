package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;

public class ConnectingRodItem extends Item {

	public ConnectingRodItem() {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.stacksTo(1));
	}

}
