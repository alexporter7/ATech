package com.alexp777.atech.item;

import com.alexp777.atech.block.ModBlocks;
import com.alexp777.atech.util.ModValue;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

	public static final CreativeModeTab ATECH_TAB = new CreativeModeTab(ModValue.CREATIVE_MODE_TAB_LABEL) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.STEEL_FORGE.get());
		}

	};
}
