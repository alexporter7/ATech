package com.alexp777.atech.block.forges;

import com.alexp777.atech.block.ATechBlockEntity;
import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteelForgeBlockEntity extends ATechBlockEntity implements MenuProvider {
	public SteelForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.STEEL_FORGE_BLOCK_ENTITY.get(), pPos, pBlockState, ModValue.STEEL_FORGE_SLOTS);
	}

	@Override
	public @NotNull Component getDisplayName() {
		return new TextComponent("Steel Forge");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
		return null;
	}
}
