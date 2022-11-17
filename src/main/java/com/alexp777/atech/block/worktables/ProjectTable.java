package com.alexp777.atech.block.worktables;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ProjectTable extends Block {

	//public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public ProjectTable() {
		super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion());
	}

}
