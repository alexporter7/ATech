package com.alexp777.atech.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public class ATechMenu extends AbstractContainerMenu {

	public ATechMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
		super(pMenuType, pContainerId);
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return false;
	}

	protected void addPlayerInventory(Inventory playerInventory) {
		for(int i = 0; i < 3; ++i)
			for(int l = 0; l < 9; ++l)
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
	}

	protected void addPlayerHotbar(Inventory playerInventory) {
		for (int i = 0; i < 9; ++i)
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
	}
}
