package com.alexp777.atech.recipe;

import com.alexp777.atech.ATech;
import com.alexp777.atech.recipe.serializer.CrusherRecipeSerializer;
import com.alexp777.atech.util.ModValue;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
			DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ATech.MODID);

	public static final RegistryObject<RecipeSerializer<SteelForgeRecipe>> STEEL_FORGE_SERIALIZER =
			SERIALIZERS.register(ModValue.STEEL_FORGE_RECIPE_TYPE_ID, () -> SteelForgeRecipe.Serializer.INSTANCE);

	public static final RegistryObject<RecipeSerializer<CrusherRecipe>> CRUSHER_RECIPE_SERIALIZER =
			SERIALIZERS.register(ModValue.CRUSHING_RECIPE_KEY, () -> CrusherRecipeSerializer.INSTANCE);

	public static void register(IEventBus eventBus) {
		SERIALIZERS.register(eventBus);
	}
}
