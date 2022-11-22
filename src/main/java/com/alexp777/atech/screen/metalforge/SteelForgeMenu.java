package com.alexp777.atech.screen.metalforge;

import com.alexp777.atech.block.forges.SteelForgeBlockEntity;
import com.alexp777.atech.block.worktables.ProjectTableBlockEntity;
import com.alexp777.atech.screen.ATechMenu;
import com.alexp777.atech.screen.ModMenuTypes;
import com.alexp777.atech.screen.slot.ModResultSlot;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class SteelForgeMenu extends ATechMenu {

	private final Level level;
	private final SteelForgeBlockEntity steelForgeBlockEntity;

	public SteelForgeMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
		this(containerId, inventory, inventory.player.level.getBlockEntity(extraData.readBlockPos()));
	}

	public SteelForgeMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
		super(ModMenuTypes.PROJECT_TABLE_MENU.get(), containerId);

		checkContainerSize(inventory, ModValue.PROJECT_TABLE_SLOTS);
		steelForgeBlockEntity = ((SteelForgeBlockEntity) blockEntity);
		this.level = inventory.player.level;

		//Super Methods
		addPlayerInventory(inventory);
		addPlayerHotbar(inventory);

		//Add in TileEntity/Block Entity Slots
		this.steelForgeBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(handler -> {
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_1, 51, 21));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_2, 69, 21));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_3, 87, 21));
					this.addSlot(new SlotItemHandler(handler, ModValue.PISTON_SLOT_4, 105, 21));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_1, 51, 39));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_2, 69, 39));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_3, 87, 39));
					this.addSlot(new SlotItemHandler(handler, ModValue.CONNECTING_ROD_SLOT_4, 105, 39));
					this.addSlot(new SlotItemHandler(handler, ModValue.ENGINE_BLOCK_SLOT, 21, 39));
					this.addSlot(new SlotItemHandler(handler, ModValue.HAMMER_SLOT, 21, 21));
					this.addSlot(new ModResultSlot(handler, ModValue.OUTPUT_SLOT, 131, 21));
				});
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return false;
	}


}
