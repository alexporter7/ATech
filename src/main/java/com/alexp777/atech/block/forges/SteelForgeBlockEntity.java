package com.alexp777.atech.block.forges;

import com.alexp777.atech.ATech;
import com.alexp777.atech.block.ATechBlockEntity;
import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.recipe.SteelForgeRecipe;
import com.alexp777.atech.screen.metalforge.SteelForgeMenu;
import com.alexp777.atech.util.ModValue;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SteelForgeBlockEntity extends ATechBlockEntity implements MenuProvider {

	private int temperature = 0;
	private int heatTicks = 0;
	private int maxTemperature = ModValue.STEEL_FORGE_MAX_TEMP;
	private int cooldownFactor = 0; //I want some kind of cooldown factor
	private int progress = 0;
	private int maxProgress = 0;

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
					case ModValue.STEEL_FORGE_PROGRESS_DATA
							: return SteelForgeBlockEntity.this.progress; 				//Progress [4]
					case ModValue.STEEL_FORGE_MAX_PROGRESS_DATA
							: return SteelForgeBlockEntity.this.maxProgress; 			//Max Progress [5]
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
					case ModValue.STEEL_FORGE_PROGRESS_DATA -> SteelForgeBlockEntity.this.progress = pValue;
					case ModValue.STEEL_FORGE_MAX_PROGRESS_DATA -> SteelForgeBlockEntity.this.maxProgress = pValue;
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

			if(hasRecipe(pEntity))
				pEntity.setMaxProgress(getRecipeMaxProgress(pEntity));

			ATech.LOGGER.info(String.valueOf(hasRecipe(pEntity)));

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
	======= Recipe Methods =======
	 */

	/**
	 * Checking to see if there's a valid recipe in there
	 * I'm immediately returning if there's nothing in the slot since
	 * why waste time
	 * @param entity The TileEntity since this is a static method
	 */
	public static boolean hasRecipe(SteelForgeBlockEntity entity) {

		Level level = entity.level;
		SimpleContainer inventory = new SimpleContainer(entity.getItemStackHandler().getSlots());
		for(int i = 0; i < entity.getItemStackHandler().getSlots(); i++)
			inventory.setItem(i, entity.getItemStackHandler().getStackInSlot(i));

		assert level != null;
		Optional<SteelForgeRecipe> match = level.getRecipeManager()
				.getRecipeFor(SteelForgeRecipe.Type.INSTANCE, inventory, level);

		return match.isPresent();
//		return match.isPresent()
//				&& canInsertInOutputSlot(inventory, match.get().getResultItem())
//				&& canInsertAmountIntoOutputSlot(inventory);

	}

	public static int getRecipeMaxProgress(SteelForgeBlockEntity entity) {

		SimpleContainer inventory = new SimpleContainer(entity.getItemStackHandler().getSlots());
		for(int i = 0; i < entity.getItemStackHandler().getSlots(); i++)
			inventory.setItem(i, entity.getItemStackHandler().getStackInSlot(i));

		assert entity.level != null;
		Optional<SteelForgeRecipe> match = entity.level.getRecipeManager()
				.getRecipeFor(SteelForgeRecipe.Type.INSTANCE, inventory, entity.level);

		return match.get().getProgressTicks();
	}

	private static boolean canInsertInOutputSlot(SimpleContainer inventory, ItemStack output) {
		return inventory.getItem(ModValue.STEEL_FORGE_OUTPUT_SLOT).isEmpty()
				|| inventory.getItem(ModValue.STEEL_FORGE_OUTPUT_SLOT).getItem() == output.getItem();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
		return inventory.getItem(ModValue.STEEL_FORGE_OUTPUT_SLOT).getMaxStackSize()
				> inventory.getItem(ModValue.STEEL_FORGE_OUTPUT_SLOT).getCount();
	}

	/*
	======= NBT Data =======
	 */
	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.putInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG, getTemperature());
		pTag.putInt(ModValue.STEEL_FORGE_COOLDOWN_FACTOR, getCooldownFactor());
		pTag.putInt(ModValue.STEEL_FORGE_HEAT_TICK_TAG, getHeatTicks());
		pTag.putInt(ModValue.STEEL_FORGE_PROGRESS_TAG, getProgress());
		pTag.putInt(ModValue.STEEL_FORGE_MAX_PROGRESS_TAG, getMaxProgress());
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		setTemperature(pTag.getInt(ModValue.STEEL_FORGE_TEMPERATURE_TAG));
		setHeatTicks(pTag.getInt(ModValue.STEEL_FORGE_HEAT_TICK_TAG));
		setCooldownFactor(pTag.getInt(ModValue.STEEL_FORGE_COOLDOWN_FACTOR));
		setProgress(pTag.getInt(ModValue.STEEL_FORGE_PROGRESS_TAG));
		setMaxProgress(pTag.getInt(ModValue.STEEL_FORGE_MAX_PROGRESS_TAG));

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

	/*
	======= Progress Methods =======
	 */

	public int getProgress() {
		return this.progress;
	}

	public void setProgress(int progress) {
		this.progress = (progress > 0)
				? Math.min(this.maxProgress, progress)
				: 0;
	}

	public int getMaxProgress() {
		return this.maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}
}
