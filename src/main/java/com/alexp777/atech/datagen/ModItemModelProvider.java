package com.alexp777.atech.datagen;

import com.alexp777.atech.ATech;
import com.alexp777.atech.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels() {

		/*
		======= Materials =======
		 */
		simpleItem(ModItems.COPPER_INGOT.get());
		simpleItem(ModItems.STEEL_INGOT.get());
		simpleItem(ModItems.TIN_INGOT.get());
		simpleItem(ModItems.NICKEL_INGOT.get());
		simpleItem(ModItems.LEAD_INGOT.get());

		simpleItem(ModItems.IRON_DUST.get());
		simpleItem(ModItems.COPPER_DUST.get());
		simpleItem(ModItems.TIN_DUST.get());
		simpleItem(ModItems.LEAD_DUST.get());
		simpleItem(ModItems.NICKEL_DUST.get());

		/*
		======= Alloys =======
		 */


		/*
		======= Motors =======
		 */
		simpleItem(ModItems.BASIC_MOTOR.get());

		/*
		======= Pistons =======
		 */
		simpleItem(ModItems.BASIC_PISTON.get());


	}

	private ItemModelBuilder simpleItem(Item item) {
		return withExistingParent(item.getRegistryName().getPath(),
				new ResourceLocation("item/generated"))
				.texture("layer0", new ResourceLocation(ATech.MODID,
										"item/" + item.getRegistryName().getPath()));
	}

	//TODO add handheld item builder
	private ItemModelBuilder handheldItem(Item item) {
		return null;
	}
}
