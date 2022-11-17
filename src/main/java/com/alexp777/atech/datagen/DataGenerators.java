package com.alexp777.atech.datagen;

import com.alexp777.atech.ATech;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ATech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	/**
	 * Called on Event Subscription, generates all data
	 * @param event Event from Main Mod Class
	 */
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		generator.addProvider(new ModRecipeProvider(generator));
		generator.addProvider(new ModItemModelProvider(generator, ATech.MODID, existingFileHelper));
		generator.addProvider(new ModBlockStateProvider(generator, existingFileHelper));
	}
}
