package kr.mog.android.lib.utils;

public class StringPatterns {
	
	// ¼ıÀÚ¸¸
	public static String patternNumber() {
		return "^[0-9]*$";
	}
	
	// ¿µ¾î¸¸
	public static String patternEng() {
		return "^[a-zA-Z]*$";
	}
	
	// ÇÑ±Û¸¸
	public static String patternKor() {
		return "^[°¡-ÆR]*$";
	}
	
	// ¿µ¾î¿Í ¼ıÀÚ¸¸
	public static String patternEngNumber() {
		return "^[a-zA-Z0-9]*$";
	}
	
	// ÀÌ¸ŞÀÏ
	public static String patternEmail() {
		return "^[a-zA-Z0-9]+@[a-zA-Z0-9]+$";
	}
	
	
}
