package com.alexp777.atech.block.blocks;

import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.block.forges.SteelForgeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SteelForge extends ATechBaseEntityBlock{

	public SteelForge(Properties pProperties) {
		super(pProperties);
	}

	//return new ProjectTableBlockEntity(pPos, pState);

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new SteelForgeBlockEntity(pPos, pState);
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if(pState.getBlock() != pNewState.getBlock()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if(blockEntity instanceof SteelForgeBlockEntity)
				((SteelForgeBlockEntity)blockEntity).drops();
		}
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}

	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
								 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if(!pLevel.isClientSide()) {
			BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
			if(blockEntity instanceof SteelForgeBlockEntity)
				NetworkHooks.openGui(((ServerPlayer) pPlayer), (SteelForgeBlockEntity) blockEntity, pPos);
			else
				throw new IllegalStateException("Container provider is missing");
		}

		return InteractionResult.sidedSuccess(pLevel.isClientSide());
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
																  BlockEntityType<T> pBlockEntityType) {
		return createTickerHelper(pBlockEntityType,
				ModBlockEntities.STEEL_FORGE_BLOCK_ENTITY.get(),
				SteelForgeBlockEntity::tick);
	}
}
