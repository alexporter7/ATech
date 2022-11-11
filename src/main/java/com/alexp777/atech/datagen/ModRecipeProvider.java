package com.alexp777.atech.datagen;

import com.alexp777.atech.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public ModRecipeProvider(DataGenerator pGenerator) {
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
		ShapedRecipeBuilder.shaped(ModItems.IRON_FLY_WHEEL.get())
				.define('I', Items.IRON_INGOT)
				.pattern(" I ")
				.pattern("I I")
				.pattern(" I ")
				.unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
				.save(pFinishedRecipeConsumer);
	}
}
