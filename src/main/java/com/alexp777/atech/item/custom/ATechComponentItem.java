package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.util.ATechMaterial;
import com.alexp777.atech.util.ATechComponent;
import com.alexp777.atech.util.FormFactor;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ATechComponentItem extends Item {

	private final ATechComponent COMPONENT;

	public ATechComponentItem(ATechComponent component) {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.fireResistant()
				.stacksTo(64));

		this.COMPONENT = component;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(new TextComponent(
				ModValue.BLUE_FORMATTER + "Type: " + ModValue.RESET_FORMATTER + getFormFactor()));
		pTooltipComponents.add(new TextComponent(
				ModValue.BLUE_FORMATTER + "Material: " + ModValue.RESET_FORMATTER + getMaterial().getChemicalSymbol()));
		pTooltipComponents.add(new TextComponent(
				ModValue.BLUE_FORMATTER + "Modifier: " + ModValue.RESET_FORMATTER + getComponentModifier()));
		pTooltipComponents.add(new TextComponent(
				ModValue.BLUE_FORMATTER + "Tier: " + ModValue.RESET_FORMATTER + getComponentTier()));

	}

	/*
	======= Component Methods =======
	*/
	public ATechComponent getComponent() {
		return COMPONENT;
	}

	public int getComponentTier() {
		return COMPONENT.getTier();
	}

	public int getComponentModifier() {
		return COMPONENT.getModifier();
	}

	public FormFactor getFormFactor() {
		return COMPONENT.getFormFactor();
	}

	public ATechMaterial getMaterial() {
		return COMPONENT.getMaterial();
	}

}
