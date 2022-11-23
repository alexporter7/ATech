package com.alexp777.atech.screen.metalforge;

import com.alexp777.atech.block.ModBlocks;
import com.alexp777.atech.block.forges.SteelForgeBlockEntity;
import com.alexp777.atech.screen.ATechMenu;
import com.alexp777.atech.screen.ModMenuTypes;
import com.alexp777.atech.screen.slot.ModResultSlot;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SteelForgeMenu extends ATechMenu {

	private final Level level;
	private final SteelForgeBlockEntity steelForgeBlockEntity;
	private final ContainerData data;

	public SteelForgeMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
		this(containerId, inventory, inventory.player.level
				.getBlockEntity(extraData.readBlockPos()),
				new SimpleContainerData(ModValue.STEEL_FORGE_CONTAINER_COUNT));
	}

	public SteelForgeMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
		super(ModMenuTypes.STEEL_FORGE_MENU.get(), containerId);

		checkContainerSize(inventory, ModValue.STEEL_FORGE_SLOTS);
		steelForgeBlockEntity = ((SteelForgeBlockEntity) blockEntity);
		this.level = inventory.player.level;
		this.data = data;

		//Super Methods
		addPlayerInventory(inventory);
		addPlayerHotbar(inventory);

		//Add in TileEntity/Block Entity Slots
		this.steelForgeBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(handler -> {
					this.addSlot(new SlotItemHandler(handler, ModValue.STEEL_FORGE_IRON_SLOT, 21, 21));
					this.addSlot(new SlotItemHandler(handler, ModValue.STEEL_FORGE_CARBON_SLOT, 21, 39));
					this.addSlot(new ModResultSlot(handler, ModValue.STEEL_FORGE_OUTPUT_SLOT, 48, 30));
				});

		addDataSlots(this.data);
	}

	public int getTemperature() {
		return this.data.get(ModValue.STEEL_FORGE_CASE_TEMPERATURE);
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(ContainerLevelAccess.create(level, steelForgeBlockEntity.getBlockPos()),
				pPlayer, ModBlocks.STEEL_FORGE.get());
	}

	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

	// THIS YOU HAVE TO DEFINE!
	private static final int TE_INVENTORY_SLOT_COUNT = ModValue.STEEL_FORGE_SLOTS;  // must be the number of slots you have!
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
