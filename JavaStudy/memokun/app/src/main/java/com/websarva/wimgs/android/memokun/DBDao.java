package com.websarva.wimgs.android.memokun;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.ArrayAdapter;

public class DBDao extends DatabaseHelper{

    protected Context dbContext ;
    SQLiteDatabase db;

    //DAO(DataAccessObject)化。
    public DBDao(Context context) {
        super(context);
        dbContext = context;
    }

    public void connect() {
        DatabaseHelper helper = new DatabaseHelper(dbContext);
        db = helper.getWritableDatabase();
    }
    //編集アクティビティにてデリートインサートを行うためクローズを個別化。
    public void close(){
        db.close();
    }
    //ListViewから取得したタイトルをもとにノートを取得するselect処理
    public String selectTitle(String title){

        String idxNote="";
        String sql = dbContext.getString(R.string.db_selectName) + "'" + title + "'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            idxNote = cursor.getString(cursor.getColumnIndex("note"));
        }
        return idxNote;
    }

    //onResumeにてアダプターにデータベース内のタイトルを入れるselect処理
    public ArrayAdapter<String> selectAll(ArrayAdapter<String> adapter) {
        Cursor cursor = db.rawQuery(dbContext.getString(R.string.db_selectAll), null);
        //最初は一つ手前にいるためmoveToNext()にて調整。なくなるまで続く。
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("name"));
            adapter.add(title);
        }
        return adapter;
    }
    //タイトルとノートを保存するinsert処理
    public void insertTN(String title,String note){


        String sqlInsert = dbContext.getString(R.string.insert);
        SQLiteStatement stmt = db.compileStatement(sqlInsert);
        stmt.bindString(1, title);
        stmt.bindString(2, note);
        stmt.executeInsert();
    }
    //タイトルを引数にデータベースから削除するdelete処理
    public void delete (String title) {

        String sqlDelete = dbContext.getString(R.string.db_DELETE_INSERT_delete);
        SQLiteStatement stmt = db.compileStatement(sqlDelete);
        stmt.bindString(1,title);
        stmt.executeUpdateDelete();

    }



}
