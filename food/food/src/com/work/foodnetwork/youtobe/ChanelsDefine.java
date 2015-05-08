package com.work.foodnetwork.youtobe;

public class ChanelsDefine {

	public static final Chanel LauraVitalesKitchen = new Chanel("Laura Vitales Kitchen", "UCNbngWUqL2eqRw12yAwcICg");
	public static final Chanel JamieOliver = new Chanel("Jamie Oliver", "UCpSgg_ECBj25s9moCDfSTsA");
	public static final Chanel Cocinaalnatural = new Chanel("Cocinaal natural", "UCvg_5WAbGznrT5qMZjaXFGA");
	public static final Chanel cookingwithdog = new Chanel("Cooking with dog", "UCpprBWvibvmOlI8yJOEAAjA");
	public static final Chanel robjnixon = new Chanel("Robjnixon", "UCffs63OaN2nh-6StR6hzfiQ");

	public static class Chanel {
		public String name;
		public String id;

		public Chanel(String name, String id) {
			this.name = name;
			this.id = id;
		}
	}
}
