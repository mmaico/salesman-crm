package br.com.kproj.salesman.infrastructure.helpers;

public enum Language {

	PT(0), EN(1), ES(2);
	
	private int id;
	
	public int getId() {
		return id;
	}

	Language(int id) {
		this.id = id;
	}
	
	public static Language get(int id) {
		for (Language status : Language.values()) {
			if(status.getId() == id) {
				return status;
			}
		}
		return null;
	}

	public static Language get(String lang) {
		
		for (Language language : values()) {
			if (language.getName().equalsIgnoreCase(lang))
				return language;
		}
		
		return Language.PT;
	}
	
	public static boolean exist(String lang) {
		
		for (Language language : values()) {
			if (language.getName().equalsIgnoreCase(lang)) {
				return true;
			}	
		}
		
		return false;
		
	}

	
	public String getName() {
		return this.toString().toLowerCase();
	}
}
