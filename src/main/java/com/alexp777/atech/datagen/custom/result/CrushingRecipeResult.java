package com.alexp777.atech.datagen.custom.result;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class CrushingRecipeResult implements FinishedRecipe {
	@Override
	public void serializeRecipeData(JsonObject pJson) {

	}

	@Override
	public ResourceLocation getId() {
		return null;
	}

	@Override
	public RecipeSerializer<?> getType() {
		return null;
	}

	@Nullable
	@Override
	public JsonObject serializeAdvancement() {
		return null;
	}

	@Nullable
	@Override
	public ResourceLocation getAdvancementId() {
		return null;
	}
}
