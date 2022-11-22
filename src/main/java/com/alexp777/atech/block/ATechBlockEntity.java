package com.alexp777.atech.block;

import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
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

	public ItemStackHandler getItemStackHandler() {
		return this.itemStackHandler;
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				? itemHandlerLazyOptional.cast()
				: super.getCapability(cap, side);
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
		for(int i = 0; i < itemStackHandler.getSlots(); i++)
			inventory.setItem(i, itemStackHandler.getStackInSlot(i));

		assert this.level != null;
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.put(ModValue.BLOCK_ENTITY_INVENTORY_KEY, itemStackHandler.serializeNBT());
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		itemStackHandler.deserializeNBT(pTag.getCompound(ModValue.BLOCK_ENTITY_INVENTORY_KEY));
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemHandlerLazyOptional.invalidate();
	}




}
