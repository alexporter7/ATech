package com.alexp777.atech.block.blocks;

import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.block.forges.SteelForgeBlockEntity;
import com.alexp777.atech.block.machines.CrusherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Crusher extends ATechBaseEntityBlock{

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public Crusher(Properties pProperties) {
		super(pProperties);
	}

	/*
	======= Direction/Facing Properties =======
	 */

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return this.defaultBlockState().setValue(FACING,
				pContext.getHorizontalDirection().getOpposite());
	}

	@SuppressWarnings("deprecated")
	@Override
	public @NotNull BlockState rotate(BlockState pState, Rotation pRotation) {
		return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
	}

	@SuppressWarnings("deprecated")
	@Override
	public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(FACING);
	}

	/*
	======= BlockState Properties =======
	 */
	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if(pState.getBlock() != pNewState.getBlock())
			if (pLevel.getBlockEntity(pPos) instanceof CrusherBlockEntity)
				((CrusherBlockEntity) pLevel.getBlockEntity(pPos)).drops();

		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}

	/*
	======= GUI =======
	 */

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if(!pLevel.isClientSide()) {
			if(pLevel.getBlockEntity(pPos) instanceof CrusherBlockEntity)
				NetworkHooks.openGui((ServerPlayer) pPlayer, (CrusherBlockEntity) pLevel.getBlockEntity(pPos), pPos);
			else
				throw new IllegalStateException("Container Provider Missing");
		}

		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}
	/*
	======= Block Entity Information =======
	 */

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new CrusherBlockEntity(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType, ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(),
				CrusherBlockEntity::tick);
	}


}
