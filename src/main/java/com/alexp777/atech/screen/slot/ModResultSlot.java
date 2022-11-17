package com.alexp777.atech.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModResultSlot extends SlotItemHandler {

	public ModResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	/**
	 * Don't want players putting stuff in result slots so
	 * @param stack ItemStack
	 * @return always false because people shouldn't shove stuff where it doesn't belong
	 */
	@Override
	public boolean mayPlace(@NotNull ItemStack stack) {
		return false;
	}
}
