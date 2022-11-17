package com.alexp777.atech.screen;

import com.alexp777.atech.block.ModBlocks;
import com.alexp777.atech.block.worktables.ProjectTableBlockEntity;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ProjectTableMenu extends AbstractContainerMenu {

	private final ProjectTableBlockEntity projectTableBlockEntity;
	private final Level level;

	public ProjectTableMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
		this(containerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
	}

	public ProjectTableMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
		super(ModMenuTypes.PROJECT_TABLE_MENU.get(), containerId);

		checkContainerSize(inventory, ModValue.PROJECT_TABLE_SLOTS);
		projectTableBlockEntity = ((ProjectTableBlockEntity) blockEntity);
		this.level = inventory.player.level;

		addPlayerInventory(inventory);
		addPlayerHotbar(inventory);

		//Add in TileEntity/Block Entity Slots
		this.projectTableBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(handler -> {
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_1, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_2, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_3, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_4, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_1, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_2, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_3, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_4, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.ENGINE_BLOCK_SLOT, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.HAMMER_SLOT, 0, 0));
					this.addSlot(new SlotItemHandler(handler, ModValue.OUTPUT_SLOT, 0, 0));
		});
	}

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	// THIS YOU HAVE TO DEFINE!
	private static final int TE_INVENTORY_SLOT_COUNT = ModValue.PROJECT_TABLE_SLOTS;  // must be the number of slots you have!
	@Override
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		Slot sourceSlot = slots.get(pIndex);
		if (sourceSlot == null || !sourceSlot.hasItem())
			return ItemStack.EMPTY;  //EMPTY_ITEM

		ItemStack sourceStack = sourceSlot.getItem();
		ItemStack copyOfSourceStack = sourceStack.copy();

		// Check if the slot clicked is one of the vanilla container slots
		if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
			// This is a vanilla container slot so merge the stack into the tile inventory
			if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
					+ TE_INVENTORY_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;  // EMPTY_ITEM
			}
		} else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
			// This is a TE slot so merge the stack into the players inventory
			if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
				return ItemStack.EMPTY;
			}
		} else {
			System.out.println("Invalid slotIndex:" + pIndex);
			return ItemStack.EMPTY;
		}

		// If stack size == 0 (the entire stack was moved) set slot contents to null
		if (sourceStack.getCount() == 0)
			sourceSlot.set(ItemStack.EMPTY);
		else
			sourceSlot.setChanged();

		sourceSlot.onTake(pPlayer, sourceStack);

		return copyOfSourceStack;

	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(level, projectTableBlockEntity.getBlockPos()),
				pPlayer, ModBlocks.PROJECT_TABLE.get());
	}

	private void addPlayerInventory(Inventory playerInventory) {
		for(int i = 0; i < 3; ++i)
			for(int l = 0; l < 9; ++l)
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
	}

	private void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; ++i)
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
	}
}
