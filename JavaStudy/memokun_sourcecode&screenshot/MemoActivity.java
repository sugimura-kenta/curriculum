package com.websarva.wimgs.android.memokun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MemoActivity extends AppCompatActivity {

    LinedEditText etNote;
    EditText etTitle;
    String memoTitle;
    DBDao dao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        //コンストラクタ
        dao = new DBDao(MemoActivity.this);
        etNote = new LinedEditText(MemoActivity.this,null);

        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);

        //getIntentにてタイトルとノートを取得
        Intent intent= getIntent();
        memoTitle = intent.getStringExtra("memoTitle");
        String note = intent.getStringExtra("note");
        //取得したものを書くetにセット
        etTitle.setText(memoTitle);
        etNote.setText(note);

    }

    //保存ボタンをタップした時の保存処理。
    public void onSaveButtonClick(View view) {

        String note = etNote.getText().toString();
        String title = etTitle.getText().toString();

        //編集前のデータを削除し新しく追加する
        dao.connect();
        dao.delete(memoTitle);
        dao.insertTN(title,note);
        dao.close();
        finish();
    }


    //バックボタンをタップした時の保存処理。ボタンタップと同じ。
    @Override
    public void onBackPressed() {

        String note = etNote.getText().toString();
        String title = etTitle.getText().toString();

        dao.connect();

        dao.delete(memoTitle);
        dao.insertTN(title,note);
        dao.close();
        finish();
    }


}