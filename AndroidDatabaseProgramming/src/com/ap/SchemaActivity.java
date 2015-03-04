package com.ap;

import java.util.Set;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import com.ap.SchemaHelper.ClassTable;

public class SchemaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SchemaHelper sh = new SchemaHelper(this);
		
		// 학생을 추가하고 그 ID를 반환한다.
		long sid1 = sh.addStudent("Jason Wei", "IL", 12);
		long sid2 = sh.addStudent("Du Chung", "AR", 12);
		long sid3 = sh.addStudent("George Tang", "CA", 11);
		long sid4 = sh.addStudent("Mark Bocanegra", "CA", 11);
		long sid5 = sh.addStudent("Bobby Wei", "IL", 12);
		
		// 과정을 추가하고 그 ID를 반환한다.
		long cid1 = sh.addCourse("Math51");
		long cid2 = sh.addCourse("CS106A");
		long cid3 = sh.addCourse("Econ1A");
		
		// 수업에 학생을 등록한다.
		sh.enrollStudentClass((int) sid1, (int) cid1);
		sh.enrollStudentClass((int) sid1, (int) cid2);
		sh.enrollStudentClass((int) sid2, (int) cid2);
		sh.enrollStudentClass((int) sid3, (int) cid1);
		sh.enrollStudentClass((int) sid3, (int) cid2);
		sh.enrollStudentClass((int) sid4, (int) cid3);
		sh.enrollStudentClass((int) sid5, (int) cid2);
		
		// 과정에 대한 학생을 얻어온다.
		Cursor c = sh.getStudentsForCourse((int) cid2);
		while(c.moveToNext()) {
			int sid = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
			System.out.println("STUDENT " + sid + " IS ENROLLED IN COURSE " + cid2);
		}
		
		// 과정에 대한 학생을 얻어와 학년으로 필터링한다.
		Set<Integer> sids = sh.getStudentByGradeForCourse((int) cid2, 11);
		for(Integer sid : sids) {
			System.out.println("STUDENT " + sid + " OF GRADE 11 IS ENROLLED IN COURSE " + cid2);
		}
		
		
		// 원하는 클래스를 얻어온다.
		c = sh.getCoursesForStudent((int) sid1);
		while(c.moveToNext()) {
			int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
			System.out.println("STUDENT " + sid1 + " IS ENROLLED IN COURSE " + cid);
		}
		
		// 과정 삭제를 시도한다.
		sh.removeCourse((int) cid1);
		
		System.out.println( "-----------------------------------------------" );
		
		// 삭제 내용이 스킴에 유지되는지 확인한다.
		c = sh.getCoursesForStudent((int) sid1);
		while(c.moveToNext()) {
			int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
			System.out.println("STUDENT " + sid1 + " IS ENTROLLED IN COURSE " + cid);
		}
		
		
	}
}
