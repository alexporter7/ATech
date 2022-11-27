package com.alexp777.atech.recipe;

import com.alexp777.atech.recipe.serializer.CrusherRecipeSerializer;
import com.alexp777.atech.util.ModValue;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CrusherRecipe implements Recipe<SimpleContainer> {

	private final ResourceLocation ID;
	private final ItemStack OUTPUT;
	private final NonNullList<Ingredient> INPUT;
	private final int TIER;
	private final int BASE_PROGRESS;
	private final int COUNT;
	public CrusherRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> input,
						 int tier, int baseProgress, int count) {
		this.ID = id;
		this.OUTPUT = output;
		this.INPUT = input;
		this.TIER = tier;
		this.BASE_PROGRESS = baseProgress;
		this.COUNT = count;
	}

	/*
	======= Custom Property Methods =======
	 */
	public int getTier() {
		return TIER;
	}

	public int getBaseProgress() {
		return BASE_PROGRESS;
	}

	public int getCount() {
		return COUNT;
	}

	/*
	======= Simple Container Recipe Methods =======
	 */
	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		return INPUT.get(0).test(pContainer.getItem(ModValue.CRUSHER_INPUT_SLOT));
	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer) {
		return OUTPUT;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return OUTPUT.copy();
	}

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CrusherRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.CrusherRecipeType.INSTANCE;
	}
}
