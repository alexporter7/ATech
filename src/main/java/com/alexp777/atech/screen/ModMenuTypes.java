package com.alexp777.atech.screen;

import com.alexp777.atech.ATech;
import com.alexp777.atech.screen.machine.CrusherMenu;
import com.alexp777.atech.screen.metalforge.SteelForgeMenu;
import com.alexp777.atech.screen.worktable.ProjectTableMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

	public static final DeferredRegister<MenuType<?>> MENUS =
			DeferredRegister.create(ForgeRegistries.CONTAINERS, ATech.MODID);

	public static final RegistryObject<MenuType<ProjectTableMenu>> PROJECT_TABLE_MENU =
			registerMenuType(ProjectTableMenu::new, "project_table_menu");
	public static final RegistryObject<MenuType<SteelForgeMenu>> STEEL_FORGE_MENU =
			registerMenuType(SteelForgeMenu::new, "steel_forge_menu");

	public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU =
			registerMenuType(CrusherMenu::new, "crusher_menu");

	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}
}
