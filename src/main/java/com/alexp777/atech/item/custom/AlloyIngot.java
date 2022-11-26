package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.util.Alloy;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloyIngot extends Item {

	private final double INTEGRITY_FACTOR;
	private final int DURABILITY;
	private final int MELTING_POINT;
	private final String MATERIAL_INPUT;
	private final String CHEMICAL_SYMBOL;

	public AlloyIngot(Alloy alloy) {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.fireResistant()
				.stacksTo(64));
		this.INTEGRITY_FACTOR = alloy.getIntegrityFactor();
		this.DURABILITY = alloy.getDurability();
		this.MELTING_POINT = alloy.getMeltingPoint();
		this.MATERIAL_INPUT = alloy.getMaterialInput();
		this.CHEMICAL_SYMBOL = alloy.getChemicalSymbol();
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(new TextComponent(ModValue.DARK_GRAY_FORMATTER + getChemicalSymbol()));
		pTooltipComponents.add(new TextComponent(
				formatTextHelper(ModValue.BLUE_FORMATTER, "Melting Point: ", getMeltingPoint())));
		pTooltipComponents.add(new TextComponent(
				formatTextHelper(ModValue.BLUE_FORMATTER, "Integrity: ", getIntegrityFactor())));
		pTooltipComponents.add(new TextComponent(
				formatTextHelper(ModValue.BLUE_FORMATTER, "Durability: ", getDurability())));
		pTooltipComponents.add(new TextComponent(
				formatTextHelper(ModValue.BLUE_FORMATTER, "Makeup: ", getMaterialInput())));
	}

	public String formatTextHelper(String formatter, String label, String value) {
		return formatter + label + ModValue.RESET_FORMATTER + value;
	}

	public String formatTextHelper(String formatter, String label, int value) {
		return formatter + label + ModValue.RESET_FORMATTER + value;
	}

	public String formatTextHelper(String formatter, String label, double value) {
		return formatter + label + ModValue.RESET_FORMATTER + value;
	}

	public double getIntegrityFactor() {
		return this.INTEGRITY_FACTOR;
	}

	public int getDurability() {
		return this.DURABILITY;
	}

	public int getMeltingPoint() {
		return this.MELTING_POINT;
	}

	public String getMaterialInput() {
		return this.MATERIAL_INPUT;
	}

	public String getChemicalSymbol() {
		return this.CHEMICAL_SYMBOL;
	}


}
