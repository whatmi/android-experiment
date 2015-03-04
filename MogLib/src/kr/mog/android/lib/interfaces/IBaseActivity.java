package kr.mog.android.lib.interfaces;

public interface IBaseActivity {

	/*
	 * 시작 함수
	 */
	void initialize();

	/*
	 * 종료 함수
	 */
	void destroy();

	/*
	 * 해당 클래스의 태그
	 */
	String getTag();
}
