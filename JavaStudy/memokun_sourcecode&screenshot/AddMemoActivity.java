package com.websarva.wimgs.android.memokun;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMemoActivity extends AppCompatActivity {

    LinedEditText addNote ;
    EditText addTitle;
    DBDao dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);
        //コンストラクタ化
        addNote = new LinedEditText(AddMemoActivity.this,null);
        dao = new DBDao(AddMemoActivity.this);

        addNote = findViewById(R.id.addNote);
        addTitle = findViewById(R.id.addTitle);


    }
    //保存ボタンタップ時のデータベース保存処理
    public void onAddButtonClick(View view) {

        //入力状況によって3つに条件分岐
        String title = addTitle.getText().toString();
        String note = addNote.getText().toString();

        //両方nullは保存しない。
        if((title == null || title.isEmpty()) && (note == null || note.isEmpty())) {
            finish();
        }else if (note == null || note.isEmpty()){
         //ノートのみnullの場合は初期値をセットしinsert処理
            note="";
            dao.connect();
            dao.insertTN(title, note);
            dao.close();
            finish();
        }else{
            //タイトルがnullの場合はノートをタイトルにセットしinsert処理
            if (title == null || title.isEmpty()) {
                //11文字に足りない場合はそのまま、多い場合は11文字までに制限
                if(note.length() < 11){
                    title = note;
                }else {
                    title = note.substring(0, 11);
                }
            }
            dao.connect();
            dao.insertTN(title, note);
            dao.close();
            finish();
        }
    }


    //バックボタンを押したときの保存処理。中身は保存ボタンと同じ。
    @Override
    public void onBackPressed() {
        String title = addTitle.getText().toString();
        String note = addNote.getText().toString();
        if((title == null || title.isEmpty()) && (note == null || note.isEmpty())) {
            finish();
        }else if (note == null || note.isEmpty()){
            note="";
            dao.connect();
            dao.insertTN(title, note);
            finish();
        }else{
            if (title == null || title.isEmpty()) {
                if(note.length() < 11){
                    title = note;
                }else {
                    title = note.substring(0, 11);
                }
            }
            dao.connect();
            dao.insertTN(title, note);

            finish();
        }
    }


}

