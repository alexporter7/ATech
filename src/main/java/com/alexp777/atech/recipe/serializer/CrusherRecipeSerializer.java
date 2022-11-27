package com.alexp777.atech.recipe.serializer;

import com.alexp777.atech.ATech;
import com.alexp777.atech.recipe.CrusherRecipe;
import com.alexp777.atech.recipe.SteelForgeRecipe;
import com.alexp777.atech.util.ModValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.storage.loot.Serializer;
import org.jetbrains.annotations.Nullable;

public class CrusherRecipeSerializer implements RecipeSerializer<CrusherRecipe> {

	/*
	======= Variables =====
	 */
	public static final CrusherRecipeSerializer INSTANCE
			= new CrusherRecipeSerializer();

	public static final ResourceLocation ID
			= new ResourceLocation(ATech.MODID, ModValue.CRUSHING_RECIPE_KEY);


	/*
	======= Get Recipe from JSON =======
	Template:
	{
	  "type": "atech:crushing",
	  "ingredient": [
		{"item": "minecraft:iron_ingot"}
	  ],
	  "output": {"item": "minecraft:iron_ingot"},
	  "tier": 1,
	  "baseProgress": 400
	}
	 */
	@Override
	public CrusherRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

		//=== Get ingredient ===
		JsonArray ingredients
				= GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredient");
		NonNullList<Ingredient> input = NonNullList.withSize(1, Ingredient.EMPTY);
		input.set(0, Ingredient.fromJson(ingredients.get(0)));

		//=== Get Output ===
		ItemStack output = ShapedRecipe.itemStackFromJson(
				GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

		//=== Get Tier ===
		int tier = GsonHelper.getAsInt(pSerializedRecipe, "tier");

		//=== Get Base Progress ===
		int baseProgress = GsonHelper.getAsInt(pSerializedRecipe, "baseProgress");

		//=== Get Count ===
		int count = GsonHelper.getAsInt(pSerializedRecipe, "count");

		//=== Return a New Recipe ===
		return new CrusherRecipe(pRecipeId, output, input, tier, baseProgress, count);

	}

	/*
	======= Network Methods =======
	 */

	@Nullable
	@Override
	public CrusherRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

		//=== Read Size and Set Ingredient From the Network ===
		NonNullList<Ingredient> input = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
		input.set(0, Ingredient.fromNetwork(pBuffer));

		//=== Read the Output from the Network ===
		ItemStack output = pBuffer.readItem();

		//=== Read Tier and Base Progress from the Network ===
		int tier = pBuffer.readInt();
		int baseProgress = pBuffer.readInt();
		int count = pBuffer.readInt();

		//=== Return new Crusher Recipe ===
		return new CrusherRecipe(pRecipeId, output, input, tier, baseProgress, count);

	}

	@Override
	public void toNetwork(FriendlyByteBuf pBuffer, CrusherRecipe pRecipe) {
		//=== Write the Size to the Network Buffer ===
		pBuffer.writeInt(pRecipe.getIngredients().size());
		//=== Write Ingredients (There should only be 1) to the network buffer ===
		for(Ingredient ingredient : pRecipe.getIngredients())
			ingredient.toNetwork(pBuffer);

		//=== Write the Resulting Item to the Network ===
		pBuffer.writeItemStack(pRecipe.getResultItem(), false);

		//=== Write Tier, Base Progress, Count to the Network ===
		pBuffer.writeInt(pRecipe.getTier());
		pBuffer.writeInt(pRecipe.getBaseProgress());
		pBuffer.writeInt(pRecipe.getCount());

	}

	/*
	======= Boilerplate Code =======
	 */

	@Override
	public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
		return INSTANCE;
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return ID;
	}

	@Override
	public Class<RecipeSerializer<?>> getRegistryType() {
		return CrusherRecipeSerializer.castClass();
	}

	private static <G> Class<G> castClass() {
		return (Class<G>) RecipeSerializer.class;
	}
}
