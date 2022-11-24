package com.alexp777.atech.datagen.custom;

import com.alexp777.atech.ATech;
import com.alexp777.atech.recipe.SteelForgeRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SteelForgeRecipeBuilder implements RecipeBuilder {

	private final Item result;
	private final Ingredient ingredient;
	private final int count;
	private final Advancement.Builder advancement = Advancement.Builder.advancement();

	/**
	 * Passing in information to be compiled into JSON
	 * @param ingredient Ingredient is what goes into the recipe
	 * @param result This is what... yknow, results from it
	 * @param count either the amount in or out idk
	 */
	public SteelForgeRecipeBuilder(ItemLike ingredient, ItemLike result, int count) {
		this.result = result.asItem();
		this.ingredient = Ingredient.of(ingredient);
		this.count = count;
	}


	/**
	 * Actually saving our compiled recipe
	 * @param pFinishedRecipeConsumer Recipe Consumer
	 * @param pRecipeId the recipe location
	 */
	@Override
	public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
		//This is something about the advancements and recipe book don't ask me how this works
		this.advancement.parent(new ResourceLocation("recipes/root"))
				.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
				.rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
		//This is actually getting the finished recipe from the Result class implemented below
		pFinishedRecipeConsumer.accept(new SteelForgeRecipeBuilder
				.Result(pRecipeId, this.result, this.count, this.ingredient, this.advancement,
						new ResourceLocation(pRecipeId.getNamespace(), "recipes/"
						+ this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
	}

	/*
	Result Class
	 */

	public static class Result implements FinishedRecipe {

		private final ResourceLocation id;
		private final Item result;
		private final Ingredient ingredient;
		private final int count;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		public Result(ResourceLocation pId, Item pResult, int pCount, Ingredient ingredient,
					  Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
			this.id = pId;
			this.result = pResult;
			this.count = pCount;
			this.ingredient = ingredient;
			this.advancement = pAdvancement;
			this.advancementId = pAdvancementId;
		}

		@Override
		public void serializeRecipeData(JsonObject pJson) {
			JsonArray jsonArray = new JsonArray();
			jsonArray.add(ingredient.toJson());

			pJson.add("ingredients", jsonArray);

			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("item", this.result.getRegistryName().toString());

			if(this.count > 1)
				jsonObject.addProperty("count", this.count);

			pJson.add("output", jsonObject);
		}

		@Override
		public ResourceLocation getId() {
			return new ResourceLocation(ATech.MODID,
					this.result.getRegistryName().getPath() + "_from_steel_forge");
		}

		@Override
		public RecipeSerializer<?> getType() {
			return SteelForgeRecipe.Serializer.INSTANCE;
		}

		@Nullable
		@Override
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}

		@Nullable
		@Override
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}


	/*
	======= Boilerplate code =======
	 */

	@Override
	public RecipeBuilder group(@Nullable String pGroupName) {
		return this;
	}

	@Override
	public Item getResult() {
		return result;
	}

	/**
	 * This is the advancement that unlocks this recipe (I think?)
	 * @param pCriterionName What criteria needs to be met
	 * @param pCriterionTrigger What actually triggers it
	 * @return Recipe Builder
	 */
	@Override
	public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
		this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
		return this;
	}



}
