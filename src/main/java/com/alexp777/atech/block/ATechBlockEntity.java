package com.alexp777.atech.block;

import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ATechBlockEntity extends BlockEntity {
	private final ItemStackHandler itemStackHandler;
	private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

	public ATechBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int slots) {
		super(pType, pPos, pBlockState);
		itemStackHandler = new ItemStackHandler(slots) {
			@Override
			protected void onContentsChanged(int slot) {
				setChanged();
			}
		};
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				? itemHandlerLazyOptional.cast()
				: super.getCapability(cap, side);
	}
}
