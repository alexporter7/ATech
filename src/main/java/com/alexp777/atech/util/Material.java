package com.alexp777.atech.util;

public enum Material {

	COPPER(0.85, 300, 1085, "Cu"),
	TIN(0.70, 200, 232, "Sn"),
	NICKEL(0, 0, 0, "Ni"),
	IRON(0, 0, 0, "Fe"),
	LEAD(0, 0, 0, "Pb");
	private final double INTEGRITY_FACTOR;
	private final int DURABILITY;
	private final int MELTING_POINT;
	private final String CHEMICAL_SYMBOL;

	private Material(double integrityFactor, int durability, int meltingPoint, String chemicalSymbol) {
		this.INTEGRITY_FACTOR = integrityFactor;
		this.DURABILITY = durability;
		this.MELTING_POINT = meltingPoint;
		this.CHEMICAL_SYMBOL = chemicalSymbol;
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

}
