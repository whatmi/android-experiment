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
		
		// �л��� �߰��ϰ� �� ID�� ��ȯ�Ѵ�.
		long sid1 = sh.addStudent("Jason Wei", "IL", 12);
		long sid2 = sh.addStudent("Du Chung", "AR", 12);
		long sid3 = sh.addStudent("George Tang", "CA", 11);
		long sid4 = sh.addStudent("Mark Bocanegra", "CA", 11);
		long sid5 = sh.addStudent("Bobby Wei", "IL", 12);
		
		// ������ �߰��ϰ� �� ID�� ��ȯ�Ѵ�.
		long cid1 = sh.addCourse("Math51");
		long cid2 = sh.addCourse("CS106A");
		long cid3 = sh.addCourse("Econ1A");
		
		// ������ �л��� ����Ѵ�.
		sh.enrollStudentClass((int) sid1, (int) cid1);
		sh.enrollStudentClass((int) sid1, (int) cid2);
		sh.enrollStudentClass((int) sid2, (int) cid2);
		sh.enrollStudentClass((int) sid3, (int) cid1);
		sh.enrollStudentClass((int) sid3, (int) cid2);
		sh.enrollStudentClass((int) sid4, (int) cid3);
		sh.enrollStudentClass((int) sid5, (int) cid2);
		
		// ������ ���� �л��� ���´�.
		Cursor c = sh.getStudentsForCourse((int) cid2);
		while(c.moveToNext()) {
			int sid = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
			System.out.println("STUDENT " + sid + " IS ENROLLED IN COURSE " + cid2);
		}
		
		// ������ ���� �л��� ���� �г����� ���͸��Ѵ�.
		Set<Integer> sids = sh.getStudentByGradeForCourse((int) cid2, 11);
		for(Integer sid : sids) {
			System.out.println("STUDENT " + sid + " OF GRADE 11 IS ENROLLED IN COURSE " + cid2);
		}
		
		
		// ���ϴ� Ŭ������ ���´�.
		c = sh.getCoursesForStudent((int) sid1);
		while(c.moveToNext()) {
			int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
			System.out.println("STUDENT " + sid1 + " IS ENROLLED IN COURSE " + cid);
		}
		
		// ���� ������ �õ��Ѵ�.
		sh.removeCourse((int) cid1);
		
		System.out.println( "-----------------------------------------------" );
		
		// ���� ������ ��Ŵ�� �����Ǵ��� Ȯ���Ѵ�.
		c = sh.getCoursesForStudent((int) sid1);
		while(c.moveToNext()) {
			int cid = c.getInt(c.getColumnIndex(ClassTable.COURSE_ID));
			System.out.println("STUDENT " + sid1 + " IS ENTROLLED IN COURSE " + cid);
		}
		
		
	}
}
