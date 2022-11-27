package com.alexp777.atech.util;

import java.util.List;

public enum ATechMaterial {

	/*
	======= Materials =======
	 */
	COPPER(0.85, 300, 1085, "Cu", ATechMaterialType.MATERIAL),
	TIN(0.70, 200, 232, "Sn", ATechMaterialType.MATERIAL),
	NICKEL(0.70, 250, 1455, "Ni", ATechMaterialType.MATERIAL),
	IRON(0.90, 500, 1538, "Fe", ATechMaterialType.MATERIAL),
	LEAD(0.85, 200, 328, "Pb", ATechMaterialType.MATERIAL);

	/*
	======= Alloys =======
	 */

	/*
	Integrity Factor determines damage chance from durability
	 */
	private final double INTEGRITY_FACTOR;

	/*
	Total durability, this doesn't decrement always, the chance is determined by the Integrity Factor
	 */
	private final int DURABILITY;

	/*
	This is when the material melts
	 */
	private final int MELTING_POINT;

	/*
	This is the chemical symbol
	 */
	private final String CHEMICAL_SYMBOL;

	/*
	This is the Material Type
	 */
	private final ATechMaterialType TYPE;

	private ATechMaterial(double integrityFactor, int durability, int meltingPoint, String chemicalSymbol, ATechMaterialType type) {
		this.INTEGRITY_FACTOR = integrityFactor;
		this.DURABILITY = durability;
		this.MELTING_POINT = meltingPoint;
		this.CHEMICAL_SYMBOL = chemicalSymbol;
		this.TYPE = type;
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

	public String getChemicalSymbol() {
		return this.CHEMICAL_SYMBOL;
	}

	public ATechMaterialType getType() {
		return TYPE;
	}

}
