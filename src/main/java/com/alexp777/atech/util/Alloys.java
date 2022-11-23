package com.alexp777.atech.util;

/*
I wanted to try storing some alloy data in here for making ingots
I have no idea how this will be implemented but I wanted to get it out of my brain

Something like the chance to take durability is the inverse of the integrity
ie: durability -= Math.random() < (1-integrity) ? 1 : 0
 */
public class Alloys {
	public enum SteelAlloy {
		S304(0.85, 400),
		S316(0.90, 400),
		S904(0.95, 450),
		HSLA(0.99, 500);

		private final double integrityFactor;
		private final int durability;

		private SteelAlloy(double integrityFactor, int durability) {
			this.integrityFactor = integrityFactor;
			this.durability = durability;
		}

		public double getIntegrityFactor() {
			return this.integrityFactor;
		}

		public int getDurability() {
			return this.durability;
		}
	}
}
