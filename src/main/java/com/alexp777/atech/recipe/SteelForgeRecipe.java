package com.alexp777.atech.recipe;

import com.alexp777.atech.ATech;
import com.alexp777.atech.util.ModValue;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class SteelForgeRecipe implements Recipe<SimpleContainer> {

	private final ResourceLocation id;
	private final ItemStack output;
	private final NonNullList<Ingredient> recipeItems;

	public SteelForgeRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		return recipeItems.get(0).test(pContainer.getItem(ModValue.STEEL_FORGE_IRON_SLOT));
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.recipeItems;
	}

	@Override
	public ItemStack assemble(SimpleContainer pContainer) {
		return this.output;
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		return output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	/*
	======= Type Class =======
	 */

	public static class Type implements RecipeType<SteelForgeRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();
		public static final String ID = ModValue.STEEL_FORGE_RECIPE_TYPE_ID;
	}


	/*
	======= Serializer Class =======
	 */

	public static class Serializer implements RecipeSerializer<SteelForgeRecipe> {

		/*
		======= Static Variables =======
		 */
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID =
				new ResourceLocation(ATech.MODID, ModValue.STEEL_FORGE_RECIPE_TYPE_ID);

		/*
		======= Get recipe data from JSON =======
		 */
		@Override
		public SteelForgeRecipe fromJson(ResourceLocation id, JsonObject json) {

			//Getting the output, ingredients (from JSON), and creating a NonNullList
			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
			JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

			//Iterate through JSON Array and push items into the NonNullList
			for(int i = 0; i < inputs.size(); i++)
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));

			//Return a new Recipe with the associated output and inputs;
			return new SteelForgeRecipe(id, output, inputs);

		}

		/*
		======= Getting From and Sending To Network =======
 		*/
		@Nullable
		@Override
		public SteelForgeRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

			NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			for(int i = 0; i < inputs.size(); i++)
				inputs.set(i, Ingredient.fromNetwork(pBuffer));

			ItemStack output = pBuffer.readItem();

			return new SteelForgeRecipe(pRecipeId, output, inputs);
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, SteelForgeRecipe pRecipe) {

			pBuffer.writeInt(pRecipe.getIngredients().size());
			for(Ingredient ingredient : pRecipe.getIngredients())
				ingredient.toNetwork(pBuffer);

			pBuffer.writeItemStack(pRecipe.getResultItem(), false);

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
			return Serializer.castClass();
		}

		@SuppressWarnings("unchecked")
		private static <G> Class<G> castClass() {
			return (Class<G>) RecipeSerializer.class;
		}

	}
}
