package com.alexp777.atech.screen.slot;

import com.alexp777.atech.item.ModItems;
import com.alexp777.atech.item.custom.EngineBlockItem;
import com.alexp777.atech.item.custom.EngineItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class EngineBlockSlot extends SlotItemHandler {
	public EngineBlockSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return true;
	}

}
