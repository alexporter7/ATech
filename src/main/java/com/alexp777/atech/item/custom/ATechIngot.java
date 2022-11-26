package com.alexp777.atech.item.custom;

import com.alexp777.atech.item.ModCreativeModeTab;
import com.alexp777.atech.util.ModValue;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ATechIngot extends Item {

	private final int MELTING_POINT;
	private final String CHEMICAL_SYMBOL;

	public ATechIngot(int meltingPoint, String chemicalSymbol) {
		super(new Item.Properties()
				.tab(ModCreativeModeTab.ATECH_TAB)
				.fireResistant()
				.stacksTo(64));
		this.MELTING_POINT = meltingPoint;
		this.CHEMICAL_SYMBOL = chemicalSymbol;

	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(new TextComponent(ModValue.DARK_GRAY_FORMATTER + getChemicalSymbol()));
		pTooltipComponents.add(new TextComponent(ModValue.BLUE_FORMATTER +
				"Melting Point: " + ModValue.RESET_FORMATTER + getMeltingPoint()));
	}

	public int getMeltingPoint() {
		return this.MELTING_POINT;
	}

	public String getChemicalSymbol() {
		return this.CHEMICAL_SYMBOL;
	}




}
