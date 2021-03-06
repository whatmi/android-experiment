package kr.mog.android.lib.utils;

public class StringPatterns {
	
	// 숫자만
	public static String patternNumber() {
		return "^[0-9]*$";
	}
	
	// 영어만
	public static String patternEng() {
		return "^[a-zA-Z]*$";
	}
	
	// 한글만
	public static String patternKor() {
		return "^[가-힣]*$";
	}
	
	// 영어와 숫자만
	public static String patternEngNumber() {
		return "^[a-zA-Z0-9]*$";
	}
	
	// 이메일
	public static String patternEmail() {
		return "^[a-zA-Z0-9]+@[a-zA-Z0-9]+$";
	}
	
	
}
