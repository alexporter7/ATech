package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EngineItem extends Item {

	public EngineItem() {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.stacksTo(1)
				.fireResistant());
	}


	//Need to store ItemStack(s) within this item (the data for them to disassemble it later


//	@Override
//	public void verifyTagAfterLoad(CompoundTag pCompoundTag) {
//		super.verifyTagAfterLoad(pCompoundTag);
//
//	}

	/**
	 * Right now isn't implemented
	 * Will take maximum RPM and power factor to spit out a number
	 * @return
	 */
	public int getMaxPowerOutput() {

		//TODO implement this
		return 0;
	}



}
