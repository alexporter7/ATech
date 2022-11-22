package com.alexp777.atech.block.forges;

import com.alexp777.atech.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SteelForgeBlockEntity extends BlockEntity implements MenuProvider {
	public SteelForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.STEEL_FORGE_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	@Override
	public Component getDisplayName() {
		return null;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		return null;
	}
}
