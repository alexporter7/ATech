package com.alexp777.atech.block;

import com.alexp777.atech.block.worktables.ProjectTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class ProjectTable extends BaseEntityBlock {


	protected ProjectTable(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new ProjectTableBlockEntity(pPos, pState);
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if(pState.getBlock() != pNewState.getBlock()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if(blockEntity instanceof ProjectTableBlockEntity)
				((ProjectTableBlockEntity) blockEntity).drops();
		}

		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
								 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		//If instance is server side
		if(!pLevel.isClientSide()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if(blockEntity instanceof ProjectTableBlockEntity) //If entity is ProjectTableBlockEntity
				NetworkHooks.openGui(((ServerPlayer) pPlayer), (ProjectTableBlockEntity) blockEntity, pPos);
			else
				throw new IllegalStateException("Container provider is missing");
		}

		//Return successful InteractionResult on Client Side
		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	//	return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
																  BlockEntityType<T> pBlockEntityType) {

		return createTickerHelper(pBlockEntityType,
				ModBlockEntities.PROJECT_TABLE_BLOCK_ENTITY.get(),
				ProjectTableBlockEntity::tick);
		//return super.getTicker(pLevel, pState, pBlockEntityType);
	}
}
