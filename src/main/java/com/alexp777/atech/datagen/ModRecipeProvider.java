package com.alexp777.atech.datagen;

import com.alexp777.atech.datagen.custom.CrushingRecipeBuilder;
import com.alexp777.atech.datagen.custom.SteelForgeRecipeBuilder;
import com.alexp777.atech.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public ModRecipeProvider(DataGenerator pGenerator) {
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

		new SteelForgeRecipeBuilder(Items.IRON_INGOT.asItem(), ModItems.STEEL_INGOT.get(), 1, 200)
				.unlockedBy("has_forge",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT.asItem()).build()))
				.save(pFinishedRecipeConsumer);


		/*
		 * ======= Crusher Recipes =======
		 */
		createCrushingRecipe(ModItems.COPPER_INGOT.get(), ModItems.COPPER_DUST.get(), 1, 200, 1, pFinishedRecipeConsumer);
		createCrushingRecipe(ModItems.TIN_INGOT.get(), ModItems.TIN_DUST.get(), 1, 150, 1, pFinishedRecipeConsumer);
		createCrushingRecipe(Items.IRON_INGOT, ModItems.IRON_DUST.get(), 2, 250, 1, pFinishedRecipeConsumer);


	}

	private void createCrushingRecipe(ItemLike input, ItemLike output, int tier, int baseProgress, int count,
									  Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
		new CrushingRecipeBuilder(input, output, tier, baseProgress, count)
				.unlockedBy("has_crusher", inventoryTrigger(ItemPredicate.Builder.item().of(input).build()))
				.save(pFinishedRecipeConsumer);
	}
}
