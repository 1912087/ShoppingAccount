package com.example.shoppingAccount.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoppingAccount.First_Adapter;
import com.example.shoppingAccount.Fragments.FirstFragment;
import com.example.shoppingAccount.R;
import com.example.shoppingAccount.dto.First_Item;

import java.util.ArrayList;

public class ProductDao {

    DBHelper dbHelper;
    SQLiteDatabase database;
    String tableName;

    private Integer[] logo1 = {R.drawable.clothes_mantoman1, R.drawable.clothes_mantoman2, R.drawable.clothes_mantoman3,
            R.drawable.clothes_mantoman4, R.drawable.clothes_mantoman5, R.drawable.clothes_mantoman6,
            R.drawable.clothes_pants1, R.drawable.clothes_pants2, R.drawable.clothes_pants3,
            R.drawable.clothes_pants4,R.drawable.clothes_pants5,R.drawable.clothes_pants6,
            R.drawable.clothes_outer1, R.drawable.clothes_outer2, R.drawable.clothes_outer3,
            R.drawable.clothes_outer4,R.drawable.clothes_outer5,R.drawable.clothes_outer6,
            R.drawable.clothes_skirt1,R.drawable.clothes_skirt2,R.drawable.clothes_skirt3,
            R.drawable.clothes_skirt4,R.drawable.clothes_skirt5,
            R.drawable.clothes_sweat_suit1,R.drawable.clothes_sweat_suit2,R.drawable.clothes_sweat_suit3,
            R.drawable.clothes_sweater1,R.drawable.clothes_sweater2,R.drawable.clothes_sweater3,
            R.drawable.clothes_sweater4,R.drawable.clothes_sweater5,R.drawable.clothes_sweater6};
    String[] name1 = {"검정 맨투맨", "심플 맨투맨", "차분한 후드티","검정 반팔티", "반팔티", "아동용 반팔티",
            "검정 레깅스", "청바지", "통 큰 바지", "베이직 팬츠", "화이트 골덴 팬츠", "찢어진 회색 바지",
            "청자켓", "아기 아우터", "모직 코트", "캐쥬얼한 자켓", "베이직 바람막이", "짙은 청자켓",
            "주름 치마", "라인 스커트", "심플 원피스", "플레어 스커트", "체크무늬 스커트",
            "심플한 츄리닝", "아디다스 블랙", "그린 앤 화이트",
            "심플 니트", "그린 스웨터", "아동용 스웨터", "화이트 니트", "체크무늬 스웨터", "레드와인 스웨터"};
    String[] account1 = {"13500", "57400", "32400", "43500", "20000", "35000", "32000",
            "35000", "32000", "46200", "45100", "56020", "45300",
            "12000", "45020", "35000", "32010", "41000", "39990",
            "45000", "32400", "36100", "29990", "35500",
            "23000", "24000", "47000",
            "45000", "32000", "31000", "24000", "55000", "32000"};
    String[] search1 = {"mantoman", "mantoman", "mantoman", "mantoman", "mantoman", "mantoman",
            "pants", "pants", "pants", "pants", "pants", "pants",
            "outer", "outer", "outer", "outer", "outer", "outer",
            "skirt", "skirt", "skirt", "skirt", "skirt",
            "sweat_suit", "sweat_suit", "sweat_suit",
            "sweater", "sweater", "sweater", "sweater", "sweater", "sweater"};

    public ProductDao(String tableName){
        dbHelper = new DBHelper(First_Adapter.mContext);
        this.database = dbHelper.getReadableDatabase();
        this.tableName = tableName;
    }

    public void table_setup(){
        database.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (PID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " LOGO INTEGER, " +
                " NAME TEXT, " +
                " ACCOUNT TEXT, " +
                " CATEGORY TEXT," +
                " FAVORITE INTEGER CHECK (FAVORITE IN(0, 1)));");  // 0: not favorite, 1: favorite
    }

    public void data_insert(){
        for(int i=0; i<logo1.length; i++){
            database.execSQL("INSERT INTO " + tableName + "(LOGO, NAME, ACCOUNT, CATEGORY) " +
                    " VALUES(" + logo1[i] + ",'" + name1[i] + "','"
                    + account1[i] + "','" + search1[i] + "', '0');");
        }
    }

    public int data_count(){
        String sql = "SELECT COUNT(*) FROM " + tableName + ";";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return -1;
    }

    public ArrayList<First_Item> productList(String category){
        String search = "";
        if(!category.equals("all")){
            search = " WHERE CATEGORY = '" + category + "'";
        }
        ArrayList<First_Item> list = new ArrayList<>();
        String sql = "SELECT PID, LOGO, NAME, ACCOUNT, CATEGORY, FAVORITE FROM "
                + tableName + search + ";";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            First_Item dto = new First_Item();
            dto.setPid(cursor.getInt(0));
            dto.setImage_list(cursor.getInt(1));
            dto.setName_list(cursor.getString(2));
            dto.setAccount_list(cursor.getString(3));
            dto.setSearch_list(cursor.getString(4));
            dto.setFavorite(cursor.getInt(5));
            list.add(dto);
        }

        return list;
    }

    public ArrayList<First_Item> productCategory(String category){
        ArrayList<First_Item> list = new ArrayList<>();
        String sql = "SELECT PID, LOGO, NAME, ACCOUNT, CATEGORY, FAVORITE FROM " +
                tableName + " WHERE CATEGORY = '" + category + "';";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            First_Item dto = new First_Item();
            dto.setPid(cursor.getInt(0));
            dto.setImage_list(cursor.getInt(1));
            dto.setName_list(cursor.getString(2));
            dto.setAccount_list(cursor.getString(3));
            dto.setSearch_list(cursor.getString(4));
            dto.setFavorite(cursor.getInt(5));
            list.add(dto);
        }

        return list;
    }

    public First_Item productInfo(int pid) {
        First_Item dto = new First_Item();
        String sql = "SELECT PID, LOGO, NAME, ACCOUNT, CATEGORY, FAVORITE FROM " +
                tableName + " WHERE PID = " + pid + ";";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            dto.setPid(cursor.getInt(0));
            dto.setImage_list(cursor.getInt(1));
            dto.setName_list(cursor.getString(2));
            dto.setAccount_list(cursor.getString(3));
            dto.setSearch_list(cursor.getString(4));
            dto.setFavorite(cursor.getInt(5));
        }

        return dto;
    }

    public ArrayList<First_Item> favoriteItem() {
        ArrayList<First_Item> list = new ArrayList<>();
        String sql = "SELECT PID, LOGO, NAME, ACCOUNT, CATEGORY, FAVORITE FROM " +
                tableName + " WHERE FAVORITE = 1;";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            First_Item dto = new First_Item();
            dto.setPid(cursor.getInt(0));
            dto.setImage_list(cursor.getInt(1));
            dto.setName_list(cursor.getString(2));
            dto.setAccount_list(cursor.getString(3));
            dto.setSearch_list(cursor.getString(4));
            dto.setFavorite(cursor.getInt(5));

            list.add(dto);
        }

        return list;
    }

    public int favoriteCheck(int pid){
        String sql = "SELECT FAVORITE FROM " + tableName + " WHERE PID = " + pid + ";";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            return cursor.getInt(0);
        }

        return -1;
    }

    public void favoriteUpdate(int pid){
        int type = 0;
        int result = favoriteCheck(pid);
        if(result == 0){
            type = 1;
        }else{
            type = 0;
        }
        String sql = "UPDATE " + tableName + " SET FAVORITE = " + type + " WHERE PID = " + pid + ";";
        database.execSQL(sql);
    }
}
