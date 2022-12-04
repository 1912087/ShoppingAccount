package com.example.shoppingAccount.dao;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.shoppingAccount.Account_Item;
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

    @Override
    public void onCreate(SQLiteDatabase db) {

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
        return db.delete(LIST_TABLE_NAME, "ID = ?" , new String[] {id});
    }

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
    public ArrayList<Item> getAllGoods() {

        ArrayList<Item> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from goods_list", null);
        res.moveToLast();
        while (res.isBeforeFirst() == false) {
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex("id")));
            item.setImage(res.getBlob(res.getColumnIndex("image")));
            item.setName(res.getString(res.getColumnIndex("name")));
            item.setAmount(res.getString(res.getColumnIndex("amount")));
            item.setAccount(res.getString(res.getColumnIndex("account")));

            //DecimalFormat myFormatter = new DecimalFormat("###,###");
            //String formattedStringPrice = myFormatter.format(Integer.parseInt(account));

            //Item item = new Item(id, image, name, amount, account);
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