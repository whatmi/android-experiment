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
	
	// ���̺�� �����ͺ��̽��� �����ϱ� ���� �� ���ڸ� ���(toggle) �Ѵ�.
	static final int DATABASE_VERSION = 1;
	
	public SchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// Student ���̺��� �����Ѵ�.
		db.execSQL("CREATE TABLE " + StudentTable.TABLE_NAME + " (" + 
				StudentTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				StudentTable.NAME + " TEXT, " + 
				StudentTable.STATE + " TEXT, " +
				StudentTable.GRADE + " INTEGER);");
		
		// Courses ���̺��� �����Ѵ�.
		db.execSQL("CREATE TABLE " + CourseTable.TABLE_NAME + " (" + 
				CourseTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				CourseTable.NAME + " TEXT);");
		
		// ���� ���̺� Ŭ������ �����Ѵ�.
		db.execSQL("CREATE TABLE " + ClassTable.TABLE_NAME + " (" + 
				ClassTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				ClassTable.STUDENT_ID + " INTEGER, " + 
				ClassTable.COURSE_ID + " INTEGER);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		
		// ���׷��̵�ƴٸ� ���� ���̺��� �����Ѵ�.
		db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CourseTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ClassTable.TABLE_NAME);
		
		// ���ο� ��Ŵ �ν��Ͻ��� �����Ѵ�.
		onCreate(db);
	}
	
	// �л� �߰��� ���� ���� �޼ҵ�
	public long addStudent(String name, String state, int grade) {
		// CONTENTVALUE ��ü ����
		ContentValues cv = new  ContentValues();
		cv.put(StudentTable.NAME, name);
		cv.put(StudentTable.STATE, state);
		cv.put(StudentTable.GRADE, grade);
		
		// ���� ������ �����ͺ��̽��� ������ �Է��Ѵ�.
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(StudentTable.TABLE_NAME, StudentTable.NAME, cv);
		return result;
	}
	
	// ���� �߰��� ���� ���� �޼ҵ�
	public long addCourse(String name) {
		ContentValues cv = new ContentValues();
		cv.put(CourseTable.NAME, name);
		
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(CourseTable.TABLE_NAME, CourseTable.NAME, cv);
		return result;
	}
	
	// �л��� ������ ����ϱ� ���� ���� �޼ҵ�
	public boolean enrollStudentClass(int studentId, int courseId) {
		ContentValues cv = new ContentValues();
		cv.put(ClassTable.STUDENT_ID, studentId);
		cv.put(ClassTable.COURSE_ID, courseId);
		
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID, cv);
		return  (result >= 0);
	}
	
	// ���� ���� ��� �л��� ���´�.
	public Cursor getStudentsForCourse(int courseId) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// �л� ID�� ��ȯ�Ѵ�.
		String[] columns = new String[] { ClassTable.STUDENT_ID };
		String[] selectionArgs = new String[] { String.valueOf(courseId) };
		
		// ���� ���� �л��� ���� Ŭ���� ������ �����Ѵ�.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null, null);
		
		return c;
	}
	
	// �ش� �л��� ��� ������ ���´�.
	public Cursor getCoursesForStudent(int studentId) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// ���� ID�� ��ȯ�Ѵ�.
		String[] columns = new String[] { ClassTable.COURSE_ID };
		String[] selectionArgs = new String[] { String.valueOf(studentId) };
		
		// �ش� �л��� ���� ID�� ���� Ŭ���� ������ �����Ѵ�.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.STUDENT_ID + "= ? ", selectionArgs, null, null, null);
		
		return c;
	}
	
	public Set<Integer> getStudentByGradeForCourse(int courseId, int grade) {
		SQLiteDatabase sd = getWritableDatabase();
		
		// ���� ID�� ��ȯ�Ѵ�.
		String[] columns = new String[] { ClassTable.STUDENT_ID };
		String[] selectionArgs = new String[] { String.valueOf(courseId) };
		
		// ���� ���� �л��� ���� Ŭ���� ������ �����Ѵ�.
		Cursor c = sd.query(ClassTable.TABLE_NAME, columns, ClassTable.COURSE_ID + "= ? ", selectionArgs, null, null, null);
		Set<Integer> returnIds = new HashSet<Integer>();
		while(c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex(ClassTable.STUDENT_ID));
			returnIds.add(id);
		}
		
		// �ι�° ������ �����
		columns = new String[] { StudentTable.ID };
		selectionArgs = new String[] { String.valueOf(grade) };
		
		c = sd.query(StudentTable.TABLE_NAME, columns, StudentTable.GRADE + "= ? ", selectionArgs, null, null, null);
		Set<Integer> gradeIds = new HashSet<Integer>();
		while(c.moveToNext()) {
			int id = c.getInt(c.getColumnIndex(StudentTable.ID));
			gradeIds.add(id);
		}
		
		// ID Set �� �������� ��ȯ�Ѵ�.
		returnIds.retainAll(gradeIds);
		return returnIds;
	}
	
	// �л��� �����ϰ� �����ϴ� �޼ҵ�
	public boolean removeStudent(int studentId) {
		SQLiteDatabase sd = getWritableDatabase();
		String[] whereArgs = new String[] { String.valueOf(studentId) };
		
		// �л��� ��ϵ� ��� Ŭ���� ������ �����Ѵ�.
		sd.delete(ClassTable.TABLE_NAME, ClassTable.STUDENT_ID + "= ? ", whereArgs);
		
		// �� �� �л��� �����Ѵ�.
		int result = sd.delete(StudentTable.TABLE_NAME, StudentTable.ID + "= ? ", whereArgs);
		
		return (result > 0);
	}
	
	// ������ �����ϰ� �����ϴ� �޼ҵ�
	public boolean removeCourse(int courseId) {
		SQLiteDatabase sd = getWritableDatabase();
		String[] whereArgs = new String[] { String.valueOf(courseId) };
		
		// ��ϵ� ��� �л����κ��� ������ �����ؾ� �Ѵ�.
		sd.delete(ClassTable.TABLE_NAME, ClassTable.COURSE_ID + "= ? ", whereArgs);
		
		// �� �� ������ �����Ѵ�.
		int result = sd.delete(CourseTable.TABLE_NAME, CourseTable.ID + "= ? ", whereArgs);
		
		return (result > 0);
	}
	
	
	public class StudentTable {
		// ��� �л��� ������ ID�� ������
		public static final String ID = "_id";
		
		// �л��� �̸�
		public static final String NAME = "student_name";
		
		// �л��� �����ϴ� ��(state)
		public static final String STATE = "state";
		
		// �л��� �г�
		public static final String GRADE = "grade";
		
		// ���̺� �̸�
		public static final String TABLE_NAME = "students";
	}
	
	
	public class CourseTable {
		
		// ���� ���� ID
		public static final String ID = "_id";
		
		// ���� �̸�
		public static final String NAME = "course_name";
		
		// ���̺� �̸�
		public static final String TABLE_NAME = "courses";
		
	}
	
	
	// �� ���̺��� �ʼ����� �л��� ������ ���� ������ ��Ÿ����.
	public class ClassTable {
		
		// �� ���� ���� ID - �������� �ǹ̴� ����.
		public static final String ID = "_id";
		
		// �л� ID
		public static final String STUDENT_ID = "student_id";
		
		// ���� ����
		public static final String COURSE_ID = "course_id";
		
		// ���̺� �̸�
		public static final String TABLE_NAME = "classes";
		
	}
}



