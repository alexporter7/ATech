package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.item.ModItems;
import com.alexp777.atech.util.FormFactor;
import com.alexp777.atech.util.Material;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ATechMaterial extends Item {

	private final double INTEGRITY_FACTOR;
	private final int DURABILITY;
	private final int MELTING_POINT;
	private final String CHEMICAL_SYMBOL;
	private final FormFactor FORM_FACTOR;

	public ATechMaterial(Material material, FormFactor formFactor) {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.fireResistant()
				.stacksTo(64));
		this.INTEGRITY_FACTOR = material.getIntegrityFactor();
		this.DURABILITY = material.getDurability();
		this.MELTING_POINT = material.getMeltingPoint();
		this.CHEMICAL_SYMBOL = material.getChemicalSymbol();
		this.FORM_FACTOR = formFactor;

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

	public int getMeltingPoint() {
		return this.MELTING_POINT;
	}

	public String getChemicalSymbol() {
		return this.CHEMICAL_SYMBOL;
	}

	public double getIntegrityFactor() {
		return this.INTEGRITY_FACTOR;
	}

	public int getDurability() {
		return this.DURABILITY;
	}

	public FormFactor getFormFactor() {
		return this.FORM_FACTOR;
	}


}
