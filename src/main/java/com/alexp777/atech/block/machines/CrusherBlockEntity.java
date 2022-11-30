package com.alexp777.atech.block.machines;

import com.alexp777.atech.block.ATechMachineEntity;
import com.alexp777.atech.block.ModBlockEntities;
import com.alexp777.atech.screen.machine.CrusherMenu;
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
import org.jetbrains.annotations.Nullable;

public class CrusherBlockEntity extends ATechMachineEntity implements MenuProvider {


	/*
	======= Instance Variables =======
	 */
	private int modifier = 0;
	private int progress = 0;
	private int maxProgress = 0;

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
					default -> 0;
				};
			}

			@Override
			public void set(int pIndex, int pValue) {
				switch (pIndex) {
					case ModValue.CRUSHER_MODIFIER_DATA -> CrusherBlockEntity.this.modifier = pValue;
					case ModValue.CRUSHER_PROGRESS_DATA -> CrusherBlockEntity.this.progress = pValue;
					case ModValue.CRUSHER_MAX_PROGRESS_DATA -> CrusherBlockEntity.this.maxProgress = pValue;
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

		//Check if Server Side
		//Check if Piston and Press Plate is present
			//If blade IS present check for recipe
				//If blade AND recipe present and not at max progress start work
					//Random chance blade damage
					//Work
				//If blade and recipe present but at max progress
					//put output item
					//decrement input item

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

	public static boolean hasRecipe(CrusherBlockEntity entity) {
		return false;
	}

	public static int getRecipeMaxProgress(CrusherBlockEntity entity) {
		return 0;
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

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = Math.max(0, maxProgress);
	}

	public int getProgress() {
		return this.progress;
	}

	public int getMaxProgress() {
		return this.progress;
	}



}
