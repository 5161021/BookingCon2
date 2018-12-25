package com.websanva.wings.android.bookingcon2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingConActivity extends AppCompatActivity {


    // newNoodleの1-30が追加されたかどうかのフラグ true:追加された false:まだ追加されてない
    boolean newNoodleAddFlag = false;

    private static final String SELECTATTRIBUTE="";
    private static final String SELECTTABLE="";

//    String purposeText = "";
//    String monthText = "";
//    String companyText = "";
//    String nameText=  "";

    /*SQL文用*/
    private static final String SQLSTRBASE = "SELECT 予約ID 利用日 開始時刻 終了時刻 会議室名 目的 予約者名" +
            "FROM " +
            "WHERE 目的 = \'%";
    private static final String SQLSTRCON1 = "%\' AND 利用日 = \'%";
    private static final String SQLSTRCON2 = "%\' AND 会議室名 = \'%";
    private static final String SQLSTRCON3 = "%\' AND 予約者名 = \'%";
    private static final String SQLSTRCONEND = "%\'";
    /**
     * 検索ボックス,スピナーの内容保持用
     * @param 0 = 目的スピナー
     * @param 1 = 企業名検索ボックス
     * @param 2 = 予約者名検索ボックス
     */
    String searchSQLVal[] = new String[2];

    /**
     * rsvtHistorySearch メソッドの識別用定数
     * // ST_PURPOSE = 目的スピナー
     * // ST_COMPANY = 企業名検索ボックス
     * // ST_NAME    = 担当者名検索ボックス
     */
    private static final int ST_PURPOSE = 0;
    private static final int ST_COMPANY = 1;
    private static final int ST_NAME    = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_con);


        //リスナー
        Spinner purposeSpinar = findViewById(R.id.bookingCon_spinar_purp);
        EditText companySachvi = findViewById(R.id.bookingCon_ed_tv_com);
        EditText nameSachvi = findViewById(R.id.bookingCon_ed_tv_name);

        //目的スピナー
        purposeSpinar.setOnItemClickListener((parent,view,position,id) ->{
            String w = "";
            // スピナーのインデックスを取得して0なら空文字列のまま、それ以外なら内容を格納
            if(position!=0){
                w = (String)parent.getItemAtPosition(position);
            }
            // 検索メソッドを呼び出す
            rsvtHistorySearch(ST_PURPOSE,w);
        });

        /**
         * 検索を行うメソッド
         * @param sachType
         * @param sachString
         */
        private void rsvtHistorySearch(int sachType, String sachString){
            searchSQLVal[sachType] = sachString;

            // データベース接続
            TestOpenHelper helper = new TestOpenHelper(BookingConActivity.this);
            try(SQLiteDatabase db =helper.getWritableDatabase()){
                // SQL文の編集
                String strSQL = SQLSTRBASE +
                        searchSQLVal[0] +
                        SQLSTRCON1 + searchSQLVal[1] +
                        SQLSTRCON2 + searchSQLVal[2] +
                        SQLSTRCONEND;
                // SQL文の実行
                Cursor cursor = db.rawQuery(strSQL,null);
                // SQL
                String note = "";
                if(cursor.moveToNext()){

                }
            }
            // データベース解放
            helper.close();

        }

//        EditTextの入力制限用
//        EditText et_c = (EditText)findViewById(R.id.bookingCon_ed_tv_com);
//        EditText et_n = (EditText)findViewById(R.id.bookingCon_ed_tv_name);
//        リストなどのコンテンツでフィルタされた文字列
//        et.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_FILTER);


        //会社名のEdittextの変更を検知する
        EditText editText_com = findViewById(R.id.bookingCon_ed_tv_com);
        editText_com.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //テキスト変更前
                //使わない
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //テキスト変更中
                //使わない
            }

            @Override
            public void afterTextChanged(Editable s) {
                //テキスト変更後（現在進行形で入力値）


            }
        });

        //名前のEdittextの変更を検知する
        EditText editText_name = findViewById(R.id.bookingCon_ed_tv_name);
        editText_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //テキスト変更前
                //使わない
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //テキスト変更中
                //使わない
            }

            @Override
            public void afterTextChanged(Editable s) {
                //テキスト変更後（現在進行形で入力されていされている値）



            }
        });


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



}