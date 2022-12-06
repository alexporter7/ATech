package com.alexp777.atech.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ATechGeneratorEntity extends ATechBlockEntity {

	/*
	 * ======= Generator Variables =======
	 */
	protected int baseGeneration;
	protected int baseExtraction;


	/**
	 * Constructor from other Generator classes
	 * @param pType block entity type is provided within the block entity class calling super
	 * @param pPos block position
	 * @param pBlockState block state
	 * @param slots how many inventory slots the block will have
	 */
	public ATechGeneratorEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slots) {
		super(pType, pPos, pBlockState, slots);
	}

	/*
	 * ======= Get Block Information =======
	 */

	protected int getBaseGeneration() {
		return this.baseGeneration;
	}

	protected void setBaseGeneration(int amount) {
		this.baseGeneration = amount;
	}

	protected int getBaseExtraction() {
		return this.baseExtraction;
	}

	protected void setBaseExtraction(int amount) {
		this.baseExtraction = amount;
	}
}
