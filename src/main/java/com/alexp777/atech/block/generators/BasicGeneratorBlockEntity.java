package com.alexp777.atech.block.generators;

import com.alexp777.atech.block.ATechGeneratorEntity;
import com.alexp777.atech.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicGeneratorBlockEntity extends ATechGeneratorEntity {

	/*
	 * ======= Static Block Entity Information
	 */

	public static final int INVENTORY_SLOTS = 1;
	public static final int BASE_GENERATION = 200;
	public static final int BASE_EXTRACTION = 500;


	/*
	 * ======= Constructors =======
	 */

	/**
	 * Called from ModBlockEntities when registering and when creating from the regular block class
	 * @param pos block position
	 * @param state block state
	 */
	public BasicGeneratorBlockEntity(BlockPos pos, BlockState state) {
		this(pos, state, INVENTORY_SLOTS);
	}

	/**
	 * Called from the other constructor since our type will always be the same and the slots in
	 * this block will always be the same
	 * @param pPos block position
	 * @param pBlockState block state
	 * @param slots total inventory slots
	 */
	public BasicGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState, int slots) {
		super(ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(), pPos, pBlockState, slots);
		//Set base generation and extraction speeds
		this.baseGeneration = BASE_GENERATION;
		this.baseExtraction = BASE_EXTRACTION;
	}



}
