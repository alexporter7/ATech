package com.alexp777.atech.block.machines;

import com.alexp777.atech.block.ATechMachineEntity;
import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.item.custom.ATechComponentItem;
import com.alexp777.atech.recipe.CrusherRecipe;
import com.alexp777.atech.recipe.ModRecipeTypes;
import com.alexp777.atech.screen.machine.CrusherMenu;
import com.alexp777.atech.util.FormFactor;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CrusherBlockEntity extends ATechMachineEntity implements MenuProvider {

	/*
	======= Instance Variables =======
	 */
	private int modifier = 0;
	private int progress = 0;
	private int maxProgress = 0;
	private WorkState workState = WorkState.OFF;

	//These aren't saved in NBT since they're set in tick
	private int hasRecipeFlag = 0;
	private int recipeTier = 0;

	protected final ContainerData data;

	public CrusherBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(), pPos,
				pBlockState, ModValue.CRUSHER_SLOTS);

		/*
		======= Container Data =======
		This is that fancy IIntArray Stuff I mentioned
		 */
		this.data = new ContainerData() {
			@Override
			public int get(int pIndex) {
				return switch (pIndex) {
					case ModValue.CRUSHER_MODIFIER_DATA -> CrusherBlockEntity.this.modifier;
					case ModValue.CRUSHER_PROGRESS_DATA -> CrusherBlockEntity.this.progress;
					case ModValue.CRUSHER_MAX_PROGRESS_DATA -> CrusherBlockEntity.this.maxProgress;
					case ModValue.CRUSHER_HAS_RECIPE_DATA -> CrusherBlockEntity.this.hasRecipeFlag;
					case ModValue.CRUSHER_RECIPE_TIER -> CrusherBlockEntity.this.recipeTier;
					default -> 0;
				};
			}

			@Override
			public void set(int pIndex, int pValue) {
				switch (pIndex) {
					case ModValue.CRUSHER_MODIFIER_DATA -> CrusherBlockEntity.this.modifier = pValue;
					case ModValue.CRUSHER_PROGRESS_DATA -> CrusherBlockEntity.this.progress = pValue;
					case ModValue.CRUSHER_MAX_PROGRESS_DATA -> CrusherBlockEntity.this.maxProgress = pValue;
					case ModValue.CRUSHER_HAS_RECIPE_DATA -> CrusherBlockEntity.this.hasRecipeFlag = pValue;
					case ModValue.CRUSHER_RECIPE_TIER -> CrusherBlockEntity.this.recipeTier = pValue;
				}
			}

			@Override
			public int getCount() {
				return ModValue.CRUSHER_DATA_SIZE;
			}
		};
	}

	/*
	======= Machine Properties and Info =======
	 */

	@Override
	public Component getDisplayName() {
		return new TextComponent("Crusher");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		return new CrusherMenu(pContainerId, pPlayerInventory, this, this.data);
	}

	/*
	======= Tick =======
	 */

	public static void tick(Level level, BlockPos pos, BlockState state, CrusherBlockEntity entity) {


		if(!level.isClientSide()) {

			/*
			 * Trying to fail fast here
			 */
			if(!entity.hasComponentsInSlots())
				entity.setWorkState(WorkState.OFF);

			//Check if there's parts in the slot since it's quick
			//Set the machine to the "ready" state
			if(entity.hasComponentsInSlots() && !entity.isWorking())
				entity.setWorkState(WorkState.READY);

			//Set up component screen info
			//Get the modifier from the PISTON
			if(entity.hasComponentsInSlots()) {
				entity.setModifier(
						((ATechComponentItem) entity
								.getItemStackHandler()
								.getStackInSlot(ModValue.CRUSHER_PISTON_SLOT)
								.getItem()).getComponentModifier());
			}
			//If stack is empty or something else modifier is 0
			else {
				entity.setModifier(0);
			}

			/*
			 * ======= Work Logic =======
			 */

			//If the machine IS ready check for a recipe and set to working
			if(entity.isReady() && hasRecipe(getOptional(entity))) {
				entity.setWorkState(WorkState.WORKING);
			}

			if(entity.isWorking()) {
				Optional<CrusherRecipe> match = getOptional(entity);
				if(hasRecipe(match)) {

					/*
					 * ======= Max Progress Setup =======
					 */
					if(entity.getMaxProgress() == 0)
						entity.setMaxProgress((int) (getRecipeMaxProgress(match) * ((double)entity.getModifier()/100)));

					/*
					 * ======= Screen Setup =======
					 */

					//Set the IIntArray Information
					entity.setHasRecipe(hasRecipe(match) ? 1 : 0);
					entity.setRecipeTier(getRecipeTier(match));

					/*
					 * ======= Work Logic =======
					 */
					if(entity.getProgress() < entity.getMaxProgress())
						entity.incrementProgress();

					if(entity.getProgress() >= entity.getMaxProgress()) {
						entity.setWorkState(WorkState.READY);
						entity.setProgress(0);
						entity.getItemStackHandler().getStackInSlot(ModValue.CRUSHER_INPUT_SLOT).shrink(1);
						entity.getItemStackHandler().insertItem(
								ModValue.CRUSHER_OUTPUT_SLOT, match.get().getResultItem().copy(), false);
					}
				}
				else {
					entity.setWorkState(WorkState.READY);
				}

			}

			if(entity.isOff() || entity.isReady())
				entity.resetProgress();


		}

	}

	/*
	======= NBT Data =======
	 */

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.putInt(ModValue.CRUSHER_MODIFIER_KEY, getModifier());
		pTag.putInt(ModValue.CRUSHER_PROGRESS_KEY, getProgress());
		pTag.putInt(ModValue.CRUSHER_MAX_PROGRESS_KEY, getMaxProgress());
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		setModifier(pTag.getInt(ModValue.CRUSHER_MODIFIER_KEY));
		setProgress(pTag.getInt(ModValue.CRUSHER_PROGRESS_KEY));
		setMaxProgress(pTag.getInt(ModValue.CRUSHER_MAX_PROGRESS_KEY));
	}

	/*
	======= Recipe Methods =======
	 */

	//TODO Fix these so it calculates the inventory once instead of like 30 times

	public static Optional<CrusherRecipe> getOptional(CrusherBlockEntity entity) {
		SimpleContainer inventory = new SimpleContainer(entity.getItemStackHandler().getSlots());
		for(int i = 0; i < entity.getItemStackHandler().getSlots(); i++)
			inventory.setItem(i, entity.getItemStackHandler().getStackInSlot(i));

		assert entity.level != null;
		Optional<CrusherRecipe> match = entity.level.getRecipeManager()
				.getRecipeFor(ModRecipeTypes.CrusherRecipeType.INSTANCE, inventory, entity.level);

		return match;
	}

	public static boolean hasRecipe(Optional<CrusherRecipe> match) {
		return match.isPresent();
	}

	public static int getRecipeTier(Optional<CrusherRecipe> match) {
		return (match.isPresent() ? match.get().getTier() : 0);
	}

	public static int getRecipeMaxProgress(Optional<CrusherRecipe> match) {
		return (match.isPresent() ? match.get().getBaseProgress() : 0);
	}

	/*
	 * ======= Local Recipe =======
	 */

	public void setHasRecipe(int flag) {
		this.hasRecipeFlag = flag;
	}

	public int getHasRecipe() {
		return this.hasRecipeFlag;
	}

	public void setRecipeTier(int tier) {
		this.recipeTier = tier;
	}

	public int getRecipeTier() {
		return this.recipeTier;
	}


	/*
	======= Modifier =======
	 */

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public int getModifier() {
		return this.modifier;
	}

	/*
	======= Progress =======
	 */

	public void setProgress(int progress) {
		this.progress = (progress >= 0)
				? Math.min(progress, this.maxProgress)
				: 0;
	}

	public void incrementProgress() {
		this.progress++;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = Math.max(0, maxProgress);
	}

	public int getProgress() {
		return this.progress;
	}

	public int getMaxProgress() {
		return this.maxProgress;
	}

	public void resetProgress() {
		this.progress = 0;
		this.maxProgress = 0;
	}

	/*
	 * ======= Check Component Slots =======
	 */

	public Item getPistonSlot() {
		return this.getItemStackHandler().getStackInSlot(ModValue.CRUSHER_PISTON_SLOT).getItem();
	}
	public Item getPressPlateSlot() {
		return this.getItemStackHandler().getStackInSlot(ModValue.CRUSHER_PRESS_PLATE_SLOT).getItem();
	}

	public boolean hasComponentsInSlots() {
		if(this.getPistonSlot() instanceof ATechComponentItem
			&& this.getPressPlateSlot() instanceof ATechComponentItem) {

			return ((ATechComponentItem) this.getPistonSlot()).getFormFactor() == FormFactor.PISTON
					&& ((ATechComponentItem) this.getPressPlateSlot()).getFormFactor() == FormFactor.PRESS_PLATE;
		}
		return false;
	}

	/*
	 * ======= Work States =======
	 */

	public void setWorkState(WorkState workState) {
		this.workState = workState;
	}

	public WorkState getWorkState() {
		return this.workState;
	}

	public boolean isOff() {
		return this.workState == WorkState.OFF;
	}

	public boolean isReady() {
		return this.workState == WorkState.READY;
	}

	public boolean isWorking() {
		return this.workState == WorkState.WORKING;
	}

	private enum WorkState {
		OFF,
		READY,
		WORKING;
	}

}
