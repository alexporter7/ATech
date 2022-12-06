package com.alexp777.atech.block.blocks;

import com.alexp777.atech.block.generators.BasicGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BasicGeneratorBlock extends BaseEntityBlock {

	public BasicGeneratorBlock() {
		super(BlockBehaviour.Properties
				.copy(Blocks.IRON_BLOCK)
				.noOcclusion());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new BasicGeneratorBlockEntity(pPos, pState);
	}
}
