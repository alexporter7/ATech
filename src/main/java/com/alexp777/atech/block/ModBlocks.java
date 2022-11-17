package com.alexp777.atech.block;

import com.alexp777.atech.ATech;
import com.alexp777.atech.block.worktables.ProjectTable;
import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {

	//Create block deferred registry
	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, ATech.MODID);

	//Blocks
	public static final RegistryObject<Block> PROJECT_TABLE =
			registerBlock("project_table", ProjectTable::new, ModCreativeModeTab.ATECH_TAB);

	/**
	 * Register a block
	 * @param name Internal name of block
	 * @param block Block object
	 * @param tab CreativeMode Tab of Object
	 * @return block
	 * @param <T>
	 */
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}

	/**
	 * Register a BlockItem
	 * @param name Internal name (should be the same as block)
	 * @param block Block object
	 * @param tab Creative Mode Tab
	 * @return BlockItem
	 * @param <T>
	 */
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
																			CreativeModeTab tab) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(tab)));
	}

	/**
	 * Register blocks with event bus
	 * @param eventBus EventBus from Main Mod class
	 */
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
