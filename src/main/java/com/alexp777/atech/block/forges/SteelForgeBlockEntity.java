package com.alexp777.atech.block.forges;

import com.alexp777.atech.block.ATechBlockEntity;
import com.alexp777.atech.block.ModBlockEntities;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SteelForgeBlockEntity extends ATechBlockEntity implements MenuProvider {

	private int temperature = 0;
	private int heatTicks = 0;
	private int maxTemperature = ModValue.STEEL_FORGE_MAX_TEMP;
	private int cooldownFactor = 0; //I want some kind of cooldown factor

	protected final ContainerData data;
	public SteelForgeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		//Calling Super
		super(ModBlockEntities.STEEL_FORGE_BLOCK_ENTITY.get(), pPos,
				pBlockState, ModValue.STEEL_FORGE_SLOTS);

		//Initialize the cooldown factor (this won't be here forever but for testing
		this.cooldownFactor = 10;

		//Initializing ContainerData (the fancy IIntArray)
		this.data = new ContainerData() {
			@Override
			public int get(int pIndex) {
				switch(pIndex) {
					case ModValue.STEEL_FORGE_CASE_TEMPERATURE_DATA                    	//Temperature [0]
							: return SteelForgeBlockEntity.this.temperature;
					case ModValue.STEEL_FORGE_CASE_MAX_TEMP_DATA                    	//Max Temperature [1]
							: return SteelForgeBlockEntity.this.maxTemperature;
					case ModValue.STEEL_FORGE_COOLDOWN_FACTOR_DATA
							: return SteelForgeBlockEntity.this.cooldownFactor; 		//Cooldown factor [2]
					case ModValue.STEEL_FORGE_HEAT_TICK_DATA
							: return SteelForgeBlockEntity.this.heatTicks; 				//Heat Ticks [3]
					default
							: return 0; 												//If something else gets thrown in return 0
				}
			}

			@Override
			public void set(int pIndex, int pValue) {
				//See this new fancy switch statement it has arrows!
				switch (pIndex) {
					case ModValue.STEEL_FORGE_CASE_TEMPERATURE_DATA -> SteelForgeBlockEntity.this.temperature = pValue;
					case ModValue.STEEL_FORGE_CASE_MAX_TEMP_DATA -> SteelForgeBlockEntity.this.maxTemperature = pValue;
					case ModValue.STEEL_FORGE_COOLDOWN_FACTOR_DATA -> SteelForgeBlockEntity.this.cooldownFactor = pValue;
					case ModValue.STEEL_FORGE_HEAT_TICK_DATA -> SteelForgeBlockEntity.this.heatTicks = pValue;
				}
			}

			//Total Array Count, should be equal to the amount of values that can be get or set
			@Override
			public int getCount() {
				return ModValue.STEEL_FORGE_CONTAINER_COUNT;
			}
		};
	}

	/**
	 * Display name, gets refreshed on GUI open
	 * @return TextComponent ie: string
	 */
	@Override
	public @NotNull Component getDisplayName() {
		return new TextComponent("Basic Steel Forge");
	}

	/**
	 * This is different from the default method when it calls super as we're passing in the ContainerData object
	 * This is so we can pass progress or temperature or whatever values we want to the menu
	 * @param pContainerId I'm honestly not sure what this is
	 * @param pPlayerInventory The Player's inventory
	 * @param pPlayer The player
	 * @return the MenuObject
	 */
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
		return new SteelForgeMenu(pContainerId, pPlayerInventory, this, this.data);
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState,
							SteelForgeBlockEntity pEntity) {

		//Temp Logic will work independently
		//IF the temperature is < 1250
			//take coal and start heating
		//If temp is > 1250
			//don't take coal

		//Check if we're NOT in the ClientSide and if the entity is NOT null (idk how that would happen but we're checking!)
		if(!pLevel.isClientSide()
		&& pEntity != null) {

			//This one's a lot, so I want to make sure that
			//The temperature is LESS THAN 1250, it's NOT heating up, the Carbon Slot is NOT empty
			//And the Carbon slot HAS coal in it
			if (pEntity.getTemperature() < 1250
					&& !pEntity.isHeating()
					&& !pEntity.getItemStackHandler().getStackInSlot(ModValue.STEEL_FORGE_CARBON_SLOT).isEmpty()
					&& pEntity.getItemStackHandler().getStackInSlot(ModValue.STEEL_FORGE_CARBON_SLOT).is(Items.COAL)) {

				//If all of the above is TRUE I want to shrink the Carbon Slot by 1
				//and add heat ticks so we can have this forge heat up
				pEntity.getItemStackHandler().getStackInSlot(ModValue.STEEL_FORGE_CARBON_SLOT).shrink(1);
				pEntity.addHeatTicks(10);

			}

			if (pEntity.isHeating()) {
				//If the Block/Tile Entity IS heating up, then the temperature should increase and
				//the amount of heat ticks should decrease
				pEntity.incrementTemperature();
				pEntity.decrementHeatTicks();
			}
			else {
				//If the block isn't heating up, then the temperature should decrease
				pEntity.decrementTemperature();
			}
		}

		//if progress < 100
			//If ingredients Do progress work
				//every "cycle" takes X degrees of the forge
			//Cool down if no progress is being done (but slower)
		//if progress = 100
			//take iron from slot
			//put steel in output slot
	}

	/*
	======= NBT Data =======
	 */
	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.putInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG, getTemperature());
		pTag.putInt(ModValue.STEEL_FORGE_COOLDOWN_FACTOR, getCooldownFactor());
		pTag.putInt(ModValue.STEEL_FORGE_HEAT_TICK_TAG, getHeatTicks());
		//TODO add heat ticks to nbt data
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		//TODO add heat ticks to loading nbt data
		setTemperature(pTag.getInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG));
		setHeatTicks(pTag.getInt(ModValue.STEEL_FORGE_HEAT_TICK_TAG));
		setCooldownFactor(pTag.getInt(ModValue.STEEL_FORGE_COOLDOWN_FACTOR));
	}

	/*
	======= Heat Tick Methods =======
	 */
	public boolean isHeating() {
		return this.heatTicks != 0;
	}

	public void addHeatTicks(int ticks) {
		this.heatTicks += ticks;
	}

	public void decrementHeatTicks() {
		this.heatTicks = Math.max(0, --this.heatTicks);
	}

	public int getHeatTicks() {
		return this.heatTicks;
	}

	public void setHeatTicks(int heatTicks) {
		this.heatTicks = heatTicks;
	}

	/*
	======= Temperature Methods =======
	 */

	public int getTemperature() {
		return temperature;
	}

	public void incrementTemperature() {
		setTemperature(++this.temperature);
	}

	/**
	 * We're taking the cooldown factor and turning it into a percentage
	 * Ex: CF(10)/100 = 0.1
	 */
	public void decrementTemperature() {
		if(Math.random() < ((double)(this.cooldownFactor))/100.0)
			setTemperature(--this.temperature);
	}

	public void setTemperature(int temperature) {
		this.temperature = (temperature < 0)
							? 0
							: Math.min(temperature, this.maxTemperature);
	}

	private boolean isAtMaxTemp() {
		return this.temperature == this.maxTemperature;
	}

	/*
	======= Cooldown Factor Methods =======
	 */
	public int getCooldownFactor() {
		return this.cooldownFactor;
	}

	public void setCooldownFactor(int cooldownFactor) {
		this.cooldownFactor = cooldownFactor;
	}
}
