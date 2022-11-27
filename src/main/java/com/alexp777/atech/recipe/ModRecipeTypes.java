package com.alexp777.atech.recipe;

import com.alexp777.atech.util.ModValue;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {

	public static class CrusherRecipeType implements RecipeType<CrusherRecipe> {
		public static final CrusherRecipeType INSTANCE = new CrusherRecipeType();
		public static final String ID = ModValue.CRUSHING_RECIPE_KEY;
		private CrusherRecipeType() {}
	}

}
