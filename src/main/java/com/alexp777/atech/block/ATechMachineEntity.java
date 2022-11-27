package com.alexp777.atech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ATechMachineEntity extends ATechBlockEntity {

	public ATechMachineEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slots) {
		super(pType, pPos, pBlockState, slots);

	}

	/*
	The reason this is here and not somewhere else is I might want
	multiple output slots or something in machines and it might
	be different than somewhere else
	 */
	public static boolean canInsertInOutputSlot(SimpleContainer inventory, ItemStack output,
												int outputSlot) {
		return inventory.getItem(outputSlot).isEmpty()
				|| inventory.getItem(outputSlot).getItem() == output.getItem();
	}

	public static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory, int outputSlot) {
		return inventory.getItem(outputSlot).getMaxStackSize()
				> inventory.getItem(outputSlot).getCount();
	}

}
