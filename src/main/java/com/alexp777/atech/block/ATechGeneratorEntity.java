package com.alexp777.atech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ATechGeneratorEntity extends ATechBlockEntity {

	public ATechGeneratorEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slots) {
		super(pType, pPos, pBlockState, slots);
	}
}
