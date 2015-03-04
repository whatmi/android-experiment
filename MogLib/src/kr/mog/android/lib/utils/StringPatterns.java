package kr.mog.android.lib.utils;

public class StringPatterns {
	
	// ���ڸ�
	public static String patternNumber() {
		return "^[0-9]*$";
	}
	
	// ���
	public static String patternEng() {
		return "^[a-zA-Z]*$";
	}
	
	// �ѱ۸�
	public static String patternKor() {
		return "^[��-�R]*$";
	}
	
	// ����� ���ڸ�
	public static String patternEngNumber() {
		return "^[a-zA-Z0-9]*$";
	}
	
	// �̸���
	public static String patternEmail() {
		return "^[a-zA-Z0-9]+@[a-zA-Z0-9]+$";
	}
	
	
}
