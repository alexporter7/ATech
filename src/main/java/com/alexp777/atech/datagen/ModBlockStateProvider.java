package com.alexp777.atech.datagen;

import com.alexp777.atech.ATech;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, ATech.MODID, exFileHelper);
	}

	/**
	 * Register all blockstates
	 */
	@Override
	protected void registerStatesAndModels() {
		//SimpleBlock
		//simpleBlock(ModBlocks.BLOCK.get())


		//buttonBlock/pressurePlateBlock/wallBlock/fenceBlock


	}



}
