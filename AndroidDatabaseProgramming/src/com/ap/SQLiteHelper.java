package com.ap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	static final String DATABASE_NAME = "my_database.db";
	
	// ���̺�� �����ͺ��̽� ������Ʈ�� ���� �� ���ڸ� ����Ѵ�.
	static final int DATABASE_VERSION = 1;
	
	// �����ϰ��� �ϴ� ���̺� �̸�
	static final String TABLE_NAME = "my_table";
	
	// �Ϻ� ���� �ʵ�
	static final String UID = "_id";
	static final String NAME = "name";
	
	public SQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARYKEY AUTOINCREMENT, " + NAME + " VARCHAR(225));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// ���׷��̵� �Ǹ� ���� ���̺��� �����Ѵ�.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		
		// ���̺��� �� �ν��Ͻ��� �����Ѵ�.
		onCreate(db);
	}

}
