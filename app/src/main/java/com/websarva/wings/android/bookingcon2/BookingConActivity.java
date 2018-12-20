package com.websarva.wings.android.bookingcon2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingConActivity extends AppCompatActivity {


    // newNoodleの1-30が追加されたかどうかのフラグ true:追加された false:まだ追加されてない
    boolean newNoodleAddFlag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_con);

//        //スピナーの中身取得
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_booking_con);
//
//        // Spinnerオブジェクトを取得
//        Spinner spinner = (Spinner)findViewById(R.id.bookingCon_spinar_purp);
//
//        // 選択されているアイテムのIndexを取得
//        int idx = spinner.getSelectedItemPosition();
//        // 選択されているアイテムを取得
//        String item = (String)spinner.getSelectedItem();
//
//
////        EditTextの入力制限用
////        EditText et_c = (EditText)findViewById(R.id.bookingCon_ed_tv_com);
////        EditText et_n = (EditText)findViewById(R.id.bookingCon_ed_tv_name);
////        リストなどのコンテンツでフィルタされた文字列
////        et.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_FILTER);
//
//
//        //会社名のEdittextの変更を検知する
//        EditText editText_com = findViewById(R.id.bookingCon_ed_tv_com);
//        editText_com.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //テキスト変更前
//                //使わない
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //テキスト変更中
//                //使わない
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                //テキスト変更後（現在進行形で入力値）
//
//
//            }
//        });
//
//        //名前のEdittextの変更を検知する
//        EditText editText_name = findViewById(R.id.bookingCon_ed_tv_name);
//        editText_name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                //テキスト変更前
//                //使わない
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //テキスト変更中
//                //使わない
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                //テキスト変更後（現在進行形で入力されていされている値）
//
//
//
//            }
//        });


        DbHelper helper = new DbHelper(BookingConActivity.this);
        try(SQLiteDatabase db = helper.getWritableDatabase()) {
            String sql = "SELECT * FROM 予約ID 目的 名前 会社名 開始時刻 終了時刻 WHERE ";
        }
        helper.close();

        // ListViewに表示する項目を生成
        ArrayList<HashMap<String, String>> listData = new ArrayList<>();

        String[] da = {"2018年8月30日 木曜日", "2018年9月1日 土曜日", "2018年9月3日 月曜日", "2018年9月10日 月曜日"};

        String[] plans = {"打ち合わせ", "打ち合わせ", "打ち合わせ", "打ち合わせ"};

        String[] time = {"9:00~10:00","9:00~10:00","10:00~11:00","10:15~11:15"};

        String[] area = {"特別","会議室B","会議室A","会議室C"};

        String[] contents = {"新卒の研修","商談","",""};

        String[] name = {"予約者:田中太郎", "予約者:佐藤太郎", "予約者:草加雅人", "予約者:檀黎斗"};

        for (int i = 0; i < 4; i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("date", da[i]);            //日付
            data.put("plans", plans[i]);        //目的
            data.put("time", time[i]);          //時間
            data.put("area",area[i]);           //場所
            data.put("name", name[i]);          //予約者
            listData.add(data);
        }
        
        /**
         * Adapterを生成
         *リストビュー自身のレイアウト。今回は自作。
         *受け渡し元項目名
         *受け渡し先ID
         */
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listData, // 使用するデータ
                R.layout.custom_list_layout, // 自作したレイアウト
                new String[]{"date", "plans", "time", "area", "name"}, // どの項目を
                new int[]{R.id.bookingCon_tv_date, R.id.bookingCon_tv_plans, R.id.bookingCon_tv_time,R.id.bookingCon_tv_area, R.id.bookingCon_tv_name} // どのidの項目に入れるか
        );
        // idがlistのListViewを取得
        ListView listView = (ListView) findViewById(R.id.bookingCon_list_vi_con);
        listView.setAdapter(simpleAdapter);
    }

};