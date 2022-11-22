package com.alexp777.atech.block.blocks;

import com.alexp777.atech.block.forges.SteelForgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SteelForge extends ATechBaseEntityBlock{

	public SteelForge(Properties pProperties) {
		super(pProperties);
	}

	//return new ProjectTableBlockEntity(pPos, pState);


	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new SteelForgeBlockEntity(pPos, pState);
	}
}
