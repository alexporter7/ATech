package com.alexp777.atech.datagen.custom.result;

import com.alexp777.atech.ATech;
import com.alexp777.atech.recipe.serializer.CrusherRecipeSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class CrushingRecipeResult implements FinishedRecipe {

	/*
	======= Recipe Resources =======
	 */
	private final ResourceLocation ID;
	private final Item RESULT;
	private final Ingredient INGREDIENT;
	private final int COUNT;
	private final Advancement.Builder ADVANCEMENT;
	private final ResourceLocation ADVANCEMENT_ID;
	private final int TIER;
	private final int BASE_PROGRESS;

	public CrushingRecipeResult(ResourceLocation id, Item result, int count, Ingredient ingredient,
								Advancement.Builder advancement, ResourceLocation advancementId,
								int tier, int baseProgress) {
		this.ID = id;
		this.RESULT = result;
		this.INGREDIENT = ingredient;
		this.COUNT = count;
		this.ADVANCEMENT = advancement;
		this.ADVANCEMENT_ID = advancementId;
		this.TIER = tier;
		this.BASE_PROGRESS = baseProgress;
	}

	@Override
	public void serializeRecipeData(JsonObject pJson) {
		//=== Ingredient to JSON ===
		JsonArray ingredients = new JsonArray();
		ingredients.add(INGREDIENT.toJson());

		pJson.add("ingredient", ingredients);

		//=== Output to JSON ===
		JsonObject output = new JsonObject();
		output.addProperty("item", RESULT.getRegistryName().toString());
		pJson.add("output", output);

		//=== Tier, Base Progress and Count to JSON ===
		pJson.addProperty("tier", TIER);
		pJson.addProperty("baseProgress", BASE_PROGRESS);
		pJson.addProperty("count", COUNT);

	}

	@Override
	public ResourceLocation getId() {
		return new ResourceLocation(ATech.MODID, RESULT.getRegistryName().getPath() + "_from_crusher");
	}

	@Override
	public RecipeSerializer<?> getType() {
		return CrusherRecipeSerializer.INSTANCE;
	}

	@Nullable
	@Override
	public JsonObject serializeAdvancement() {
		return ADVANCEMENT.serializeToJson();
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementId() {
		return ADVANCEMENT_ID;
	}
}
