package com.alexp777.atech.util;

public enum Alloy {
	BRONZE(0.85, 350, 913, "Copper, Tin", "CuSn");

	private final double INTEGRITY_FACTOR;
	private final int DURABILITY;
	private final int MELTING_POINT;
	private final String MATERIAL_INPUT;
	private final String CHEMICAL_SYMBOL;

	private Alloy(double integrityFactor, int durability, int meltingPoint, String materialInput, String chemicalSymbol) {
		this.INTEGRITY_FACTOR = integrityFactor;
		this.DURABILITY = durability;
		this.MELTING_POINT = meltingPoint;
		this.MATERIAL_INPUT = materialInput;
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

	public String getMaterialInput() {
		return this.MATERIAL_INPUT;
	}

	public String getChemicalSymbol() {
		return this.CHEMICAL_SYMBOL;
	}

}
