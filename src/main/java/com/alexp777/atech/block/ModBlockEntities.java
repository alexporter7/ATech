package com.alexp777.atech.block;

import com.alexp777.atech.ATech;
import com.alexp777.atech.block.worktables.ProjectTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ATech.MODID);

	//Block Entities
	public static final RegistryObject<BlockEntityType<ProjectTableBlockEntity>> PROJECT_TABLE_BLOCK_ENTITY =
			BLOCK_ENTITIES.register("project_table_block_entity",
					() -> BlockEntityType.Builder.of(ProjectTableBlockEntity::new, ModBlocks.PROJECT_TABLE.get())
							.build(null));
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}
