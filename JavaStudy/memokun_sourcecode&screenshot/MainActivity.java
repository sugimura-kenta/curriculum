package com.websarva.wimgs.android.memokun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    DBDao dao;
    ListView lvMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DBDaoをコンストラクタ
        dao = new DBDao(MainActivity.this);

        lvMemo = findViewById(R.id.lvMemo);
        adapter = new ArrayAdapter<>(MainActivity.this,R.layout.title_lv);
        lvMemo.setAdapter(adapter);
        lvMemo.setOnItemClickListener(new ListItemClickListener());
        lvMemo.setOnItemLongClickListener(new OnItemLongClickListener());
    }

    //他のアクティビティから戻る際にListViewを更新したいためonResumeに記述
    @Override
    protected void onResume(){
        super.onResume();
        //アダプターを初期化
        adapter.clear();
        //データベースに接続しtitleを入手後アダプターにセット
        dao.connect();
        dao.selectAll(adapter);
        dao.close();

    }

    //リストをタップ時に編集アクティビティに遷移
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //ポジションからタップしたリストのデータを取得
            String memoTitle = (String) parent.getItemAtPosition(position);
            //nullにならないように初期化
            String note = "";
            //データベースに接続し取得したタイトルからノートを所得
            dao.connect();
            note = dao.selectTitle(memoTitle);
            dao.close();
            //インテントにて編集アクティビティへ遷移
            Intent intent = new Intent(MainActivity.this,MemoActivity.class);
            intent.putExtra("memoTitle",memoTitle);
            intent.putExtra("note",note);
            startActivity(intent);
        }
    }

    // リストをロングタップ時に削除ダイアログを表示。唯一の削除方法。
    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        public boolean onItemLongClick(AdapterView<?> parent,View view ,int position,long id) {
            //ダイアログ準備
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("削除");
            builder.setMessage("削除しますか？");
            //ネガティブボタンがタップされた場合の処理。この処理は別にメソッドを作り呼び出すほうが綺麗になりそう。
            builder.setNegativeButton("削除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //タップされたリストのデータを所得
                    String memoTitle = (String) parent.getItemAtPosition(position);
                    //データベースに接続し取得したタイトルを引数にデータベースから削除。
                    dao.connect();
                    dao.delete(memoTitle);
                    dao.close();
                    //アダプターからも削除。
                    adapter.remove(memoTitle);
                    //ListView(アダプター)を更新
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setPositiveButton("いいえ",null);
            builder.setCancelable(true);
            builder.show();
            return true;
        }

    }

    //オプションメニューを取得
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //選択されたオプションメニューから処理を分岐
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        //追加を選択されたとき、新規メモアクティビティへ遷移。
        if(itemId == R.id.add){
            Intent intent = new Intent(MainActivity.this ,AddMemoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}



