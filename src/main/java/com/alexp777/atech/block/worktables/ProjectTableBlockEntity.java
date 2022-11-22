package com.alexp777.atech.block.worktables;

import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.screen.ProjectTableMenu;
import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectTableBlockEntity extends BlockEntity implements MenuProvider {

	//ItemStackHandler
	private final ItemStackHandler itemStackHandler = new ItemStackHandler(ModValue.PROJECT_TABLE_SLOTS) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};

	private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();
	public ProjectTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.PROJECT_TABLE_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Project Table");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		return new ProjectTableMenu(pContainerId, pPlayerInventory, this);
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				? itemHandlerLazyOptional.cast()
				: super.getCapability(cap, side);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemHandlerLazyOptional.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.put(ModValue.PROJECT_TABLE_INVENTORY_KEY, itemStackHandler.serializeNBT());
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		itemStackHandler.deserializeNBT(serializeNBT().getCompound(ModValue.PROJECT_TABLE_INVENTORY_KEY));
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ProjectTableBlockEntity pEntity) {

	}

	/**
	 * Provides logic for crafting process for the actual item
	 * @param entity
	 */
	private static void craftItem(ProjectTableBlockEntity entity) {

	}

	/**
	 * Checks for the current inventory having a recipe
	 * @param entity
	 */
	private static void hasRecipe(ProjectTableBlockEntity entity) {

	}

	//TODO actually implement this
	private static boolean hasNotReachedStackLimit(ProjectTableBlockEntity entity) {

		return false;
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
		for(int i = 0; i < itemStackHandler.getSlots(); i++)
			inventory.setItem(i, itemStackHandler.getStackInSlot(i));

		assert this.level != null;
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}
}
