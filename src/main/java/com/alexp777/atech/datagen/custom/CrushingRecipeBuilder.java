package com.alexp777.atech.datagen.custom;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CrushingRecipeBuilder implements RecipeBuilder {

	/*
	======= Recipe Variables =======
	 */
	private final Item RESULT;
	private final Ingredient INGREDIENT;
	private final int TIER;
	private final int BASE_PROGRESS;
	private final int COUNT; //TODO implement the count

	/**
	 * Constructor for CrushingRecipeBuilder
	 * @param ingredient input ingredient into the recipe
	 * @param result the result from the recipe
	 * @param tier the minimum tier to process recipe
	 * @param baseProgress the base time in ticks for the recipe
	 * @param count the amount of items output
	 */
	public CrushingRecipeBuilder(ItemLike ingredient, ItemLike result, int tier,
								 int baseProgress, int count) {
		this.INGREDIENT = Ingredient.of(ingredient);
		this.RESULT = result.asItem();
		this.TIER = tier;
		this.BASE_PROGRESS = baseProgress;
		this.COUNT = count;
	}

	/*
	======= Static Info =======
	 */
	private final Advancement.Builder ADVANCEMENT = Advancement.Builder.advancement();

	@Override
	public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
		return null;
	}

	@Override
	public RecipeBuilder group(@Nullable String pGroupName) {
		return null;
	}

	@Override
	public Item getResult() {
		return null;
	}

	@Override
	public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {

	}
}
