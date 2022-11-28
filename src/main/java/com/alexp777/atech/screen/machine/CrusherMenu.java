package com.alexp777.atech.screen.machine;

import com.alexp777.atech.block.ModBlocks;
import com.alexp777.atech.block.machines.CrusherBlockEntity;
import com.alexp777.atech.screen.ATechMenu;
import com.alexp777.atech.screen.ModMenuTypes;
import com.alexp777.atech.screen.slot.ModResultSlot;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class CrusherMenu extends ATechMenu {

	private final Level LEVEL;
	private final CrusherBlockEntity CRUSHER_BLOCK_ENTITY;
	private final ContainerData DATA;

	public CrusherMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
		this(pContainerId, inventory,
				inventory.player.level.getBlockEntity(extraData.readBlockPos()),
				new SimpleContainerData(ModValue.CRUSHER_DATA_SIZE));
	}

	public CrusherMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData containerData) {
		super(ModMenuTypes.CRUSHER_MENU.get(), containerId);

		/*
		======= Initialize Data =======
		 */
		checkContainerSize(inventory, ModValue.CRUSHER_SLOTS);
		CRUSHER_BLOCK_ENTITY = ((CrusherBlockEntity) blockEntity);
		LEVEL = inventory.player.level;
		DATA = containerData;

		/*
		======= Call Boilerplate Super Methods =======
		 */
		addPlayerInventory(inventory);
		addPlayerHotbar(inventory);

		/*
		======= Add In Slots =======
		 */
		CRUSHER_BLOCK_ENTITY.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(iItemHandler -> {
					this.addSlot(new SlotItemHandler(iItemHandler, ModValue.CRUSHER_INPUT_SLOT,
							24, 15));
					this.addSlot(new SlotItemHandler(iItemHandler, ModValue.CRUSHER_ENERGY_SLOT,
							24, 37));
					this.addSlot(new SlotItemHandler(iItemHandler, ModValue.CRUSHER_PISTON_SLOT,
							149, 13));
					this.addSlot(new SlotItemHandler(iItemHandler, ModValue.CRUSHER_PRESS_PLATE_SLOT,
							149, 32));
					this.addSlot(new ModResultSlot(iItemHandler, ModValue.CRUSHER_OUTPUT_SLOT,
							46, 15));
				});

		/*
		======= Register Container Data
		 */
		addDataSlots(DATA);
	}

	/*
	======= Container Data Methods =======
	 */

	public int getProgress() {
		return DATA.get(ModValue.CRUSHER_PROGRESS_DATA);
	}

	public int getModifier() {
		return DATA.get(ModValue.CRUSHER_MODIFIER_DATA);
	}

	public int getMaxProgress() {
		return DATA.get(ModValue.CRUSHER_MAX_PROGRESS_DATA);
	}

	/*
	======= Still Valid Method
	 */

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(LEVEL, CRUSHER_BLOCK_ENTITY.getBlockPos()),
				pPlayer, ModBlocks.CRUSHER.get());
	}

	/*
		======= Shift Clicking Mess =======
		 */
	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	// THIS YOU HAVE TO DEFINE!
	private static final int TE_INVENTORY_SLOT_COUNT = ModValue.CRUSHER_SLOTS;  // must be the number of slots you have!
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
}
