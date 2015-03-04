package com.ap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	static final String DATABASE_NAME = "my_database.db";
	
	// 테이블과 데이터베이스 업데이트를 위해 이 숫자를 토글한다.
	static final int DATABASE_VERSION = 1;
	
	// 생성하고자 하는 테이블 이름
	static final String TABLE_NAME = "my_table";
	
	// 일부 예제 필드
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
		// 업그레이드 되면 이전 테이블을 제거한다.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		
		// 테이블의 새 인스턴스를 생성한다.
		onCreate(db);
	}

}
