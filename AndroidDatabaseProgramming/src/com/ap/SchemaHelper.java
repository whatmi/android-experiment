package com.ap;

import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchemaHelper extends SQLiteOpenHelper {
	
	static final String TAG = "SchemaHelper";
	
	static final String DATABASE_NAME = "adv_data.db";
	
	// 테이블과 데이터베이스를 갱신하기 위해 이 숫자를 토글(toggle) 한다.
	static final int DATABASE_VERSION = 1;
	
	public SchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// Student 테이블을 생성한다.
		db.execSQL("CREATE TABLE " + StudentTable.TABLE_NAME + " (" + 
				StudentTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				StudentTable.NAME + " TEXT, " + 
				StudentTable.STATE + " TEXT, " +
				StudentTable.GRADE + " INTEGER);");
		
		// Courses 테이블을 생성한다.
		db.execSQL("CREATE TABLE " + CourseTable.TABLE_NAME + " (" + 
				CourseTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				CourseTable.NAME + " TEXT);");
		
		// 매핑 테이블 클래스를 생성한다.
		db.execSQL("CREATE TABLE " + ClassTable.TABLE_NAME + " (" + 
				ClassTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				ClassTable.STUDENT_ID + " INTEGER, " + 
				ClassTable.COURSE_ID + " INTEGER);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		
		// 업그레이드됐다면 이전 테이블을 제거한다.
		db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CourseTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ClassTable.TABLE_NAME);
		
		// 새로운 스킴 인스턴스를 생성한다.
		onCreate(db);
	}
	
	// 학생 추가를 위한 래퍼 메소드
	public long addStudent(String name, String state, int grade) {
		// CONTENTVALUE 객체 생성
		ContentValues cv = new  ContentValues();
		cv.put(StudentTable.NAME, name);
		cv.put(StudentTable.STATE, state);
		cv.put(StudentTable.GRADE, grade);
		
		// 쓰기 가능한 데이터베이스를 가져와 입력한다.
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(StudentTable.TABLE_NAME, StudentTable.NAME, cv);
		return result;
	}
	
	// 과정 추가를 위한 래퍼 메소드
	public long addCourse(String name) {
		ContentValues cv = new ContentValues();
		cv.put(CourseTable.NAME, name);
		
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(CourseTable.TABLE_NAME, CourseTable.NAME, cv);
		return result;
	}
	
	// 학생을 과정에 등록하기 위한 래퍼 메소드
	public boolean enrollStudentClass(int studentId, int courseId) {
		ContentValues cv = new ContentValues();
		cv.put(ClassTable.STUDENT_ID, studentId);
		cv.put(ClassTable.COURSE_ID, courseId);
		
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID, cv);
		return  (result >= 0);
	}
	
	// 과정 내의 모든 학생을 얻어온다.
	public Cursor getStudentsForCourse(int courseId) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// 학생 ID만 반환한다.
		String[] columns = new String[] { ClassTable.STUDENT_ID };
		String[] selectionArgs = new String[] { String.valueOf(courseId) };
		
		// 과정 내의 학생에 대한 클래스 매핑을 쿼리한다.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null, null);
		
		return c;
	}
	
	// 해당 학생의 모든 과정을 얻어온다.
	public Cursor getCoursesForStudent(int studentId) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// 과정 ID만 반환한다.
		String[] columns = new String[] { ClassTable.COURSE_ID };
		String[] selectionArgs = new String[] { String.valueOf(studentId) };
		
		// 해당 학생의 과정 ID에 대한 클래스 매핑을 쿼리한다.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.STUDENT_ID + "= ? ", selectionArgs, null, null, null);
		
		return c;
	}
	
	public Set<Integer> getStudentByGradeForCourse(int courseId, int grade) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// 과정 ID만 반환한다.
		String[] columns = new String[] { ClassTable.STUDENT_ID };
		String[] selectionArgs = new String[] { String.valueOf(courseId) };
		
		// 과정 내의 학생에 대한 클래스 매핑을 쿼리한다.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null, null);
		Set<Integer> returnIds = new HashSet<Integer>();
		while(c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
			returnIds.add(id);
		}
		
		// 두번째 쿼리를 만든다
		columns = new String[] { StudentTable.ID };
		selectionArgs = new String[] { String.valueOf(grade) };
		
		c = sd.query(StudentTable.TABLE_NAME, columns, StudentTable.GRADE + "= ? ", selectionArgs, null, null, null);
		Set<Integer> gradeIds = new HashSet<Integer>();
		while(c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex(StudentTable.ID));
			gradeIds.add(id);
		}
		
		// ID Set 의 교집합을 반환한다.
		returnIds.retainAll(gradeIds);
		return returnIds;
	}
	
	// 학생을 안전하게 제거하는 메소드
	public boolean removeStudent(int studentId) {
		SQLiteDatabase sd = getWritableDatabase();
		String[] whereArgs = new String[] { String.valueOf(studentId) };
		
		// 학생이 등록된 모든 클래스 매핑을 제거한다.
		sd.delete(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID + "= ? ", whereArgs);
		
		// 그 후 학생을 삭제한다.
		int result = sd.delete(StudentTable.TABLE_NAME, StudentTable.ID + "= ? ", whereArgs);
		
		return (result > 0);
	}
	
	// 과정을 안전하게 제거하는 메소드
	public boolean removeCourse(int courseId) {
		SQLiteDatabase sd = getWritableDatabase();
		String[] whereArgs = new String[] { String.valueOf(courseId) };
		
		// 등록된 모든 학생으로부터 과정을 제거해야 한다.
		sd.delete(ClassTable.TABLE_NAME, ClassTable.COURSE_ID + "= ? ", whereArgs);
		
		// 그 후 과정을 삭제한다.
		int result = sd.delete(CourseTable.TABLE_NAME, CourseTable.ID + "= ? ", whereArgs);
		
		return (result > 0);
	}
	
	
	public class StudentTable {
		// 모든 학생은 고유한 ID를 가진다
		public static final String ID = "_id";
		
		// 학생의 이름
		public static final String NAME = "student_name";
		
		// 학생이 거주하는 주(state)
		public static final String STATE = "state";
		
		// 학생의 학년
		public static final String GRADE = "grade";
		
		// 테이블 이름
		public static final String TABLE_NAME = "students";
	}
	
	
	public class CourseTable {
		
		// 과정 고유 ID
		public static final String ID = "_id";
		
		// 과정 이름
		public static final String NAME = "course_name";
		
		// 테이블 이름
		public static final String TABLE_NAME = "courses";
		
	}
	
	
	// 이 테이블은 필수적인 학생과 과정의 매핑 정보를 나타낸다.
	public class ClassTable {
		
		// 각 행의 고유 ID - 실제적인 의미는 없다.
		public static final String ID = "_id";
		
		// 학생 ID
		public static final String STUDENT_ID = "student_id";
		
		// 관련 과정
		public static final String COURSE_ID = "course_id";
		
		// 테이블 이름
		public static final String TABLE_NAME = "classes";
		
	}
}



