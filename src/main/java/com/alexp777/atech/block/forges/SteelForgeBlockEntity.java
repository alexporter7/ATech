package com.alexp777.atech.block.forges;

import com.alexp777.atech.block.ATechBlockEntity;
import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.block.worktables.ProjectTableBlockEntity;
import com.alexp777.atech.screen.metalforge.SteelForgeMenu;
import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteelForgeBlockEntity extends ATechBlockEntity implements MenuProvider {

	private int temperature = 0;
	private int maxTemperature = ModValue.STEEL_FORGE_MAX_TEMP;

	protected final ContainerData data;
	public SteelForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.STEEL_FORGE_BLOCK_ENTITY.get(), pPos,
				pBlockState, ModValue.STEEL_FORGE_SLOTS);
		this.data = new ContainerData() {
			@Override
			public int get(int pIndex) {
				switch(pIndex) {
					case ModValue.STEEL_FORGE_CASE_TEMPERATURE
							: return SteelForgeBlockEntity.this.temperature;
					case ModValue.STEEL_FORGE_CASE_MAX_TEMP
							: return SteelForgeBlockEntity.this.maxTemperature;
					default
							: return 0;
				}
			}

			@Override
			public void set(int pIndex, int pValue) {
				switch (pIndex) {
					case ModValue.STEEL_FORGE_CASE_TEMPERATURE -> SteelForgeBlockEntity.this.temperature = pValue;
					case ModValue.STEEL_FORGE_CASE_MAX_TEMP -> SteelForgeBlockEntity.this.maxTemperature = pValue;
				}
			}

			@Override
			public int getCount() {
				return ModValue.STEEL_FORGE_CONTAINER_COUNT;
			}
		};
	}

	@Override
	public @NotNull Component getDisplayName() {
		return new TextComponent("Steel Forge | " + getTemperature());
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
		return new SteelForgeMenu(pContainerId, pPlayerInventory, this, this.data);
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState,
							SteelForgeBlockEntity pEntity) {
		if(!pLevel.isClientSide()
		&& pEntity != null
		&& !pEntity.isAtMaxTemp()) {
			if(!pEntity.getItemStackHandler().getStackInSlot(0).isEmpty()) {
				pEntity.incrementTemperature();
			}
		}
	}

	private boolean isAtMaxTemp() {
		return this.temperature == this.maxTemperature;
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.putInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG, getTemperature());
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		setTemperature(pTag.getInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG));
	}

	public int getTemperature() {
		return temperature;
	}

	public void incrementTemperature() {
		setTemperature(++this.temperature);
	}

	public void setTemperature(int temperature) {
		this.temperature = (Math.min(temperature, this.maxTemperature));
	}
}
