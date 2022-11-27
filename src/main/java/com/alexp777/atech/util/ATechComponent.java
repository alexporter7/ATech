package com.alexp777.atech.util;

public enum ATechComponent {

	BASIC_MOTOR(1, 5, ATechMaterial.COPPER, FormFactor.MOTOR),
	IRON_PRESS_PLATE(2, 1, ATechMaterial.IRON, FormFactor.PRESS_PLATE);

	/*
	Tier Determines what level of ore/item can be handled
	 */
	private final int TIER;

	/*
	Modifier determines how much faster or slower something is
	 */
	private final int MODIFIER;

	/*
	Material represents what item is made out of
	 */
	private final ATechMaterial MATERIAL;

	/*
	Form Factor is the component type ie: Piston, Motor, etc
	 */
	private final FormFactor FORM_FACTOR;

	private ATechComponent(int tier, int modifier, ATechMaterial material, FormFactor formFactor) {
		this.TIER = tier;
		this.MODIFIER = modifier;
		this.MATERIAL = material;
		this.FORM_FACTOR = formFactor;
	}

	public int getTier() {
		return TIER;
	}

	public int getModifier() {
		return MODIFIER;
	}

	public ATechMaterial getMaterial() {
		return MATERIAL;
	}

	public FormFactor getFormFactor() {
		return FORM_FACTOR;
	}
}
