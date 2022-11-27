package com.alexp777.atech.block.blocks;

import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.block.machines.CrusherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class Crusher extends ATechBaseEntityBlock{

	public Crusher(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new CrusherBlockEntity(pPos, pState);
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if(pState.getBlock() != pNewState.getBlock())
			if (pLevel.getBlockEntity(pPos) instanceof CrusherBlockEntity)
				((CrusherBlockEntity) pLevel.getBlockEntity(pPos)).drops();

		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(),
				CrusherBlockEntity::tick);
	}
}
