package com.alexp777.atech.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ATechBaseEntityBlock extends BaseEntityBlock {

	protected ATechBaseEntityBlock(Properties pProperties) {
		super(pProperties);
	}


	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return null;
	}



}
