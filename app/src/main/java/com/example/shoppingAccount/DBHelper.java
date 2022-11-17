package com.example.shoppingAccount;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.shoppingAccount.orderList.Item;
import com.example.shoppingAccount.orderList.order_main;
import com.example.shoppingAccount.orderList.order_planAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    final String TAG = "DBHelper";
    public List<Item> userList ;
    public Context context;

    ArrayList<order_planAdapter> list = new ArrayList<order_planAdapter>();

    //Table name and column name
    public static final String LIST_TABLE_NAME = "goods_list";
    public static final String LIST_COLUMN_ID = "id";
    public static final String LIST_COLUMN_IMAGE = "image";
    public static final String LIST_COLUMN_NAME = "name";
    public static final String LIST_COLUMN_AMOUNT = "amount";
    public static final String LIST_COLUMN_ACCOUNT = "account";
    public final static String LIST_COLUMN_DATE = "date";

    //Database information
    static final String DB_NAME = "Goods_list.db";
    static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    //    최초 DB가 존재하지 않으면 새로 생성한다.
    @Override
    public void onCreate(SQLiteDatabase db) {

        //변수랑 섞어 놔서 복잡하니 공백을 잘 체크하자. 이것 때문에 에러나서 많은 시간을 허비했다.
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE " +LIST_TABLE_NAME + "(");
        stringBuffer.append(LIST_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuffer.append(LIST_COLUMN_IMAGE + " BLOB, ");
        stringBuffer.append(LIST_COLUMN_NAME + " TEXT, ");
        stringBuffer.append(LIST_COLUMN_AMOUNT + " TEXT, ");
        stringBuffer.append(LIST_COLUMN_ACCOUNT + " TEXT, ");
        stringBuffer.append(LIST_COLUMN_DATE + " TEXT);");

        //Log.d(TAG, stringBuffer.toString());
        db.execSQL(stringBuffer.toString());
        //Message.message(context, "DB 생성 완료");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            //Message.message(context, "onUpgrade");
            db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE_NAME);
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    DB에 직접 데어터를 추가하고 수정하고 삭제하는 함수들을 모아 둠
     */
    //데이터 추가하고 true/false를 반환한다.
    public boolean insertData(byte[] image, String name, String amount, String account, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_COLUMN_IMAGE, image);
        values.put(LIST_COLUMN_NAME, name);
        values.put(LIST_COLUMN_AMOUNT, amount);
        values.put(LIST_COLUMN_ACCOUNT, account);
        values.put(LIST_COLUMN_DATE, date);
        long result = db.insert(LIST_TABLE_NAME, null, values);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //데이터 모두 가져오기
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor corsor = db.rawQuery("select * from " + LIST_TABLE_NAME + " order by _id desc", null);
        Cursor corsor = db.rawQuery("select * from " + LIST_TABLE_NAME + " order by _id desc ", null);
        db.close();
        return corsor;
    }

    //데이터 수정 업데이트
    public boolean updateData(Integer id, byte[] image, String name, String amount, String account, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_COLUMN_ID, id);
        values.put(LIST_COLUMN_IMAGE, image);
        values.put(LIST_COLUMN_NAME, name);
        values.put(LIST_COLUMN_AMOUNT, amount);
        values.put(LIST_COLUMN_ACCOUNT, account);
        values.put(LIST_COLUMN_DATE, date);
        db.update(LIST_TABLE_NAME, values, "ID = ?", new String[] { Integer.toString(id) });
        return true;
    }

    //테이블 삭제
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(LIST_TABLE_NAME, "ACCOUNT = ?" , new String[] {id});
    }

    /*
        DB에서 데이터를 모두 가져와서 ResyclerView 어답터에서 사용할 Items 배열에
        순더대로 추가하는 함수. 여기저기에서 불러서 사용해야 할경우를 대비 여기에 넣어 둠
     */
    public void updateItems() {
        order_main.items.clear();
        Cursor cursor = getAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(context.getApplicationContext(), "데이터 없음",Toast.LENGTH_SHORT).show();
            //Message.message(context, "데이터 없음");
        } else {
            while (cursor.moveToNext()){

                byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String amount = cursor.getString(cursor.getColumnIndex("amount"));
                String account = cursor.getString(cursor.getColumnIndex("account"));

                order_main.items.add(new Item(image, name, amount, account));
            }
        }
        cursor.close();
    }
    public ArrayList getAllGoods() {

        ArrayList<Item> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from goods_list", null);
        res.moveToLast();
        while (res.isBeforeFirst() == false) {

            byte[] image = res.getBlob(res.getColumnIndex("image"));
            String name = res.getString(res.getColumnIndex("name"));
            String amount = res.getString(res.getColumnIndex("amount"));
            String account = res.getString(res.getColumnIndex("account"));

            //DecimalFormat myFormatter = new DecimalFormat("###,###");
            //String formattedStringPrice = myFormatter.format(Integer.parseInt(account));

            Item item = new Item(image, name, amount, account);
            array_list.add(item);
            res.moveToPrevious();
        }
        return array_list;
    }
    public ArrayList getAccount() {

        ArrayList<Account_Item> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from goods_list", null);
        res.moveToLast();
        while (res.isBeforeFirst() == false) {
            String account = res.getString(res.getColumnIndex("account"));
            String date = res.getString(res.getColumnIndex("date"));

            Account_Item item = new Account_Item(account, date);
            array_list.add(item);
            res.moveToPrevious();
        }
        return array_list;
    }
    public List<Account_Item> getTop(String Date) {
        ArrayList<Account_Item> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from goods_list", null);
        res.moveToLast();
        while (res.isBeforeFirst() == false) {
            String month_ch = "-" + Date + "-";
            //int a = 0;
            //if () {
            String account = res.getString(res.getColumnIndex("account"));
            String date = res.getString(res.getColumnIndex("date"));
            if(date.contains(month_ch)){
                Account_Item item = new Account_Item(account, date);
                array_list.add(item);
            }
            res.moveToPrevious();
            //}
        }
        return array_list;
    }

}